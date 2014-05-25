package com.radirius.merc.gfx;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import com.radirius.merc.data.TextFile;

/**
 * @author opiop65
 */

public class Spritesheet {
    
    private HashMap<String, SubTexture> textures = new HashMap<String, SubTexture>();
    private Texture texture;
    private int numTextures;
    private float texSize;
    
    public Spritesheet(TextFile file, InputStream textureIn) {
        parseSheet(file, textureIn);
    }
    
    private void parseSheet(TextFile file, InputStream textureIn) {
        texture = Texture.loadTexture(textureIn);
        String tempData = file.getContent();
        
        String[] data = tempData.split("\\r?\\n");
        for (int i = 0; i < data.length; i++) {
            String[] lineData = data[i].split(" ");
            if (i == 0) {
                numTextures = Integer.valueOf(lineData[1]);
                texSize = 1.0f / numTextures;
            } else
                textures.put(lineData[0], new SubTexture(this, Integer.valueOf(lineData[1]) * texSize, Integer.valueOf(lineData[2]) * texSize, texSize));
        }
    }
    
    public int getNumTextures() {
        return numTextures;
    }
    
    public Texture getSheetTexture() {
        return texture;
    }
    
    public SubTexture getTexture(String value) {
        return textures.get(value);
    }
    
    public static Spritesheet loadSheet(URL in, InputStream textureIn) {
        return new Spritesheet(TextFile.loadFile(in), textureIn);
    }
}
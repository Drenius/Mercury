package com.radirius.merc.util;

import static org.lwjgl.opengl.GL11.GL_NEAREST;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.radirius.merc.gfx.Texture;

/**
 * A utility class to get texture grids and strips from single files. It is more
 * efficient to store all textures on one image than having multiple resources
 * on different files
 * 
 * @from merc in com.radirius.merc.util
 * @authors wessles
 * @website www.wessles.com
 * @license (C) Dec 23, 2013 www.wessles.com This file, and all others of the
 *          project 'MERCury' are licensed under WTFPL license. You can find the
 *          license itself at http://www.wtfpl.net/about/.
 */
public class TextureFactory {
    /** Parses from in, dividing every divwidth. */
    public static Texture[] getTextureStrip(InputStream in, int divwidth) throws FileNotFoundException, IOException {
        return getTextureStrip(ImageIO.read(in), divwidth, false, false, GL_NEAREST);
    }
    
    /** Parses from in, dividing every divwidth, and filtering through filter. */
    public static Texture[] getTextureStrip(InputStream in, int divwidth, int filter) throws FileNotFoundException, IOException {
        return getTextureStrip(ImageIO.read(in), divwidth, false, false, filter);
    }
    
    /**
     * Parses from in, dividing every divwidth, flipping each subsection
     * depending on local_fliphor horizontal, and local_flipvert vertical.
     */
    public static Texture[] getTextureStrip(InputStream in, int divwidth, boolean local_fliphor, boolean local_flipvert) throws FileNotFoundException, IOException {
        return getTextureStrip(ImageIO.read(in), divwidth, local_fliphor, local_flipvert, GL_NEAREST);
    }
    
    /**
     * Parses from in, dividing every divwidth, flipping each subsection
     * depending on local_fliphor horizontal, and local_flipvert vertical, and
     * filtering through filter.
     */
    public static Texture[] getTextureStrip(InputStream in, int divwidth, boolean local_fliphor, boolean local_flipvert, int filter) throws FileNotFoundException, IOException {
        return getTextureStrip(ImageIO.read(in), divwidth, local_fliphor, local_flipvert, filter);
    }
    
    /** Parses from bi, dividing every divwidth. */
    public static Texture[] getTextureStrip(BufferedImage bi, int divwidth) {
        return getTextureStrip(bi, divwidth, false, false, GL_NEAREST);
    }
    
    /** Parses from bi, dividing every divwidth, and filtering through filter. */
    public static Texture[] getTextureStrip(BufferedImage bi, int divwidth, int filter) {
        return getTextureStrip(bi, divwidth, false, false, filter);
    }
    
    /**
     * Parses from bi, dividing every divwidth, flipping each subsection
     * depending on local_fliphor horizontal, and local_flipvert vertical.
     */
    public static Texture[] getTextureStrip(BufferedImage bi, int divwidth, boolean local_fliphor, boolean local_flipvert) {
        return getTextureStrip(bi, divwidth, local_fliphor, local_flipvert, GL_NEAREST);
    }
    
    /**
     * Parses from bi, dividing every divwidth, flipping each subsection
     * depending on local_fliphor horizontal, and local_flipvert vertical, and
     * filtering through filter.
     */
    public static Texture[] getTextureStrip(BufferedImage bi, int divwidth, boolean local_fliphor, boolean local_flipvert, int filter) {
        Texture[] result = new Texture[bi.getWidth() / divwidth];
        int cnt = 0;
        
        for (int x = 0; x < bi.getWidth(); x += divwidth) {
            result[cnt] = Texture.loadTexture(bi.getSubimage(x, 0, divwidth, bi.getHeight()));
            cnt++;
        }
        
        return result;
    }
    
    /** Parses from in, dividing every divwidth and divheight. */
    public static Texture[][] getTextureGrid(InputStream in, int divwidth, int divheight) throws FileNotFoundException, IOException {
        return getTextureGrid(ImageIO.read(in), divwidth, divheight, false, false, GL_NEAREST);
    }
    
    /**
     * Parses from in, dividing every divwidth and divheight, and filtering
     * through filter.
     */
    public static Texture[][] getTextureGrid(InputStream in, int divwidth, int divheight, int filter) throws FileNotFoundException, IOException {
        return getTextureGrid(ImageIO.read(in), divwidth, divheight, false, false, filter);
    }
    
    /**
     * Parses from in, dividing every divwidth and divheight, flipping each
     * subsection depending on local_fliphor horizontal, and local_flipvert
     * vertical.
     */
    public static Texture[][] getTextureGrid(InputStream in, int divwidth, int divheight, boolean local_fliphor, boolean local_flipvert) throws FileNotFoundException, IOException {
        return getTextureGrid(ImageIO.read(in), divwidth, divheight, local_fliphor, local_flipvert, GL_NEAREST);
    }
    
    /**
     * Parses from in, dividing every divwidth and divheight, flipping each
     * subsection depending on local_fliphor horizontal, and local_flipvert
     * vertical, and filtering through filter.
     */
    public static Texture[][] getTextureGrid(InputStream in, int divwidth, int divheight, boolean local_fliphor, boolean local_flipvert, int filter) throws FileNotFoundException, IOException {
        return getTextureGrid(ImageIO.read(in), divwidth, divheight, local_fliphor, local_flipvert, filter);
    }
    
    /** Parses from bi, dividing every divwidth and divheight. */
    public static Texture[][] getTextureGrid(BufferedImage bi, int divwidth, int divheight) {
        return getTextureGrid(bi, divwidth, divheight, false, false, GL_NEAREST);
    }
    
    /**
     * Parses from bi, dividing every divwidth and divheight, and filtering
     * through filter.
     */
    public static Texture[][] getTextureGrid(BufferedImage bi, int divwidth, int divheight, int filter) {
        return getTextureGrid(bi, divwidth, divheight, false, false, filter);
    }
    
    /**
     * Parses from bi, dividing every divwidth and divheight, flipping each
     * subsection depending on local_fliphor horizontal, and local_flipvert
     * vertical.
     */
    public static Texture[][] getTextureGrid(BufferedImage bi, int divwidth, int divheight, boolean local_fliphor, boolean local_flipvert) {
        return getTextureGrid(bi, divwidth, divheight, local_fliphor, local_flipvert, GL_NEAREST);
    }
    
    /**
     * Parses from bi, dividing every divwidth and divheight, flipping each
     * subsection depending on local_fliphor horizontal, and local_flipvert
     * vertical, and filtering through filter.
     */
    public static Texture[][] getTextureGrid(BufferedImage bi, int divwidth, int divheight, boolean local_fliphor, boolean local_flipvert, int filter) {
        Texture[][] result = new Texture[bi.getWidth() / divwidth][bi.getHeight() / divheight];
        
        for (int y = 0; y < bi.getHeight(); y += divheight)
            for (int x = 0; x < bi.getWidth(); x += divwidth)
                result[x / divwidth][y / divheight] = Texture.loadTexture(bi.getSubimage(x, y, divwidth, divheight), local_fliphor, local_flipvert, filter);
        
        return result;
    }
    
    /**
     * Gets texture strip from a texture grid, reading left-right and down. Like
     * a book; a bad@$$ book.
     */
    public static Texture[] getTextureStripFromGrid(Texture[][] textures) {
        Texture[] result = new Texture[textures.length * textures[0].length];
        int index = 0;
        for (int y = 0; y < textures[0].length; y++)
            for (Texture[] texture : textures) {
                result[index] = texture[y];
                index++;
            }
        return result;
    }
}

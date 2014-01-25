package com.teama.merc.test;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.teama.merc.exc.MERCuryException;
import com.teama.merc.fmwk.Core;
import com.teama.merc.fmwk.Runner;
import com.teama.merc.gfx.Graphics;
import com.teama.merc.gfx.Texture;
import com.teama.merc.in.Input;
import com.teama.merc.res.ResourceManager;
import com.teama.merc.slickaud.Audio;
import com.teama.merc.slickaud.SlickSoundPlugin;
import com.teama.merc.spl.SplashScreen;

/**
 * @from MERCury in com.teama.merc.test
 * @by wessles
 * @website www.wessles.com
 * @license (C) Jan 25, 2014 www.wessles.com This file, and all others of the project 'MERCury' are licensed under WTFPL license. You can find the license itself at http://www.wtfpl.net/about/.
 */

public class SlickAudioTest extends Core
{
    public Runner rnr = Runner.getInstance();
    
    public Audio ogg, wav;
    
    public SlickAudioTest()
    {
        super("Slick Audio Test!");
        rnr.addPlugin(new SlickSoundPlugin());
        rnr.init(this, 300, 300);
        rnr.run();
    }
    
    public static void main(String[] args) {
        new SlickAudioTest();
    }
    
    @Override
    public void init(ResourceManager RM)
    {
        try
        {
            RM.loadResource(Audio.loadAudio("res/test/AudioTest/sound.ogg"), "ogg");
            RM.loadResource(Audio.loadAudio("res/test/AudioTest/sound.wav"), "wav");
        } catch (MERCuryException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            Texture tex = Texture.loadTexture("res/splash.png");
            float ratio = tex.getTextureWidth() / tex.getTextureHeight();
            int height = (int) (rnr.getWidth() / ratio);
            SplashScreen splash = new SplashScreen(tex, 3000, rnr.getWidth(), height, true);
            rnr.addSplashScreen(splash);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(float delta)
    {
        Input in = rnr.getInput();
        if (in.keyDown(Keyboard.KEY_W))
            ((Audio) rnr.getResourceManager().retrieveResource("wav")).play();
        else if (in.keyClicked(Keyboard.KEY_O))
            ((Audio) rnr.getResourceManager().retrieveResource("ogg")).toggle();
    }
    
    @Override
    public void render(Graphics g)
    {
        g.drawString(0, 0, "O = OGG\nW = WAV");
    }
    
    @Override
    public void cleanup(ResourceManager RM)
    {
    }
}

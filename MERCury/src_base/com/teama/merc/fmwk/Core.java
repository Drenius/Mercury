package com.teama.merc.fmwk;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.teama.merc.exc.MERCuryException;
import com.teama.merc.gfx.Graphics;
import com.teama.merc.gfx.VAOGraphics;
import com.teama.merc.log.Logger;
import com.teama.merc.res.ResourceManager;

/**
 * The {@code Core} that will host the game. It is ran above by the {@code Runner} class.
 * 
 * @from merc in com.teama.merc.fmwk
 * @authors wessles
 * @website www.wessles.com
 * @license (C) Dec 23, 2013 www.wessles.com This file, and all others of the project 'MERCury' are licensed under WTFPL license. You can find the license itself at http://www.wtfpl.net/about/.
 */

public abstract class Core
{
    public final String name;
    
    public Core(String name)
    {
        this.name = name;
    }
    
    /**
     * Called first (after {@code initDisplay}, {@code initGraphics}, and {@code initAudio}), used to initialize all resources, and for whatever you wish to do for initialization.
     */
    public abstract void init(ResourceManager RM) throws IOException, MERCuryException;
    
    /**
     * Called once every frame, and used to handle all logic.
     */
    public abstract void update(float delta) throws MERCuryException;
    
    /**
     * Called once every frame, and used to render everything, via {@code Graphics g}.
     */
    public abstract void render(Graphics g) throws MERCuryException;
    
    /**
     * Called when the Runner is done
     */
    public abstract void cleanup(ResourceManager RM) throws IOException, MERCuryException;
    
    public void initDisplay(int WIDTH, int HEIGHT, boolean fullscreen, boolean vsync)
    {
        try
        {
            Display.setVSyncEnabled(vsync);
            
            DisplayMode dm = new DisplayMode(WIDTH, HEIGHT);
            
            boolean fullscreen_matched = false;
            
            if (fullscreen)
            {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                
                Logger.debug("Full-screen requested; attempting to find matching fullscreen display mode...");
                for (DisplayMode mode : modes)
                    if (mode.getWidth() == dm.getWidth() && mode.getHeight() == dm.getHeight())
                    {
                        dm = mode;
                        fullscreen_matched = true;
                    }
                
                if (!fullscreen_matched)
                {
                    Logger.debug("No fullscreen matched. Finding lowest sized available fullscreen display mode...");
                    DisplayMode lowest_mode = modes[0];
                    for (DisplayMode mode : modes)
                        if (mode.isFullscreenCapable())
                            if (mode.getWidth() < lowest_mode.getWidth() || mode.getHeight() < lowest_mode.getHeight())
                                lowest_mode = mode;
                    dm = lowest_mode;
                }
                
                Logger.debug("Fullscreen matched: " + dm.toString());
                
                Display.setFullscreen(true);
            }
            
            Display.setDisplayMode(dm);
            Display.setTitle(name);
            Display.create();
        } catch (LWJGLException e)
        {
            e.printStackTrace();
        }
    }
    
    public Graphics initGraphics()
    {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_ALPHA_TEST);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_DEPTH_SCALE);
        glDepthMask(true);
        glDepthFunc(GL_LEQUAL);
        
        glAlphaFunc(GL_GREATER, 0.1f);
        
        return new VAOGraphics();
    }
}

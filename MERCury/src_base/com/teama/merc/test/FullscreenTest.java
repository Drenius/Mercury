package com.teama.merc.test;

import org.lwjgl.input.Keyboard;

import com.teama.merc.fmwk.Core;
import com.teama.merc.fmwk.Runner;
import com.teama.merc.geo.Rectangle;
import com.teama.merc.gfx.Graphics;
import com.teama.merc.res.ResourceManager;

/**
 * @from MERCury in com.teama.merc.test
 * @by wessles
 * @website www.wessles.com
 * @license (C) Jan 22, 2014 www.wessles.com This file, and all others of the project 'MERCury' are licensed under WTFPL license. You can find the license itself at http://www.wtfpl.net/about/.
 */

public class FullscreenTest extends Core
{
    Runner rnr = Runner.getInstance();
    
    public FullscreenTest()
    {
        super("ga");
        /** Will choose lowest resolution, since near no monitor will view fullscreen at these dimensions. */
        rnr.init(this, 500, 500, true, true);
        rnr.run();
    }
    
    public static void main(String[] args)
    {
        new FullscreenTest();
    }
    
    @Override
    public void init(ResourceManager RM)
    {
    }
    
    @Override
    public void update(float delta)
    {
        if (rnr.getInput().keyDown(Keyboard.KEY_ESCAPE))
            rnr.end();
    }
    
    float x, y;
    
    @Override
    public void render(Graphics g)
    {
        /** Testing for vsync stuffs */
        g.drawRect(new Rectangle(x += 1, y += 3, 100, 100));
    }
    
    @Override
    public void cleanup(ResourceManager RM)
    {
    }
    
}

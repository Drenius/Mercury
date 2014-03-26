package com.radirius.merc.tuts;

import com.radirius.merc.fmwk.Core;
import com.radirius.merc.fmwk.Runner;
import com.radirius.merc.gfx.Graphics;
import com.radirius.merc.res.ResourceManager;

/**
 * @from merc in com.radirius.merc.tuts
 * @authors wessles
 * @website www.wessles.com
 * @license (C) Jan 21, 2014 www.wessles.com This file, and all others of the project 'MERCury' are licensed under WTFPL license. You can find the license itself at http://www.wtfpl.net/about/.
 */

public class BasicUpdating extends Core
{
    Runner rnr = Runner.getInstance();
    
    public BasicUpdating()
    {
        super("...                             Timing!");
        rnr.init(this, 500, 50);
        rnr.run();
    }
    
    public static void main(String[] args)
    {
        new BasicUpdating();
    }
    
    @Override
    public void init(ResourceManager RM)
    {
        rnr.setFpsTarget(5);
    }
    
    float x;
    
    @Override
    public void update(float delta)
    {
        /** No matter what the framerate, this speed will stay the same. */
        x += 0.02f * delta;
        /** Don't believe me? Play around with the target-frame-rate in the init() method */
    }
    
    @Override
    public void render(Graphics g)
    {
        g.drawCircle(x, 10, 5);
    }
    
    @Override
    public void cleanup(ResourceManager RM)
    {
    }
    
}

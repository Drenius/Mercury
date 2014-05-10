package com.radirius.merc.tuts;

import java.io.IOException;

import com.radirius.merc.exc.MERCuryException;
import com.radirius.merc.fmwk.Core;
import com.radirius.merc.fmwk.Runner;
import com.radirius.merc.gfx.Graphics;
import com.radirius.merc.res.ResourceManager;

/**
 * @from MERCury in package com.radirius.merc.tuts;
 * @authors wessles
 * @website www.wessles.com
 * @license (C) May 8, 2014 www.wessles.com This file, and all others of the
 *          project 'MERCury' are licensed under WTFPL license. You can find the
 *          license itself at http://www.wtfpl.net/about/.
 */

public class Blackbox extends Core {
    Runner rnr = Runner.getInstance();
    
    public Blackbox(String name) {
        super(name);
        // Make a game window that is 500x500
        rnr.init(this, 500, 500);
        // Now run it!
        rnr.run();
    }
    
    @Override
    public void init(ResourceManager RM) throws IOException, MERCuryException {
    }
    
    @Override
    public void update(float delta) throws MERCuryException {
        rnr.sleep(5000);
        rnr.end();
    }
    
    @Override
    public void render(Graphics g) throws MERCuryException {
    }
    
    @Override
    public void cleanup(ResourceManager RM) throws IOException, MERCuryException {
    }
    
    public static void main(String[] args) {
        new Blackbox("This is the title of the window!");
    }
    
}

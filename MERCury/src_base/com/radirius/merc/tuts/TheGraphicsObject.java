package com.radirius.merc.tuts;

import com.radirius.merc.fmwk.Core;
import com.radirius.merc.fmwk.Runner;
import com.radirius.merc.geo.Rectangle;
import com.radirius.merc.gfx.Color;
import com.radirius.merc.gfx.Graphics;

/**
 * @author wessles
 */

public class TheGraphicsObject extends Core {
    Runner rnr = Runner.getInstance();
    
    public TheGraphicsObject(String name) {
        super(name);
        // Make a game window that is 500x500
        rnr.init(this, 500, 500);
        // Now run it!
        rnr.run();
    }
    
    @Override
    public void init() {
        rnr.getGraphics().setBackground(Color.blue);
    }
    
    @Override
    public void update(float delta) {
        
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(new Rectangle(0, 0, 100, 100));
    }
    
    @Override
    public void cleanup() {
    }
    
    public static void main(String[] args) {
        new TheGraphicsObject("Next Gen Graphics!!!");
    }
    
}

package com.radirius.merc.test;

import static com.radirius.merc.gfx.Color.black;
import static com.radirius.merc.gfx.Color.blue;
import static com.radirius.merc.gfx.Color.coal;
import static com.radirius.merc.gfx.Color.cyan;
import static com.radirius.merc.gfx.Color.gray;
import static com.radirius.merc.gfx.Color.green;
import static com.radirius.merc.gfx.Color.magenta;
import static com.radirius.merc.gfx.Color.marble;
import static com.radirius.merc.gfx.Color.ocean;
import static com.radirius.merc.gfx.Color.orange;
import static com.radirius.merc.gfx.Color.rasberry;
import static com.radirius.merc.gfx.Color.red;
import static com.radirius.merc.gfx.Color.springgreen;
import static com.radirius.merc.gfx.Color.turquoise;
import static com.radirius.merc.gfx.Color.violet;
import static com.radirius.merc.gfx.Color.white;
import static com.radirius.merc.gfx.Color.yellow;

import com.radirius.merc.fmwk.Core;
import com.radirius.merc.fmwk.Runner;
import com.radirius.merc.geo.Rectangle;
import com.radirius.merc.gfx.Color;
import com.radirius.merc.gfx.Graphics;

/**
 * Colors got screwed up... So this should be a good tool for the future and
 * now.
 * 
 * @author wessles
 */

public class ColorTest extends Core {
    Runner rnr = Runner.getInstance();
    
    public ColorTest() {
        super("Color Test");
        rnr.init(this, 640, 480);
        rnr.run();
    }
    
    public static void main(String[] args) {
        new ColorTest();
    }
    
    @Override
    public void init() {
        rnr.getGraphics().setBackground(Color.black);
        rnr.getGraphics().scale(8);
    }
    
    @Override
    public void update(float delta) {
    }
    
    @Override
    public void render(Graphics g) {
        test(red, g);
        test(orange, g);
        test(yellow, g);
        test(springgreen, g);
        test(green, g);
        test(turquoise, g);
        test(cyan, g);
        test(ocean, g);
        test(blue, g);
        test(violet, g);
        test(magenta, g);
        test(rasberry, g);
        
        test(black, g);
        
        test(white, g);
        test(marble, g);
        test(gray, g);
        test(coal, g);
        test(black, g);
        colidx = 0;
    }
    
    int colidx;
    
    public void test(Color color, Graphics g) {
        g.pushSetColor(color);
        g.drawRect(new Rectangle(colidx * 4, 0, 4, 8));
        colidx++;
    }
    
    @Override
    public void cleanup() {
    }
    
}

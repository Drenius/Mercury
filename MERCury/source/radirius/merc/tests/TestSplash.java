package radirius.merc.tests;

import radirius.merc.framework.Core;
import radirius.merc.framework.Runner;
import radirius.merc.graphics.Color;
import radirius.merc.graphics.Graphics;
import radirius.merc.splash.SplashScreen;

/**
 * @author wessles
 */

public class TestSplash extends Core {
    Runner rnr = Runner.getInstance();
    
    public TestSplash() {
        super("Splash Screen Test!");
        rnr.init(this, 800, 600);
        rnr.run();
    }
    
    public static void main(String[] args) {
        new TestSplash();
    }
    
    @Override
    public void init() {
        rnr.addSplashScreen(SplashScreen.getMERCuryDefault());
    }
    
    @Override
    public void update(float delta) {
        
    }
    
    @Override
    public void render(Graphics g) {
        if (!rnr.showSplashScreens(g))
            return;
        
        if (!rnr.inited)
            return;
        
        g.setColor(Color.white);
        g.drawString("Let's exit now.", 0, 0);
    }
    
    @Override
    public void cleanup() {
        
    }
    
}

package us.radiri.merc.test;

import org.lwjgl.input.Keyboard;

import us.radiri.merc.fmwk.Core;
import us.radiri.merc.fmwk.Runner;
import us.radiri.merc.geo.Rectangle;
import us.radiri.merc.geo.Vec2;
import us.radiri.merc.gfx.Graphics;
import us.radiri.merc.gfx.Texture;
import us.radiri.merc.in.Input;
import us.radiri.merc.part.ParticleEmitter;
import us.radiri.merc.part.ParticleEmitter.ParticleSetup;
import us.radiri.merc.res.Loader;

/**
 * @author wessles
 */

public class ParticleTest extends Core {
    Runner rnr = Runner.getInstance();
    
    ParticleEmitter emitter1;
    
    public ParticleTest() {
        super("Particle Test");
        rnr.init(this, 500, 300);
        rnr.setMouseGrab(true);
        rnr.run();
    }
    
    public static void main(String[] args) {
        new ParticleTest();
    }
    
    Vec2 torchpos = new Vec2();
    Texture parent;
    
    @Override
    public void init() {
        parent = Texture.loadTexture(Loader.streamFromClasspath("us/radiri/merc/test/dAWWWW.png"));
        
        rnr.getGraphics().scale(4);
        
        ParticleSetup emitter1setup = new ParticleSetup();
        emitter1setup.validangle = new Vec2(0, 360);
        emitter1setup.gravity = new Vec2(0, 0);
        emitter1setup.speed = 0.06f;
        emitter1setup.size = 16;
        emitter1setup.growth = 0.94f;
        emitter1setup.lifeinframes = 60;
        emitter1setup.texture = parent;
        
        emitter1 = new ParticleEmitter(new Rectangle(0, 0, parent.getTextureWidth(), parent.getTextureHeight()), emitter1setup);
    }
    
    @Override
    public void update(float delta) {
        Input in = rnr.getInput();
        
        if (in.keyDown(Keyboard.KEY_ESCAPE))
            rnr.setMouseGrab(false);
        
        emitter1.getEmitterBounds().translate(in.getAbsoluteMouseX() / 4 - emitter1.getEmitterBounds().getX(), in.getAbsoluteMouseY() / 4 - emitter1.getEmitterBounds().getY());
        torchpos.set(emitter1.getEmitterBounds().getCenter().x - parent.getTextureWidth() / 2, emitter1.getEmitterBounds().getY());
        
        emitter1.update(delta);
        
        emitter1.generateParticle(2);
    }
    
    @Override
    public void render(Graphics g) {
        emitter1.render(g);
        g.drawTexture(parent, torchpos.x, torchpos.y);
    }
    
    @Override
    public void cleanup() {
        
    }
    
}

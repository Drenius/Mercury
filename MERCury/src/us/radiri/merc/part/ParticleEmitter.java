package us.radiri.merc.part;

import us.radiri.merc.env.Entity;
import us.radiri.merc.geo.Rectangle;
import us.radiri.merc.geo.Vec2;
import us.radiri.merc.gfx.Color;
import us.radiri.merc.gfx.Graphics;
import us.radiri.merc.gfx.Texture;
import us.radiri.merc.math.MercMath;
import us.radiri.merc.util.WipingArrayList;

/**
 * An object that will emit particle from a given point in a given direction, at
 * a given speed (run-on!).
 * 
 * @author wessles
 */

public class ParticleEmitter implements Entity {
    public static class ParticleSetup {
        /**
         * The 2 valid angles in between which any particle can thrust itself
         * out of spawnarea with.
         */
        public Vec2 validangle = new Vec2(0, 360);
        /** The percent chance that a particle will spawn. */
        public float percentchance = 0.3f;
        
        /** The color of the particles. */
        public Color color = Color.DEFAULT_TEXTURE_COLOR;
        /** The texture of each particle. */
        public Texture texture;
        /** The size of the particles. */
        public float size = 1;
        /**
         * The value by which the size of the particles will be multiplied each
         * frame.
         */
        public float growth = 1;
        
        /** The speed by which the particle will be launched out of the emitter. */
        public float speed = 0.01f;
        /**
         * The value by which the velocity of a particle will be multiplied each
         * frame.
         */
        public float acceleration = 0.98f;
        /** The value that adds to the x and y of each particle each frame. */
        public Vec2 gravity;
        /**
         * The amount of frames that will pass a single particle before
         * death/removal.
         */
        public int lifeinframes = 1000;
    }
    
    private WipingArrayList<Particle> parts;
    
    private Rectangle emitter;
    private ParticleSetup pesetup;
    
    /**
     * @param emitter
     *            The area in which a particle may spawn.
     * @param pesetup
     *            The particle's setup.
     */
    public ParticleEmitter(Rectangle emitter, ParticleSetup pesetup) {
        this.parts = new WipingArrayList<Particle>();
        
        this.emitter = emitter;
        this.pesetup = pesetup;
    }
    
    /**
     * Adds in new particles to the emitter.
     * 
     * @param amount
     *            The amount of particles you wish to generate.
     */
    public void generateParticle(int amount) {
        for (int p = 0; p < amount; p++)
            if (MercMath.chance(pesetup.percentchance)) {
                float angle = (float) MercMath.random(pesetup.validangle.x, pesetup.validangle.y);
                parts.add(new Particle(angle, this));
            }
    }
    
    @Override
    public void update(float delta) {
        for (Particle part : parts)
            part.update(delta);
        
        parts.sweep();
    }
    
    @Override
    public void render(Graphics g) {
        for (Object part : parts)
            ((Particle) part).render(g);
    }
    
    public ParticleSetup getOptions() {
        return pesetup;
    }
    
    public Rectangle getEmitterBounds() {
        return emitter;
    }
}

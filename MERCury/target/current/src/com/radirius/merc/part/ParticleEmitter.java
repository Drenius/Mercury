package com.radirius.merc.part;

import com.radirius.merc.env.AdvancedMercEntity;
import com.radirius.merc.geo.Rectangle;
import com.radirius.merc.geo.Vec2;
import com.radirius.merc.gfx.Color;
import com.radirius.merc.gfx.Graphics;
import com.radirius.merc.math.MercMath;
import com.radirius.merc.util.WipingArrayList;

/**
 * An object that will emit particle from a given point in a given direction, at
 * a given speed (run-on!).
 * 
 * @author wessles
 */

public class ParticleEmitter extends AdvancedMercEntity {
    private WipingArrayList<Particle> parts;
    
    private Vec2 validangle;
    private float percentchance;
    
    private Color color;
    private float size;
    private boolean shrink;
    private float speed;
    private float damp;
    private Vec2 grav;
    private int lifeinframes;
    
    /**
     * @param spawnarea
     *            The area in which a particle may spawn.
     * @param validangle
     *            The 2 valid angles in between which any particle can thrust
     *            itself out of spawnarea with.
     * @param grav
     *            The value that adds to the x and y of each particle each
     *            frame.
     * @param percentchance
     *            The percent chance that a particle will spawn.
     * @param color
     *            The color of the particles.
     * @param size
     *            The size of the particles.
     * @param shrink
     *            The value by which the size of the particles will be
     *            multiplied each frame.
     * @param speed
     *            The speed of the particles.
     * @param damp
     *            The value by which the velocity of a particle will be
     *            multiplied each frame.
     * @param lifeinframes
     *            The amount of frames that will pass a single particle before
     *            death/removal.
     */
    public ParticleEmitter(Rectangle spawnarea, Vec2 validangle, Vec2 grav, float percentchance, Color color, float size, boolean shrink, float speed, float damp, int lifeinframes) {
        super(spawnarea.getX1(), spawnarea.getY1(), spawnarea.getWidth(), spawnarea.getHeight());
        
        parts = new WipingArrayList<Particle>();
        
        this.validangle = validangle;
        this.percentchance = percentchance;
        
        this.color = color;
        this.size = size;
        this.shrink = shrink;
        
        this.speed = speed;
        this.damp = damp;
        this.grav = grav;
        this.lifeinframes = lifeinframes;
    }
    
    /**
     * @param x
     *            The x position of the emitter.
     * @param y
     *            The y position of the emitter.
     * @param validangle
     *            The 2 valid angles in between which any particle can thrust
     *            itself out of spawnarea with.
     * @param grav
     *            The value that adds to the x and y of each particle each
     *            frame.
     * @param percentchance
     *            The percent chance that a particle will spawn.
     * @param color
     *            The color of the particles.
     * @param size
     *            The size of the particles.
     * @param shrink
     *            The value by which the size of the particles will be
     *            multiplied each frame.
     * @param speed
     *            The speed of the particles.
     * @param damp
     *            The value by which the velocity of a particle will be
     *            multiplied each frame.
     * @param lifeinframes
     *            The amount of frames that will pass a single particle before
     *            death/removal.
     */
    public ParticleEmitter(float x, float y, Vec2 validangle, float percentchance, Color color, float size, float damp, boolean shrink, float speed, Vec2 grav, int lifeinframes) {
        this(new Rectangle(x, y, 1, 1), validangle, grav, percentchance, color, size, shrink, speed, damp, lifeinframes);
    }
    
    @Override
    public void update(float delta) {
        if (MercMath.chance(percentchance)) {
            float angle = (float) MercMath.random(validangle.x, validangle.y);
            float x = (float) MercMath.random(getBounds().getX1(), getBounds().getX2()), y = (float) MercMath.random(getBounds().getY1(), getBounds().getY2());
            parts.add(new Particle(color, size, shrink, x, y, angle, speed, damp, lifeinframes, this));
        }
        
        for (Particle part : parts)
            part.update(delta);
        
        parts.sweep();
    }
    
    @Override
    public void render(Graphics g) {
        for (Object part : parts)
            ((Particle) part).render(g);
    }
    
    public WipingArrayList<Particle> getParticles() {
        return parts;
    }
    
    public Vec2 getValidAngles() {
        return validangle;
    }
    
    public Vec2 getGravity() {
        return grav;
    }
    
    public float getPercentChance() {
        return percentchance;
    }
    
    public Color getParticleColor() {
        return color;
    }
    
    public float getParticleSize() {
        return size;
    }
    
    public float getParticleSpeed() {
        return speed;
    }
    
    public float getParticleDamp() {
        return damp;
    }
    
    public boolean getParticleShrinking() {
        return shrink;
    }
    
    public int getParticleLifeInFrames() {
        return lifeinframes;
    }
    
    public void setValidAngles(Vec2 validangle) {
        this.validangle = validangle;
    }
    
    public void setGravity(Vec2 grav) {
        this.grav = grav;
    }
    
    public void setPercentChance(float percentchance) {
        this.percentchance = percentchance;
    }
    
    public void setParticleColor(Color color) {
        this.color = color;
    }
    
    public void setParticleSize(float size) {
        this.size = size;
    }
    
    public void setParticleSpeed(float speed) {
        this.speed = speed;
    }
    
    public void setParticleDamp(float damp) {
        this.damp = damp;
    }
    
    public void setParticleShrinking(boolean shrinking) {
        shrink = shrinking;
    }
    
    public void setParticleLifeInFrames(int lifeinframes) {
        this.lifeinframes = lifeinframes;
    }
    
    @Override
    public boolean isValidPosition(float x, float y) {
        return true;
    }
    
}

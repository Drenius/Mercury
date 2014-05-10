package com.radirius.merc.geo;

/**
 * A base class for all vectors.
 * 
 * @from MERCury in com.radirius.merc.geo
 * @by wessles
 * @website www.wessles.com
 * @license (C) Jan 22, 2014 www.wessles.com This file, and all others of the
 *          project 'MERCury' are licensed under WTFPL license. You can find the
 *          license itself at http://www.wtfpl.net/about/.
 */

public abstract class Vec {
    /**
     * @param vec
     *            The vector to add the values of.
     */
    public abstract void add(Vec vec);
    
    /**
     * @param vec
     *            The vector to subtract the values of.
     */
    public abstract void sub(Vec vec);
    
    /**
     * @param vec
     *            The vector to multiply the values of.
     */
    public abstract void mul(Vec vec);
    
    /**
     * @param vec
     *            The vector to divide the values of.
     */
    public abstract void div(Vec vec);
    
    /**
     * @param vec
     *            The vector to derive the values of.
     */
    public abstract void set(Vec vec);
    
    /**
     * @param coord
     *            The coordinates to convert and set to.
     */
    public abstract void set(float... coord);
    
    /**
     * @param a
     *            The value by which to scale each value of the vector.
     */
    public abstract void scale(float a);
    
    /** Scales the vector by -1. */
    public abstract void negate();
    
    /** @return The length of the vector. */
    public abstract float length();
    
    /** Normalizes the vector. */
    public abstract void normalize();
    
    /** @return The dot product of me and vec. */
    public abstract float dot(Vec vec);
    
    /** @return The distance of a vector */
    public abstract float distance(Vec vec);
    
    /** @return A copy of me. */
    public abstract Vec copy();
    
    @Override
    public abstract String toString();
}

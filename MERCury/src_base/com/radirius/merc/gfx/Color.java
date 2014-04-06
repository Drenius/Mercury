package com.radirius.merc.gfx;

import org.lwjgl.opengl.GL11;

/**
 * A class for Color, that will hold the three values; r, g, and b, and will
 * darken, brighten, multiply, etc.
 * 
 * @from merc in com.radirius.merc.gfx
 * @authors wessles
 * @website www.wessles.com
 * @license (C) Dec 23, 2013 www.wessles.com This file, and all others of the
 *          project 'MERCury' are licensed under WTFPL license. You can find the
 *          license itself at http://www.wtfpl.net/about/.
 */

public class Color
{
    public static final Color red = new Color(255, 0, 0, 255);
    public static final Color orange = new Color(255, 125, 0, 255);
    public static final Color yellow = new Color(255, 255, 0, 255);
    public static final Color springgreen = new Color(125, 255, 0, 255);
    public static final Color green = new Color(0, 255, 0, 255);
    public static final Color turquoise = new Color(0, 255, 125, 255);
    public static final Color cyan = new Color(0, 255, 255, 255);
    public static final Color ocean = new Color(0, 125, 255, 255);
    public static final Color blue = new Color(0, 0, 255, 255);
    public static final Color violet = new Color(125, 0, 255, 255);
    public static final Color magenta = new Color(255, 0, 255, 255);
    public static final Color rasberry = new Color(255, 0, 125, 255);

    public static final Color white = new Color(255, 255, 255, 255);
    public static final Color marble = new Color(188, 188, 188, 255);
    public static final Color gray = new Color(125, 125, 125, 255);
    public static final Color coal = new Color(63, 63, 63, 255);
    public static final Color black = new Color(0, 0, 0, 255);

    public static final Color DEFAULT_BACKGROUND = black;
    public static final Color DEFAULT_DRAWING = white;
    public static final Color DEFAULT_TEXTURE_COLOR = white;

    public float r = 0, g = 0, b = 0, a = 0;

    public Color(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        a = 1;
    }

    public Color(float r, float g, float b, float a)
    {
        this(r, g, b);
        this.a = a;
    }

    public Color(int r, int g, int b)
    {
        this.r = r / 255f;
        this.g = g / 255f;
        this.b = b / 255f;
        a = 1;
    }

    public Color(int r, int g, int b, int a)
    {
        this.r = r / 255f;
        this.g = g / 255f;
        this.b = b / 255f;
        this.a = a / 255f;
    }

    public Color(int value)
    {
        r = (value & 0x00FF0000) >> 16;
        g = (value & 0x0000FF00) >> 8;
        b = value & 0x000000FF;
        a = (value & 0xFF000000) >> 24;
    }

    public void bind()
    {
        GL11.glColor4f(r, g, b, a);
    }

    public void darken()
    {
        darken(.3f);
    }

    public void darken(float scale)
    {
        scale = 1 - scale;
        r *= scale;
        g *= scale;
        b *= scale;
    }

    public void brighten()
    {
        brighten(.2f);
    }

    public void brighten(float scale)
    {
        scale++;
        r *= scale;
        g *= scale;
        b *= scale;
    }

    public int getRed()
    {
        return (int) r * 255;
    }

    public int getGreen()
    {
        return (int) g * 255;
    }

    public int getBlue()
    {
        return (int) b * 255;
    }

    public int getAlpha()
    {
        return (int) a * 255;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Color)
        {
            Color col = (Color) obj;
            if (col.r == r && col.g == g && col.b == b && col.a == a)
                return true;
        }
        return false;
    }
}

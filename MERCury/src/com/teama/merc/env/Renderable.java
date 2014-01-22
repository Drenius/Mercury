package com.teama.merc.env;

import com.teama.merc.gfx.Graphics;

/**
 * An abstraction for objects that can be rendered.
 * 
 * @from MERCury in com.wessles.MERCury
 * @by wessles
 * @website www.wessles.com
 * @license (C) Dec 23, 2013 www.wessles.com This file, and all others of the project 'MERCury' are licensed under WTFPL license. You can find the license itself at http://www.wtfpl.net/about/.
 */

public interface Renderable
{
    public void render(Graphics g);
}

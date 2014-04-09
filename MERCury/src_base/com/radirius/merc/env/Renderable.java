package com.radirius.merc.env;

import com.radirius.merc.gfx.Graphics;

/**
 * An abstraction for objects that can be rendered.
 * 
 * @from merc in com.radirius.merc.env
 * @authors wessles
 * @website www.wessles.com
 * @license (C) Dec 23, 2013 www.wessles.com This file, and all others of the
 *          project 'MERCury' are licensed under WTFPL license. You can find the
 *          license itself at http://www.wtfpl.net/about/.
 */

public interface Renderable
{
    /**
     * The render method. In here there should be peripheral activity, such as
     * graphics, or sound, given g.
     * 
     * @param g
     *            The graphics object.
     */
    public void render(Graphics g);
}

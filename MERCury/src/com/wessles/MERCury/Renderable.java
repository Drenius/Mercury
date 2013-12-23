package com.wessles.MERCury;

import com.wessles.MERCury.opengl.Graphics;

/**
 * An abstraction for objects that can be rendered.
 * 
 * @from MERCury in com.wessles.MERCury
 * @by wessles
 * @website www.wessles.com
 * @license (C) Dec 23, 2013 www.wessles.com This file, and all others of the project 'MERCury' are licensed under GPLv2.0 license. You can find the license itself at bit.ly/1eyRQJ7.
 */

public interface Renderable {
  public void render(Graphics g);
}

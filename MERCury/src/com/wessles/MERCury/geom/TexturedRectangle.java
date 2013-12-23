package com.wessles.MERCury.geom;

import com.wessles.MERCury.opengl.Texture;
import com.wessles.MERCury.opengl.Textured;

/**
 * A textured version of a rectangle.
 * 
 * @from MERCury in com.wessles.MERCury.geom
 * @by wessles
 * @website www.wessles.com
 * @license (C) Dec 23, 2013 www.wessles.com This file, and all others of the project 'MERCury' are licensed under GPLv2.0 license. You can find the license itself at bit.ly/1eyRQJ7.
 */

public class TexturedRectangle extends Rectangle implements Textured {
  private Texture texture;
  
  public TexturedRectangle(Rectangle rect, Texture texture) {
    super(rect.colors, rect.nx, rect.ny, rect.fx, rect.ny, rect.fx, rect.fy, rect.nx, rect.fy);
    this.texture = texture;
  }
  
  @Override
  public Texture getTexture() {
    return texture;
  }
}

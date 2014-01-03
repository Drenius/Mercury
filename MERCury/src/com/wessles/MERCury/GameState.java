package com.wessles.MERCury;

import com.wessles.MERCury.opengl.Graphics;

/**
 * A game state class to be used in {@code StateCore}.
 * 
 * @from MERCury in com.wessles.MERCury
 * @by wessles
 * @website www.wessles.com
 * @license (C) Dec 23, 2013 www.wessles.com This file, and all others of the project 'MERCury' are licensed under WTFPL license. You can find the license itself at http://www.wtfpl.net/about/.
 */
public abstract class GameState {
  protected final StateCore parent_statecore;
  
  public GameState(StateCore parent_statecore) {
    this.parent_statecore = parent_statecore;
  }
  
  public abstract void update(float delta);
  
  public abstract void render(Graphics g);
  
  public StateCore getParentStateCore() {
    return parent_statecore;
  }
}

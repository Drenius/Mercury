package com.radirius.mercury.framework;

import com.radirius.mercury.graphics.Graphics;
import com.radirius.mercury.utilities.misc.Renderable;
import com.radirius.mercury.utilities.misc.Updatable;

/**
 * A class that allows the dividing of a game into different 'states' or
 * 'screens'.
 *
 * @author wessles
 */
public class GameState implements Updatable, Renderable {

	/**
	 * Executed when the GameState is entered.
	 */
	public void onEnter() {}

	/**
	 * Executed when a different GameState is left.
	 */
	public void onLeave() {}

	@Override
	public void update() {}

	@Override
	public void render(Graphics g) {}
}

package com.radirius.mercury.graphics.wip.gui;

/**
 * @author wessles
 */
public interface Button {
	/**
	 * @return Whether the button has been clicked since the
	 *         last time this method was called.
	 */
	public boolean wasActive();
}
package com.radirius.mercury.utilities;

/**
 * An abstraction for objects that can 'wipe' themselves, or self destruct.
 *
 * @author wessles
 */
public interface Wipeable {
	/**
	 * Wipes the object.
	 */
	public void wipe();

	/**
	 * Returns Whether or not the object is wiped.
	 */
	public boolean wiped();
}

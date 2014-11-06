package com.radirius.mercury.graphics;

import com.radirius.mercury.resource.Resource;

/**
 * A class for spritesheets.
 *
 * @author wessles, opiop65, Jeviny
 */
public class SpriteSheet implements Resource {
	private Texture baseTexture;
	private SubTexture[] subTextures;

	private SpriteSheet(Texture baseTexture, SubTexture... subTextures) {
		this.baseTexture = baseTexture;
		this.subTextures = subTextures;
	}

	/** @return The number of subtextures. */
	public int getNumberOfSubTextures() {
		return subTextures.length;
	}

	/**
	 * @return The texture corresponding to the texnum.
	 */
	public SubTexture getTexture(int numTextures) {
		return subTextures[numTextures];
	}

	/** @return The base texture for all SubTextures. */
	public Texture getBaseTexture() {
		return baseTexture;
	}

	/**
	 * Slices the Texture baseTexture up, cutting horizontally and
	 * vertically every divSize length.
	 */
	public static SpriteSheet loadSpriteSheet(Texture baseTexture, int divSize) {
		return loadSpriteSheet(baseTexture, divSize, divSize);
	}

	/**
	 * Slices the Texture baseTexture up, cutting vertically every
	 * divWidth length, and cutting horizontally every
	 * divHeight length. The sub-textures are counted reading
	 * left to right.
	 */
	public static SpriteSheet loadSpriteSheet(Texture baseTexture, int divWidth, int divHeight) {
		SubTexture texture = (SubTexture) baseTexture;

		if (texture.getWidth() % divWidth != 0)
			throw new ArithmeticException("The width of the Texture must be divisible by the division width!");

		int xCut = texture.getWidth() / divWidth;
		int yCut = texture.getHeight() / divHeight;

		SubTexture[] subTextures = new SubTexture[xCut * yCut];

		for (int y = 0; y < yCut; y++)
			for (int x = 0; x < xCut; x++)
				subTextures[x + y * xCut] = new SubTexture(baseTexture, x * divWidth, y * divHeight, (x + 1) * divWidth, (y + 1) * divHeight);

		return new SpriteSheet(texture, subTextures);
	}

	/**
	 * @return A sprite-sheet based off of Texture baseTexture, with
	 *         SubTextures subTextures.
	 */
	public static SpriteSheet loadSpriteSheet(Texture baseTexture, SubTexture... subTextures) {
		return new SpriteSheet(baseTexture, subTextures);
	}

	@Override
	public void clean() {}
}
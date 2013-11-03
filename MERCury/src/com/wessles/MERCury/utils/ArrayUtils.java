package com.wessles.MERCury.utils;

import com.wessles.MERCury.geom.Vector2f;

/**
 * A utilities class for arrays.
 * 
 * @from MERCury
 * @author wessles
 * @website www.wessles.com
 */
public class ArrayUtils {
	public static Vector2f[] getVector2fs(float... coords) {
		if (coords.length % 2 != 0) {
			throw new IllegalArgumentException("Vertex coords must be even!");
		}

		Vector2f[] vectors = new Vector2f[coords.length / 2];

		for (int v = 0; v < coords.length; v += 2) {
			vectors[v / 2] = new Vector2f(coords[v], coords[v + 1]);
		}

		return vectors;
	}
}

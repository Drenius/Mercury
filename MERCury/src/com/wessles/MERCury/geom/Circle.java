package com.wessles.MERCury.geom;

/**
 * An ellipse in which the length and width are the same.
 * 
 * @from MERCury in com.wessles.MERCury.geom
 * @by wessles
 * @website www.wessles.com
 * @license (C) Dec 23, 2013 www.wessles.com This file, and all others of the
 *          project 'MERCury' are licensed under WTFPL license. You can find the
 *          license itself at http://www.wtfpl.net/about/.
 */
public class Circle extends Ellipse
{
	public Circle(float x, float y, float radius)
	{
		super(x, y, radius, radius);
	}
}

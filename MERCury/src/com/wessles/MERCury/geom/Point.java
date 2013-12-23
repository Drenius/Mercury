package com.wessles.MERCury.geom;

/**
 * A point shape. No sides, just a point.
 * 
 * @from MERCury in com.wessles.MERCury.geom
 * @by wessles
 * @website www.wessles.com
 * @license (C) Dec 23, 2013 www.wessles.com This file, and all others of the project 'MERCury' are licensed under GPLv2.0 license. You can find the license itself at bit.ly/1eyRQJ7.
 */

public class Point extends Shape {
  
  public Point(float x, float y) {
    super(x, y);
  }
  
  @Override
  public String toString() {
    return "Point at " + nx + ", " + ny;
  }
  
  @Override
  public float getArea() {
    return 1;
  }
  
  public Vector2f toVector2f() {
    return new Vector2f(nx, ny);
  }
  
  public static Point getPoint(float x, float y) {
    return new Point(x, y);
  }
  
  public static Point getPoint(Point point) {
    return new Point(point.nx, point.ny);
  }
  
  @Override
  public boolean intersects(Shape s) {
    return s.contains(Vector2f.get(getX1(), getY1()));
  }
  
  @Override
  public boolean contains(Vector2f v) {
    return v.x == getX1() && v.y == getY1();
  }
}

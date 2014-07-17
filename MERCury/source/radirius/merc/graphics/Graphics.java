package radirius.merc.graphics;

import radirius.merc.font.Font;
import radirius.merc.geometry.Circle;
import radirius.merc.geometry.Ellipse;
import radirius.merc.geometry.Line;
import radirius.merc.geometry.Point;
import radirius.merc.geometry.Rectangle;
import radirius.merc.geometry.Triangle;
import radirius.merc.geometry.Vec2;

/**
 * An abstraction for all graphics.
 * 
 * @author wessles, Jeviny
 */

public interface Graphics {
    /** Initializes the graphics object. */
    public void init();
    
    /** Prepares the graphics object for rendering. */
    public void pre();
    
    /** Cleans up the graphics object at the end of rendering. */
    public void post();
    
    /** Sets the line width. */
    public void setLineWidth(float width);
    
    /** Scales the matrix. */
    public void setScale(float factor);
    
    /**
     * Scales the matrix.
     * 
     * @param x
     *            The scale factor of x
     * @param y
     *            The scale factor of y
     */
    public void setScale(float x, float y);
    
    /** The last scale that was set in the graphics object's x and y. */
    public Vec2 getScaleDimensions();
    
    /**
     * @return The average last scale that was set in the graphics object's x
     *         and y.
     */
    public float getScale();
    
    /** Sets the font. */
    public void setFont(Font font);
    
    /** @return The last set font. */
    public Font getFont();
    
    /** Sets the background color. */
    public void setBackground(Color col);
    
    /** @return The background color. */
    public Color getBackground();
    
    /** Sets the color. */
    public void setColor(Color color);
    
    /** Sets the color for one drawing, and then sets it to the previous color. */
    public void pushSetColor(Color color);
    
    /** @return The last set color. */
    public Color getColor();
    
    /** Uses a shader. */
    public void useShader(Shader shader);
    
    /** Releases shaders. */
    public void releaseShaders();
    
    /** @return The batcher. */
    public Batcher getBatcher();
    
    /** Pushes raw vertices to the batcher. */
    public void drawRawVertices(VAOBatcher.VertexData... verts);
    
    /** Pushes data in batcher to OGL. */
    public void flush();
    
    /** Draws msg at x and y. */
    public void drawString(float x, float y, String msg);
    
    /** Draws msg at x and y with font. */
    public void drawString(Font font, float x, float y, String msg);
    
    /** Draws msg at x and y. */
    public void drawString(float scale, float x, float y, String msg);
    
    /** Draws msg at x and y with font. */
    public void drawString(float scale, Font font, float x, float y, String msg);
    
    /** Draws a Texture texture at x and y. */
    public void drawTexture(Texture texture, float x, float y);
    
    /** Draws a Texture texture at x and y at w and h size. */
    public void drawTexture(Texture texture, float x, float y, float w, float h);
    
    /**
     * Draws a Texture texture at x and y at w and h size, rotated rot degrees by
     * the local origin (0, 0).
     */
    public void drawTexture(Texture texture, float x, float y, float w, float h, float rot);
    
    /**
     * Draws a Texture texture at x and y at w and h size, rotated rot degrees by
     * the local origin (local_origin_x, local_origin_y).
     */
    public void drawTexture(Texture texture, float x, float y, float w, float h, float rot, float local_origin_x,
            float local_origin_y);
    
    /** Draws a portion of the texture at x and y. */
    public void drawTexture(Texture texture, float sx1, float sy1, float sx2, float sy2, float x, float y);
    
    /** Draws a portion of the texture at x1 and y1, to x2 and y2. */
    public void drawTexture(Texture texture, float sx1, float sy1, float sx2, float sy2, float x, float y, float w,
            float h);
    
    /** Draws a Texture texture to the Rectangle region. */
    public void drawTexture(Texture texture, Rectangle region);
    
    /** Draws a portion of a texture to the Rectangle region. */
    public void drawTexture(Texture texture, float sx1, float sy1, float sx2, float sy2, Rectangle region);
    
    /** Draws the portion sourceregion of a texture to the Rectangle region */
    public void drawTexture(Texture texture, Rectangle sourceregion, Rectangle region);
    
    /** Draws a rectangle. */
    public void drawRect(Rectangle rectangle);
    
    /** Makes and draws a rectangle. */
    public void drawRect(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4);
    
    /** Draws multiple rectangles. */
    public void drawRects(Rectangle[] rects);
    
    /** Draws a triangle. */
    public void drawTriangle(Triangle triangle);
    
    /** Makes and draws a triangle. */
    public void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3);
    
    /** Draws multiple triangles. */
    public void drawTriangles(Triangle[] triangles);
    
    /** Draws an ellipse. */
    public void drawEllipse(Ellipse ellipse);
    
    /** Makes and draws an ellipse. */
    public void drawEllipse(float x, float y, float radx, float rady);
    
    /** Draws multiple ellipses. */
    public void drawEllipses(Ellipse[] ellipses);
    
    /** Draws a circle. */
    public void drawCircle(Circle circle);
    
    /** Makes and draws a circle. */
    public void drawCircle(float x, float y, float radius);
    
    /** Draws multiple circles. */
    public void drawCircles(Circle[] circles);
    
    /** Traces a rectangle. */
    public void traceRect(Rectangle rectangle);
    
    /** Makes and traces a rectangle. */
    public void traceRect(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4);
    
    /** Traces multiple rectangles. */
    public void traceRects(Rectangle[] rects);
    
    /** Traces a triangle. */
    public void traceTriangle(Triangle triangle);
    
    /** Makes and traces a triangle. */
    public void traceTriangle(float x1, float y1, float x2, float y2, float x3, float y3);
    
    /** Traces multiple triangles. */
    public void traceTriangles(Triangle[] triangles);
    
    /** Traces an ellipse. */
    public void traceEllipse(Ellipse ellipse);
    
    /** Makes and traces an ellipse. */
    public void traceEllipse(float x, float y, float radx, float rady);
    
    /** Traces multiple ellipses. */
    public void traceEllipses(Ellipse[] ellipses);
    
    /** Traces a circle. */
    public void traceCircle(Circle circle);
    
    /** Makes and traces a circle. */
    public void traceCircle(float x, float y, float radius);
    
    /** Traces multiple circles. */
    public void traceCircles(Circle[] circles);
    
    // Geometry nerds beware! I know it is actually a line SEGMENT, but for
    // simplicity's sake, I will just call it a line, like a normal human being.
    
    /** Draws a line from x1, y1 to x2, y2. */
    public void drawLine(float x1, float y1, float x2, float y2);
    
    /** Draws a line from p1 to p2. */
    public void drawLine(Vec2 p1, Vec2 p2);
    
    /** Draws a line. */
    public void drawLine(Line l);
    
    /** Draws a point. */
    public void drawPoint(Point point);
    
    /** Makes and draws a point. */
    public void drawPoint(float x, float y);
    
    /** Draws multiple points. */
    public void drawPoints(Point[] points);
}

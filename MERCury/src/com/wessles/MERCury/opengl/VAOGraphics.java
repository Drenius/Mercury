package com.wessles.MERCury.opengl;

import static org.lwjgl.opengl.GL11.*;

import com.wessles.MERCury.Runner;
import com.wessles.MERCury.geom.Circle;
import com.wessles.MERCury.geom.Ellipse;
import com.wessles.MERCury.geom.Point;
import com.wessles.MERCury.geom.Rectangle;
import com.wessles.MERCury.geom.TexturedRectangle;
import com.wessles.MERCury.geom.TexturedTriangle;
import com.wessles.MERCury.geom.Triangle;
import com.wessles.MERCury.geom.Vector2f;
import com.wessles.MERCury.utils.ColorUtils;

/**
 * An object used for graphics. It will draw just about anything for you.
 * 
 * @from MERCury
 * @author wessles
 * @website www.wessles.com
 */

public class VAOGraphics implements Graphics {
	private VAOBatcher batcher;
	private Color background_color;

	public void init() {
		batcher = new VAOBatcher();
	}

	public void pre() {
		batcher.begin();

		float x = Runner.camera().getOffsetX();
		float y = Runner.camera().getOffsetY();
		float w = Runner.width(Runner.SCALE);
		float h = Runner.height(Runner.SCALE);

		drawRect(new Rectangle(x, y, x + w, y, x + w, y + h, x, y + h, new Color[] { background_color, background_color, background_color, background_color }));
	}

	public void post() {
		batcher.end();
	}

	public void scale(float factor) {
		scale(factor, factor);
	}

	public void scale(float x, float y) {
		glScalef(x, y, 1);
	}

	public void setBackground(Color color) {
		background_color = color;
	}

	public void setColor(Color color) {
		batcher.setColor(color);
	}

	public void useShader(Shader shad) {
		batcher.setShader(shad);
	}

	public void releaseShaders() {
		batcher.clearShaders();
	}
	
	public Batcher getBatcher() {
		return batcher;
	}
	
	public void drawRawVertices(VAOBatcher.VertexData... verts) {
		if (verts.length % 3 != 0) {
			throw new IllegalArgumentException("Vertices must be in multiples of 3!");
		}

		for(VAOBatcher.VertexData vdo : verts) {
			batcher.vertex(vdo);
		}
	}

	public void drawTexture(Texture texture, float x, float y) {
		float w = texture.getTextureWidth();
		float h = texture.getTextureHeight();

		batcher.setTexture(texture);

		batcher.vertex(x, y, ColorUtils.DEFAULT_TEXTURE_COLOR, 0, 0);
		batcher.vertex(x + w, y, ColorUtils.DEFAULT_TEXTURE_COLOR, 1, 0);
		batcher.vertex(x, y + h, ColorUtils.DEFAULT_TEXTURE_COLOR, 0, 1);

		batcher.vertex(x + w, y + h, ColorUtils.DEFAULT_TEXTURE_COLOR, 1, 1);
		batcher.vertex(x + w, y, ColorUtils.DEFAULT_TEXTURE_COLOR, 1, 0);
		batcher.vertex(x, y + h, ColorUtils.DEFAULT_TEXTURE_COLOR, 0, 1);
	}

	public void drawRect(Rectangle rectangle) {
		float x1 = rectangle.getVertices()[0].x;
		float y1 = rectangle.getVertices()[0].y;
		float x2 = rectangle.getVertices()[1].x;
		float y2 = rectangle.getVertices()[1].y;
		float x3 = rectangle.getVertices()[2].x;
		float y3 = rectangle.getVertices()[2].y;
		float x4 = rectangle.getVertices()[3].x;
		float y4 = rectangle.getVertices()[3].y;

		Color c1 = null;
		Color c2 = null;
		Color c3 = null;
		Color c4 = null;

		if (!rectangle.ignorecolored) {
			c1 = rectangle.getColors()[0];
			c2 = rectangle.getColors()[1];
			c3 = rectangle.getColors()[2];
			c4 = rectangle.getColors()[3];
		}

		if (!(rectangle instanceof TexturedRectangle)) {
			batcher.clearTextures();
		} else {
			TexturedRectangle texrect = (TexturedRectangle) rectangle;
			batcher.setTexture(texrect.getTexture());
		}

		if (rectangle.ignorecolored) {
			batcher.vertex(x1, y1, 0, 0);
			batcher.vertex(x2, y2, 1, 0);
			batcher.vertex(x4, y4, 0, 1);

			batcher.vertex(x3, y3, 1, 1);
			batcher.vertex(x4, y4, 0, 1);
			batcher.vertex(x2, y2, 1, 0);
		} else {
			batcher.vertex(x1, y1, c1, 0, 0);
			batcher.vertex(x2, y2, c2, 1, 0);
			batcher.vertex(x4, y4, c4, 0, 1);

			batcher.vertex(x3, y3, c3, 1, 1);
			batcher.vertex(x4, y4, c4, 0, 1);
			batcher.vertex(x2, y2, c2, 1, 0);
		}
	}

	public void drawRect(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
		drawRect(new Rectangle(x1, y1, x2, y2, x3, y3, x4, y4));
	}

	public void drawRects(Rectangle[] rects) {
		for (Rectangle rect : rects) {
			drawRect(rect);
		}
	}

	public void drawTriangle(Triangle triangle) {
		float x1 = triangle.getVertices()[0].x;
		float y1 = triangle.getVertices()[0].y;
		float x2 = triangle.getVertices()[1].x;
		float y2 = triangle.getVertices()[1].y;
		float x3 = triangle.getVertices()[2].x;
		float y3 = triangle.getVertices()[2].y;

		Color c1 = null;
		Color c2 = null;
		Color c3 = null;

		if (!triangle.ignorecolored) {
			c1 = triangle.getColors()[0];
			c2 = triangle.getColors()[1];
			c3 = triangle.getColors()[2];
		}

		if (!(triangle instanceof TexturedTriangle)) {
			batcher.clearTextures();
		} else {
			TexturedTriangle texrect = (TexturedTriangle) triangle;
			batcher.setTexture(texrect.getTexture());
		}

		if (triangle.ignorecolored) {
			batcher.vertex(x1, y1, 0, 0);
			batcher.vertex(x3, y3, 1, 0);
			batcher.vertex(x2, y2, 0, 1);
		} else {
			batcher.vertex(x1, y1, c1, 0, 0);
			batcher.vertex(x3, y3, c3, 1, 0);
			batcher.vertex(x2, y2, c2, 0, 1);
		}
	}

	public void drawTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
		drawTriangle(new Triangle(x1, y1, x2, y2, x3, y3));
	}

	public void drawTriangles(Triangle[] triangles) {
		for (Triangle triangle : triangles) {
			drawTriangle(triangle);
		}
	}

	public void drawEllipse(Ellipse ellipse) {
		batcher.clearTextures();

		Vector2f[] vs = ellipse.getVertices();

		for (int c = 0; c < vs.length; c++) {
			batcher.vertex(ellipse.getCenterX(), ellipse.getCenterY(), 0, 0);

			if (c >= vs.length - 1) {
				batcher.vertex(vs[0].x, vs[0].y, 0, 0);
			} else {
				batcher.vertex(vs[c].x, vs[c].y, 0, 0);
			}

			if (c >= vs.length - 1) {
				batcher.vertex(vs[vs.length - 1].x, vs[vs.length - 1].y, 0, 0);
			} else {
				batcher.vertex(vs[c + 1].x, vs[c + 1].y, 0, 0);
			}
		}
	}

	public void drawEllipse(float x, float y, float radx, float rady) {
		drawEllipse(new Ellipse(x, y, radx, rady));
	}

	public void drawEllipses(Ellipse[] ellipses) {
		for (Ellipse ellipse : ellipses) {
			drawEllipse(ellipse);
		}
	}

	public void drawCircle(Circle circle) {
		drawEllipse(circle);
	}

	public void drawCircle(float x, float y, float radius) {
		drawCircle(new Circle(x, y, radius));
	}

	public void drawCircles(Circle[] circs) {
		for (Circle circ : circs) {
			drawCircle(circ);
		}
	}

	public void drawString(float x, float y, String str, Font font) {
		drawString(x, y, str, font, 1);
	}

	public void drawString(float x, float y, String str, Font font, float size) {
		if (font instanceof BitmapFont) {
			BitmapFont bmfont = (BitmapFont) font;

			int w = bmfont.getCharWidth();
			int h = bmfont.getCharHeight();

			int ax = 0;
			for (int i = 0; i < str.length(); i++) {
				int asciiCode = str.charAt(i);
				int cx = asciiCode % bmfont.getTextures().length;
				int cy = asciiCode / bmfont.getTextures()[0].length;

				drawRect(new TexturedRectangle(x + ax, y, x + ax + w * size, y, x + ax + w * size, y + h * size, x + ax, y + h * size, bmfont.getTextures()[cx][cy]));

				ax += w * size;
			}
		}
	}

	public void drawPoint(Point point) {
		float x = point.getX1();
		float y = point.getY1();
		drawRect(new Rectangle(x, y, x + 1, y, x + 1, y + 1, x, y + 1));
	}

	public void drawPoint(float x, float y) {
		drawPoint(new Point(x, y));
	}

	public void drawPoints(Point[] points) {
		for (Point point : points) {
			drawPoint(point);
		}
	}
}

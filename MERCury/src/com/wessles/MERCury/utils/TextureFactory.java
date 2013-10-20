package com.wessles.MERCury.utils;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import static org.lwjgl.opengl.GL11.*;

import com.wessles.MERCury.opengl.Texture;

/**
 * @from MERCury
 * @author wessles
 * @website www.wessles.com
 */
public class TextureFactory {
	public static Texture[] getTextureStrip(String location, int divwidth) throws FileNotFoundException, IOException {
		return getTextureStrip(ImageIO.read(new FileInputStream(location)), divwidth, false, false, GL_NEAREST);
	}

	public static Texture[] getTextureStrip(String location, int divwidth, int filter) throws FileNotFoundException, IOException {
		return getTextureStrip(ImageIO.read(new FileInputStream(location)), divwidth, false, false, filter);
	}

	public static Texture[] getTextureStrip(String location, int divwidth, boolean local_fliphor, boolean local_flipvert) throws FileNotFoundException, IOException {
		return getTextureStrip(ImageIO.read(new FileInputStream(location)), divwidth, local_fliphor, local_flipvert, GL_NEAREST);
	}

	public static Texture[] getTextureStrip(String location, int divwidth, boolean local_fliphor, boolean local_flipvert, int filter) throws FileNotFoundException, IOException {
		return getTextureStrip(ImageIO.read(new FileInputStream(location)), divwidth, local_fliphor, local_flipvert, filter);
	}

	public static Texture[] getTextureStrip(BufferedImage bi, int divwidth) {
		return getTextureStrip(bi, divwidth, false, false, GL_NEAREST);
	}

	public static Texture[] getTextureStrip(BufferedImage bi, int divwidth, int filter) {
		return getTextureStrip(bi, divwidth, false, false, filter);
	}

	public static Texture[] getTextureStrip(BufferedImage bi, int divwidth, boolean local_fliphor, boolean local_flipvert) {
		return getTextureStrip(bi, divwidth, local_fliphor, local_flipvert, GL_NEAREST);
	}

	public static Texture[] getTextureStrip(BufferedImage bi, int divwidth, boolean local_fliphor, boolean local_flipvert, int filter) {
		Texture[] result = new Texture[bi.getWidth() / divwidth];
		int cnt = 0;

		for (int x = 0; x < bi.getWidth(); x += divwidth) {
			result[cnt] = Texture.loadTexture(bi.getSubimage(x, 0, divwidth, bi.getHeight()));
			cnt++;
		}

		return result;
	}

	public static Texture[][] getTextureGrid(String location, int divwidth, int divheight) throws FileNotFoundException, IOException {
		return getTextureGrid(ImageIO.read(new FileInputStream(location)), divwidth, divheight, false, false, GL_NEAREST);
	}

	public static Texture[][] getTextureGrid(String location, int divwidth, int divheight, int filter) throws FileNotFoundException, IOException {
		return getTextureGrid(ImageIO.read(new FileInputStream(location)), divwidth, divheight, false, false, filter);
	}

	public static Texture[][] getTextureGrid(String location, int divwidth, int divheight, boolean local_fliphor, boolean local_flipvert) throws FileNotFoundException, IOException {
		return getTextureGrid(ImageIO.read(new FileInputStream(location)), divwidth, divheight, local_fliphor, local_flipvert, GL_NEAREST);
	}

	public static Texture[][] getTextureGrid(String location, int divwidth, int divheight, boolean local_fliphor, boolean local_flipvert, int filter) throws FileNotFoundException, IOException {
		return getTextureGrid(ImageIO.read(new FileInputStream(location)), divwidth, divheight, local_fliphor, local_flipvert, filter);
	}

	public static Texture[][] getTextureGrid(BufferedImage bi, int divwidth, int divheight) {
		return getTextureGrid(bi, divwidth, divheight, false, false, GL_NEAREST);
	}

	public static Texture[][] getTextureGrid(BufferedImage bi, int divwidth, int divheight, int filter) {
		return getTextureGrid(bi, divwidth, divheight, false, false, filter);
	}

	public static Texture[][] getTextureGrid(BufferedImage bi, int divwidth, int divheight, boolean local_fliphor, boolean local_flipvert) {
		return getTextureGrid(bi, divwidth, divheight, local_fliphor, local_flipvert, GL_NEAREST);
	}

	public static Texture[][] getTextureGrid(BufferedImage bi, int divwidth, int divheight, boolean local_fliphor, boolean local_flipvert, int filter) {
		Texture[][] result = new Texture[bi.getWidth() / divwidth][bi.getHeight() / divheight];

		int cx = 0, cy = 0;
		for (int x = 0; x < bi.getWidth(); x += divwidth) {
			for (int y = 0; y < bi.getHeight(); y += divheight) {
				result[cx][cy] = Texture.loadTexture(bi.getSubimage(x, y, divwidth, divheight));
				cy++;
			}
			cx++;
		}

		return result;
	}
}

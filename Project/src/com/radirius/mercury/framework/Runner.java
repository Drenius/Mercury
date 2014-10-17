package com.radirius.mercury.framework;

import static org.lwjgl.opengl.GL11.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

import com.radirius.mercury.exceptions.*;
import com.radirius.mercury.framework.splash.*;
import com.radirius.mercury.graphics.*;
import com.radirius.mercury.input.*;
import com.radirius.mercury.scene.GameScene;
import com.radirius.mercury.utilities.*;
import com.radirius.mercury.utilities.command.*;
import com.radirius.mercury.utilities.logging.*;

/**
 * The heart of Mercury. Runs the Core and provides all of the various materials
 * required for your game.
 *
 * @authors wessles, Jeviny
 */
public class Runner {
	/**
	 * The singleton instance of the Runner.  
	 * This should be the only Runner used.
	 */
	private final static Runner singleton = new Runner();

	/** Whether or not the Runner is running. */
	public boolean running = false;

	/** A list of splash screens. */
	private final ArrayList<SplashScreen> splashes = new ArrayList<SplashScreen>();

	/** A list of plugins. */
	private final ArrayList<Plugin> plugins = new ArrayList<Plugin>();

	/** A Runnable for the console thread. */
	private final CommandThread consoleRunnable = new CommandThread();

	/** A Thread for the console. */
	private final Thread consoleThread = new Thread(consoleRunnable);

	/** Whether or not the game is being updated. */
	private boolean updating = true;

	/** Whether or not the game is being rendered. */
	private boolean rendering = true;

	/** Whether or not v-sync is enabled. */
	private boolean vsync;

	/** The delta variable. */
	private int delta = 1;

	/** The target framerate. */
	private int fpsTarget = 60;

	/** The current framerate. */
	private int fps;

	/** The last frame. Used for calculating the framerate. */
	private long lastFrame;

	/** The factor by which the delta time is multiplied. */
	private float deltaFactor = 1;

	/**
	 * A string that holds debugging data to be rendered to the screen, should
	 * `showDebug` be true.
	 */
	private String debugData = "";

	/** Whether or not the debugdata will be drawn to the screen. */
	private boolean showDebug = false;

	/** The Core being ran. */
	private Core core;

	/** The graphics object. */
	private Graphics graphics;

	/** The camera object. */
	private Camera camera;

	/** The input object. */
	private Input input;

	/*
	 * We don't want anybody attempting to create another Runner, there's a
	 * singleton and it should be put to use.
	 */
	private Runner() {
	}

	/**
	 * An object that will be used for initializing the Runner with default
	 * values that can be modified.
	 */
	public static class InitSetup {
		public InitSetup(Core core, int width, int height) {
			this.core = core;
			this.width = width;
			this.height = height;
		}

		/** The Core to be ran. */
		public Core core;
		/** The width of the display. */
		public int width;
		/** The height of the display. */
		public int height;
		/** Whether or not fullscreen is enabled. */
		public boolean fullscreen = false;
		/** Whether or not v-sync is enabled. */
		public boolean vsync = true;
		/** Whether or not the Core is initialized on a separate thread. */
		public boolean multithread = false;
		/** Whether or not the developers console is enabled. */
		public boolean devconsole = true;
	}

	/**
	 * Initializes Mercury.
	 *
	 * @param core
	 *            The Core to be ran.
	 * @param width
	 *            The width of the display.
	 * @param height
	 *            The height of the display.
	 */
	public void init(Core core, int width, int height) {
		init(core, width, height, false);
	}

	/**
	 * Initializes Mercury.
	 *
	 * @param core
	 *            The Core to be ran.
	 * @param width
	 *            The width of the display.
	 * @param height
	 *            The height of the display.
	 * @param fullscreen
	 *            Whether or not fullscreen is enabled.
	 */
	public void init(Core core, int width, int height, boolean fullscreen) {
		init(core, width, height, fullscreen, true, false, true);
	}

	/**
	 * Initializes Mercury.
	 *
	 * @param core
	 *            The Core to be ran.
	 * @param fullscreen
	 *            Whether or not fullscreen is enabled.
	 * @param vsync
	 *            Whether or not v-sync is enabled.
	 */
	public void init(Core core, boolean fullscreen, boolean vsync) {
		init(core, Display.getDesktopDisplayMode().getWidth(), Display.getDesktopDisplayMode().getHeight(), fullscreen,
				vsync, false, true);
	}

	/**
	 * Initializes Mercury.
	 *
	 * @param iniset
	 *            The initialization setup filled with information to initialize
	 *            with.
	 */
	public void init(InitSetup iniset) {
		init(iniset.core, iniset.width, iniset.height, iniset.fullscreen, iniset.vsync, iniset.multithread,
				iniset.devconsole);
	}

	public boolean inited = false;

	/**
	 * Initializes the library
	 *
	 * @param core
	 *            The Core to be ran.
	 * @param width
	 *            The width of the display.
	 * @param height
	 *            The height of the display.
	 * @param fullscreen
	 *            Whether or not fullscreen is enabled.
	 * @param vsync
	 *            Whether or not v-sync is enabled.
	 * @param multithread
	 *            Whether or not the Core is initialized on a separate thread.
	 * @param devconsole
	 *            Whether or not the developers console is enabled.
	 */
	public void init(final Core core, int width, int height, boolean fullscreen, boolean vsync, boolean multithread,
			boolean devconsole) {
		System.out.println("Mercury 2D Game Library\n" + "Designed by Radirius\n" + "Website: http://merc.radiri.us/");
		System.out.println("-------------------------------");

		// Lots of initialization that is self explanatory...
		Logger.info("Mercury Starting:");
		Logger.info("Making Core...");
		this.core = core;
		this.vsync = vsync;

		Logger.info("Making Display & Graphics...");
		this.core.initDisplay(width, height, fullscreen, vsync);

		graphics = this.core.initGraphics();
		Logger.info("OpenGL Version: " + GL11.glGetString(GL11.GL_VERSION));
		Logger.info("Display Mode: " + Display.getDisplayMode());

		Logger.info("Starting Graphics...");
		graphics.init();

		Logger.info("Making Audio...");
		this.core.initAudio();

		Logger.info("Making Camera...");
		camera = new Camera(0, 0);

		Logger.info("Making Input...");
		input = new Input();
		input.create();

		Logger.info("Making Plugins...");
		for (Plugin plugin : plugins) {
			Logger.info("\tInitializing " + plugin.getName() + "...");

			plugin.init();
		}

		Logger.info("Starting Core" + (multithread ? " (On Separate Thread)" : "") + "...");
		if (multithread) {
			Runnable initthread_run = new Runnable() {
				@Override
				public void run() {
					core.init();

					inited = true;
				}
			};

			Thread initthread = new Thread(initthread_run);

			initthread.run();
		}

		Logger.info("Making Default CommandList 'merc...'");
		CommandList.addCommandList(CommandList.getDefaultCommandList());

		Logger.info("Starting Developer Console Thread...");
		consoleThread.setName("merc_devconsole");
		consoleThread.start();

		Logger.info("Ready to begin game loop. Awaiting permission from Core...");
	}

	/**
	 * The main game loop.
	 */
	public void run() {
		Logger.info("Starting Game Loop...");
		Logger.newLine();

		core.init();
		GameScene.init();

		inited = true;

		running = true;

		int FPS1 = 0;
		long lastfps;

		/*
		 * Initial 'last time...' Otherwise the first delta will be about
		 * 50000000.
		 */
		lastfps = lastFrame = Sys.getTime() * 1000 / Sys.getTimerResolution();

		while (running) {
			// Set time for FPS and Delta calculations
			long time = Sys.getTime() * 1000 / Sys.getTimerResolution();

			// Calculate delta
			delta = (int) (time - lastFrame);

			// Update FPS
			if (time - lastfps < 1000) {
				FPS1++;
			} else {
				lastfps = time;
				fps = FPS1;
				FPS1 = 0;
			}

			if (fps == 0)
				fps = fpsTarget;

			lastFrame = time;

			input.poll();

			if (rendering)
				glClear(GL_COLOR_BUFFER_BIT);

			if (updating) {
				GameScene.update(getDelta());
				core.update(getDelta());
			}
				
			// Update timing
			TaskTiming.update();

			if (rendering) {
				// Pre-Render Camera
				camera.pre(graphics);

				// Render Game
				if (showSplashScreens(graphics)) {
					GameScene.render(graphics);
					core.render(graphics);
				}

				// Debug
				if (showDebug) {
					addDebugData("FPS", getFPS() + "");

					graphics.drawString(debugData, 1 / graphics.getScale(), 4, 4);
					debugData = "";
				}

				// Post-Render Camera
				camera.post(graphics);
			}

			// Close the window if the window is closed.
			if (Display.isCloseRequested())
				end();

			// Update and sync the FPS.
			Display.update();
			Display.sync(fpsTarget);
		}

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		Display.update();

		// End the loop and clean up things...

		Logger.newLine();
		Logger.info("Ending Game Loop...");
		Logger.info("Beginning Clean Up:");

		Logger.info("Cleaning Up Developers Console...");
		consoleThread.interrupt();

		Logger.info("Cleaning Up Core & Plugins...");
		GameScene.cleanup();
		core.cleanup();

		for (Plugin plugin : plugins) {
			Logger.info("     Cleaning Up '" + plugin.getName() + "' Plugin...");

			plugin.cleanup();
		}

		Logger.info("Clean Up Complete.");
		Logger.info("Mercury Shutting Down...");
	}

	/** @return The framerate. */
	public int getFPS() {
		return fps;
	}

	/** @return The vertices rendered in the last rendering frame. */
	public int getVerticesLastRendered() {
		return getGraphics().getBatcher().getVerticesLastRendered();
	}

	/**
	 * @param target
	 *            The new FPS target
	 */
	public void setFPSTarget(int target) {
		fpsTarget = target;
	}

	/**
	 * Sets whether or not debug data should be displayed.
	 *
	 * @param showdebug
	 *            Whether or not debug is to be shown onscreen.
	 */
	public void showDebug(boolean showdebug) {
		this.showDebug = showdebug;
	}

	/**
	 * Adds information to the debugdata. Debug data is wiped every single
	 * update frame, so this is to be called every frame.
	 *
	 * @param name
	 *            The name of the debug information.
	 * @param value
	 *            The value of the debug information.
	 */
	public void addDebugData(String name, String value) {
		name.trim();
		value.trim();

		debugData += name + " " + value + "\n";
	}

	/** @return The width of the display. */
	public int getWidth() {
		return Display.getWidth();
	}

	/** @return The height of the display. */
	public int getHeight() {
		return Display.getHeight();
	}

	/** @return The aspect ratio of the display. */
	public float getAspectRatio() {
		return (float) (getWidth()) / (float) (getHeight());
	}

	/** @return Time in milliseconds. */
	public float getMillis() {
		return System.currentTimeMillis();
	}

	/** @return Time in seconds. */
	public float getSeconds() {
		return getMillis() / 1000f;
	}

	/**
	 * Sleeps the thread for a few milliseconds.
	 *
	 * @param milliseconds
	 *            The milliseconds to wait.
	 */
	public void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Enables or disables mouse grabbing.
	 *
	 * @param grab
	 *            Whether or not to grab the mouse.
	 */
	public void enableMouseGrab(boolean grab) {
		Mouse.setGrabbed(grab);
	}

	/**
	 * Sets whether or not v-sync is enabled.
	 *
	 * @param vsync
	 *            Whether or not to use v-sync.
	 */
	public void enableVsync(boolean vsync) {
		this.vsync = vsync;

		Display.setVSyncEnabled(vsync);
	}

	/** @return Whether or not the window has the focus. */
	public boolean isFocused() {
		return Display.isActive();
	}

	/**
	 * Sets the title of the window.
	 *
	 * @param title
	 *            The title of the window.
	 */
	public void setTitle(String title) {
		Display.setTitle(title);
	}

	/**
	 * Sets the icon for given size(s). Recommended sizes that you should put in
	 * are x16, x32, and x64.
	 *
	 * @param icons
	 *            Icon(s) for the game.
	 */
	public void setIcon(InputStream... icons) {
		ArrayList<ByteBuffer> buffers = new ArrayList<ByteBuffer>();

		for (InputStream is : icons) {
			if (is != null) {
				try {
					buffers.add(Texture.convertBufferedImageToBuffer(ImageIO.read(is)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		ByteBuffer[] bufferarray = new ByteBuffer[buffers.size()];

		buffers.toArray(bufferarray);
		Display.setIcon(bufferarray);
	}

	/**
	 * Sets the resizability of the window.
	 *
	 * @param resizable
	 *            The resizability of the window
	 */
	public void setResizable(boolean resizable) {
		Display.setResizable(resizable);

		remakeDisplay();
	}

	/** Remakes the display. */
	private void remakeDisplay() {
		Display.destroy();

		getCore().initDisplay(getWidth(), getHeight(), Display.isFullscreen(), vsync);

		graphics = getCore().initGraphics();
	}

	/** Ends the loop. */
	public void end() {
		running = false;
	}

	/** @return The delta time variable. */
	public float getDelta() {
		return delta * deltaFactor;
	}

	/**
	 * Sets the factor by which the delta time will be multiplied.
	 *
	 * @param factor
	 *            The new delta factor.
	 */
	public void setDeltaFactor(float factor) {
		deltaFactor = factor;
	}

	/** @return Get the Core being ran. */
	public Core getCore() {
		return core;
	}

	/**
	 * Enables/Disables all updating.
	 *
	 * @param updating
	 *            Whether or not to stop updating.
	 */
	public void enableUpdating(boolean updating) {
		this.updating = updating;
	}

	/** @return The graphics object. */
	public Graphics getGraphics() {
		return graphics;
	}

	/**
	 * Enables/Disables all rendering.
	 *
	 * @param rendering
	 *            Whether or not to stop rendering.
	 */
	public void enableRendering(boolean rendering) {
		this.rendering = rendering;
	}

	/** @return The camera object. */
	public Camera getCamera() {
		return camera;
	}

	/** @return The input object. */
	public Input getInput() {
		return input;
	}
	
	public void setViewport(int x, int y, int width, int height) {
		glViewport(x, y, width, height);
	}

	/** The current splash screen. */
	private int splidx = 0;

	/**
	 * Shows the current splash screen.
	 *
	 * @return Whether there aren't any more splash screens to be shown.
	 */
	private boolean showSplashScreens(Graphics g) {
		if (splidx > splashes.size() - 1)
			return true;

		if (!splashes.get(splidx).show(g))
			splidx++;

		return false;
	}

	/*
	 * @return Whether the splashes screens are being shown.
	 */
	public boolean showingSplashScreens() {
		if (splidx > splashes.size() - 1)
			return false;

		return true;
	}

	/**
	 * Adds a splash screen to the queue.
	 *
	 * @param splash
	 *            The splash screen to add.
	 */
	public void addSplashScreen(SplashScreen splash) {
		splashes.add(splash);
	}

	/**
	 * Adds a plugin.
	 *
	 * @param plugin
	 *            The plugin to add.
	 */
	public void addPlugin(Plugin plugin) {
		plugins.add(plugin);
	}

	/**
	 * @param name
	 *            The name of the plugin you want.
	 *
	 * @return The plugin corresponding to name.
	 */
	public Plugin getPlugin(String name) throws MercuryException {
		for (Plugin plugin : plugins)
			if (plugin.getName().equalsIgnoreCase(name))
				return plugin;

		throw new MercuryException("Plugin '" + name + "' was not found!");
	}

	/** @return The singleton instance of Runner. */
	public static Runner getInstance() {
		return singleton;
	}
}

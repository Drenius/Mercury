package radirius.merc.framework.splash;

import radirius.merc.framework.Runner;
import radirius.merc.graphics.Color;
import radirius.merc.graphics.Graphics;
import radirius.merc.graphics.Texture;
import radirius.merc.math.geometry.Rectangle;
import radirius.merc.resource.Loader;
import radirius.merc.utilities.TaskTiming;
import radirius.merc.utilities.TaskTiming.Task;
import radirius.merc.utilities.easing.EasingUtils;
import radirius.merc.utilities.easing.EasingValue;

/**
 * A base for all splash screens.
 * 
 * @author wessles
 */

public class SplashScreen {
	static Runner runner = Runner.getInstance();
	
	public boolean showing = false;
	private boolean returned = true;

	public long showTimeMillis;
	public Texture splashTexture;
	private boolean fitToScreen;

	/**
	 * @param splashTexture
	 *            The texture of the splash screen.
	 * @param showTimeMillis
	 *            The time that the splash screen is shown.
	 * @param fitToScreen
	 *            Whether or not to fit the image to the screen while still
	 *            maintaining the aspect ratio.
	 */
	public SplashScreen(Texture splashTexture, long showTimeMillis, boolean fitToScreen) {
		this.showTimeMillis = showTimeMillis;
		this.splashTexture = splashTexture;
		this.fitToScreen = fitToScreen;
	}

	/**
	 * @param splashTexture
	 *            The texture of the splash screen.
	 * @param showTimeMillis
	 *            The time that the splash screen is shown.
	 */
	public SplashScreen(Texture splashTexture, long showTimeMillis) {
		this(splashTexture, showTimeMillis, (splashTexture.width <= runner.getWidth() && splashTexture.height <= runner.getHeight()) ? false : true);
	}

	EasingValue easingValue;

	/**
	 * Shows the splash screen on screen, whilst checking if it is time to stop
	 * as well.
	 * 
	 * @return Whether or not the splash is done.
	 */
	public boolean show(Graphics g) {
		if (!showing) {
			TaskTiming.addTask(new Task(showTimeMillis) {
				@Override
				public void run() {
					returned = false;
				}
			});

			easingValue = new EasingValue(EasingUtils.BOUNCING_EASE_QUINT, 0, 1, showTimeMillis);

			showing = true;
		}

		Rectangle cam = Runner.getInstance().getCamera().getBounds();
		
		float width = splashTexture.getWidth();
		float height = splashTexture.getHeight();

		if (fitToScreen) {
			float scale = cam.getWidth() / splashTexture.getWidth();
			
			width = cam.getWidth();
			height = splashTexture.getHeight() * scale;
			scale = cam.getHeight() / height;
			height = cam.getHeight();

			width *= scale;
		}

		g.setColor(new Color(0, 0, 0, easingValue.get()));
		g.drawTexture(splashTexture, cam.getX() + cam.getWidth() / 2 - width / 2, cam.getY() + cam.getHeight() / 2 - height / 2, width, height);
		
		return returned;
	}

	/**
	 * Show some love for Mercury by using a shiny Mercury splash screen!
	 * 
	 * @return The love of Mercury's developers. <3
	 */
	public static SplashScreen getMercuryDefault() {
		Texture texture = Texture.loadTexture(Loader.streamFromClasspath("radirius/merc/framework/splash/splash.png"), Texture.FILTER_LINEAR);

		return new SplashScreen(texture, 4000);
	}
}

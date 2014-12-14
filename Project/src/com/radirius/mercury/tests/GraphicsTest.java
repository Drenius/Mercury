package com.radirius.mercury.tests;

import org.lwjgl.opengl.GL11;

import com.radirius.mercury.framework.Core;
import com.radirius.mercury.framework.CoreSetup;
import com.radirius.mercury.framework.Window;
import com.radirius.mercury.graphics.Graphics;

class GraphicsTest extends Core {

	public GraphicsTest(CoreSetup setup) {
		super(setup);
	}

	public static void main(String[] args) {
		CoreSetup setup = new CoreSetup("Graphics Test");
		setup.vSync = false;
		setup.showConsoleDebug = true;
		setup.showDebug = true;

		new GraphicsTest(setup).start();
	}

	@Override
	public void init() {}

	@Override
	public void update() {}

	@Override
	public void render(Graphics g) {
		/*
		 * Notice that functions that previously were in the Runner can now be
		 * accessed directly in the Core.
		 */

		addDebugData("OpenGL", GL11.glGetString(GL11.GL_VERSION));
		addDebugData("Vertices Last Rendered", "" + getBatcher().getVerticesLastRendered());

		getCamera().setOrigin(Window.getCenter());
		getCamera().rotate(0.01f);
	}

	@Override
	public void cleanup() {}
}

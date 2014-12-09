package com.radirius.mercury.tests;

import com.radirius.mercury.framework.*;
import com.radirius.mercury.graphics.*;
import com.radirius.mercury.graphics.font.TrueTypeFont;
import com.radirius.mercury.input.Input;

class GameStateTest extends Core {
	GameState gs0 = new GameState() {
		public void render(Graphics g) {
			g.setBackground(Color.BLUE);
		}
	};
	GameState gs1 = new GameState() {
		public void render(Graphics g) {
			g.setBackground(Color.RED);
		}
	};

	public GameStateTest(CoreSetup setup) {
		super(setup);
	}

	public static void main(String[] args) {
		CoreSetup setup = new CoreSetup("GameState Test");
		
		new GameStateTest(setup).start();
	}


	public void init() {
	}

	public void update() {
		if (Input.keyClicked(Input.KEY_RETURN)) {
			if (getCurrentState() == gs0)
				switchGameState(gs1);
			else
				switchGameState(gs0);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Mash Enter.", Window.getCenterX() - TrueTypeFont.ROBOTO_REGULAR.getWidth("Mash Enter.") / 2, 128);

	}

	public void cleanup() {

	}
}

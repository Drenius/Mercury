package com.radirius.mercury.tests;

import com.radirius.mercury.framework.Core;
import com.radirius.mercury.framework.CoreSetup;
import com.radirius.mercury.graphics.Color;
import com.radirius.mercury.graphics.Graphics;
import com.radirius.mercury.graphics.Texture;
import com.radirius.mercury.graphics.particles.ParticleSystem;
import com.radirius.mercury.input.Input;
import com.radirius.mercury.math.MathUtil;
import com.radirius.mercury.math.geometry.Vector2f;
import com.radirius.mercury.resource.Loader;

/**
 * @author wessles
 */
public class ParticleTest extends Core {

	public ParticleTest(CoreSetup coreSetup) {
		super(coreSetup);
	}

	public static void main(String[] args) {
		CoreSetup coreSetup = new CoreSetup("Particle Test");
		coreSetup.width = 1280;
		coreSetup.height = 720;

		ParticleTest particleTest = new ParticleTest(coreSetup);
		particleTest.start();
	}

	ParticleSystem smoke;

	@Override
	public void init() {
		ParticleSystem.ParticleSetup smokeSetup = new ParticleSystem.ParticleSetup();
		smokeSetup.size = 64;
		smokeSetup.texture = Texture.loadTexture(Loader.getResourceAsStream("com/radirius/mercury/tests/cloud.png"));
		smokeSetup.color = Color.CONCRETE.duplicate();
		smokeSetup.color.a = 0.04f;

		smokeSetup.gravity = new Vector2f(0, -0.1f);
		smoke = new ParticleSystem(smokeSetup);
	}

	@Override
	public void update() {
		for (int i = 0; i < 5; i++) {
			smoke.getOptions().speed = MathUtil.random(0.5f, 1.5f);
			smoke.getOptions().angle = MathUtil.random(0, -180);
			smoke.generateParticle(1, Input.getGlobalMousePosition());
		}
		smoke.update();
	}

	@Override
	public void render(Graphics g) {
		smoke.render(g);
	}

	@Override
	public void cleanup() {}
}

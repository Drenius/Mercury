package radirius.merc.utilities.easing;

import static radirius.merc.utilities.easing.EasingUtils.*;

/**
 * An object that makes easing easier. (No Pun Intended).
 *
 * @author wessles, Jeviny
 */

public class EasingValue {
	public int easeType;
	public float startVal, endVal, easeValue;
	public long startMillis, durationMillis;

	public EasingValue(int easeType, float startVal, float endVal, long durationMillis) {
		this(easeType, System.currentTimeMillis(), startVal, endVal, durationMillis);
	}

	public EasingValue(int easeType, long startMillis, float startVal, float endVal, long durationMillis) {
		this.easeType = easeType;
		this.startMillis = startMillis;
		this.startVal = startVal;
		this.endVal = endVal;
		this.easeValue = startVal;
		this.durationMillis = durationMillis;
	}

	public float get() {
		float easeValue = 0;

		switch (easeType) {
		case LINEAR_TWEEN:
			easeValue = linearTween(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case BOUNCING_LINEAR_TWEEN:
			easeValue = bouncingLinearTween(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case EASE_QUAD:
			easeValue = easeQuad(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case BOUNCING_EASE_QUAD:
			easeValue = bouncingEaseQuad(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case EASE_CUBIC:
			easeValue = easeCubic(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case BOUNCING_EASE_CUBIC:
			easeValue = bouncingEaseCubic(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case EASE_QUINT:
			easeValue = easeQuint(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case BOUNCING_EASE_QUINT:
			easeValue = bouncingEaseQuint(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case EASE_SINE:
			easeValue = easeSine(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case BOUNCING_EASE_SINE:
			easeValue = bouncingEaseSine(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case EASE_EXPO:
			easeValue = easeExpo(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case BOUNCING_EASE_EXPO:
			easeValue = bouncingEaseExpo(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case EASE_CIRCLE:
			easeValue = easeCircle(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		case BOUNCING_EASE_CIRCLE:
			easeValue = bouncingEaseCircle(System.currentTimeMillis() - startMillis, startVal, endVal, durationMillis);
		}

		return easeValue;
	}

	public void reset() {
		reset(0);
	}

	public void reset(int point) {
		startMillis = System.currentTimeMillis() - point;
	}
}

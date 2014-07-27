package radirius.merc.utils;

/**
 * Mostly nabbed from here http://gizma.com/easing/. Bouncing by me :).
 * 
 * @author wessles
 */

public class EasingUtils {
    public static float linearTween(float time, float startval, float endval, float duration) {
        float change = endval - startval;
        return change * time / duration + startval;
    }
    
    public static float easeQuad(float time, float startval, float endval, float duration) {
        float change = endval - startval;
        time /= duration;
        return change * time * time + startval;
    }
    
    public static float bouncingEaseQuad(float time, float startval, float endval, float duration) {
        if (time < duration / 2)
            return easeQuad(time, startval, endval, duration / 2);
        else
            return easeQuad(time - duration / 2, endval, startval, duration / 2);
    }
    
    public static float easeCubic(float time, float startval, float endval, float duration) {
        float change = endval - startval;
        time /= duration;
        return change * time * time * time + startval;
    }
    
    public static float bouncingEaseCubic(float time, float startval, float endval, float duration) {
        if (time < duration / 2)
            return easeCubic(time, startval, endval, duration / 2);
        else
            return easeCubic(time - duration / 2, endval, startval, duration / 2);
  }
    
    public static float easeQuint(float time, float startval, float endval, float duration) {
        float change = endval - startval;
        time /= duration;
        return change * time * time * time * time * time + startval;
    }
    
    public static float bouncingEaseQuint(float time, float startval, float endval, float duration) {
        if (time < duration / 2)
            return easeQuint(time, startval, endval, duration / 2);
        else
            return easeQuint(time - duration / 2, endval, startval, duration / 2);
    }
    
    public static float easeSine(float time, float startval, float endval, float duration) {
        float change = endval - startval;
        return -change * (float) Math.cos(time / duration * (Math.PI / 2)) + change + startval;
    }
    
    public static float bouncingEaseSine(float time, float startval, float endval, float duration) {
        if (time < duration / 2)
            return easeSine(time, startval, endval, duration / 2);
        else
            return easeSine(time - duration / 2, endval, startval, duration / 2);
    }
    
    public static float easeExpo(float time, float startval, float endval, float duration) {
        float change = endval - startval;
        return change * (float) Math.pow(2, 10 * (time / duration - 1)) + startval;
    }
    
    public static float bouncingEaseExpo(float time, float startval, float endval, float duration) {
        if (time < duration / 2)
            return easeExpo(time, startval, endval, duration / 2);
        else
            return easeExpo(time - duration / 2, endval, startval, duration / 2);
    }
    
    public static float easeCirc(float time, float startval, float endval, float duration) {
        float change = endval - startval;
        time /= duration;
        return -change * ((float) Math.sqrt(1 - time * time) - 1) + startval;
    }
    
    public static float bouncingEaseCirc(float time, float startval, float endval, float duration) {
        if (time < duration / 2)
            return easeCirc(time, startval, endval, duration / 2);
        else
            return easeCirc(time - duration / 2, endval, startval, duration / 2);
    }
}

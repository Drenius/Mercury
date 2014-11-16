package com.radirius.mercury.graphics;

import com.radirius.mercury.framework.Runner;
import com.radirius.mercury.math.geometry.Matrix4f;
import com.radirius.mercury.math.geometry.Rectangle;
import com.radirius.mercury.math.geometry.Vector2f;
import com.radirius.mercury.utilities.GraphicsUtils;

/**
 * An object for the camera.
 *
 * @author wessles
 */
public class Camera {
    /**
     * The position on its respective axis
     */
    float x, y;
    /**
     * The scaling / zoom of the camera
     */
    Vector2f scale = new Vector2f(1, 1);
    /**
     * Rotation of the camera
     */
    float rot = 0;
    /**
     * The point on the screen that anchors the camera to
     * the world.
     */
    private Vector2f origin = new Vector2f(0, 0);

    /**
     * @param x The x position.
     * @param y The y position.
     */
    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Prepares the camera for each frame.
     */
    public void pre(Graphics g) {
        GraphicsUtils.pushMatrix();
        updateTransforms();
        g.pre();
    }

    private void updateTransforms()
    {
        Runner.getInstance().getGraphics().getBatcher().flush();

        // Update the transformation matrix
        Matrix4f cm = GraphicsUtils.getCurrentMatrix();

        cm.initIdentity()
                .mul(new Matrix4f().initRotation(rot))
                .mul(new Matrix4f().initScale(scale.x, scale.y))
                .mul(new Matrix4f().initTranslation(origin.x, origin.y));
    }

    /**
     * Applies changes to OGL at the end of each frame.
     */
    public void post(Graphics g) {
        g.post();

        GraphicsUtils.popMatrix();
    }

    /**
     * @return The origin point (on screen).
     */
    public Vector2f getOrigin() {
        return origin;
    }

    /**
     * @param origin The point to set the origin to (on
     *               screen).
     */
    public void setOrigin(Vector2f origin) {
        this.origin = origin;
    }

    /**
     * Zooms the camera in / scales the scene.
     *
     * @param x The value by which to scale horizontally.
     * @param y The value by which to scale vertically.
     */
    public void scale(float x, float y) {
        scale.x += x;
        scale.y += y;
        updateTransforms();
    }

    /**
     * Zooms the camera in / scales the scene.
     *
     * @param x The value to set the horizontal scaling.
     * @param y The value to set the vertical scaling.
     */
    public void setScale(float x, float y) {
        scale.x = x;
        scale.y = y;
        updateTransforms();
    }

    /**
     * Zooms the camera in / scales the scene.
     *
     * @param scale The value by which to scale.
     */
    public void scale(float scale) {
        this.scale.x += scale;
        this.scale.y += scale;
        updateTransforms();
    }

    /**
     * Rotates the camera on its origin.
     *
     * @param rot The amount by which to rotate
     */
    public void rotate(float rot) {
        this.rot += rot;
        updateTransforms();
    }

    /**
     * Translates the camera by x and y.
     *
     * @param x The x movement.
     * @param y The y movement.
     */
    public void translate(float x, float y) {
        this.x -= x;
        this.y -= y;
        updateTransforms();
    }

    /**
     * Translates the camera to x and y.
     *
     * @param x The x position.
     * @param y The y position.
     */
    public void translateTo(float x, float y) {
        this.x = x;
        this.y = y;
        updateTransforms();
    }

    /**
     * @return The real world width of the camera.
     */
    public float getWidth() {
        return Runner.getInstance().getWidth() / Runner.getInstance().getGraphics().getScaleDimensions().x;
    }

    /**
     * @return The real world height of the camera.
     */
    public float getHeight() {
        return Runner.getInstance().getHeight() / Runner.getInstance().getGraphics().getScaleDimensions().y;
    }

    /**
     * @return The real world x position of the camera.
     */
    public float getPositionX() {
        return getPosition().x;
    }

    /**
     * @return The real world y position of the camera.
     */
    public float getPositionY() {
        return getPosition().y;
    }

    /**
     * @return The scaling / zoom of the camera per axis.
     */
    public Vector2f getScaleDimensions() {
        return scale;
    }

    /**
     * @return The scaling / zoom of the camera.
     */
    public float getScale() {
        // Return the average
        return (scale.x + scale.y) / 2;
    }

    /**
     * Zooms the camera in / scales the scene.
     *
     * @param scale The value to set the scaling.
     */
    public void setScale(float scale) {
        this.scale.x = scale;
        this.scale.y = scale;
        updateTransforms();
    }

    /**
     * @return The rotation of the camera by its origin.
     */
    public float getRotation() {
        return rot;
    }

    /**
     * Sets the camera rotation on its origin.
     *
     * @param rot The amount of rotation.
     */
    public void setRotation(float rot) {
        this.rot = rot;
        updateTransforms();
    }

    /**
     * @return The real world position of the camera.
     */
    public Vector2f getPosition() {
        return new Vector2f(-x, -y);
    }

    /**
     * Returns an in-game rectangle that represents where
     * the camera lies.
     */
    public Rectangle getBounds() {
        Rectangle camera = new Rectangle(getPositionX() - origin.x / scale.x, getPositionY() - origin.y / scale.y, getWidth(), getHeight());
        camera.rotate(getPositionX(), getPositionY(), -rot);
        return camera;
    }
}

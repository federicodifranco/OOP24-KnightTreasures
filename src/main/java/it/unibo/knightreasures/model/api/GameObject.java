package it.unibo.knightreasures.model.api;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 * Represents a game object in the Knight Treasures game.
 * Provides methods for rendering, state management, and animation handling.
 */
public interface GameObject {

    /**
     * Resets the state of the object.
     */
    void reset();

    /**
     * Draws the hitbox of the object.
     *
     * @param g          The graphics context used for rendering.
     * @param xLvlOffset The horizontal offset of the level.
     */
    void drawHitbox(Graphics g, int xLvlOffset);

    /**
     * Gets the object type identifier.
     *
     * @return The object type.
     */
    int getObjType();

    /**
     * Gets the hitbox of the object.
     *
     * @return The hitbox as a {@link Rectangle2D.Float}.
     */
    Rectangle2D.Float getHitbox();

    /**
     * Checks whether the object is currently active.
     *
     * @return {@code true} if the object is active, otherwise {@code false}.
     */
    boolean isActive();

    /**
     * Sets the active state of the object.
     *
     * @param active {@code true} to activate the object, {@code false} to deactivate it.
     */
    void setActive(boolean active);

    /**
     * Sets whether the object should animate.
     *
     * @param doAnimation {@code true} to enable animation, {@code false} to disable it.
     */
    void setDoAnimation(boolean doAnimation);

    /**
     * Gets the current animation index of the object.
     *
     * @return The animation index.
     */
    int getAniIndex();

    /**
     * Gets the X offset of the object.
     *
     * @return The X offset.
     */
    int getXOffset();

    /**
     * Gets the Y offset of the object.
     *
     * @return The Y offset.
     */
    int getYOffset();

    /**
     * Sets the animation index of the object.
     *
     * @param aniIndex The new animation index.
     */
    void setAniIndex(final int aniIndex);

    /**
     * Sets the Y offset of the object.
     *
     * @param yOffset The new Y offset.
     */
    void setYOffset(final int yOffset);

    /**
     * Sets the X offset of the object.
     *
     * @param xOffset The new X offset.
     */
    void setXOffset(final int xOffset);

    /**
     * Checks whether the object has animation enabled.
     *
     * @return {@code true} if the object is animating, otherwise {@code false}.
     */
    boolean getDoAnimation();

    /**
     * Sets the animation tick count for the object.
     *
     * @param aniTick The animation tick count.
     */
    void setAniTick(final int aniTick);

    /**
     * Gets the x-coordinate of the object.
     * 
     * @return The x-coordinate.
     */
    int getX();

    /**
     * Sets the x-coordinate of the object.
     * 
     * @param x The new x-coordinate.
     */
    void setX(final int x);

    /**
     * Gets the y-coordinate of the object.
     * 
     * @return The y-coordinate.
     */
    int getY();

    /**
     * Sets the y-coordinate of the object.
     * 
     * @param y The new y-coordinate.
     */
    void setY(final int y);
}

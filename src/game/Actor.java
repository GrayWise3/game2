package game;

import javafx.scene.Node;

/**
 * Actor is a superclass to every moving part of the game. It sets the minimum 
 * methods required for every object one may interact with in the game, such as the 
 * hook, fish, sharks, and player classes. 
 * @author Grayson Wise
 *
 */
public abstract class Actor {
    private double myX;
    private double myY;
    private int height;
    private int width;

    /**
     * Returns the X value of the Actor's location
     *
     * @return
     */
    public double getX () {
        return myX;
    }

    /**
     * Returns the Y value of the Actor's location
     *
     * @return
     */
    public double getY () {
        return myY;
    }

    /**
     * Sets the X value of the Actor's location
     *
     * @return
     */
    public void setX (double inputX) {
        myX = inputX;
    }

    /**
     * Sets the Y value of the Actor's location
     *
     * @return
     */
    public void setY (double inputY) {
        myY = inputY;
    }

    /**
     * Returns the width of the Actor
     *
     * @return
     */
    public int getWidth () {
        return width;
    }

    /**
     * Returns the height of the Actor
     *
     * @return
     */
    public int getHeight () {
        return height;
    }

    /**
     * Defined in each subclass
     *
     * @return
     */
    public abstract Node draw ();

}

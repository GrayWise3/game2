package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class Hook extends Actor {
    private static final double KEY_INPUT_SPEED = 10;
    private ImageView hookview;
    private Image hook;
    private int startX = 180;
    private int startY = 160;
    private double myX;
    private double myY;
    private int height = 40;
    private int width = 20;
    private boolean gameOverCondition = false;
    private boolean isIndestructable = false;

    /**
     * Creates a new Hook at given locations
     */
    public Hook () {
        myX = startX;
        myY = startY;
        init();
    }

    @Override
    public double getX () {
        return myX;
    }

    @Override
    public double getY () {
        return myY;
    }

    @Override
    public void setX (double input) {
        myX = input;
    }

    @Override
    public void setY (double input) {
        myY = input;
    }

    @Override
    public int getWidth () {
        return width;
    }

    @Override
    public int getHeight () {
        return height;
    }

    /**
     * Loads the image needed for the hook
     */
    public void init () {
        hook = new Image(getClass().getClassLoader().getResourceAsStream("hook.png"));
        hookview = new ImageView();
        hookview.setImage(hook);
        hookview.setFitHeight(height);
        hookview.setFitWidth(width);
    }

    /**
     * Draws the hook at the starting location
     */
    @Override
    public Node draw () {
        hookview.setX(myX);
        hookview.setY(myY);
        return hookview;
    }

    /**
     * Calls draw to update hook location
     *
     * @param elapsedTime
     */
    public void step (double elapsedTime) {
        draw();
    }

    /**
     * Sets what the hook does when certain keys are pressed
     *
     * @param code
     */
    public void control (KeyCode code) {
        if (gameOverCondition == false) {
            switch (code) {
                case RIGHT:
                    if (getX() + width < 750) {
                        setX(getX() + KEY_INPUT_SPEED);
                    }
                    break;
                case LEFT:
                    if (getX() > 180) {
                        setX(getX() - KEY_INPUT_SPEED);
                    }
                    break;
                case UP:
                    if (getY() > 160) {
                        setY(getY() - KEY_INPUT_SPEED);
                    }
                    break;
                case DOWN:
                    if (getY() < 500 - height) {
                        setY(getY() + KEY_INPUT_SPEED);
                    }
                    break;
                case G:
                    isIndestructable = true;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Checks if hook is colliding with passed in Actor
     *
     * @param a
     * @return
     */
    public boolean isColliding (Actor a) {
        if (myX >= a.getX() &&
            myX <= a.getX() + a.getWidth() - 10 &&
            myY >= a.getY() - 20 &&
            myY <= a.getY() + a.getHeight() - 30) {
            return true;
        }
        return false;
    }

    /**
     * Sets the hook to recognize when the game ends
     */
    public void gameOver () {
        gameOverCondition = true;
    }

    /**
     * Resets the hook to original location
     */
    public void reset () {
        gameOverCondition = false;
        myX = startX;
        myY = startY;
        draw();
    }
    
    /**
     * Returns the value of the boolean isIndestructable
     * @return
     */
    public boolean checkDestructable(){
        return isIndestructable;
    }
}

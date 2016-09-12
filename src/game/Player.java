package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class Player extends Actor {
    private static final double KEY_INPUT_SPEED = 10;
    private ImageView myPlayer;
    private Image fisherman;
    private double myX;
    private double myY;
    private int height = 183;
    private int width = 258;
    private int screenWidth = 800;
    private boolean gameOverCondition = false;

    /**
     * Creates a new Player at the starting x and y locations
     */
    public Player () {
        myX = 0;
        myY = 0;
        init();
    }

    /**
     * Loads the image for the fisherman
     */
    public void init () {
        fisherman = new Image(getClass().getClassLoader().getResourceAsStream("fisherman.png"));
        myPlayer = new ImageView();
        myPlayer.setImage(fisherman);
        myPlayer.setFitHeight(height);
        myPlayer.setFitWidth(width);
        draw();
    }

    /**
     * Draws the Player at the starting location
     */
    @Override
    public Node draw () {
        myPlayer.setX(myX);
        myPlayer.setY(myY);
        return myPlayer;
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
    public void setX (double i) {
        myX = i;
    }

    @Override
    public void setY (double i) {
        myY = i;
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
     * Controls Player's movement based on key inputs
     *
     * @param code
     */
    public void control (KeyCode code) {
        if (gameOverCondition == false) {
            switch (code) {
                case RIGHT:
                    if (myPlayer.getX() + width < screenWidth) {
                        myPlayer.setX(myPlayer.getX() + KEY_INPUT_SPEED);
                    }
                    break;
                case LEFT:
                    if (myPlayer.getX() > 0) {
                        myPlayer.setX(myPlayer.getX() - KEY_INPUT_SPEED);
                    }
                    break;
                default:
            }
        }
    }

    /**
     * Sets the player to know it is game over
     */
    public void gameOver () {
        gameOverCondition = true;
    }

    /**
     * Resets the player to its original position
     */
    public void reset () {
        gameOverCondition = false;
        setX(0);
        setY(0);
        draw();
    }

    public void step (double elapsedTime) {
    }
}

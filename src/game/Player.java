package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Player extends Actor{
    private static final double KEY_INPUT_SPEED = 10;
    private ImageView myPlayer;
    private Image fisherman;
    private double myX;
    private double myY;
    private int height = 183;
    private int width = 258;
    private int screenWidth = 800;
    private boolean gameOverCondition = false;
    
    public Player(){
    	myX = 0;
    	myY = 0;
    }
    
    public Node draw(){
    fisherman = new Image(getClass().getClassLoader().getResourceAsStream("fisherman.png"));
    myPlayer = new ImageView();
    myPlayer.setImage(fisherman);
    myPlayer.setFitHeight(height);
    myPlayer.setFitWidth(width);
    myPlayer.setX(myX);
    myPlayer.setY(myY);
    return myPlayer;
    }

    public double getX(){
    	return myX;
    }
    
    public double getY(){
    	return myY;
    }
    
    public void setX(double i){
    	myX = i;
    }
    
    public void setY(double i){
    	myY = i;
    }
    
    public int getWidth () {
        return width;
    }
    
    public int getHeight () {
        return height;
    }

    public void move(KeyCode code) {
        if(gameOverCondition == false)
        {
        switch (code) {
        case RIGHT:
        	if(myPlayer.getX()+width < screenWidth)
                myPlayer.setX(myPlayer.getX() + KEY_INPUT_SPEED);
            break;
        case LEFT:
        	if(myPlayer.getX() > 0)
        	myPlayer.setX(myPlayer.getX() - KEY_INPUT_SPEED);
            break;
        default:
		} 
        }
    }

    public void gameOver(){
        gameOverCondition = true;
    }
    
    public void reset(){
        gameOverCondition = false;
        setX(0);
        setY(0);
        draw();
    }

    public void step (double elapsedTime) {}
}
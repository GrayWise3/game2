package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Player extends Actor{
    private static final double KEY_INPUT_SPEED = 10;
    private ImageView myPlayer;
    private Image fisherman;
    private double x;
    private double y;
    private double velocity;
    private int height = 183;
    private int width = 258;
    private int screenWidth = 800;
    private int screenHeight = 500;
    private boolean gameOverCond = false;
    
    public Player(){
    	x = 0;
    	y = 0;
    }
    
    public Node draw(){
    fisherman = new Image("/images/fisherman.png");
    myPlayer = new ImageView();
    myPlayer.setImage(fisherman);
    myPlayer.setFitHeight(height);
    myPlayer.setFitWidth(width);
    myPlayer.setX(x);
    myPlayer.setY(y);
    return myPlayer;
    }

    public double getX(){
    	return x;
    }
    
    public double getY(){
    	return y;
    }
    
    public void setX(double i){
    	x = i;
    }
    
    public void setY(double i){
    	y = i;
    }
    
    public void setVelocity(double d){
    	
    }
    
    public void step(double elapsedTime){
    	
    }

    public void move(KeyCode code) {
        if(gameOverCond == false)
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
        case UP:    	
            break;
        case DOWN:
            break;
        case SPACE:
            
            break;
        default:
		} 
        }
    }

    public int getWidth () {
        return width;
    }
    
    public int getHeight () {
        return height;
    }

    public void gameOver(){
        gameOverCond = true;
    }
    
    public void reset(){
        gameOverCond = false;
        setX(0);
        setY(0);
        draw();
    }

}

package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Hook extends Actor{
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
    
    public Hook(){
        myX = (double) startX;
        myY = (double) startY;
    	init();
    }
    
    public double getX(){
        return myX;
    }
    
    public double getY(){
        return myY;
    }
    
    public void setX(double input){
        myX = input;
    }
    
    public void setY(double input){
        myY = input;
    }

    public int getWidth () {
        return width;
    }
            
    public int getHeight () {
        return height;
    }
    
    public void init(){
        hook = new Image(getClass().getClassLoader().getResourceAsStream("hook.png"));
        hookview = new ImageView();
        hookview.setImage(hook);
        hookview.setFitHeight(height);
        hookview.setFitWidth(width);
    }
    
    public Node draw(){
        hookview.setX(myX);
        hookview.setY(myY);
        return hookview;
    }
    
    public void step(double elapsedTime){
        draw();
    }

    public void control(KeyCode code) {
        if(gameOverCondition == false)
        {
	switch (code) {
        case RIGHT:
                if(getX()+width < 750)
        	setX(getX() + KEY_INPUT_SPEED);
                break;
        case LEFT:
        	if(getX() > 180)
        	{
        	setX(getX() - KEY_INPUT_SPEED);
        	}
            break;
        case UP:    
        	if(getY() > 160)
        	{
        	setY(getY() - KEY_INPUT_SPEED);
        	}
            break;
        case DOWN:
            if(getY() < 500-height)
        	{
        	setY(getY() + KEY_INPUT_SPEED);
        	}
            break;
            default:
		} 
        }
    }

    public boolean isColliding(Actor a){
        if(myX >= a.getX() && myX <= a.getX()+a.getWidth()-10 && myY >= a.getY()-20 && myY <= a.getY()+a.getHeight()-30){
            return true;
        }
        return false;
    }

    public void gameOver() {
        gameOverCondition = true;
    }
    
    public void reset(){
        gameOverCondition = false;
        myX = startX;
        myY = startY;
        draw();
    }
}
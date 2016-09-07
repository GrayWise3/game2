package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Hook extends Actor{
    private static final double KEY_INPUT_SPEED = 10;
    private ImageView hookview;
    private Image hook;
    private double x;
    private double y;
    private int height = 40;
    private int width = 20;
    private boolean gameOverCond = false;
    
    public Hook(){
    	x = 180;
    	y = 160;
    	init();
    }
    
    public void init(){
        hook = new Image("/images/hook.png");
        hookview = new ImageView();
        hookview.setImage(hook);
        hookview.setFitHeight(height);
        hookview.setFitWidth(width);
    }
    
    public Node draw(){
        hookview.setX(x);
        hookview.setY(y);
        return hookview;
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
    
    public void step(double elapsedTime){
        draw();
    }

    public void move(KeyCode code) {
        if(gameOverCond == false)
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
	

    public int getWidth () {
        return width;
    }
	    
    public int getHeight () {
        return height;
    }

    public boolean isColliding(Actor a){
        if(x >= a.getX() && x <= a.getX()+a.getWidth()-10 && y >= a.getY()-20 && y <= a.getY()+a.getHeight()-30){
            return true;
        }
            return false;
	}

    public void gameOver() {
        gameOverCond = true;
    }
    
    public void reset(){
        gameOverCond = false;
        x = 180;
        y = 160;
        draw();
    }

}
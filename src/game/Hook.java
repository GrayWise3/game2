package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Hook extends Actor{
    private static final double KEY_INPUT_SPEED = 15;
    private ImageView hookview;
    private Image hook;
    private double x;
    private double y;
    private int height = 40;
    private int width = 20;
    
    public Hook(){
    	x = 180;
    	y = 160;
    }
    
    public Node draw(){
    hook = new Image("/images/hook.png");
    hookview = new ImageView();
    hookview.setImage(hook);
    hookview.setFitHeight(height);
    hookview.setFitWidth(width);
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
    	
    }

    public void move(KeyCode code) {
	switch (code) {
        case RIGHT:
        	hookview.setX(hookview.getX() + KEY_INPUT_SPEED);
        	setX(getX() + KEY_INPUT_SPEED);
                break;
        case LEFT:
        	if(hookview.getX() > 180)
        	{
        	hookview.setX(hookview.getX() - KEY_INPUT_SPEED);
        	setX(getX() - KEY_INPUT_SPEED);
        	}
            break;
        case UP:    
        	if(hookview.getY() > 160)
        	{
        	hookview.setY(hookview.getY() - KEY_INPUT_SPEED);
        	setY(getY() - KEY_INPUT_SPEED);
        	}
            break;
        case DOWN:
            if(hookview.getY() < 500-height)
        	{
        	hookview.setY(hookview.getY() + KEY_INPUT_SPEED);
        	setY(getY() + KEY_INPUT_SPEED);
        	//System.out.println(y);
        	}
            break;
        case SPACE:
            hookview.setY(160);
            break;
        default:
		} 
	}
	
	public int getWidth () {
	        return width;
	    }
	    
	public int getHeight () {
	        return height;
	    }

	//STILL WORKING ON THIS
	public boolean isColliding(Actor a){

            if(x >= a.getX() && x <= a.getX()+a.getWidth() && y >= a.getY() && y <= a.getY()+a.getHeight()){
                //System.out.println("colliding with fish");
                return true;
            }
            //System.out.println("not colliding with fish");
            return false;
	}

}
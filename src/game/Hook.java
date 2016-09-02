package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Hook extends Actor{
	private static final double KEY_INPUT_SPEED = 15;
	private ImageView hookview;
    private Image hook;
    private int x;
    private int y;
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

    public int getX(){
    	return x;
    }
    
    public int getY(){
    	return y;
    }
    
    public void setX(int i){
    	x = i;
    }
    
    public void setY(int i){
    	y = i;
    }
    
    public void step(double elapsedTime){
    	
    }

	public void move(KeyCode code) {
		switch (code) {
        case RIGHT:
        	hookview.setX(hookview.getX() + KEY_INPUT_SPEED);
            break;
        case LEFT:
        	if(hookview.getX() > 180)
        	hookview.setX(hookview.getX() - KEY_INPUT_SPEED);
            break;
        case UP:    
        	if(hookview.getY() > 160)
        	hookview.setY(hookview.getY() - KEY_INPUT_SPEED);
            break;
        case DOWN:
        	if(hookview.getY() < 500-height)
        	hookview.setY(hookview.getY() + KEY_INPUT_SPEED);
            break;
        case SPACE:
        	hookview.setY(160);
        	break;
        case S:
            break;
        case B:
        	break;
        default:
		} 
	}

}
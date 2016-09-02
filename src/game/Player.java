package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Player extends Actor{
	private static final double KEY_INPUT_SPEED = 15;
	private ImageView player;
    private Image fisherman;
    private int x;
    private int y;
    private int height = 183;
    private int width = 258;
    
    public Player(){
    	x = 0;
    	y = 0;
    }
    
    public Node draw(){
    fisherman = new Image("/images/fisherman.png");
    player = new ImageView();
    player.setImage(fisherman);
    player.setFitHeight(height);
    player.setFitWidth(width);
    player.setX(x);
    player.setY(y);
    return player;
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
        	player.setX(player.getX() + KEY_INPUT_SPEED);
            break;
        case LEFT:
        	if(player.getX() != 0)
        	player.setX(player.getX() - KEY_INPUT_SPEED);
            break;
        case UP:    	
            break;
        case DOWN:
            break;
        case SPACE:
        	
        	break;
        case S:
        	
            break;
        case B:
        	break;
        default:
		} 
	}

}

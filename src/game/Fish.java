package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Fish extends Actor {
	private ImageView fishview;
    private Image fish;
    private double x;
    private double y;
    private double fishXSpeed = 40;
    private double fishYSpeed = 0;
    private int height = 42;
    private int width = 83;
    
    public Fish(){
    	x = 300;
    	y = 300;
    	init();
    }
    
    public void init() {
        
        fish = new Image("/images/fish.gif");
        fishview = new ImageView();
        fishview.setImage(fish);
        fishview.setFitHeight(height);
        fishview.setFitWidth(width);
    }
	
	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setX(double i) {
		x = i;
	}

	@Override
	public void setY(double i) {
		y = i;
	}

	public void move(KeyCode code) {
		switch(code){
		case S:
		    fishXSpeed = fishXSpeed/2;
		    break;
		case F:
		    fishXSpeed = fishXSpeed*2;
		    break;
		case SPACE:
		    
		    break;
		default:
		}
	}

	@Override
	public void step(double elapsedTime) {
		setX(getX() - fishXSpeed*elapsedTime);
		setY(getY() - fishYSpeed*elapsedTime);
		if(getX()+width <= 0)
			setX(600);
		draw();
	}

	@Override
	public Node draw() {
	   
	    fishview.setX(x);
	    fishview.setY(y);
	    return fishview;
	}
	
	public int getWidth () {
	    return width;
	    }
	    
	public int getHeight () {
	    return height;
	    }
	
	//STILL WORKING ON THIS
	public void caught(Actor a) {
//            setSpeed(0);
//            setY(160);
//            fishview.setY(y);
    }
	public void setSpeed(int i){
	    fishXSpeed = i;
	}
	
	public void setYSpeed(int i){
            fishYSpeed = i;
        }

}

package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Fish extends Actor {
    private int screenWidth = 800;
    private int screenHeight = 500;
    private ImageView fishview;
    private Image fish;
    private double myX;
    private double myY;
    private double fishXSpeed;
    private double fishYSpeed = 0;
    private int height = 42;
    private int width = 83;
    private int fishDir = 1;
    
    public Fish(double d, double e){
    	myX = d;
    	myY = e;
    	fishXSpeed = ((Math.random()+2)*30)*fishDir;
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
		return myX;
	}

	@Override
	public double getY() {
		return myY;
	}

	@Override
	public void setX(double i) {
		myX = i;
	}

	@Override
	public void setY(double i) {
		myY = i;
	}

	public void move(KeyCode code) {
		switch(code){
		case S:
		    fishXSpeed = fishXSpeed/2;
		    break;
		case F:
		    fishXSpeed = fishXSpeed*2;
		    break;
		case D:
		    fishDir = -1;
		    fishXSpeed = ((Math.random()+2)*30)*fishDir;
		    break;
		default:
		}
	}

	@Override
	public void step(double elapsedTime) {
		setX(getX() - fishXSpeed*elapsedTime);
		setY(getY() - fishYSpeed*elapsedTime);
		if(getX()+width <= 0)
			setX(screenWidth);
		if(getY() <= 160){
		    setY(160);
		    
		}		
		draw();	
	}

	@Override
	public Node draw() {
	   
	    fishview.setX(myX);
	    fishview.setY(myY);
	    return fishview;
	}
	
	public int getWidth () {
	    return width;
	    }
	    
	public int getHeight () {
	    return height;
	}
	
	public void setSpeed(int i){
	    fishXSpeed = i;
	}
	
	public void setYSpeed(int i){
            fishYSpeed = i;
        }

}

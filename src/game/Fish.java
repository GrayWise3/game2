package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Fish extends Actor {
    private int screenWidth = 800;
    private ImageView fishview;
    private Image fish;
    private double myX;
    private double myY;
    private double fishXSpeed;
    private double fishYSpeed = 0;
    private int height = 42;
    private int width = 83;
    
    public Fish(double inX, double inY){
    	myX = inX;
    	myY = inY;
    	fishXSpeed = ((Math.random()+2)*30);
    	init();
    }
    
    public void init() {
        fish = new Image(getClass().getClassLoader().getResourceAsStream("fish.gif"));
        fishview = new ImageView();
        fishview.setImage(fish);
        fishview.setFitHeight(height);
        fishview.setFitWidth(width);
    }
	
    public double getX() {
        return myX;
	}

    public double getY() {
        return myY;
	}

    public void setX(double i){
        myX = i;
	}

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
            default:
            }
	}

    public void step(double elapsedTime) {
        setX(getX() - fishXSpeed*elapsedTime);
        setY(getY() - fishYSpeed*elapsedTime);
        if(getX()+width <= 0){
            setX(screenWidth);
            }
        if(getY() <= 160){
            setY(160);
            }		
        draw();	
	}

    public Node draw(){
        fishview.setX(myX);
        fishview.setY(myY);
        return fishview;
	}
	
    public int getWidth(){
        return width;
	    }
	    
    public int getHeight(){
        return height;
	}
	
    public void setSpeed(int i){
        fishXSpeed = i;
	}
	
    public void setYSpeed(int i){
        fishYSpeed = i;
        }
	
    public void reset(){
        fishXSpeed = ((Math.random()+2)*30);
        draw();
        }
}
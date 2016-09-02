package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Fish extends Actor {
	private ImageView fishview;
    private Image fish;
    private int x;
    private int y;
    private double fishSpeed = 1;
    private int height = 42;
    private int width = 83;
    
    public Fish(){
    	x = 300;
    	y = 300;
    }
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int i) {
		x = i;
	}

	@Override
	public void setY(int i) {
		y = i;
	}

	public void move(KeyCode code) {
		switch(code){
		case S:
			fishSpeed = fishSpeed/2;
			break;
		case F:
			fishSpeed = fishSpeed*2;
			break;
		default:
		}
	}

	@Override
	public void step(double elapsedTime) {
		fishview.setX(fishview.getX() - fishSpeed);
		if(fishview.getX()+width <= 0)
			fishview.setX(600);
	}

	@Override
	public Node draw() {
	    fish = new Image("/images/fish.gif");
	    fishview = new ImageView();
	    fishview.setImage(fish);
	    fishview.setFitHeight(height);
	    fishview.setFitWidth(width);
	    fishview.setX(x);
	    fishview.setY(y);
	    return fishview;
	}

}

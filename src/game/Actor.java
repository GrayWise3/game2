package game;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public abstract class Actor {
    
	public abstract Node draw();
	public abstract double getX();
	public abstract double getY();
	public abstract void setX(double i);
	public abstract void setY(double i);
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract void step(double elapsedTime);
}

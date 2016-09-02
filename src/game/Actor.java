package game;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public abstract class Actor {
	public abstract Node draw();
	public abstract int getX();
	public abstract int getY();
	public abstract void setX(int i);
	public abstract void setY(int i);
	public abstract void step(double elapsedTime);
}

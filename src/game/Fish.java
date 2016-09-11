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
    
    /**
     * Creates a new Fish at the given location
     * @param inX
     * @param inY
     */
    public Fish(double inX, double inY){
        myX = inX;
        myY = inY;
        fishXSpeed = ((Math.random()+2)*30);
        init();
    }
    
    /**
     * Loads the image needed for a fish
     */
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
    
    public int getWidth(){
        return width;
        }
        
    public int getHeight(){
        return height;
        }

    /**
     * Handles the movement of a fish that is affected by 
     * user input (cheat keys)
     * @param code
     */
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

    /**
     * Updates location of the fish based on elapsed time and speed
     * @param elapsedTime
     */
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

    /**
     * Draws the fish at a set location
     */
    public Node draw(){
        fishview.setX(myX);
        fishview.setY(myY);
        return fishview;
        }
      
    /**
     * Sets the speed of the fish in the X-direction
     * @param i
     */
    public void setXSpeed(int i){
        fishXSpeed = i;
        }
        
    /**
     * Sets the speed of the fish in the Y-direction
     * @param i
     */
    public void setYSpeed(int i){
        fishYSpeed = i;
        }
        
    /**
     * Resets the fish to its original speed
     */
    public void reset(){
        fishXSpeed = ((Math.random()+2)*30);
        draw();
        }
}
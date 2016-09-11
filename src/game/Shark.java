package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Shark extends Actor {
    private int screenWidth = 800;
    private ImageView sharkview;
    private Image shark;
    private double myX;
    private double myY;
    private double sharkXSpeed;
    private double sharkYSpeed = 0;
    private int height = 86;
    private int width = 133;

    /**
     * Creates a new Shark at given location and sets a random speed
     * @param inX
     * @param inY
     */
    public Shark(double inX, double inY){
        myX = inX;
        myY = inY;
        sharkXSpeed = ((Math.random()+2)*30);
        init();
    }
    
    /**
     * Loads the image needed for a shark
     */
    public void init() {
        
        shark = new Image(getClass().getClassLoader().getResourceAsStream("shark.gif"));
        sharkview = new ImageView();
        sharkview.setImage(shark);
        sharkview.setFitHeight(height);
        sharkview.setFitWidth(width);
    }
        
    public double getX() {
        return myX;
    }

    public double getY() {
        return myY;
    }

    public void setX(double inputX) {
        myX = inputX;
    }

    public void setY(double inputY) {
        myY = inputY;
    }
        
    public int getWidth(){
        return width;
    }
            
    public int getHeight(){
        return height;
    }

    /**
     * Handles user-input for movement of the Shark (cheat keys)
     * @param code
     */
    public void move(KeyCode code) {
        switch(code){
            case S:
                sharkXSpeed = sharkXSpeed/2;
                break;
            case F:
                sharkXSpeed = sharkXSpeed*2;
                break;
            default:
                break;
            }
    }

    /**
     * Updates the sharks position based on elapsed time and the speed
     * @param elapsedTime
     */
    public void step(double elapsedTime){
        setX(getX() - sharkXSpeed*elapsedTime);
        setY(getY() - sharkYSpeed*elapsedTime);
        if(getX()+width <= 0)
            {
            setX(screenWidth);
            resize();
            }
        if(getY() <= 160){
            setY(160);             
            }               
        draw(); 
        }

    /**
     * Draws the shark at a set location
     */
    public Node draw() {          
        sharkview.setX(myX);
        sharkview.setY(myY);
        return sharkview;
        }
    
    /**
     * Sets the speed of the shark in the x-direction
     * @param i
     */
    public void setXSpeed(int i){
        sharkXSpeed = i;
        }
        
    /**
     * Sets the speed of the shark in the y-direction
     * @param i
     */
    public void setYSpeed(int i){
        sharkYSpeed = i;
        }
        
    /**
     * Resets the sharks speed to the original speed
     */
    public void reset(){
        sharkXSpeed = ((Math.random()+2)*30);
        draw();
        }
       
    /**
     * Shrinks the shark to 1.5x its size
     */
    public void shrink(){
        if(height > 45){
            height = (int) (height/1.5);
            width = (int) (width/1.5);
            sharkview.setFitHeight(height);
            sharkview.setFitWidth(width);
        }
        else{}
      }
      
    /**
     * Returns the shark to its original size
     */
    public void resize(){
          height = 86;
          width = 133;
          sharkview.setFitHeight(height);
          sharkview.setFitWidth(width); 
      }
}
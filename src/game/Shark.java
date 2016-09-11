package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Shark extends Actor {
    private int screenWidth = 800;
    private int screenHeight = 500;
    private ImageView sharkview;
    private Image shark;
    private double myX;
    private double myY;
    private double sharkXSpeed;
    private double sharkYSpeed = 0;
    private int height = 86;
    private int width = 133;
    private boolean isFrozen;
    private int counter;
    private int sharkDir = 1;
    
    public Shark(double d, double e){
        myX = d;
        myY = e;
        sharkXSpeed = ((Math.random()+2)*30)*sharkDir;
        init();
    }
    
    public void init() {
        
        shark = new Image(getClass().getClassLoader().getResourceAsStream("shark.gif"));
        sharkview = new ImageView();
        sharkview.setImage(shark);
        sharkview.setFitHeight(height);
        sharkview.setFitWidth(width);
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
                    sharkXSpeed = sharkXSpeed/2;
                    break;
                case F:
                    sharkXSpeed = sharkXSpeed*2;
                    break;
                case D:
                    sharkDir = -1;
                    sharkXSpeed = ((Math.random()+2)*30)*sharkDir;
                    break;
                default:
                }
        }

        @Override
        public void step(double elapsedTime) {
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

        @Override
        public Node draw() {
           
            sharkview.setX(myX);
            sharkview.setY(myY);
            return sharkview;
        }
        
        public int getWidth () {
            return width;
            }
            
        public int getHeight () {
            return height;
        }
        
        public void setSpeed(int i){
            sharkXSpeed = i;
        }
        
        public void setYSpeed(int i){
            sharkYSpeed = i;
        }
        
        public void reset(){
            sharkXSpeed = ((Math.random()+2)*30)*sharkDir;
            draw();
        }
       
        
      public void shrink(){
          if(height > 45){
          height = (int) (height/1.5);
          width = (int) (width/1.5);
          sharkview.setFitHeight(height);
          sharkview.setFitWidth(width);
          }
          else{}
      }
      
      public void resize(){
          height = 86;
          width = 133;
          sharkview.setFitHeight(height);
          sharkview.setFitWidth(width); 
      }

}


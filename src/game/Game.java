package game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

class Game {
    public static final String TITLE = "Hook Line Sinker";
    public static final int KEY_INPUT_SPEED = 5;
    private Group root;
    private Scene myScene;
    private Player player = new Player();
    private Fish fish = new Fish();
    private Hook hook = new Hook();
    public String getTitle () {
        return TITLE;
    }
    public Scene init (int width, int height) {
        root = new Group();
        myScene = new Scene(root, width, height, Color.WHITE);
        // respond to input
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        draw();
        myScene.setRoot(root);
        return myScene;
    }


	public void draw(){ 
        root.getChildren().addAll(player.draw());
        root.getChildren().addAll(fish.draw());
        root.getChildren().addAll(hook.draw());
    }

    public void step (double elapsedTime) {
    	fish.step(elapsedTime);

    }
    
    private void handleKeyInput(KeyCode code) {
        //System.out.println(code.getName());
        if(code.getName().equals("Space")){
            //System.out.println("in if");
            if(hook.isColliding(fish)){
                //System.out.println("Reeling in");
                fish.setSpeed(0);
                hook.setY(160);
                fish.setYSpeed(100);
            }
        }
        player.move(code);
        hook.move(code);
        fish.move(code);
        
    }
}

package game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class Game {
    public static final String TITLE = "Hook Line Sinker";
    public static final int KEY_INPUT_SPEED = 10;
    private Group root;
    private Scene myScene;
    private Player player = new Player();
    private ArrayList<Integer> exes = new ArrayList<Integer>();
    private ArrayList<Integer> whys = new ArrayList<Integer>();
    private ArrayList<Fish> fish = new ArrayList<Fish>();
    private Hook hook = new Hook();
    private Image background;
    private ImageView bgView;
    private Text score;
    private int scoreInt = 0;
    private Text time;
    private double keepTime = 5;
    boolean isOver = false;
    Text gameOverText = new Text("Game Over");
    
    public String getTitle () {
        return TITLE;
    }
    public Scene init (int width, int height) {
        root = new Group();
        score = new Text("Score: " + scoreInt);
        score.setFont(Font.font ("Verdana", 20));
        score.setX(650);
        score.setY(30);
        time = new Text("Time: " + keepTime);
        time.setFont(Font.font ("Verdana", 20));
        time.setX(650);
        time.setY(60);
        gameOverText.setFont(Font.font ("Verdana", 100));
        gameOverText.setX(125);
        gameOverText.setY(400);
        background = new Image("/images/background.jpg");
        bgView = new ImageView();
        bgView.setImage(background);
        bgView.setFitHeight(600);
        bgView.setY(-50);
        bgView.preserveRatioProperty();
        myScene = new Scene(root, width, height, Color.WHITE);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        exes.addAll(Arrays.asList(400, 500, 600, 700, 800, 900, 1000, 1100, 
                                  1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 
                                  2000, 2100, 2200, 2300, 2400, 2500, 2600, 2700, 2800, 2900, 3000));
        whys.addAll(Arrays.asList(300, 250, 400, 350, 200, 400,  300,  200,  
                                  300,  350,  250,  400,  350,  200,  400,  300, 
                                  350, 300, 250, 400, 350, 200, 400,  300,  200,  300,  350,  250));
        for(int i = 0; i < exes.size(); i++)
        {
            fish.add(new Fish(exes.get(i), whys.get(i)));
        }
        draw();
        myScene.setRoot(root);
        return myScene;
    }


    public void draw(){ 
        root.getChildren().addAll(bgView);
        root.getChildren().addAll(score);
        root.getChildren().addAll(time);
        root.getChildren().addAll(player.draw());
        for(int i = 0; i < fish.size(); i++)
        {
            root.getChildren().addAll(fish.get(i).draw());
        }
        root.getChildren().addAll(hook.draw());
    }

    public void step (double elapsedTime) {
    	for(int i = 0; i < fish.size(); i++)
    	{
            fish.get(i).step(elapsedTime);
    	}
    	hook.step(elapsedTime);
    	//change time
    	keepTime = keepTime - elapsedTime;
    	time.setText("Time: " + (int)keepTime);
    	if((int)keepTime == 0){
    	    gameOver();
    	    keepTime = 0;
    	}
    }
    
    private void handleKeyInput(KeyCode code) {
        if(code.getName().equals("Space")){
            for(int i = 0; i < fish.size(); i++)
            {
            if(hook.isColliding(fish.get(i))){
                hook.setY(160);
                fish.get(i).setSpeed(0);  
                fish.get(i).setYSpeed(5000);
                scoreInt += 5;
                score.setText(("Score: " + scoreInt));
            }
            }
        hook.setY(160);
        }
        player.move(code);
        hook.move(code);
        for(int i = 0; i < fish.size(); i++)
        {
            fish.get(i).move(code);
        }
        
    }
    
    public void gameOver(){
        if(isOver == false){
        root.getChildren().addAll(gameOverText);
        isOver = true;
        for(int i = 0; i < fish.size(); i++)
        {
            fish.get(i).setSpeed(0);
            hook.gameOver();
            player.gameOver();
        }
        }
    }
}

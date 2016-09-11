package game;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class Game {
    private static final String TITLE = "Hook Line Sinker";
    private Group root;
    private Scene myScene;
    private Player player = new Player();
    private ArrayList<Integer> exes = new ArrayList<Integer>();
    private ArrayList<Integer> whys = new ArrayList<Integer>();
    private ArrayList<Fish> fish = new ArrayList<Fish>();
    private ArrayList<Integer> sharkX = new ArrayList<Integer>();
    private ArrayList<Integer> sharkY = new ArrayList<Integer>();
    private ArrayList<Shark> sharks = new ArrayList<Shark>();
    private Hook hook = new Hook();
    private Image background;
    private ImageView bgView;
    private Text score;
    private Text wonGameText;
    private Image mainScreen;
    private boolean splash = true;
    private ImageView mainView;
    private int scoreInt = 0;
    private Text time;
    private boolean levelOne = false;
    private double START_TIME = 100;
    private double keepTime = START_TIME;
    private boolean isOver = false;
    private boolean didWin = false;
    private boolean levelTwo = false;
    private Text gameOverText = new Text("Game Over");
    private Text youWinText = new Text("Level Complete");
    private Button tryAgain = new Button("Try Again");
    private Button nextLevel = new Button("Level 2");
    private Button startGame = new Button("Start Game");
    private Button wonGame = new Button("You Won!");
    private int count = 0;
    
    
    public Game(int width, int height) {
        root = new Group();
        myScene = new Scene(root, width, height, Color.WHITE);
    }
    public String getTitle () {
        return TITLE;
    }

    public Scene init () {
        root = new Group();
        root.getChildren().clear();
        score = new Text("Score: " + scoreInt);
        wonGameText = new Text();
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
        wonGameText.setFont(Font.font ("Verdana", 60));
        wonGameText.setX(100);
        wonGameText.setY(400);
        tryAgain.setLayoutX(360);
        tryAgain.setLayoutY(425);
        tryAgain.setOnMouseClicked(e -> restart());
        nextLevel.setLayoutX(360);
        nextLevel.setOnMouseClicked(e -> levelTwo());
        nextLevel.setLayoutY(425);
        startGame.setLayoutX(360);
        startGame.setOnMouseClicked(e -> restart());
        startGame.setLayoutY(425);
        youWinText.setFont(Font.font ("Verdana", 70));
        youWinText.setX(125);
        youWinText.setY(400);
        background = new Image(getClass().getClassLoader().getResourceAsStream("background.jpg"));
        bgView = new ImageView();
        bgView.setImage(background);
        bgView.setFitHeight(600);
        bgView.setY(-50);
        bgView.preserveRatioProperty();
        mainScreen = new Image(getClass().getClassLoader().getResourceAsStream("mainscreen.png"));
        mainView = new ImageView();
        mainView.setImage(mainScreen);
        wonGame.setLayoutX(360);
        wonGame.setLayoutY(425);
        wonGame.setOnMouseClicked(e -> splashScreen());
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        if(splash && !levelOne && !levelTwo){
            splashScreen();
            }
        if(!splash && levelOne && !levelTwo){
            levelOne();
            }
        if(!splash && !levelOne && levelTwo){
            levelTwo();
            }
        myScene.setRoot(root);
        return myScene;
    }
    
    private void splashScreen () {
        root.getChildren().addAll(mainView);
        root.getChildren().addAll(startGame);
        //add instructions
        Image inst = new Image(getClass().getClassLoader().getResourceAsStream("instructions.png"));
        ImageView instructions = new ImageView(inst);
        instructions.setX(500);
        instructions.setY(225);
        instructions.setFitHeight(231);
        instructions.setFitWidth(227);
        root.getChildren().add(instructions);
    }
    private void levelTwo(){
        splash = false;
        levelOne = false;
        levelTwo = true;
        restart();  
        draw();
        splash = false;
        levelOne = false;
        levelTwo = true;
        addFish();
        
        sharkX.addAll(Arrays.asList(400, 700, 1200, 1500, 2000));
        sharkY.addAll(Arrays.asList(400, 300, 200, 250, 350));
        for(int i = 0; i < sharkX.size(); i++)
        {
            sharks.add(new Shark(sharkX.get(i), sharkY.get(i)));
        }
        for(int i = 0; i < sharks.size(); i++)
        {
            root.getChildren().addAll(sharks.get(i).draw());

        }
        root.getChildren().addAll(hook.draw());

    }
    private void addFish () {
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
        for(int i = 0; i < fish.size(); i++)
        {
            root.getChildren().addAll(fish.get(i).draw());
        }
    }
    private void restart() {
        root.getChildren().clear();
        fish.clear();
        exes.clear();
        whys.clear();
        sharks.clear();
        sharkX.clear();
        sharkY.clear();
        hook.reset();
        player.reset();
        keepTime = START_TIME;
        if(isOver == true){
            scoreInt = 0;
        }
        isOver = false;
        didWin = false;
        splash = false;
        levelOne = true;
        init();
        
    }
    
    public void levelOne(){
        
        splash = false;
        levelOne = true;
        draw();
        addFish();
        root.getChildren().addAll(hook.draw());

    }
    
    public void draw(){ 
        root.getChildren().addAll(bgView);
        root.getChildren().addAll(score);
        root.getChildren().addAll(time);
        root.getChildren().addAll(player.draw());
    }

    public void step (double elapsedTime) {
    	for(int i = 0; i < fish.size(); i++)
    	{
            fish.get(i).step(elapsedTime);
    	}
    	for(int i = 0; i < sharks.size(); i++)
        {
            sharks.get(i).step(elapsedTime);
        }
    	hook.step(elapsedTime);
        score.setText("Score: " + scoreInt);
    	updateTime(elapsedTime);
    	youWin();
    	if((int)keepTime == 0){
    	    gameOver();
    	}
    	
    	for(int i = 0; i < sharks.size(); i++)
        {
            if(hook.isColliding(sharks.get(i))){
                gameOver();
            }
        }
    }
    
    private void updateTime (double elapsedTime) {
        if(didWin==false && isOver == false && splash == false)
        {
        keepTime = keepTime - elapsedTime;
        time.setText("Time: " + (int)keepTime);
        }
        else
        {
            time.setText("Time: " + (int)keepTime);
        }
    }
    
    private void handleMouseInput(double x, double y){
        if(tryAgain.contains(x, y)){
            isOver = false;
            tryAgain.getOnMouseClicked();
        }
        if(nextLevel.contains(x, y)){
            levelOne = false;
            nextLevel.getOnMouseClicked();
        }
        
        if(wonGame.contains(x, y)){
            splash = true;
            levelOne = false;
            levelTwo = false;
            wonGame.getOnMouseClicked();
        }
        
        if(startGame.contains(x, y)){
            splash = false;
            levelOne = true;
            startGame.getOnMouseClicked();
        }
        
        for(int i = 0; i < sharks.size(); i++)
        {
            if(x >= sharks.get(i).getX() &&
                    x <= sharks.get(i).getX()+sharks.get(i).getWidth() && 
                    y >= sharks.get(i).getY() && 
                    y <= sharks.get(i).getY()+sharks.get(i).getHeight() && isOver == false){
                sharks.get(i).shrink();
            }
        }
    }
    
    private void handleKeyInput(KeyCode code) {
       switch (code){
           case DIGIT2:
               count = fish.size();
               break;
           default:
               break;
       }
       if(isOver == false)
       {
        if(code.getName().equals("Space") && splash == false){
            for(int i = 0; i < fish.size(); i++)
            {
            if(hook.isColliding(fish.get(i)) && fish.get(i).getY() != 160){
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
    }
    
    public void gameOver(){
        if(isOver == false && splash == false){
        root.getChildren().addAll(gameOverText);
        root.getChildren().addAll(tryAgain);
        
        for(int i = 0; i < fish.size(); i++)
        {
            fish.get(i).setSpeed(0);       
        }
        hook.gameOver();
        player.gameOver();
        isOver = true;
        splash = true;
        levelOne = false;
        levelTwo = false;
        }
    }
    
    public void youWin(){
        
        for(int i = 0; i < fish.size(); i++)
        {
           if(fish.get(i).getY() == 160){
               count++;
           }
        }
        if(count >= fish.size() && didWin == false && splash == false){
            scoreInt = scoreInt + (int)keepTime;
            score.setText("Score: " + scoreInt);
            didWin = true;
            if(levelOne){
            root.getChildren().addAll(youWinText);
            root.getChildren().addAll(nextLevel);
            }
            else if(levelTwo){
                wonGameText.setText("YOU WIN!" + "\n" + "Final Score: " + scoreInt);
                root.getChildren().addAll(wonGameText);
                root.getChildren().addAll(wonGame);
            }
            
        }
        count = 0;
    }
}

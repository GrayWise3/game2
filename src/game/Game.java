package game;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The Game class handles all of the creation of the GUI, as well as all of the 
 * interactions between Actors on the screen. It builds the levels, fills them with
 * fish and/or sharks, and then sets everything in motion. It is also responsible for
 * displaying all of the messages and buttons that pop up on the screen throughout
 * gameplay.
 * @author Grayson Wise
 *
 */
class Game {
    private static final String TITLE = "Hook Line Sinker";
    private Group root;
    private Scene myScene;
    private Player player = new Player();
    private Hook hook = new Hook();
    private ArrayList<Integer> fishX = new ArrayList<>();
    private ArrayList<Integer> fishY = new ArrayList<>();
    private ArrayList<Fish> fish = new ArrayList<>();
    private ArrayList<Integer> sharkX = new ArrayList<>();
    private ArrayList<Integer> sharkY = new ArrayList<>();
    private ArrayList<Shark> sharks = new ArrayList<>();
    private Image background;
    private ImageView bgView;
    private Image mainScreen;
    private ImageView mainView;
    private Text score;
    private Text time;
    private Text gameOverText;
    private Text youWinText;
    private double START_TIME = 100;
    private double keepTime = START_TIME;
    private boolean levelOne = false;
    private boolean isOver = false;
    private boolean didWin = false;
    private boolean levelTwo = false;
    private boolean splash = true;
    private Button tryAgain = new Button("Try Again");
    private Button winner = new Button("Level 2");
    private Button startGame = new Button("Start Game");
    private Button wonGame = new Button("You Won!");
    private int count = 0;
    private int scoreInt = 0;

    /**
     * Constructs the Game object
     *
     * @param width
     * @param height
     */
    public Game (int width, int height) {
        root = new Group();
        myScene = new Scene(root, width, height, Color.WHITE);
    }

    /**
     * Returns the title of the Game
     *
     * @return
     */
    public String getTitle () {
        return TITLE;
    }

    /**
     * Initializes the game, sets up handling of clicks/keystrokes,
     * and decides which game screen to load
     *
     * @return
     */
    public Scene init () {
        root = new Group();
        root.getChildren().clear();
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        placeButtons();
        placeMessages();
        placeImages();
        if (splash && !levelOne && !levelTwo) {
            splashScreen();
        }
        if (!splash && levelOne && !levelTwo) {
            levelOne();
        }
        if (!splash && !levelOne && levelTwo) {
            levelTwo();
        }
        myScene.setRoot(root);
        return myScene;
    }

    private void placeButtons () {
        tryAgain.setLayoutX(360);
        tryAgain.setLayoutY(425);
        tryAgain.setOnMouseClicked(e -> restart());
        winner.setLayoutX(360);
        winner.setLayoutY(425);
        winner.setOnMouseClicked(e -> levelTwo());
        startGame.setLayoutX(360);
        startGame.setLayoutY(425);
        startGame.setOnMouseClicked(e -> restart());
        wonGame.setLayoutX(360);
        wonGame.setLayoutY(425);
        wonGame.setOnMouseClicked(e -> splashScreen());
    }

    private void placeMessages () {
        score = new Text("Score: " + scoreInt);
        score.setFont(Font.font("Verdana", 20));
        score.setX(650);
        score.setY(30);
        time = new Text("Time: " + keepTime);
        time.setFont(Font.font("Verdana", 20));
        time.setX(650);
        time.setY(60);
        gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font("Verdana", 100));
        gameOverText.setX(125);
        gameOverText.setY(400);
        youWinText = new Text();
        youWinText.setFont(Font.font("Verdana", 70));
        youWinText.setX(125);
        youWinText.setY(400);
    }

    private void placeImages () {
        background = new Image(getClass().getClassLoader().getResourceAsStream("background.jpg"));
        bgView = new ImageView();
        bgView.setImage(background);
        bgView.setFitHeight(600);
        bgView.setY(-50);
        bgView.preserveRatioProperty();
        mainScreen = new Image(getClass().getClassLoader().getResourceAsStream("mainscreen.png"));
        mainView = new ImageView();
        mainView.setImage(mainScreen);
    }

    private void splashScreen () {
        splash = true;
        levelOne = false;
        levelTwo = false;
        isOver = false;
        didWin = false;
        scoreInt = 0;
        root.getChildren().add(mainView);
        root.getChildren().add(startGame);
        Image inst = new Image(getClass().getClassLoader().getResourceAsStream("instructions.png"));
        ImageView instructions = new ImageView(inst);
        instructions.setX(500);
        instructions.setY(225);
        instructions.setFitHeight(231);
        instructions.setFitWidth(227);
        root.getChildren().add(instructions);
    }

    private void levelOne () {
        splash = false;
        levelOne = true;
        levelTwo = false;
        draw();
        addFish();
        root.getChildren().addAll(hook.draw());
    }

    private void levelTwo () {
        splash = false;
        levelOne = false;
        levelTwo = true;
        restart();
        draw();
        splash = false;
        levelOne = false;
        levelTwo = true;
        addFish();
        addSharks();
        root.getChildren().addAll(hook.draw());

    }

    private void addSharks () {
        sharkX.addAll(Arrays.asList(400, 700, 1200, 1500, 2000));
        sharkY.addAll(Arrays.asList(400, 300, 200, 250, 350));
        for (int i = 0; i < sharkX.size(); i++) {
            sharks.add(new Shark(sharkX.get(i), sharkY.get(i)));
        }
        for (int i = 0; i < sharks.size(); i++) {
            root.getChildren().addAll(sharks.get(i).draw());

        }
    }

    private void addFish () {
        fishX.addAll(Arrays.asList(400, 500, 600, 700, 800, 900, 1000, 1100,
                                   1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900,
                                   2000, 2100, 2200, 2300, 2400, 2500, 2600, 2700, 2800, 2900,
                                   3000));
        fishY.addAll(Arrays.asList(300, 250, 400, 350, 200, 400, 300, 200,
                                   300, 350, 250, 400, 350, 200, 400, 300,
                                   350, 300, 250, 400, 350, 200, 400, 300, 200, 300, 350, 250));
        for (int i = 0; i < fishX.size(); i++) {
            fish.add(new Fish(fishX.get(i), fishY.get(i)));
        }
        for (int i = 0; i < fish.size(); i++) {
            root.getChildren().addAll(fish.get(i).draw());
        }
    }

    private void restart () {
        root.getChildren().clear();
        fish.clear();
        fishX.clear();
        fishY.clear();
        sharks.clear();
        sharkX.clear();
        sharkY.clear();
        hook.reset();
        player.reset();
        keepTime = START_TIME;
        if (isOver == true) {
            scoreInt = 0;
        }
        isOver = false;
        didWin = false;
        splash = false;
        levelOne = true;
        init();
    }

    private void draw () {
        root.getChildren().addAll(bgView);
        root.getChildren().addAll(score);
        root.getChildren().addAll(time);
        root.getChildren().addAll(player.draw());
    }

    /**
     * Updates every actor's position, as well as the time,
     * by calling all of their step methods every "elapsed time"
     *
     * @param elapsedTime
     */
    public void step (double elapsedTime) {
        for (int i = 0; i < fish.size(); i++) {
            fish.get(i).step(elapsedTime);
        }
        for (int i = 0; i < sharks.size(); i++) {
            sharks.get(i).step(elapsedTime);
        }
        hook.step(elapsedTime);
        score.setText("Score: " + scoreInt);
        updateTime(elapsedTime);
        youWin();
        if ((int) keepTime == 0) {
            gameOver();
        }

        for (int i = 0; i < sharks.size(); i++) {
            if (hook.checkDestructable() == false && didWin == false &&
                    hook.isColliding(sharks.get(i))) {
                gameOver();
            }
        }
    }

    private void updateTime (double elapsedTime) {
        if (didWin == false && isOver == false && splash == false) {
            keepTime = keepTime - elapsedTime;
            time.setText("Time: " + (int) keepTime);
        }
        else {
            time.setText("Time: " + (int) keepTime);
        }
    }

    private void handleMouseInput (double clickX, double clickY) {
        if (tryAgain.contains(clickX, clickY)) {
            isOver = false;
            tryAgain.getOnMouseClicked();
        }
        if (winner.contains(clickX, clickY)) {
            levelOne = false;
            winner.getOnMouseClicked();
        }

        if (wonGame.contains(clickX, clickY)) {
            splash = true;
            levelOne = false;
            levelTwo = false;
            wonGame.getOnMouseClicked();
        }

        if (startGame.contains(clickX, clickY)) {
            splash = false;
            levelOne = true;
            startGame.getOnMouseClicked();
        }

        for (int i = 0; i < sharks.size(); i++) {
            if (clickX >= sharks.get(i).getX() &&
                clickX <= sharks.get(i).getX() + sharks.get(i).getWidth() &&
                clickY >= sharks.get(i).getY() &&
                clickY <= sharks.get(i).getY() + sharks.get(i).getHeight() && isOver == false) {
                sharks.get(i).shrink();
            }
        }
    }

    private void handleKeyInput (KeyCode code) {
        switch (code) {
            case DIGIT2:
                count = fish.size();
                break;
            default:
                break;
        }
        if (isOver == false) {
            if (code.getName().equals("Space") && splash == false) {
                for (int i = 0; i < fish.size(); i++) {
                    if (hook.isColliding(fish.get(i)) && fish.get(i).getY() != 160) {
                        fish.get(i).setXSpeed(0);
                        fish.get(i).setYSpeed(5000);
                        scoreInt += 5;
                        score.setText(("Score: " + scoreInt));
                    }
                }
                hook.setY(160);
            }
            player.control(code);
            hook.control(code);
            for (int i = 0; i < fish.size(); i++) {
                fish.get(i).move(code);
            }
        }
    }

    private void gameOver () {
        if (isOver == false && splash == false) {
            root.getChildren().add(gameOverText);
            root.getChildren().add(tryAgain);
            for (int i = 0; i < fish.size(); i++) {
                fish.get(i).setXSpeed(0);
            }
            hook.gameOver();
            player.gameOver();
            isOver = true;
            splash = true;
            levelOne = false;
            levelTwo = false;
        }
    }

    private void youWin () {
        for (int i = 0; i < fish.size(); i++) {
            if (fish.get(i).getY() == 160) {
                count++;
            }
        }
        if (count >= fish.size() && didWin == false && splash == false) {
            scoreInt = scoreInt + (int) keepTime;
            score.setText("Score: " + scoreInt);
            didWin = true;
            if (levelOne) {
                youWinText.setText("Level Complete!");
                root.getChildren().add(youWinText);
                root.getChildren().add(winner);
            }
            else if (levelTwo) {
                youWinText.setFont(Font.font("Verdana", 45));
                youWinText.setText("You Win! Final Score: " + scoreInt);
                root.getChildren().add(youWinText);
                root.getChildren().add(wonGame);
                didWin = true;
            }
            for (int i = 0; i < fish.size(); i++) {
                fish.get(i).setXSpeed(0);
            }
        }
        count = 0;
    }
}

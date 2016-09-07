package game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application{
    
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private int screenHeight = 500;
    private int screenWidth = 800;

    @Override
    public void start (Stage s) {
    	Game myGame = new Game(screenWidth, screenHeight);
        s.setTitle(myGame.getTitle());
        Scene scene = myGame.init();
        s.setScene(scene);
        s.show();
        //set the games loop
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
        	 e -> myGame.step(SECOND_DELAY)) ;
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public static void main (String[] args) {
        launch(args);
    }
}

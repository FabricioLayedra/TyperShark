/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesybuceadores;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;



/**
 *
 * @author Daniel Santacruz
 */
public class TiburonesyBuceadores extends Application {
    MediaPlayer mediaplayer;
    
    @Override
    public void start(Stage primaryStage) {
        String path = new File("src/tiburonesybuceadores/sonido.mp3").getAbsolutePath();
        
        Media musicFile = new Media (new File(path).toURI().toString());
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.setAutoPlay(true);
        mediaplayer.setVolume(0.3);
        mediaplayer.setCycleCount((int)(Duration.INDEFINITE.toHours()));
        PaneOrganizer principal= new PaneOrganizer();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Tiburones y Buceadores");
        
        primaryStage.setScene(principal.getScene());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

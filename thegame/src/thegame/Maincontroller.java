package thegame;

import java.util.ArrayList;

import thegame.Main;
import javafx.animation.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.channels.Pipe.SourceChannel;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.text.TextAlignment;

public class Maincontroller extends Main{
	
	@FXML
	Circle player,enemy;
	@FXML
	Rectangle arena;
	@FXML
	Pane arenaPane;
	@FXML
	BorderPane bp;
	@FXML
	Button btnButton;
	
	private Scene mainScene;
	private Sounds soundPlayer = new Sounds();

    ArrayList<String> keyInput = new ArrayList<>();
    
    @FXML
    public void initialize() {
    	System.out.println(mainScene);
    	System.out.println();
    	System.out.println(player);
    	System.out.println(arena);
    	System.out.println(arenaPane);
    	System.out.println(btnButton.getId());
    	System.out.println(btnButton);
    	btnButton.setOnAction((event)->{
    		//Sounds.playSound("/output.wav");
    		soundPlayer.playSound(0);
    	});
    	//mainScene=player.getScene();
    	//setupKeyPressHandlers();
    }
    
    private void setupKeyPressHandlers() {
    	
        this.mainScene.setOnKeyPressed((KeyEvent e) ->{
            String code = e.getCode().toString();
            if (!keyInput.contains(code)) {
                keyInput.add(code);
            }
        });

        this.mainScene.setOnKeyReleased((KeyEvent e) ->{
            String code = e.getCode().toString();
            keyInput.remove(code);
        });
    }

    public void setScene(Scene s) {
    	mainScene=s;
    	System.out.println(s);
    	System.out.println("5");

    	System.out.println(mainScene);
    	System.out.println();
    	System.out.println(player);
    	System.out.println(arena);
    	System.out.println(arenaPane);
    	System.out.println(btnButton);
    	//mainScene=player.getScene();
    }
}

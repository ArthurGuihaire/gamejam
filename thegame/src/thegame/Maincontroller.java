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
	
	Player payler;
	private Scene mainScene;
	private Sounds soundPlayer = new Sounds();
	private AnimationTimer gameloop;

    ArrayList<String> keyInput = new ArrayList<>();
    
    @FXML
    public void initialize() {

    	mainScene=bp.getScene();
    	System.out.println(mainScene);
    	System.out.println();
//    	System.out.println(player);
//    	System.out.println(arena);
//    	System.out.println(arenaPane);
//    	System.out.println(btnButton.getId());
//    	System.out.println(btnButton);
    	
    	btnButton.setOnAction((event)->{
    		System.out.println("hello");
    		System.out.println(mainScene);
    		startGame();				
    		mainScene=btnButton.getScene();	
    		setScene(btnButton.getScene());
    		soundPlayer.playSound(0);
    	});
    	
    	//gameLoop();
    	//setupKeyPressHandlers();
    	
    }
    
    public void startGame() {
		mainScene=bp.getScene();

    	initPlayer();		
		setupKeyPressHandlers();
		
    	gameLoop();
    }
    
    public void initPlayer() {
    	payler=new Player();
    	
    	payler.setTranslateX(50);
    	payler.setTranslateY(50);
    	arenaPane.getChildren().add(payler);
    	payler.setVisible(true);
    	
    	
    }
    
    
    private void gameLoop() {
    	gameloop=new AnimationTimer() {
			
			@Override
			public void handle(long args) {
			update();
				
			}
		}; gameloop.start();
    }
    
    private void update() {
    	mainScene=this.btnButton.getScene();
    	if (keyInput.contains("W")) {
    		payler.moveUp();
    	}
    	if (keyInput.contains("A")) {
    		payler.moveLeft();
    	}
    	if (keyInput.contains("S")) {
    		payler.moveDown();
    	}
    	if (keyInput.contains("D")) {
    		payler.moveRight();
    	}
    	System.out.println(keyInput);
    	
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
//    	System.out.println(s);
//    	System.out.println("5");
//
//    	System.out.println(mainScene);
//    	System.out.println();
//    	System.out.println(player);
//    	System.out.println(arena);
//    	System.out.println(arenaPane);
//    	System.out.println(btnButton);
    	//mainScene=player.getScene();
    }
}

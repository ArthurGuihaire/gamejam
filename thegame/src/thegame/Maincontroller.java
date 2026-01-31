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
	Button btnButton,btnEnemy;
	
	Player payler;
	
	private Scene mainScene;
	public static Sounds soundPlayer = new Sounds();
	private AnimationTimer gameloop;
	
	public static int arenaLeft;
	public static int arenaTop;
	

	int xmove=0;
	int ymove=0;
	
	
    ArrayList<String> keyInput = new ArrayList<>();
    
    
    
    @FXML
    public void initialize() {
    	arenaLeft=(int)arena.getLayoutX()-685;
    	arenaTop=(int)arena.getLayoutY()-385;

    	mainScene=arena.getScene();
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
    	
    	btnEnemy.setOnAction((event)->{
    		Enemy en=new Enemy();
    		arenaPane.getChildren().add(en);
    		
    	});
    	
    	//gameLoop();
    	//setupKeyPressHandlers();
    	
    }
    
    public void startGame() {
		mainScene=arena.getScene();

    	initPlayer();		
		setupKeyPressHandlers();
		
    	gameLoop();
    }
    
    public void initPlayer() {
    	payler=new Player();
    	
    	arenaPane.getChildren().add(payler);
    	payler.setTranslateX(arenaLeft+100);
    	payler.setTranslateY(arenaTop+100);
    	payler.setVisible(true);
    	payler.setScaleX(1);
    	payler.setScaleY(1);
    	
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
    	//mainScene=this.btnButton.getScene();
    	//payler.moveMode = !(keyInput.contains("CONTROL"));
    	
    	if (payler.moveMode) {
	    	if (keyInput.contains("W")) {
	    		if (payler.getBoundsInParent().getMinY()>arena.getLayoutY())payler.moveUp();
	    	}
	    	if (keyInput.contains("A")) {
	    		if (payler.getBoundsInParent().getMinX()>arena.getLayoutX())payler.moveLeft();
	    	}
	    	if (keyInput.contains("S")) {
	    		if (payler.getBoundsInParent().getMaxY()<arena.getHeight()+arena.getLayoutY())payler.moveDown();
	    	}
	    	if (keyInput.contains("D")) {
	    		if (payler.getBoundsInParent().getMaxX()<arena.getWidth()+arena.getLayoutX())payler.moveRight();
	    	}
	    	//System.out.println(keyInput);
	    	//System.out.println(xmove+", "+ymove);
	    	analyseDirection();
    	}
    	
    	enemyCollision();
    }
    
    private void removeDirection(String key) {
    	switch (key) {
    	case "W": ymove+=1; break;
    	case "A": xmove+=1; break;
    	case "S": ymove-=1; break;
    	case "D": xmove-=1; break;
    	
    	}
    }
    private void addDirection(String key) {
    	switch (key) {
    	case "W": ymove-=1; break;
    	case "A": xmove-=1; break;
    	case "S": ymove+=1; break;
    	case "D": xmove+=1; break;
    	
    	}
    }
    private void analyseDirection() {
    	if (ymove==1&&xmove==0) payler.setImage(new Image(this.getClass().getResource("/images/tux-down.png").toExternalForm()));
    	if (ymove==1&&xmove==1) payler.setImage(new Image(this.getClass().getResource("/images/tux-down-right.png").toExternalForm()));
    	if (ymove==0&&xmove==1) payler.setImage(new Image(this.getClass().getResource("/images/tux-right.png").toExternalForm()));
    	if (ymove==-1&&xmove==1) payler.setImage(new Image(this.getClass().getResource("/images/tux-up-right.png").toExternalForm()));
    	if (ymove==-1&&xmove==0) payler.setImage(new Image(this.getClass().getResource("/images/tux-up.png").toExternalForm()));
    	if (ymove==-1&&xmove==-1) payler.setImage(new Image(this.getClass().getResource("/images/tux-up-left.png").toExternalForm()));
    	if (ymove==0&&xmove==-1) payler.setImage(new Image(this.getClass().getResource("/images/tux-left.png").toExternalForm()));
    	if (ymove==1&&xmove==-1) payler.setImage(new Image(this.getClass().getResource("/images/tux-down-left.png").toExternalForm()));
    	
    	
    }
    
    private void enemyCollision() {
    	arenaPane.getChildren().forEach(this::playerCollision);
    }
    
    private void playerCollision(Node n) {
    	if (n instanceof Player) {
    		for (Node e : arenaPane.getChildren()) {
				if (n.getBoundsInParent().intersects(e.getBoundsInParent())&&!(e instanceof Player)&&(e instanceof Enemy)) {
					((Enemy) e).die();
					System.out.println("die");
				}
				
				
			}
    	}
    	
    	
    }
    
    private void setupKeyPressHandlers() {
    	
        this.mainScene.setOnKeyPressed((KeyEvent e) ->{
            String code = e.getCode().toString();
            if (!keyInput.contains(code)) {
            	addDirection(code);
                keyInput.add(code);
            }
            if (code.equals("CONTROL")) payler.moveMode = !payler.moveMode;
        });

        this.mainScene.setOnKeyReleased((KeyEvent e) ->{
            String code = e.getCode().toString();
            removeDirection(code);
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

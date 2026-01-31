package thegame;

import java.util.ArrayList;

import thegame.Main;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
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
	
	@FXML
	StackPane crosshair;
	
	@FXML
	TextField cmdLine;
	
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
    	payler.setTranslateX(arenaLeft+1000);
    	payler.setTranslateY(arenaTop+900);
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
    	mainScene=this.btnButton.getScene();
    	analyseDirection();
    	removeDeadPeople();
    	
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
	    	//System.out.println(xmove+", "+ymove);
	    	analyseDirection();
    	}
    	System.out.println(keyInput);
    	
    	arena.setOnMouseMoved(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle (MouseEvent mouse) {
    			crosshair.setLayoutX(mouse.getSceneX()-25);
    			crosshair.setLayoutY(mouse.getSceneY()-25);
    		}
    		
    	});
    	crosshair.setOnMouseMoved(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle (MouseEvent mouse) {
    			if (mouse.getSceneX()>arena.getLayoutX()&&mouse.getSceneY()>arena.getLayoutY()&&mouse.getSceneY()<arena.getHeight()+arena.getLayoutY()&&mouse.getSceneX()<arena.getWidth()+arena.getLayoutX())
    			{	crosshair.setLayoutX(mouse.getSceneX()-25);
    			crosshair.setLayoutY(mouse.getSceneY()-25);}
    		}
    		
    	});
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
    	if (ymove==0&&xmove==1) payler.setImage(new Image(this.getClass().getResource("/images/purple-laser.png").toExternalForm()));
    	if (ymove==-1&&xmove==1) payler.setImage(new Image(this.getClass().getResource("/images/tux-up-right.png").toExternalForm()));
    	if (ymove==-1&&xmove==0) payler.setImage(new Image(this.getClass().getResource("/images/tux-up.png").toExternalForm()));
    	if (ymove==-1&&xmove==-1) payler.setImage(new Image(this.getClass().getResource("/images/tux-up-left.png").toExternalForm()));
    	if (ymove==0&&xmove==-1) payler.setImage(new Image(this.getClass().getResource("/images/tux-left.png").toExternalForm()));
    	if (ymove==1&&xmove==-1) payler.setImage(new Image(this.getClass().getResource("/images/tux-down-left.png").toExternalForm()));
    	
    	
    }
    
    private void enemyCollision() {
    	arenaPane.getChildren().forEach(this::playerCollision);
		removeDeadPeople();
    }
    
    private void playerCollision(Node n) {
    	if (n instanceof Player) {
    		ArrayList<Enemy> elist=new ArrayList<Enemy>();
    		for (Node en : arenaPane.getChildren()) {
				if (en instanceof Enemy emy) elist.add(emy);
			}
    		for (Enemy e : elist) {
				if (n.getBoundsInParent().intersects(e.getBoundsInParent())&&(!e.isDead())) {
					die((Enemy)e);
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
            if (code.equals("CONTROL")) {
            	payler.moveMode = !payler.moveMode;
            	
            }
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
    
	public void useCommand(String command) {
		String[] sections = command.split(" ");
		if (sections[0].equals("rm")) {
			if (payler.current == null) return;
			if (sections[1].equals("rf")) {
				//remove recursive force!!!
			}
			else {
				payler.current.health -= Player.RM_DAMAGE;
				if (payler.current.health <= 0) {
					die(payler.current);
					payler.current = null;
				}
			}
		}
		
		else if (sections[0].equals("cd")) {
			if (sections[1].equals("-")) {
				payler.current = payler.previous;
				payler.previous = null;
			}
			else {
				Enemy candidate = null;
				double mouseX = 400.0;
				double mouseY = 400.0;
				for (Node n : arenaPane.getChildren()) {
					double minDistanceSquared = Double.MAX_VALUE;
					if (n instanceof Enemy emy) {
						double x = emy.getLayoutX();
						double y = emy.getLayoutY();
						
						double distanceSquared = (x-mouseX) * (x-mouseX) + (y-mouseY) * (y-mouseY);
						if (distanceSquared < minDistanceSquared) {
							minDistanceSquared = distanceSquared;
							candidate = emy;
						}
					}
				}
				
				payler.previous = payler.current;
				payler.current = candidate;
			}
		}
	}

	public void die(Enemy e) {
		double direction= (Math.random()*90);
		System.out.println(direction);
		direction=Math.toRadians(direction);
		
		GlassShard gs1=new GlassShard();
		gs1.setLayoutX(e.getLayoutX()); gs1.setLayoutY(e.getLayoutY());
		GlassShard gs2=new GlassShard();
		gs2.setLayoutX(e.getLayoutX()); gs2.setLayoutY(e.getLayoutY());
		GlassShard gs3=new GlassShard();
		gs3.setLayoutX(e.getLayoutX()); gs3.setLayoutY(e.getLayoutY());
		Platform.runLater(() -> arenaPane.getChildren().addAll(gs1,gs2,gs3));
		
		TranslateTransition tt1=new TranslateTransition(Duration.seconds(0.5), gs1);
		RotateTransition rt1=new RotateTransition(Duration.seconds(0.5), gs1); rt1.setByAngle(360);
		tt1.setByX(Math.cos(direction)*100);
		tt1.setByY(Math.sin(direction)*100);
		direction=Math.toDegrees(direction);
		direction+=120;
		System.out.println(direction);
		direction=Math.toRadians(direction);
		
		TranslateTransition tt2=new TranslateTransition(Duration.seconds(0.5), gs2);
		RotateTransition rt2=new RotateTransition(Duration.seconds(0.5), gs2); rt2.setByAngle(360);
		tt2.setByX(Math.cos(direction)*100);
		tt2.setByY(Math.sin(direction)*100);
		direction=Math.toDegrees(direction);
		direction+=120;
		System.out.println(direction);
		direction=Math.toRadians(direction);
		
		
		TranslateTransition tt3=new TranslateTransition(Duration.seconds(0.5), gs3);
		RotateTransition rt3=new RotateTransition(Duration.seconds(0.5), gs3); rt3.setByAngle(360);
		tt3.setByX(Math.cos(direction)*100);
		tt3.setByY(Math.sin(direction)*100);
		
		tt1.setOnFinished((event)->{
			gs1.setDead(true);
			gs2.setDead(true);
			gs3.setDead(true);
		});
		
		tt1.play();
		tt2.play();
		tt3.play();
		rt1.playFromStart();
		rt2.play();
		rt3.play();
		
		e.setVisible(false);
		e.setDead(true);
		
		
		
		Maincontroller.soundPlayer.playSound(1);
	}
	
	public void removeDeadPeople() {
		List<Node> nodes = new ArrayList<>(arenaPane.getChildren());
		for (Node n : nodes) {
			if (n instanceof Enemy e) {
				if (e.isDead())
				arenaPane.getChildren().remove(e);
			}
			else if (n instanceof GlassShard gs) {
				if (gs.isDead())
					arenaPane.getChildren().remove(gs);
			}
			
		}
	}
	

	private class GlassShard extends ImageView {
		boolean dead=false;
		
		
		public boolean isDead() {
			return dead;
		}


		public void setDead(boolean dead) {
			this.dead = dead;
		}


		GlassShard() {
			int type=(int) Math.floor(Math.random()*5+1);
			switch (type) {
			case 1: this.setImage(new Image(getClass().getResource("/images/shard-1.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 2: this.setImage(new Image(getClass().getResource("/images/shard-2.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 3: this.setImage(new Image(getClass().getResource("/images/shard-3.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 4: this.setImage(new Image(getClass().getResource("/images/shard-4.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 5: this.setImage(new Image(getClass().getResource("/images/shard-5.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			default: this.setImage(new Image(getClass().getResource("/images/tux-left.png").toExternalForm())); break;
			}
		}
		
	}
	
    
    
}

package thegame;

import java.util.ArrayList;

import thegame.Main;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.channels.Pipe.SourceChannel;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.text.*;

public class Maincontroller extends Main{
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
	
	@FXML
	Text txtCompleted;
	
	@FXML
	Rectangle mana;
	
	final static int maxMana = 454;
	
	Player player;
	
	static Main main;
	
	private Scene mainScene;
	public static Sounds soundPlayer = new Sounds();
	private AnimationTimer gameloop;
	
	public static int arenaLeft;
	public static int arenaTop;
	
	public int currentlevel=0;
	public int enemiesleft=0;

	int xmove=0;
	int ymove=0;
	
	private int boostFrames = 0;
	private boolean boost = false;
	
    ArrayList<String> keyInput = new ArrayList<>();
    
    @FXML
    public void initialize() {
    	arenaLeft=(int)arena.getLayoutX();
    	arenaTop=(int)arena.getLayoutY();
    	cmdLine.setDisable(true);
    	txtCompleted.setVisible(false);
    	
    	mana.setWidth(maxMana);

    	mainScene=this.btnButton.getScene();
    	mainScene=arena.getScene();
    	
    	btnButton.setOnAction((event)->{
    		startGame();				
    		mainScene=btnButton.getScene();	
    		setScene(btnButton.getScene());
    		soundPlayer.playSound(0);
    		btnButton.setDisable(true);
    	});
    	
    	btnEnemy.setOnAction((event)->{
    		Enemy en=new Enemy();
    		arenaPane.getChildren().add(en);
    		crosshair.toFront();
    		
    	});
    	
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
    	player=new Player();

    	player.setScaleX(0.4);
    	player.setScaleY(0.4);
    	
    	arenaPane.getChildren().add(player);
    	//player.setTranslateX(arenaLeft+1000);
    	//player.setTranslateY(arenaTop+900);
    	player.setLayoutX(player.getLayoutX() + 1000);
    	player.setLayoutY(player.getLayoutY() + 400);
    	player.setVisible(true);
    	 
    	
    }
    public void LevelUP() {
    	//System.out.println("current level:"+currentlevel);
    	if (enemiesleft!=0) return;
    	if (currentlevel==0) {
    		gameloop.stop();
    		currentlevel++;
    		enemiesleft=currentlevel+2;
    		for (int i=0;i<enemiesleft;i++) {
    			Enemy e=new Enemy();
    			e.setLayoutY(e.getLayoutY() + Math.random() * 500);
    			arenaPane.getChildren().add(e);
    			//System.out.println("new enemy!");

    		}    		gameloop.start();
    	}
    	else if (enemiesleft<=0&&currentlevel>0){
    		gameloop.stop();
    		//System.out.println("stop");
	    	currentlevel++;
	    	enemiesleft=currentlevel+2;
	  
	    	ObservableList<Node> al= arenaPane.getChildren();
	    	
	    	for (Node n : al) {
	    		if (n instanceof Enemy) {
	    			Platform.runLater(() -> al.remove(n));
	    		}
	    		if (n instanceof Projectile proj) {
	    			Platform.runLater(() -> {
	    				proj.treeDie(arenaPane);
	    				al.remove(n);
	    			});
	    		}
	    	}
	
	    	txtCompleted.setVisible(true);    	clearProjectiles();
	    	gameloop.stop();
	    	TranslateTransition tt=new TranslateTransition(Duration.seconds(3),player);
	    	if (currentlevel > 5) {
	    		txtCompleted.setText("Congruatulations, you've rescued arch linux by hunting all the linux commands!");
	    	}
	    	else {
		    	tt.setOnFinished((event)->{
		    		txtCompleted.setVisible(false);
		    		generateEnemies();
		    		gameloop.start();
		    	});
		    	tt.playFromStart();
	    	}
	    }
    }
    
    private void generateEnemies() {
    int pos=15;
	for (int i=0;i<enemiesleft;i++) {

		Enemy e=new Enemy();    			
		e.setLayoutY(e.getLayoutY() + Math.random() * 500);
		arenaPane.getChildren().add(e);
		e.setLayoutX(e.getLayoutX()+pos);
		System.out.println("newer enemy");
		pos+=30;
	}
    }
    private void gameLoop() { 
    	gameloop=new AnimationTimer() {
			@Override
			public void handle(long args) {
		        update();
			}
		}; 
		gameloop.start();
    }
    
    private void update() {
    	mainScene=this.btnButton.getScene();
    	removeDeadPeople();
    	
    	if (boost && boostFrames-- <= 0) {
    		boost = false;
    		player.speedBoost = 1.0;
    	}
    	
    	if (player.moveMode) {
	    	if (keyInput.contains("W")) {
	    		if (player.getBoundsInParent().getMinY()>arena.getLayoutY())player.moveUp();
	    	}
	    	if (keyInput.contains("A")) {
	    		if (player.getBoundsInParent().getMinX()>arena.getLayoutX())player.moveLeft();
	    	}
	    	if (keyInput.contains("S")) {
	    		if (player.getBoundsInParent().getMaxY()<arena.getHeight()+arena.getLayoutY())player.moveDown();
	    	}
	    	if (keyInput.contains("D")) {
	    		if (player.getBoundsInParent().getMaxX()<arena.getWidth()+arena.getLayoutX())player.moveRight();
	    	}

	    	analyseDirection();
	    	//System.out.println(xmove+", "+ymove);
    	}
    	
    	for (Node n : arenaPane.getChildren()) {
    		if (n instanceof Projectile p) {
    			p.move();
    		}
    		if (n instanceof Enemy e) {
    			e.moveTowards(player);
    		}
    	}

    	//System.out.println(keyInput);
    	
    	enemyCollision();
    	projectileCollision();
    	LevelUP();
    	updateProjectileTimers();
    	
    	//mana.setScaleX(mana.getScaleX()+0.001);
    	if (mana.getWidth() < maxMana)
    		mana.setWidth(mana.getWidth() + 0.07);
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
    	if (ymove==1&&xmove==0) player.setImage(new Image(this.getClass().getResource("/images/tux-down.png").toExternalForm()));
    	if (ymove==1&&xmove==1) player.setImage(new Image(this.getClass().getResource("/images/tux-down-right.png").toExternalForm()));
    	if (ymove==0&&xmove==1) player.setImage(new Image(this.getClass().getResource("/images/tux-right.png").toExternalForm()));
    	if (ymove==-1&&xmove==1) player.setImage(new Image(this.getClass().getResource("/images/tux-up-right.png").toExternalForm()));
    	if (ymove==-1&&xmove==0) player.setImage(new Image(this.getClass().getResource("/images/tux-up.png").toExternalForm()));
    	if (ymove==-1&&xmove==-1) player.setImage(new Image(this.getClass().getResource("/images/tux-up-left.png").toExternalForm()));
    	if (ymove==0&&xmove==-1) player.setImage(new Image(this.getClass().getResource("/images/tux-left.png").toExternalForm()));
    	if (ymove==1&&xmove==-1) player.setImage(new Image(this.getClass().getResource("/images/tux-down-left.png").toExternalForm()));
    	
    	
    }
    
    private void enemyCollision() {
    	arenaPane.getChildren().forEach(this::playerCollision);
		removeDeadPeople();
    }
    
    private void projectileCollision() {
    	ArrayList<Node> pls=new ArrayList<>();
    	pls.addAll(arenaPane.getChildren());
    	for (Node idk : arenaPane.getChildren()) {
			if (idk instanceof Projectile p) {
				for (Node node : pls) {
					if (p.getBoundsInParent().intersects(node.getBoundsInParent())&&node instanceof Enemy e&&!p.isDead()) {
						if (p.type == "tree")
							p.treeDie(arenaPane);
						else {
							kill(e);
							p.pierced++;
							if (p.pierced>=p.maxpierce)Platform.runLater(()->kill(p));
						}
					}
				}
			}
		}
    	
    }
    private void clearProjectiles() {
    	for (Node n : arenaPane.getChildren()) {
    		if (n instanceof Projectile p) {
    			p.treeDie(arenaPane);
    			Platform.runLater(()->kill(p));
    		}
		}
    }
    
    private void playerCollision(Node n) {
    	if (n instanceof Player) {
    		ArrayList<Enemy> elist=new ArrayList<Enemy>();
    		for (Node en : arenaPane.getChildren()) {
				if (en instanceof Enemy emy) elist.add(emy);
			}
    		for (Enemy e : elist) {
				if (n.getBoundsInParent().intersects(e.getBoundsInParent())&&(!e.isDead())) {
					//die((Enemy)e);
					//System.out.println("die");
					playerDie();
				}
			}
    	}
    }
    
    private void playerDie() {
    	arenaPane.getChildren().clear();
    	
    	FXMLLoader newscene=new FXMLLoader(Main.class.getResource("/test.fxml"));
		newscene.setController(new Maincontroller());
		try {
			Parent newp=newscene.load();
			s.setRoot(newp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
            	player.moveMode =false;
            	cmdLine.setDisable(false);
            	cmdLine.requestFocus();
            }
        });

        this.mainScene.setOnKeyReleased((KeyEvent e) ->{
            String code = e.getCode().toString();
            removeDirection(code);
            if (code.equals("ENTER")) {
            	String currentcmd=cmdLine.getText();
            	player.moveMode=true;
            	cmdLine.clear();
            	this.useCommand(currentcmd);
            	xmove=0;
            	ymove=0;
            	cmdLine.setDisable(true);
            }
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
		switch (sections[0].toLowerCase().trim()) {
			case ("rm"): {
				if (player.current == null) return;
				if (sections.length > 1 && sections[1].equals("-rf") && currentlevel > 3) {
					if (this.mana.getWidth() < maxMana * 0.98) {
						cmdLine.setText("Error: not enough mana");
						break;
					}
					soundPlayer.playSound(7);
					for (Node n : arenaPane.getChildren()) {
						if (n instanceof Enemy e) {
							ScaleTransition s = new ScaleTransition(Duration.seconds(1.0), e);
							s.setToX(0.0);
							s.setToY(0.0);
							s.setOnFinished((event) -> {
								this.kill(n);
							});
							s.playFromStart();
						}
					}
					this.mana.setWidth(this.mana.getWidth() - 0.95 * maxMana);
				}
				else {
					player.current.health -= Player.RM_DAMAGE * 100;
					if (player.current.health <= 0) {
						kill(player.current);
						player.current = null;
					}
				}
				
				mana.setWidth(mana.getWidth() + 50);
			}
			break;
			case ("cd"): {
				if (sections.length > 1 && sections[1].equals("-")) {
					player.current = player.previous;
					player.previous = null;
				}
				else {
					Enemy candidate = null;
					double mouseX = crosshair.getLayoutX()+25;
					double mouseY = crosshair.getLayoutX()+25;
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
					
					player.previous = player.current;
					player.current = candidate;
				}
				break;
			}
			case ("magick"): {
				if (player.current == null) {
					cmdLine.setText("Error: target is null, try using cd first");
					break;
				}
				
				player.current.setScaleX(0.35);
				player.current.setScaleY(0.35);
	
				player.current.damage -= 10;
				Projectile p = new Projectile("magick", player.current.getLayoutX()- 110, player.current.getLayoutY()-80,player);
				arenaPane.getChildren().add(p);

				p.setLayoutX(player.getLayoutX()+100);
				p.setLayoutY(player.getLayoutY()+100);
				break;
			}
			case ("shred"): {
				if (currentlevel > 1) {
					if (mana.getWidth() > 0.32 * maxMana) {
						if (player.current != null) {
							Projectile p = new Projectile("shred", player.current.getLayoutX() - 130, player.current.getLayoutY() - 200, player);
							if (sections.length>1&&sections[1].equals("-f"))p.pierced=-999;
							arenaPane.getChildren().add(p);
							p.setLayoutX(player.getLayoutX()-30);
							p.setLayoutY(player.getLayoutY()+15);
						}
						else {
							Projectile p = new Projectile("shred", crosshair.getLayoutX() - 130, crosshair.getLayoutY() - 200, player);
							if (sections.length>1&&sections[1].equals("-f"))p.pierced=-999;
							p.setLayoutX(player.getLayoutX()-30);
							p.setLayoutY(player.getLayoutY()+15);
							arenaPane.getChildren().add(p);
						}
						
						
						mana.setWidth(mana.getWidth() - 0.32 * maxMana);
					}
					else {
						cmdLine.setText("Error: not enough mana");
					}
				}
				break;
			}
			case ("tree"): {
				if (mana.getWidth() < 0.12 * maxMana) {
					cmdLine.setText("Error: not enough mana");
					break;
				}
				if (player.current == null) {
					cmdLine.setText("Error: target is null, try using cd first");
					break;
				}
				Projectile p = new Projectile("tree", player.current.getLayoutX() - 110, player.current.getLayoutY() - 200, player);
				arenaPane.getChildren().add(p);
				p.setLayoutX(player.getLayoutX()-30);
				p.setLayoutY(player.getLayoutY()+15);
				break;
			}
			case ("java"): {
				if (currentlevel > 2) {
					boost = true;
					boostFrames = 1000;
					player.speedBoost = 1.5;
					break;
				}
			}
			case ("null"):{
				mana.setWidth(maxMana);
				
				player.current=null;
				player.previous=null;
				break;
			}
		}
	}
	
	public void updateProjectileTimers() {
		ArrayList<Projectile> al=new ArrayList<Projectile>();
		for (Node projectile : arenaPane.getChildren()) {
			if (projectile instanceof Projectile p){
			p.timeexisting+=1;
			if (p.timeexisting>p.maxtime) kill(p);
			else
			if (p.pierced>=p.maxpierce) kill(p);
			
			}
		}			
	}
	
	public void kill(Node n) {
		
		if (n instanceof Projectile p) {
			p.setDead(true);
			Platform.runLater(()->arenaPane.getChildren().remove(p));
		}else if (n instanceof Enemy e) {
			e.setDead(true);
			die(e);
			enemiesleft--;
		}
	}
	public void die(Enemy e) {
		double direction= (Math.random()*120);
		//System.out.println(direction);
		direction=Math.toRadians(direction);

		final int amount = 60;
		GlassShard gs1=new GlassShard();
		gs1.setLayoutX(e.getLayoutX() + amount); gs1.setLayoutY(e.getLayoutY() + amount);
		GlassShard gs2=new GlassShard();
		gs2.setLayoutX(e.getLayoutX() + amount); gs2.setLayoutY(e.getLayoutY() + amount);
		GlassShard gs3=new GlassShard();
		gs3.setLayoutX(e.getLayoutX() + amount); gs3.setLayoutY(e.getLayoutY() + amount);
		
		Platform.runLater(() -> arenaPane.getChildren().addAll(gs1,gs2,gs3));

		TranslateTransition tt1=new TranslateTransition(Duration.seconds(0.5), gs1);
		tt1.setInterpolator(Interpolator.EASE_IN);
		RotateTransition rt1=new RotateTransition(Duration.seconds(0.5), gs1); rt1.setByAngle(720);
		tt1.setByX(Math.cos(direction)*100);
		tt1.setByY(Math.sin(direction)*100);
		direction=Math.toDegrees(direction);
		direction+=120;
		//System.out.println(direction);
		direction=Math.toRadians(direction);
		
		TranslateTransition tt2=new TranslateTransition(Duration.seconds(0.5), gs2);
		tt2.setInterpolator(Interpolator.EASE_IN);
		RotateTransition rt2=new RotateTransition(Duration.seconds(0.5), gs2); rt2.setByAngle(720);
		tt2.setByX(Math.cos(direction)*100);
		tt2.setByY(Math.sin(direction)*100);
		direction=Math.toDegrees(direction);
		direction+=120;
		//System.out.println(direction);
		direction=Math.toRadians(direction);
		
		
		TranslateTransition tt3=new TranslateTransition(Duration.seconds(0.5), gs3);
		tt3.setInterpolator(Interpolator.EASE_IN);
		RotateTransition rt3=new RotateTransition(Duration.seconds(0.5), gs3); rt3.setByAngle(720);
		tt3.setByX(Math.cos(direction)*100);
		tt3.setByY(Math.sin(direction)*100);
		
		tt1.setOnFinished((event)->{
			gs1.setDead(true);
			gs2.setDead(true);
			gs3.setDead(true);
			removeDeadPeople();
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
		ArrayList<Node> nodes = new ArrayList<>(arenaPane.getChildren());
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


	public class GlassShard extends ImageView {
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

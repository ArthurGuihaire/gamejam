package thegame;

import java.util.LinkedList;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Projectile extends ImageView {
	
	String type="";
	double projectilespeed=0;
	private double dx, dy;
	double timeexisting=0;
	int maxtime;
	
	int pierced=0;
	int maxpierce;
	boolean dead=false;
	
	/**
	 * Creates a new Projectile object. Projectiles are fired from commands, so 
	 * @param cmd the command that will be firing the Projectile.
	 */
	Projectile(String cmd/*,LinkedList<ImageView> X*/,double x, double y, Node owner){
		dx = x - owner.getLayoutX() + owner.getBoundsInParent().getWidth();
		dy = y - owner.getLayoutY() + owner.getBoundsInParent().getHeight();
		double distance = Math.sqrt(dx*dx+dy*dy);
		
		dx /= distance;
		dy /= distance;

		switch (cmd.toLowerCase().trim()) {
		case "shred":
			this.type="shred";
			this.setImage(new Image(getClass().getResource("/images/sawblade.png").toExternalForm()));
			RotateTransition rt=new RotateTransition(Duration.seconds(10),this); 
			rt.setInterpolator(Interpolator.LINEAR);
			rt.setByAngle(360*20);
			rt.play();
			System.out.println("SHREDDING");
			
			projectilespeed=0.7;
			maxtime=240;
			maxpierce=3;
			break;
			
		case "magick":
			this.type="magick";
			this.setImage(new Image(getClass().getResource("/images/fireball.png").toExternalForm()));
			System.out.println("MAGICK");
			
			projectilespeed=2;
			maxtime=180;
			maxpierce=1;
			break;
		case "tree":
			this.type = "tree";
			this.setImage(new Image(getClass().getResource("/images/purple-laser.png").toExternalForm()));
			this.setScaleX(0.1);
			this.setScaleY(0.1);
			System.out.println("TREE");
			
			maxtime=300;
			break;
		}

		
		this.setRotate(Math.toDegrees(Math.atan(dy / dx)));
		if (dx < 0) this.setRotate(this.getRotate() + 180);
	}

	public void treeDie(Pane arenaPane) {
		double minDistance1 = Double.MAX_VALUE;
		double minDistance2 = Double.MAX_VALUE;

		
		if (type.equals("tree")) {
			System.out.println("TREE DIE");
			
			for (Node n : arenaPane.getChildren()) {
				if (n instanceof Enemy emy) {
					double dx = n.getLayoutX() - this.getLayoutX();
					double dy = n.getLayoutY() - this.getLayoutY();
					
					double dist = dx*dx+dy*dy;
					
					if (dist < 100) {
						emy.speed = 0.5;
						emy.speedTimer = 600;
					}
				}
			}
		}
	}
	
	public void setDead(boolean b) {
		this.dead=b;
	}
	public boolean isDead() {
		return this.dead;
	}
	

	public void move() {
		this.setLayoutX(this.getLayoutX() + dx * 10*projectilespeed);
		this.setLayoutY(this.getLayoutY() + dy * 10*projectilespeed);
	}
}

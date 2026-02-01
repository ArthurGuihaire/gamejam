package thegame;

import java.util.LinkedList;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Projectile extends ImageView {
	
	String type="";
	double projectilespeed;
	private double dx, dy;
	
	/**
	 * Creates a new Projectile object. Projectiles are fired from commands, so 
	 * @param cmd the command that will be firing the Projectile.
	 */
	Projectile(String cmd/*,LinkedList<ImageView> X*/,double x, double y,Node owner){
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
			System.out.println("SHREDDING"); projectilespeed=0.7;
			break;
			
		case "magick":
			this.type="magick";
			this.setImage(new Image(getClass().getResource("/images/fireball.png").toExternalForm()));
			System.out.println("MAGICK");
			projectilespeed=2;
			break;
		}

		
		this.setRotate(Math.toDegrees(Math.atan(dy / dx)));
		if (dx < 0) this.setRotate(this.getRotate() + 180);
	}


	public void move() {
		this.setLayoutX(this.getLayoutX() + dx * 10*projectilespeed);
		this.setLayoutY(this.getLayoutY() + dy * 10*projectilespeed);
		
		
	}
}

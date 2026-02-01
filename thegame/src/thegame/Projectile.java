package thegame;

import java.util.LinkedList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends ImageView {
	
	double projectilespeed;
	double xDestination;
	double yDestination;
	
	/**
	 * Creates a new Projectile object. Projectiles are fired from commands, so 
	 * @param cmd the command that will be firing the Projectile.
	 */
	Projectile(String cmd/*,LinkedList<ImageView> X*/,double x, double y){
		this.xDestination=x;
		this.yDestination=y;
		switch (cmd.toLowerCase().trim()) {
		case "shred":
			this.setImage(new Image(getClass().getResource("/images/purple-laser.png").toExternalForm()));
			System.out.println("SHREDDING"); projectilespeed=0.15;
			break;
		case "magick":
			System.out.println("MAGICK");
			projectilespeed=0.2;
			break;
		}

		this.setRotate(Math.toDegrees(Math.atan(xDestination-this.getLayoutX())));
	}
	
	
	public void move() {
		double dx = xDestination - this.getLayoutX();
		double dy = yDestination - this.getLayoutY();
		double distance = Math.sqrt(dx*dx+dy*dy);
		this.setLayoutX(this.getLayoutX() + (xDestination - this.getLayoutX()) / distance);
		this.setLayoutY(this.getLayoutY() + (yDestination - this.getLayoutY()) / distance);
		
		this.setRotate(Math.toDegrees(Math.atan(dy / dx)));
	}
	
}

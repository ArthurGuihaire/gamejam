package thegame;

import java.util.LinkedList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends ImageView {
	
	double projectilespeed;
	private double dx, dy;
	
	/**
	 * Creates a new Projectile object. Projectiles are fired from commands, so 
	 * @param cmd the command that will be firing the Projectile.
	 */
	Projectile(String cmd/*,LinkedList<ImageView> X*/,double x, double y){
		double dx = x - this.getLayoutX();
		double dy = y - this.getLayoutY();
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

		this.setRotate(Math.toDegrees(Math.atan(dy / dx)));
		if (dx > 0) this.setRotate(this.getRotate() + 180);
	}
	
	
	public void move() {
		double distance = Math.sqrt(dx*dx+dy*dy);
		
		this.setRotate(Math.toDegrees(Math.atan(dy / dx)));
		if (dx > 0) this.setRotate(this.getRotate() + 180);
	}
	
}

package thegame;

import java.util.LinkedList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends ImageView {
	
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
			this.setImage(new Image(getClass().getResource("/images/sawblade.png").toExternalForm()));
			System.out.println("SHREDDING"); projectilespeed=0.15;
			break;
		case "magick":
			System.out.println("MAGICK");
			projectilespeed=0.2;
			break;
		}

		this.setRotate(Math.toDegrees(Math.atan(dy / dx)));
		if (dx < 0) this.setRotate(this.getRotate() + 180);
	}


	public void move() {
		this.setLayoutX(this.getLayoutX() + dx * 10);
		this.setLayoutY(this.getLayoutY() + dy * 10);
	}
}

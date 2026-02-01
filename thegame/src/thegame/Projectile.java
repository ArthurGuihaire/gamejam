package thegame;

import java.util.LinkedList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile extends ImageView {
	
	/**
	 * Creates a new Projectile object. Projectiles are fired from commands, so 
	 * @param cmd the command that will be firing the Projectile.
	 */
	Projectile(String cmd/*,LinkedList<ImageView> X*/){
		switch (cmd.toLowerCase().trim()) {
		case "shred":
			this.setImage(new Image(getClass().getResource("/images/purple-laser.png").toExternalForm()));
			break;
		case "magick":
			System.out.println("thats crazy");
			break;
		}
	}
	
	
}

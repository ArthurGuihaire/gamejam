package thegame;

import javafx.scene.image.*;
import javafx.scene.layout.*;

public class Player extends ImageView {
	public static final int RM_DAMAGE = 20;
	int health;
	Enemy current = null;
	Enemy previous = null;
	public boolean moveMode = true;
	
	Player(){
		this.health=100;
		this.setImage(new Image(this.getClass().getResource("/testplayer.png").toExternalForm()));
		this.setScaleX(0.1);
		this.setScaleY(0.1);
	}
	
	public void moveRight() {
		this.setLayoutX(this.getLayoutX()+5);
		
	}
	public void moveLeft() {
		this.setLayoutX(this.getLayoutX()-5);
		
	}
	public void moveUp() {
		this.setLayoutY(this.getLayoutY()-5);
		
	}
	public void moveDown() {
		this.setLayoutY(this.getLayoutY()+5);
		
	}
}

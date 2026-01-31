package thegame;

import javafx.scene.image.*;
import javafx.scene.layout.*;

public class Player extends ImageView{
	
	int health;
	
	Player(){
		
		this.setImage(new Image(this.getClass().getResource("/testplayer.png").toExternalForm()));
		
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

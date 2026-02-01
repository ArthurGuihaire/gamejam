package thegame;

import javafx.scene.image.Image;

public class Boss extends Enemy {
	
	
	Boss(){
		
		damage=100;
		health=500;

		speed=0.5;
		defaultSpeed=0.5;
		this.setImage(new Image(getClass().getResource("/images/windows.png").toExternalForm()));
		this.setScaleX(2);
		this.setScaleY(2);
	}

}

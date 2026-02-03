package thegame;

import javafx.scene.image.Image;

public class Boss extends Enemy {
	
	
	Boss(){
		damage=100;
		health=900;

		speed=0.7;
		defaultSpeed=0.7;
		this.setImage(new Image(getClass().getResource("/images/windows.png").toExternalForm()));
		this.setScaleX(2.4);
		this.setScaleY(2.4);
	}

}

package thegame;

import javafx.scene.image.Image;

public class Boss extends Enemy {
	
	int health=10000;
	int dmg=100;
	double speed=0.5;
	double default_speed=0.5;
	
	Boss(){
		this.setImage(new Image(getClass().getResource("/images/windows.png").toExternalForm()));
		this.setScaleX(2);
		this.setScaleY(2);
	}

}

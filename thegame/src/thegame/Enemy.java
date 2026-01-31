package thegame;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Enemy extends ImageView {
	int health;
	Enemy() {
		int type=(int) Math.floor(Math.random()*3+1);
		switch (type) {
		case 1: this.setImage(new Image(getClass().getResource("/images/glass-pane-1.png").toExternalForm())); this.setScaleX(0.5); this.setScaleY(0.5); break;
		case 2: this.setImage(new Image(getClass().getResource("/images/glass-pane-2.png").toExternalForm())); this.setScaleX(0.5); this.setScaleY(0.5); break;
		case 3: this.setImage(new Image(getClass().getResource("/images/glass-pane-3.png").toExternalForm())); this.setScaleX(0.5); this.setScaleY(0.5); break;
		default: this.setImage(new Image(getClass().getResource("/images/tux-left.png").toExternalForm())); break;
		}
		
		
	}
	
	public void die() {
		double direction= (Math.random()*90);
		direction=Math.toRadians(direction);
		
		GlassShard gs1=new GlassShard();
		TranslateTransition tt1=new TranslateTransition(Duration.seconds(0.5), gs1);
		tt1.setByX(Math.cos(direction)*25);
		tt1.setByY(Math.sin(direction)*25);
		direction=Math.toDegrees(direction);
		direction+=120;
		direction=Math.toRadians(direction);
		
		GlassShard gs2=new GlassShard();
		TranslateTransition tt2=new TranslateTransition(Duration.seconds(0.5), gs2);
		tt1.setByX(Math.cos(direction)*25);
		tt1.setByY(Math.sin(direction)*25);
		direction=Math.toDegrees(direction);
		direction+=120;
		direction=Math.toRadians(direction);
		
		
		GlassShard gs3=new GlassShard();
		TranslateTransition tt3=new TranslateTransition(Duration.seconds(0.5), gs3);
		tt1.setByX(Math.cos(direction)*25);
		tt1.setByY(Math.sin(direction)*25);
		
		Maincontroller.soundPlayer.playSound(1);
	}
	

	private class GlassShard extends ImageView {
		
		GlassShard() {
			int type=(int) Math.floor(Math.random()*5+1);
			switch (type) {
			case 1: this.setImage(new Image(getClass().getResource("/images/shard-1.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 2: this.setImage(new Image(getClass().getResource("/images/shard-2.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 3: this.setImage(new Image(getClass().getResource("/images/shard-3.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 4: this.setImage(new Image(getClass().getResource("/images/shard-4.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 5: this.setImage(new Image(getClass().getResource("/images/shard-5.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			default: this.setImage(new Image(getClass().getResource("/images/tux-left.png").toExternalForm())); break;
			}
		}
		
	}
	
	
}



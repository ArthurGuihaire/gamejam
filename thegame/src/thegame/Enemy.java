package thegame;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
		
		
	}
	

	private class GlassShard extends ImageView {
		
		GlassShard() {
			int type=(int) Math.floor(Math.random()*5+1);
			switch (type) {
			case 1: this.setImage(new Image(getClass().getResource("/images/glass-shard-1.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 2: this.setImage(new Image(getClass().getResource("/images/glass-shard-2.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 3: this.setImage(new Image(getClass().getResource("/images/glass-shard-3.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 4: this.setImage(new Image(getClass().getResource("/images/glass-shard-3.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			case 5: this.setImage(new Image(getClass().getResource("/images/glass-shard-3.png").toExternalForm())); this.setScaleX(0.2); this.setScaleY(0.2); break;
			default: this.setImage(new Image(getClass().getResource("/images/tux-left.png").toExternalForm())); break;
			}
		}
		
	}
	
	
}



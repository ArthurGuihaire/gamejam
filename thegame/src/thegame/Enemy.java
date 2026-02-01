package thegame;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Enemy extends ImageView {
	int health = 100;
	int damage = 30;
	boolean dead=false;
	double speed = 1.0;
	double defaultSpeed = 1.0;
	
	public int speedTimer = 0;
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	Enemy() {
		int type=(int) Math.floor(Math.random()*3+1);
		switch (type) {
			case 1: this.setImage(new Image(getClass().getResource("/images/glass-pane-1.png").toExternalForm())); this.setScaleX(0.5); this.setScaleY(0.5); break;
			case 2: this.setImage(new Image(getClass().getResource("/images/glass-pane-2.png").toExternalForm())); this.setScaleX(0.5); this.setScaleY(0.5); break;
			case 3: this.setImage(new Image(getClass().getResource("/images/glass-pane-3.png").toExternalForm())); this.setScaleX(0.5); this.setScaleY(0.5); break;
			default: this.setImage(new Image(getClass().getResource("/images/purple-laser.png").toExternalForm())); break;
		}
	}
	
	public void moveTowards(Player p) {
		if (speedTimer-- == 0) {
			speed = defaultSpeed;
		}
		double px = p.getLayoutX();
		double py = p.getLayoutY();
		
		double ex = this.getLayoutX();
		double ey = this.getLayoutY();
		
		double dx = px - ex;
		double dy = py - ey;
		
		double distance = Math.sqrt(dx*dx+dy*dy);
		this.setLayoutX(ex + dx * speed / distance);
		this.setLayoutY(ey + dy * speed / distance);
	}
}

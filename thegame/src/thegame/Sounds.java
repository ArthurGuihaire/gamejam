package thegame;

import javafx.scene.media.*;
import java.io.File;
import java.util.ArrayList;

public class Sounds {
	private ArrayList<AudioClip> clips = new ArrayList<AudioClip>();
	public Sounds() {
		this.load("/sounds/output.wav");
		this.load("/sounds/glass-break.wav");
		this.load("/sounds/shred1.wav");
		this.load("/sounds/shred2.wav");
		this.load("/sounds/shred3.wav");
		this.load("/sounds/magick.wav");
		this.load("sounds/tree.wav");
		this.load("sounds/remove-rf.wav");
		
	}
	
	private void load(String soundFilePath) {
		String url = Sounds.class.getResource(soundFilePath).toExternalForm();
		clips.add(new AudioClip(url));
	}
	
	public void playSound(int index) {
		clips.get(index).play();
	}
}

package thegame;

import javafx.scene.media.*;
import java.util.ArrayList;

public class Sounds {
	
	private ArrayList<AudioClip> clips = new ArrayList<AudioClip>();
	public Sounds() {
		this.load("/sounds/output.wav");
		this.load("/sounds/glass-break.wav");
		this.load("/sounds/magick.wav");
		this.load("/sounds/tree.wav");
		this.load("/sounds/remove-rf-1.wav");
		this.load("/sounds/remove-rf-2.wav");
		this.load("/sounds/shred-force.wav");
		this.load("/sounds/remove.wav");
		this.load("/sounds/chmod.wav");
		this.load("/sounds/sound-file-test.wav");
		this.load("/sounds/oh-no.wav");
		this.load("/sounds/java.wav");
		
	}
	// just cuz we kinda recorded our sounds loud
	private static final double BASE_SOUND=0.35;
	static double counter=0;
	
	
	private void load(String soundFilePath) {
		String url = Sounds.class.getResource(soundFilePath).toExternalForm();
		clips.add(new AudioClip(url));
	}
	
	public void playSound(int index) {
		
		// haha yes so many more print statements 
//		if (index!=1) {System.out.println(counter);counter++;}
		AudioClip toplay=clips.get(index);
//		System.out.println(toplay.getVolume());
//		System.out.println(toplay.getBalance());
//		System.out.println(toplay.getRate());
//		System.out.println(toplay.getPan());
//		System.out.println(toplay.getPriority());
//		System.out.println();

		if (index!=1&&index<=11) {
		double randomrate=Math.random()*0.25+0.88;	
			
		toplay.play(BASE_SOUND, 0, randomrate, 1, 0);
		}
		else 
			toplay.play(BASE_SOUND, 0, 1, 1, 0);
		/*	different parameters of play():
		 *  volume (0.0-1.0), balance (-1.0,1.0), rate (0.0-inf), pan (-1.0,1.0), priority (0,idk)
		 *  OR:
		 *  volume (0.0-1.0)
		 *  OR:
		 *  no parameters
		 *  
		 *  default values of parameters: 1, 0, 1, 0, 0
		 */
	}
}

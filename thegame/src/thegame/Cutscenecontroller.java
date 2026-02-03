package thegame;

import java.io.IOException;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javafx.util.Duration;

public class Cutscenecontroller extends Main {
	
	@FXML
	ImageView windows,archlinux,tux;
	
	@FXML
	Text text;
	
	@FXML
	public void initialize() {
		TranslateTransition ohno=new TranslateTransition(Duration.seconds(0.3),windows);
		ohno.setOnFinished((event)->{
			Sounds bob=new Sounds();
			bob.playSound(13);
		});
		
		TranslateTransition ts=new TranslateTransition(Duration.seconds(2.8), windows);
		ts.setByX(-220);
		TranslateTransition runaway=new TranslateTransition(Duration.seconds(3.3), windows);
		runaway.setByX(3300);

		TranslateTransition comelinux=new TranslateTransition(Duration.seconds(3.3), archlinux);
		comelinux.setByX(3300);
		runaway.setOnFinished((event)->{

			FXMLLoader newscene=new FXMLLoader(Main.class.getResource("/tutorial.fxml"));
			newscene.setController(new Tutorialcontroller());
			try {
				Parent newp=newscene.load();
				s.setRoot(newp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		ts.setOnFinished((event)->{

			runaway.play();
			comelinux.play();
			
		});
		ohno.play();
		ts.play();
	}
}

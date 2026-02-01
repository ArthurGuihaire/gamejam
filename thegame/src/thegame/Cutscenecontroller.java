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
		TranslateTransition ts=new TranslateTransition(Duration.seconds(2.8), windows);
		ts.setByX(-220);
		TranslateTransition runaway=new TranslateTransition(Duration.seconds(3.5), windows);
		runaway.setByX(3500);

		TranslateTransition comelinux=new TranslateTransition(Duration.seconds(3.5), archlinux);
		comelinux.setByX(3500);
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

		ts.play();
	}
}

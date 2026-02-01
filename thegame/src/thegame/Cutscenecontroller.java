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
		TranslateTransition ts=new TranslateTransition(Duration.seconds(2.5), windows);
		ts.setByX(-220);
		TranslateTransition runaway=new TranslateTransition(Duration.seconds(4), windows);
		runaway.setByX(4000);

		TranslateTransition comelinux=new TranslateTransition(Duration.seconds(4), archlinux);
		comelinux.setByX(4000);
		runaway
		.setOnFinished((event)->{

			FXMLLoader newscene=new FXMLLoader(Main.class.getResource("/test.fxml"));
			newscene.setController(new Maincontroller());
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

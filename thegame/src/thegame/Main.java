package thegame;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hi");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader fl=new FXMLLoader(Main.class.getResource("/test.fxml"));
			fl.setController(new Maincontroller());
			Parent root=fl.load();
			
			Scene s=new Scene(root);
			primaryStage.setScene(s);
			
			primaryStage.show();
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

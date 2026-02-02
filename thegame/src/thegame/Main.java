package thegame;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("hi");
		launch(args);
	}
	public static boolean linuxmode=false;
	public static Scene s;
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fl=new FXMLLoader(Main.class.getResource("/mainmenu.fxml"));
			fl.setController(new MainMenucontroller());
			Parent root=fl.load();
			Maincontroller.main = this;
			
			s=new Scene(root,1800,900);
			primaryStage.setScene(s);
			primaryStage.setTitle("sound-file-test");
			primaryStage.show();
			primaryStage.setAlwaysOnTop(true);
			primaryStage.setAlwaysOnTop(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void switchScene() {
		FXMLLoader newscene=new FXMLLoader(Main.class.getResource("/openingcutscene.fxml"));
		newscene.setController(new Cutscenecontroller());
		try {
			Parent newp=newscene.load();
			s.setRoot(newp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

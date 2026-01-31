package thegame;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenucontroller {

	@FXML
	Button btnStart;
	
	@FXML
	public void initialize() {
		btnStart.setOnAction((event)->{
			Main.switchScene();
		});
	}
	
}

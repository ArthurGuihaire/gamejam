package thegame;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Ellipse;

public class MainMenucontroller extends Main {

	@FXML
	Button btnStart;
	@FXML
	Ellipse circleBelly,linuxmode;
	
	
	@FXML
	public void initialize() {
		btnStart.setOnAction((event)->{
			Main.switchScene();
		});
	circleBelly.setOnMouseClicked((event)->{
		btnStart.fire();
	});
	linuxmode.setOnMouseClicked((event)->{
		Main.linuxmode=true;
		btnStart.fire();
	});
		
	}
	
	
	
}

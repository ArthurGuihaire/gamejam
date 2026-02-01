package thegame;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Ellipse;

public class MainMenucontroller {

	@FXML
	Button btnStart;
	@FXML
	Ellipse circleBelly;
	
	@FXML
	public void initialize() {
		btnStart.setOnAction((event)->{
			Main.switchScene();
		});
	circleBelly.setOnMouseClicked((event)->{
		btnStart.fire();
	});
		
	}
	
	
	
}

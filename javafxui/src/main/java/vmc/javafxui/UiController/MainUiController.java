package vmc.javafxui.UiController;

import org.springframework.stereotype.Component;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

@Component
public class MainUiController {
	
	private final HostServices hostServices;
	
	@FXML
	public Label label;
	
	@FXML
	public Button button;
	
	public MainUiController(HostServices hostServices) {
		this.hostServices = hostServices;
	}
	
	@FXML
	public void initialize() {
		this.button.setOnAction(event -> this.label.setText(this.hostServices.getDocumentBase()));
		
	}
	
	

}

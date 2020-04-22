package vmc.javafxui.UiControler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import vmc.javafxui.beans.BuildingUserBean;
import vmc.javafxui.beans.UserBean;
import vmc.javafxui.proxies.UserProxy;

@Component
@Controller
public class MainUiController {
	
	private final HostServices hostServices;
	
	@Autowired
	private UserProxy userProxy;
	
	@FXML
	public Label label;
	public Button button;
	
	public MainUiController(HostServices hostServices) {
		this.hostServices = hostServices;
	}
	
	@FXML
	public void initialize() {
		this.button.setOnAction(event -> this.label.setText(this.hostServices.getDocumentBase()));
	}
}

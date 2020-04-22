package vmc.javafxui.UiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vmc.javafxui.beans.CityBean;
import vmc.javafxui.beans.UserBean;
import vmc.javafxui.proxies.CityProxy;
import vmc.javafxui.proxies.UserProxy;

@Component
@Controller
public class MainUiController {
	
	private final HostServices hostServices;
	
	@Autowired
	private UserProxy userProxy;
	
	@Autowired
	private CityProxy cityProxy;
	
	@FXML
	public Label label, userMail, userPassword, userRole;
	
	@FXML
	public Button button;
	
	public MainUiController(HostServices hostServices) {
		this.hostServices = hostServices;
	}
	
	@FXML
	public void initialize() {
		UserBean user = userProxy.getOneUser(2);
		CityBean city = cityProxy.getCityById(2);
		userMail.setText(user.getEmail());
		userPassword.setText(user.getPassword());
		userRole.setText(Integer.toString(user.getRole()));
		this.button.setOnAction(event -> this.label.setText(this.hostServices.getDocumentBase()));
	}
}

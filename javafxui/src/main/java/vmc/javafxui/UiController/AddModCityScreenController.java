package vmc.javafxui.UiController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vmc.javafxui.beans.CityBean;

@Controller
@Component
public class AddModCityScreenController implements Initializable {

	AppMainUiController main;
	
	@FXML
	TextField nameTextField;
	
	@FXML
	Button validateButton;
	
	public AddModCityScreenController() {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setMainApp(AppMainUiController mainApp) {
		this.main = mainApp;
	}
	
	public void validateButtonClick(Event event) {
		CityBean city = new CityBean();
		city.setName(nameTextField.getText());
		main.createCity(city);
		Stage stage = (Stage) validateButton.getScene().getWindow();
		stage.close();
	}

}

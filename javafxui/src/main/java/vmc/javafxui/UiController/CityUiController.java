package vmc.javafxui.UiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import vmc.javafxui.beans.CityBean;
import vmc.javafxui.proxies.CityProxy;
import vmc.javafxui.proxies.UserProxy;

@Component
@Controller
public class CityUiController {

	private final HostServices hostServices;
	
	@Autowired
	private CityProxy cityProxy;
	
	@FXML
	public ListView<String> cities;
	
	public CityUiController(HostServices hostServices) {
		this.hostServices = hostServices;
	}
	
	@FXML
	public void initialize() {
		for (CityBean cityBean : cityProxy.getCities()) {
			cities.getItems().add(cityBean.getName());
		}
	}
	
}

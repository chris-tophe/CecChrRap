package vmc.javafxui.UiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import vmc.javafxui.beans.CityBean;
import vmc.javafxui.beans.UserBean;
import vmc.javafxui.proxies.CityProxy;
import vmc.javafxui.proxies.UserProxy;

@Component
@Controller
public class CityUiController {

	private final HostServices hostServices;
	
	@Autowired
	private CityProxy cityProxy;
	
	@FXML
	public ListView<CityBean> cities;
	
	public CityUiController(HostServices hostServices) {
		this.hostServices = hostServices;
	}
	
	@FXML
	public void initialize() {
		for (CityBean cityBean : cityProxy.getCities()) {
			cities.getItems().add(cityBean);
		}
		
		cities.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	public void cityClick(Event e) throws Exception {
		ObservableList<CityBean> selectedCity;
		selectedCity = cities.getSelectionModel().getSelectedItems();
		System.out.println("You selected " + selectedCity.get(0).getName());
	}
	
	
	
}

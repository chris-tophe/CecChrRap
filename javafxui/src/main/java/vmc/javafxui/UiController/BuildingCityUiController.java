package vmc.javafxui.UiController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import vmc.javafxui.beans.BuildingCityBean;
import vmc.javafxui.beans.CityBean;

@Component
@Controller
public class BuildingCityUiController implements Initializable {
	
	@FXML
	ListView<BuildingCityBean> BuildingCityListView;
	
	AppMainUiController main;
	
	public void refresh() {		
		BuildingCityListView.getItems().clear();
		BuildingCityListView.getItems().addAll(main.getBuildingCityList());
	}
	
	public void setMainApp(AppMainUiController mainApp) {
        this.main = mainApp;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

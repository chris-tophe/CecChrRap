package vmc.javafxui.UiController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import vmc.javafxui.beans.BuildingBean;

@Component
@Controller
public class BuildingDetailsUiController implements Initializable {

	BuildingBean building;
	
	@FXML
	ListView<BuildingBean> BuildingDetailsView;
	
	AppMainUiController main;
	
	public void refresh() {
		
		BuildingDetailsView.getItems().clear();
		BuildingDetailsView.getItems().addAll(main.getBuildingDetails());
		
	}
	
	public void setMainApp(AppMainUiController mainApp) {
        
		this.main = mainApp;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}

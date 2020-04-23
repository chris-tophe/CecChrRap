package vmc.javafxui.UiController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import vmc.javafxui.beans.BuildingCityBean;
import vmc.javafxui.beans.BuildingUserBean;
import vmc.javafxui.beans.CityBean;

@Component
@Controller
public class BuildingUserUiController implements Initializable {
	
	@FXML
	ListView<BuildingUserBean> buildingUserListView;
	
	AppMainUiController main;
	
	public void refresh() {
		
		buildingUserListView.getItems().clear();
		buildingUserListView.getItems().addAll(main.getBuildingUserList());
		buildingUserListView.setCellFactory(BuildingUserListView -> new ListCell<BuildingUserBean>() {
			@Override
			public void updateItem(BuildingUserBean building, boolean empty) {
				super.updateItem(building, empty);
				if (empty) {
					setText(null);
				}
				else {
					setText(building.getName());
				}
			}
		});
	}
	
	public void setMainApp(AppMainUiController mainApp) {
        this.main = mainApp;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buildingUserListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
	}
}

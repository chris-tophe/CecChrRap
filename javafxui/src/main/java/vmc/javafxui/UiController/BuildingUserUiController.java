package vmc.javafxui.UiController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
			
			ImageView imageView = new ImageView();
			@Override
			public void updateItem(BuildingUserBean building, boolean empty) {
				super.updateItem(building, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				}
				else {
					setText(building.getName());
					String buildingPhotoUrl = building.getPhoto();
					Image buildingPhoto = new Image(buildingPhotoUrl);
					imageView.setFitHeight(20);
					imageView.setFitWidth(20);
					imageView.setPreserveRatio(true);
					imageView.setImage(buildingPhoto);
					setGraphic(imageView);
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
	
	// Récupère des informations d'un bâtiment sélectionné et envoie son id au contrôleur main pour refresh
	public void buildingUserClick(Event e) throws Exception {
		ObservableList<BuildingUserBean> selectedBuilding;
		selectedBuilding = buildingUserListView.getSelectionModel().getSelectedItems();
		if (!selectedBuilding.isEmpty())
		main.setSelectBuilding(selectedBuilding.get(0).getIdBuilding());
		//System.out.println("You selected the building with id : " + selectedBuilding.get(0).getIdBuilding());
	}
}

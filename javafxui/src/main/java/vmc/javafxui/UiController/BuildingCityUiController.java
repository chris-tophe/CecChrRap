package vmc.javafxui.UiController;

import java.net.URL;
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

@Component
@Controller
public class BuildingCityUiController implements Initializable {
	
	@FXML
	ListView<BuildingCityBean> BuildingCityListView;
	
	AppMainUiController main;
	
	public void setMainApp(AppMainUiController mainApp) {
        this.main = mainApp;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Rend les bâtiments de la liste sélectionnables
		BuildingCityListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	public void refresh() {
		
		BuildingCityListView.getItems().clear();
		BuildingCityListView.getItems().addAll(main.getBuildingCityList());
		
		// Met à jour les cellules pour n'afficher que le nom et la photo des bâtiments
		BuildingCityListView.setCellFactory(BuildingCityListView -> new ListCell<BuildingCityBean>() {
			
			ImageView imageView = new ImageView();
			@Override
			public void updateItem(BuildingCityBean buildingCity, boolean empty) {
				super.updateItem(buildingCity, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				}
				else {
					setText(buildingCity.getName());
					String buildingPhotoUrl = buildingCity.getPhotoUrl();
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
	
	// Récupère des informations d'un bâtiment sélectionné et envoie son id au contrôleur main pour refresh
	public void buildingCityClick(Event e) throws Exception {
		ObservableList<BuildingCityBean> selectedBuilding;
		selectedBuilding = BuildingCityListView.getSelectionModel().getSelectedItems();
		if (!selectedBuilding.isEmpty())
		main.setSelectBuilding(selectedBuilding.get(0).getIdBuildingCity());
		//System.out.println("You selected the building with id : " + selectedBuilding.get(0).getIdBuildingCity());
	}
}

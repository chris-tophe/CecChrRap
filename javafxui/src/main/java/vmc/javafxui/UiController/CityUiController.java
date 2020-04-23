package vmc.javafxui.UiController;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import vmc.javafxui.beans.CityBean;

@Component
@Controller
public class CityUiController {
	
	
	@FXML
	public ListView<CityBean> CityListView;
	ObservableList<String> cities;
	
	AppMainUiController main;
	
	public CityUiController() {
	}
	
	public void setMainApp(AppMainUiController mainApp) {
        this.main = mainApp;
    }
	
	@FXML
	public void initialize() {
		// Rend les villes de la liste sélectionnables
		CityListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	public void refresh() {
		CityListView.getItems().clear();
		CityListView.getItems().addAll(main.getCityList());
		
		// Met à jour les cellules pour n'afficher que le nom des villes
		CityListView.setCellFactory(CityListView -> new ListCell<CityBean>() {
			@Override
			public void updateItem(CityBean city, boolean empty) {
				super.updateItem(city, empty);
				if (empty) {
					setText(null);
				}
				else {
					setText(city.getName());
				}
			}
		});
		
	}
	
	// Récupère des informations d'une ville sélectionnée et envoie son id au contrôleur main pour refresh
	public void cityClick(Event e) throws Exception {
		ObservableList<CityBean> selectedCity;
		selectedCity = CityListView.getSelectionModel().getSelectedItems();
		//System.out.println("You selected city with id : " + selectedCity.get(0).getIdCity());
		main.setSelectCity(selectedCity.get(0));
	}
	
	
	
}

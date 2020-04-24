package vmc.javafxui.UiController;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import vmc.javafxui.beans.BuildingBean;
import vmc.javafxui.beans.BuildingCityBean;
import vmc.javafxui.beans.CityBean;
import vmc.javafxui.proxies.BuildingProxy;
import vmc.javafxui.proxies.CityProxy;

@Component
@Controller
public class EditBuildingUiController implements Initializable {
	
	@Autowired
	BuildingProxy buildingProxy;
	
	@Autowired
	CityProxy cityProxy;
	
	@FXML
	TextField 
	nameTextField, streetNumberTextField, streetTextField, 
	zipCodeTextField, cityNameTextField, latitudeTextField, 
	longitudeTextField, contructionYearTextField, ArchitextTextField,
	photo1TextField, photo2TextField, photo3TextField;
	
	@FXML
	ComboBox<CityBean> linkedCity;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		linkedCity.getItems().clear();
		linkedCity.getItems().addAll(cityProxy.getCities());
		
		// Met à jour les cellules pour n'afficher que le nom des villes
		linkedCity.setCellFactory(linkedCity -> new ListCell<CityBean>() {
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
	
	public void editBuildingClick(Event e) throws Exception {
		BuildingBean newBuilding = new BuildingBean();
		newBuilding.setName(nameTextField.getText());
		newBuilding.setStreetNumber(streetNumberTextField.getText());
		newBuilding.setStreetName(streetTextField.getText());
		newBuilding.setZipCode(zipCodeTextField.getText());
		newBuilding.setCityAddress(cityNameTextField.getText());
		newBuilding.setLatitude(latitudeTextField.getText());
		newBuilding.setLongitude(longitudeTextField.getText());
		if (contructionYearTextField.getText() != "") {
			newBuilding.setConstructionYear(Integer.parseInt(contructionYearTextField.getText()));
		}
		newBuilding.setArchitecte(ArchitextTextField.getAccessibleText());
		List<String> listPhotos = new LinkedList<String>();
		listPhotos.add(photo1TextField.getText());
		listPhotos.add(photo2TextField.getText());
		listPhotos.add(photo3TextField.getText());
		newBuilding.setPhotos(listPhotos);
		
		BuildingBean newBuildingCreated = buildingProxy.addBuilding(newBuilding);
		System.out.println(newBuildingCreated);
		
		BuildingCityBean newBuildingCityBean = new BuildingCityBean(newBuildingCreated);
		System.out.println(newBuildingCityBean);
		
		CityBean selectedCity = linkedCity.getSelectionModel().getSelectedItem();
		cityProxy.addBuildingToCity(newBuildingCityBean, selectedCity.getIdCity());
	}

}

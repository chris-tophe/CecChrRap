package vmc.javafxui.UiController;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import vmc.javafxui.beans.BuildingBean;
import vmc.javafxui.beans.BuildingCityBean;
import vmc.javafxui.beans.CityBean;
import vmc.javafxui.proxies.BuildingProxy;
import vmc.javafxui.proxies.CityProxy;

@Component
@Controller
public class EditBuildingUiController implements Initializable {
	
	AppMainUiController main;
	BuildingBean buildingSaved;
	
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
	TextArea descriptionTextField;
	
	@FXML
	ComboBox<CityBean> linkedCity;
	
	@FXML
	Button validateButton;
	
	public void setMainApp(AppMainUiController mainApp) {
        this.main = mainApp;
    }
	
	public void setBuildingSelected(BuildingBean buildingSelected) {
		this.buildingSaved = buildingSelected;
		this.fillFormFields(
				buildingSelected, descriptionTextField, 
				nameTextField, streetNumberTextField, streetTextField, 
				zipCodeTextField, cityNameTextField, latitudeTextField, 
				longitudeTextField, contructionYearTextField, ArchitextTextField, 
				photo1TextField, photo2TextField, photo3TextField);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		linkedCity.getItems().clear();
		linkedCity.getItems().addAll(cityProxy.getCities());
		
		// Met à jour la sélection pour n'afficher que le nom d'une ville
		linkedCity.setButtonCell(new ListCell<CityBean>() {
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
		
		if (buildingSaved == null) {
			buildingSaved = new BuildingBean();
			
			if (this.fillBuildingFields(
					buildingSaved, descriptionTextField,
					nameTextField, streetNumberTextField, streetTextField, 
					zipCodeTextField, cityNameTextField, latitudeTextField, 
					longitudeTextField, contructionYearTextField, ArchitextTextField, 
					photo1TextField, photo2TextField, photo3TextField
					)
				&& !linkedCity.getSelectionModel().isEmpty()) 
			{
				BuildingBean BuildingSavedInAPI = buildingProxy.addBuilding(buildingSaved);
				BuildingCityBean newBuildingCityBean = new BuildingCityBean(BuildingSavedInAPI);
				
				CityBean selectedCity = linkedCity.getSelectionModel().getSelectedItem();
				cityProxy.addBuildingToCity(newBuildingCityBean, selectedCity.getIdCity());
				
				Stage stage = (Stage) validateButton.getScene().getWindow();
				stage.close();
				
				this.showAlertSuccess();
			}
			else {
				this.showAlertEmptyFields();
			}
		}
		else {
			if (this.fillBuildingFields(
					buildingSaved, descriptionTextField,
					nameTextField, streetNumberTextField, streetTextField, 
					zipCodeTextField, cityNameTextField, latitudeTextField, 
					longitudeTextField, contructionYearTextField, ArchitextTextField, 
					photo1TextField, photo2TextField, photo3TextField
					)
				&& !linkedCity.getSelectionModel().isEmpty()) 
			{
				BuildingBean BuildingSavedInAPI = buildingProxy.updateBuilding(buildingSaved);
				CityBean selectedCity = linkedCity.getSelectionModel().getSelectedItem();
				BuildingCityBean buildingCityBean = cityProxy.getBuildingByPosition(selectedCity.getIdCity(), BuildingSavedInAPI.getIdBuilding());
				
				if (buildingCityBean == null) {
					cityProxy.addBuildingToCity(buildingCityBean, selectedCity.getIdCity());
				}
				else {
					buildingCityBean.setName(nameTextField.getText());
					buildingCityBean.setPhotoUrl(photo1TextField.getText());
					// Manque la sauvegarde du bâtiment dans la liste de la ville...
				}
				
				Stage stage = (Stage) validateButton.getScene().getWindow();
				stage.close();
				
				this.showAlertModSuccess();
			}
			else {
				this.showAlertEmptyFields();
			}
		}
		

	}
    
    private boolean fillBuildingFields(
    		BuildingBean building, TextArea description,
    		TextField name, TextField streetNumber, TextField streetName,
    		TextField zipCode, TextField city, TextField latitude, 
    		TextField longitude, TextField year, TextField architect, 
    		TextField photo1, TextField photo2, TextField photo3
    		)
    {
    	List<String> listPhotos = new LinkedList<String>();
    	
    	if (!description.getText().trim().isEmpty()) {
    		building.setDescription(description.getText());
		}
    	else {
    		building.setDescription("");
		}
    	if (!name.getText().trim().isEmpty()) {
			building.setName(name.getText());
		}
    	else {
			return false;
		}
    	if (!streetNumber.getText().trim().isEmpty()) {
			building.setStreetNumber(streetNumber.getText());
		}
    	else {
    		building.setStreetNumber("");
		}
    	if (!streetName.getText().trim().isEmpty()) {
			building.setStreetName(streetName.getText());
		}
    	else {
    		building.setStreetName("n/d");
		}
    	if (!zipCode.getText().trim().isEmpty()) {
			building.setZipCode(zipCode.getText());
		}
    	else {
    		building.setZipCode("n/d");
		}
    	if (!city.getText().trim().isEmpty()) {
			building.setCityAddress(city.getText());
		}
    	else {
    		building.setCityAddress("n/d");
		}
    	if (!latitude.getText().trim().isEmpty()) {
			building.setLatitude(latitude.getText());
		}
    	else {
    		building.setLatitude("n/d");
		}
    	if (!longitude.getText().trim().isEmpty()) {
			building.setLongitude(longitude.getText());
		}
    	else {
    		building.setLongitude("n/d");
		}
    	if (!year.getText().trim().isEmpty()) {
			building.setConstructionYear(Integer.parseInt(year.getText()));
		}
    	else {
    		building.setConstructionYear(0);
		}
    	if (!architect.getText().trim().isEmpty()) {
			building.setArchitecte(architect.getText());
		}
    	else {
    		building.setArchitecte("n/d");
		}
    	if (!photo1.getText().trim().isEmpty()) {
    		listPhotos.add(photo1.getText());
		}
    	else {
			return false;
		}
    	if (!photo2.getText().trim().isEmpty()) {
    		listPhotos.add(photo2.getText());
		}
    	if (!photo3.getText().trim().isEmpty()) {
    		listPhotos.add(photo3.getText());
		}
    	building.setPhotos(listPhotos);
    	return true;
    }
    
    private void fillFormFields(
    		BuildingBean building, TextArea description,
    		TextField name, TextField streetNumber, TextField streetName,
    		TextField zipCode, TextField city, TextField latitude, 
    		TextField longitude, TextField year, TextField architect, 
    		TextField photo1, TextField photo2, TextField photo3
    		)
    {
    	if (building.getDescription() != null) {
    		description.setText(building.getDescription());
		}
    	else {
			description.setText(null);
		}
    	if (building.getName() != null) {
    		name.setText(building.getName());
		}
    	else {
			name.setText(null);
		}
    	if (building.getStreetNumber() != null) {
    		streetNumber.setText(building.getStreetNumber());
		}
    	else {
			streetNumber.setText(null);
		}
    	if (building.getStreetName() != null) {
    		streetName.setText(building.getStreetName());
		}
    	else {
			streetName.setText(null);
		}
    	if (building.getZipCode() != null) {
    		zipCode.setText(building.getZipCode());
		}
    	else {
			zipCode.setText(null);
		}
    	if (building.getCityAddress() != null) {
    		city.setText(building.getCityAddress());
		}
    	else {
			city.setText(null);
		}
    	if (building.getLatitude() != null) {
    		latitude.setText(building.getLatitude());
		}
    	else {
			latitude.setText(null);
		}
    	if (building.getLongitude() != null) {
    		longitude.setText(building.getLongitude());
		}
    	else {
			longitude.setText(null);
		}
    	year.setText(Integer.toString(building.getConstructionYear()));
    	if (building.getArchitecte() != null) {
    		architect.setText(building.getArchitecte());
		}
    	else {
			architect.setText(null);
		}
    	if (building.getPhotos().get(0) != null) {
    		photo1.setText(building.getPhotos().get(0));
		}
    	else {
			photo1.setText(null);
		}
    	if (building.getPhotos().size() >= 2) {
    		photo2.setText(building.getPhotos().get(1));
		}
    	else {
			photo2.setText(null);
		}
    	if (building.getPhotos().size() >= 3) {
    		photo3.setText(building.getPhotos().get(2));
		}
    	else {
			photo3.setText(null);
		}
    }
    
    private void showAlertEmptyFields() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez donner au moins un nom, une photo et une ville de rattachement pour identifier le bâtiment.");
        alert.showAndWait();
    }
    
    private void showAlertSuccess() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout réussi");
        alert.setHeaderText(null);
        alert.setContentText("Le bâtiment a bien été ajouté !");
        alert.showAndWait();
    }
    
    private void showAlertModSuccess() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Modification réussie");
        alert.setHeaderText(null);
        alert.setContentText("Le bâtiment a bien été modifié !");
        alert.showAndWait();
    }

}

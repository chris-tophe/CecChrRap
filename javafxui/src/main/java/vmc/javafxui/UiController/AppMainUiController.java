package vmc.javafxui.UiController;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.sun.javafx.collections.ArrayListenerHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vmc.javafxui.beans.BuildingBean;
import vmc.javafxui.beans.BuildingCityBean;
import vmc.javafxui.beans.BuildingUserBean;
import vmc.javafxui.beans.CityBean;
import vmc.javafxui.beans.UserBean;
import vmc.javafxui.proxies.BuildingProxy;
import vmc.javafxui.proxies.CityProxy;
import vmc.javafxui.proxies.UserProxy;

@Component
@Controller
public class AppMainUiController implements Initializable {

	Resource buildingCityUi, cityUi, buildingUserUi, buildingDetailsUi , addModBuilginScreen;

	private final ApplicationContext appContext;

	BuildingCityUiController buildingCityUiController = new BuildingCityUiController();
	CityUiController cityUiController = new CityUiController();
	BuildingUserUiController buildingUserUiController = new BuildingUserUiController();
	BuildingDetailsUiController buildingDetailsUiController = new BuildingDetailsUiController();

	UserBean user = new UserBean();

	@FXML
	AnchorPane buildingCityViewPane, cityViewPane, buildingUserViewPane ,buildingDetailsPane;
	
	@FXML
	Button addBuildingButton, modBuildingButton;

	@Autowired
	CityProxy cities;

	@Autowired
	UserProxy userProxy;

	@Autowired
	BuildingProxy buildings;

	private List<BuildingCityBean> buildingCityList = new LinkedList<BuildingCityBean>();
	private List<CityBean> cityList = new LinkedList<CityBean>();
	private BuildingBean buildingDetails = new BuildingBean();

	public AppMainUiController(@Value("classpath:/buildingCityUi.fxml") Resource buildingCityUi,
			@Value("classpath:/cityUi.fxml") Resource cityUi,
			@Value("classpath:/buildingDetailsUi.fxml") Resource buildingDetailsUi,
			@Value("classpath:/buildingUserUi.fxml") Resource buildingUserUi,
			@Value("classpath:/addModBuilginScreen.fxml") Resource addModBuilginScreen,
			ApplicationContext appContext) {
		this.buildingUserUi = buildingUserUi;
		this.buildingCityUi = buildingCityUi;
		this.cityUi = cityUi;
		this.buildingDetailsUi = buildingDetailsUi;
		this.addModBuilginScreen = addModBuilginScreen;
		this.appContext = appContext;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VBox listViewBuildingCity = new VBox();
		VBox listViewCity = new VBox();
		VBox listViewBuildingDetails = new VBox();
		VBox listViewBuildingUser = new VBox();

		try {
			FXMLLoader buildingCityUiLoader = new FXMLLoader(this.buildingCityUi.getURL());
			FXMLLoader cityUiLoader = new FXMLLoader(this.cityUi.getURL());
			FXMLLoader buildingDetailsUiLoader = new FXMLLoader(this.buildingDetailsUi.getURL());
			FXMLLoader buildingUserUiLoader = new FXMLLoader(this.buildingUserUi.getURL());

			buildingCityUiLoader.setControllerFactory(appContext::getBean);
			cityUiLoader.setControllerFactory(appContext::getBean);
			buildingDetailsUiLoader.setControllerFactory(appContext::getBean);
			buildingUserUiLoader.setControllerFactory(appContext::getBean);

			listViewBuildingCity = (VBox) buildingCityUiLoader.load();
			listViewCity = (VBox) cityUiLoader.load();
			listViewBuildingDetails = (VBox) buildingDetailsUiLoader.load();
			listViewBuildingUser = (VBox) buildingUserUiLoader.load();

			buildingCityUiController = buildingCityUiLoader.getController();
			cityUiController = cityUiLoader.getController();
			buildingDetailsUiController = buildingDetailsUiLoader.getController();
			buildingUserUiController = buildingUserUiLoader.getController();

		} catch (IOException e) {
			e.printStackTrace();
		}

		AnchorPane.setBottomAnchor(listViewBuildingCity, (double) 0);
		AnchorPane.setTopAnchor(listViewBuildingCity, (double) 0);
		AnchorPane.setLeftAnchor(listViewBuildingCity, (double) 0);
		AnchorPane.setRightAnchor(listViewBuildingCity, (double) 0);

		AnchorPane.setBottomAnchor(listViewCity, (double) 0);
		AnchorPane.setTopAnchor(listViewCity, (double) 0);
		AnchorPane.setLeftAnchor(listViewCity, (double) 0);
		AnchorPane.setRightAnchor(listViewCity, (double) 0);

		AnchorPane.setBottomAnchor(listViewBuildingUser, (double) 0);
		AnchorPane.setTopAnchor(listViewBuildingUser, (double) 0);
		AnchorPane.setLeftAnchor(listViewBuildingUser, (double) 0);
		AnchorPane.setRightAnchor(listViewBuildingUser, (double) 0);

		AnchorPane.setBottomAnchor(listViewBuildingDetails, (double) 0);
		AnchorPane.setTopAnchor(listViewBuildingDetails, (double) 0);
		AnchorPane.setLeftAnchor(listViewBuildingDetails, (double) 0);
		AnchorPane.setRightAnchor(listViewBuildingDetails, (double) 0);

		buildingCityViewPane.getChildren().add(listViewBuildingCity);
		buildingCityUiController.setMainApp(this);
    
		cityViewPane.getChildren().add(listViewCity);
		cityUiController.setMainApp(this);
		this.cityList = cities.getCities();
		cityUiController.refresh();

		buildingUserViewPane.getChildren().add(listViewBuildingUser);
		buildingUserUiController.setMainApp(this);
		buildingUserUiController.refresh();

		buildingDetailsPane.getChildren().add(listViewBuildingDetails);
		buildingDetailsUiController.setMainApp(this);
	}

	public List<BuildingUserBean> getBuildingUserList() {
		return this.user.getBuildings();
	}

	public void setUser(UserBean user) {
		this.user = user;
		buildingUserUiController.refresh();
	}

	// pas testé !
	public void addBuildingToUser(BuildingBean building) {
		BuildingUserBean buildingCity = new BuildingUserBean(building);
		this.user.getBuildings().add(buildingCity);
		userProxy.updateUser(this.user);
		buildingUserUiController.refresh();
	}

	// Retourne la liste des bâtiments d'une ville
	public List<BuildingCityBean> getBuildingCityList() {
		return buildingCityList;
	}

	// Retourne la liste des villes
	public List<CityBean> getCityList(){return cityList;}
	
	// Retourne les détails d'un bâtiment
	public BuildingBean getBuildingDetails(){return buildingDetails;}

	// Détermine la liste des bâtiments selon la ville sélectionnée
	public void setSelectCity(CityBean city) {
		this.buildingCityList = cities.getBuildingByCityId(city.getIdCity());
		buildingCityUiController.refresh();
	}

	// Détermine le bâtiment sélectionné
	public void setSelectBuilding(int idBuilding) {
		this.buildingDetails = buildings.oneBuilding(idBuilding);
		buildingDetailsUiController.refresh();
	}
	
	// add mod Building Section
	
	public void displayBuildingWindow(Event event){
		
		try {
			FXMLLoader addModBuilginScreenLoader = new FXMLLoader(this.addModBuilginScreen.getURL());
			addModBuilginScreenLoader.setControllerFactory(appContext::getBean);
			Parent addModBuilginScreenScene = addModBuilginScreenLoader.load();
			//addModBuilginScreenController = addModBuilginScreenLoader.getController();
			//addModBuilginScreenController.setMainApp(this);
			Stage addModStage = new Stage();
			Scene addModScene = new Scene(addModBuilginScreenScene);
			addModStage.setScene(addModScene);
			addModStage.setTitle("Batiment");
			addModStage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		
	}

}

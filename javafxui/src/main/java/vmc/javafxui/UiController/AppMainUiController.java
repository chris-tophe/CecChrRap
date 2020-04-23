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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import vmc.javafxui.beans.BuildingBean;
import vmc.javafxui.beans.BuildingCityBean;
import vmc.javafxui.beans.CityBean;
import vmc.javafxui.proxies.BuildingProxy;
import vmc.javafxui.proxies.CityProxy;


@Component
@Controller
public class AppMainUiController implements Initializable{
	
	Resource buildingCityUi, cityUi, buildingDetailsUi;
	private final ApplicationContext appContext;
	
	BuildingCityUiController buildingCityUiController = new BuildingCityUiController() ;
	CityUiController cityUiController = new CityUiController();
	BuildingDetailsUiController buildingDetailsUiController = new BuildingDetailsUiController() ;
		
	@FXML
	AnchorPane buildingCityViewPane, cityViewPane, buildingDetailsPane;
	
	@Autowired
	CityProxy cities;
	
	@Autowired
	BuildingProxy buildings;
	
	private List<BuildingCityBean> buildingCityList = new LinkedList<BuildingCityBean>();
	private List<CityBean> cityList = new LinkedList<CityBean>();
	private BuildingBean buildingDetails = new BuildingBean();
	
	public AppMainUiController(
			@Value("classpath:/buildingCityUi.fxml") Resource buildingCityUi,
			@Value("classpath:/cityUi.fxml") Resource cityUi,
			@Value("classpath:/buildingDetailsUi.fxml") Resource buildingDetailsUi,
			ApplicationContext appContext  ) {
		this.buildingCityUi = buildingCityUi;
		this.cityUi = cityUi;
		this.buildingDetailsUi = buildingDetailsUi;
		this.appContext = appContext;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VBox listViewBuildingCity = new VBox();
		VBox listViewCity = new VBox();
		VBox listViewBuildingDetails = new VBox();
		
		try {
			FXMLLoader buildingCityUiLoader = new FXMLLoader(this.buildingCityUi.getURL());
			FXMLLoader cityUiLoader = new FXMLLoader(this.cityUi.getURL());
			FXMLLoader buildingDetailsUiLoader = new FXMLLoader(this.buildingDetailsUi.getURL());
			
			buildingCityUiLoader.setControllerFactory(appContext::getBean);
			cityUiLoader.setControllerFactory(appContext::getBean);
			buildingDetailsUiLoader.setControllerFactory(appContext::getBean);
			
			listViewBuildingCity = (VBox) buildingCityUiLoader.load();
			listViewCity = (VBox) cityUiLoader.load();
			listViewBuildingDetails = (VBox) buildingDetailsUiLoader.load();
			
			buildingCityUiController = buildingCityUiLoader.getController() ;
			cityUiController = cityUiLoader.getController() ;
			buildingDetailsUiController = buildingDetailsUiLoader.getController() ;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AnchorPane.setBottomAnchor(listViewBuildingCity, (double) 0);
		AnchorPane.setTopAnchor(listViewBuildingCity,  (double) 0);
		AnchorPane.setLeftAnchor(listViewBuildingCity, (double)  0);
		AnchorPane.setRightAnchor(listViewBuildingCity, (double)  0);
		
		AnchorPane.setBottomAnchor(listViewCity, (double) 0);
		AnchorPane.setTopAnchor(listViewCity,  (double) 0);
		AnchorPane.setLeftAnchor(listViewCity, (double)  0);
		AnchorPane.setRightAnchor(listViewCity, (double)  0);
		
		AnchorPane.setBottomAnchor(listViewBuildingDetails, (double) 0);
		AnchorPane.setTopAnchor(listViewBuildingDetails,  (double) 0);
		AnchorPane.setLeftAnchor(listViewBuildingDetails, (double)  0);
		AnchorPane.setRightAnchor(listViewBuildingDetails, (double)  0);
		
		buildingCityViewPane.getChildren().add(listViewBuildingCity);
		buildingCityUiController.setMainApp(this);
		//this.buildingCityList = cities.getBuildingByCityId();
		buildingCityUiController.refresh();
		
		cityViewPane.getChildren().add(listViewCity);
		cityUiController.setMainApp(this);
		this.cityList = cities.getCities();
		cityUiController.refresh();
		
		buildingDetailsPane.getChildren().add(listViewBuildingDetails);
		buildingDetailsUiController.setMainApp(this);
		this.buildingDetails = buildings.oneBuilding(1);
		buildingDetailsUiController.refresh();
		
	}

	// Retourne la liste des bâtiments d'une ville
	public List<BuildingCityBean> getBuildingCityList(){return buildingCityList;}

	// Retourne la liste des villes
	public List<CityBean> getCityList(){return cityList;}
	public BuildingBean getBuildingDetails(){return buildingDetails;}
	
	
	// Détermine la liste des bâtiments selon la ville sélectionnée
	public void setSelectCity(CityBean city) {
		this.buildingCityList = cities.getBuildingByCityId(city.getIdCity());
		buildingCityUiController.refresh();
	}
	
	// Détermine le bâtiment sélectionné
	public void setSelectBuilding(int idBuilding) {
		this.buildingDetails = buildings.oneBuilding(idBuilding);
		//BuildingDetailsUiController.refresh();
	}

}

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
import vmc.javafxui.beans.BuildingUserBean;
import vmc.javafxui.beans.CityBean;
import vmc.javafxui.beans.UserBean;
import vmc.javafxui.proxies.CityProxy;
import vmc.javafxui.proxies.UserProxy;

@Component
@Controller
public class AppMainUiController implements Initializable {

	Resource buildingCityUi, cityUi , buildingUserUi;
	
	private final ApplicationContext appContext;
	
	BuildingCityUiController buildingCityUiController = new BuildingCityUiController();
	CityUiController cityUiController = new CityUiController();
	BuildingUserUiController buildingUserUiController = new BuildingUserUiController();
	
	UserBean user = new UserBean();
	
	@FXML
	AnchorPane buildingCityViewPane, cityViewPane, buildingUserViewPane;

	@Autowired
	CityProxy cities;
	@Autowired
	UserProxy userProxy;

	private List<BuildingCityBean> buildingCityList = new LinkedList<BuildingCityBean>();
	private List<CityBean> cityList = new LinkedList<CityBean>();

	public AppMainUiController(@Value("classpath:/buildingCityUi.fxml") Resource buildingCityUi,
			@Value("classpath:/cityUi.fxml") Resource cityUi,
			@Value("classpath:/buildingUserUi.fxml") Resource buildingUserUi,
			ApplicationContext appContext) {
		this.buildingCityUi = buildingCityUi;
		this.cityUi = cityUi;
		this.buildingUserUi = buildingUserUi;
		this.appContext = appContext;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VBox listViewBuildingCity = new VBox();
		VBox listViewCity = new VBox();
		VBox listViewBuildingUser = new VBox();

		try {
			FXMLLoader buildingCityUiLoader = new FXMLLoader(this.buildingCityUi.getURL());
			FXMLLoader cityUiLoader = new FXMLLoader(this.cityUi.getURL());
			FXMLLoader buildingUserUiLoader = new FXMLLoader(this.buildingUserUi.getURL());

			buildingCityUiLoader.setControllerFactory(appContext::getBean);
			cityUiLoader.setControllerFactory(appContext::getBean);
			buildingUserUiLoader.setControllerFactory(appContext::getBean);

			listViewBuildingCity = (VBox) buildingCityUiLoader.load();
			listViewCity = (VBox) cityUiLoader.load();
			listViewBuildingUser = (VBox) buildingUserUiLoader.load();

			buildingCityUiController = buildingCityUiLoader.getController();
			cityUiController = cityUiLoader.getController();
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

		buildingCityViewPane.getChildren().add(listViewBuildingCity);
		buildingCityUiController.setMainApp(this);

		cityViewPane.getChildren().add(listViewCity);
		cityUiController.setMainApp(this);
		this.cityList = cities.getCities();
		cityUiController.refresh();
		
		buildingUserViewPane.getChildren().add(listViewBuildingUser);
		buildingUserUiController.setMainApp(this);
		buildingUserUiController.refresh();
	}

	public List<BuildingCityBean> getBuildingCityList() {
		return buildingCityList;
	}

	public List<CityBean> getCityList() {
		return cityList;
	}

	public List<BuildingUserBean> getBuildingUserList() {
		return this.user.getBuildings();
	}
	
	public void setUser(UserBean user) {
		this.user = user;
		buildingUserUiController.refresh();
	}
	//pas testé !
	public void addBuildingToUser(BuildingBean building) {
		BuildingUserBean buildingCity = new BuildingUserBean(building);
		this.user.getBuildings().add(buildingCity);
		userProxy.updateUser(this.user);
		buildingUserUiController.refresh();
	}
	

}

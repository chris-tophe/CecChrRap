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
import vmc.javafxui.beans.BuildingCityBean;
import vmc.javafxui.proxies.CityProxy;


@Component
@Controller
public class AppMainUiController implements Initializable{
	
	Resource buildingCityUi;
	private final ApplicationContext appContext;
		
	@FXML
	AnchorPane buildingCityViewPane;
	
	@Autowired
	CityProxy cities;
	
	private List<BuildingCityBean> buildingCityList = new LinkedList<BuildingCityBean>();
	
	
	public AppMainUiController(@Value("classpath:/buildingCityUi.fxml") Resource buildingCityUi, ApplicationContext appContext  ) {
		this.buildingCityUi = buildingCityUi;
		this.appContext = appContext;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VBox listViewBuildingCity = new VBox();
		BuildingCityUiController buildingCityUiController = new BuildingCityUiController() ;
		try {
			FXMLLoader buildingCityUiLoader = new FXMLLoader(this.buildingCityUi.getURL());
			buildingCityUiLoader.setControllerFactory(appContext::getBean);
			listViewBuildingCity = (VBox) buildingCityUiLoader.load();
			buildingCityUiController = buildingCityUiLoader.getController() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		AnchorPane.setBottomAnchor(listViewBuildingCity, (double) 0);
		AnchorPane.setTopAnchor(listViewBuildingCity,  (double) 0);
		AnchorPane.setLeftAnchor(listViewBuildingCity, (double)  0);
		AnchorPane.setRightAnchor(listViewBuildingCity, (double)  0);
		buildingCityViewPane.getChildren().add(listViewBuildingCity);
		buildingCityUiController.setMainApp(this);
		this.buildingCityList = cities.getBuildingByCityId(2);
		buildingCityUiController.refresh();
		
	}

	public List<BuildingCityBean> getBuildingCityList(){return buildingCityList;}

}

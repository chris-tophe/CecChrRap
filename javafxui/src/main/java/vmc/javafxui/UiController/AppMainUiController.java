package vmc.javafxui.UiController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


@Component
@Controller
public class AppMainUiController implements Initializable{
	
	Resource buildingCityUi;
	private final ApplicationContext appContext;
	
	@FXML
	AnchorPane buildingCityViewPane;
	
	public AppMainUiController(@Value("classpath:/buildingCityUi.fxml") Resource buildingCityUi, ApplicationContext appContext  ) {
		this.buildingCityUi = buildingCityUi;
		this.appContext = appContext;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VBox listViewBuildingCity;
		try {
			FXMLLoader buildingCityUiLoader = new FXMLLoader(this.buildingCityUi.getURL());
			buildingCityUiLoader.setControllerFactory(appContext::getBean);
			listViewBuildingCity = (VBox) buildingCityUiLoader.load();
			AnchorPane.setBottomAnchor(listViewBuildingCity, (double) 0);
			AnchorPane.setTopAnchor(listViewBuildingCity,  (double) 0);
			AnchorPane.setLeftAnchor(listViewBuildingCity, (double)  0);
			AnchorPane.setRightAnchor(listViewBuildingCity, (double)  0);
			buildingCityViewPane.getChildren().add(listViewBuildingCity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}

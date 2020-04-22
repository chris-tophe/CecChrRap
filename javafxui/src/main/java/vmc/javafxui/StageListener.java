package vmc.javafxui;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {
	
	private final String appTitle;
	private final Resource fxml;
	private final ApplicationContext appContext;
	
	public StageListener(@Value("${spring.application.ui.title}") String appTitle,
			@Value("classpath:/mainui.fxml") Resource resource, ApplicationContext appContext) {
		this.appTitle = appTitle;
		this.fxml = resource;
		this.appContext = appContext;
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		
		try {
			Stage stage = event.getStage();
			URL url = this.fxml.getURL();
			FXMLLoader fxmlLoader = new FXMLLoader(url);
			fxmlLoader.setControllerFactory(appContext::getBean);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root,600,600);
			stage.setScene(scene);
			stage.setTitle(this.appTitle);
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

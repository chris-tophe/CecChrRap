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
	private final Resource loginUiFxml;
	private final ApplicationContext appContext;
	
	public StageListener(@Value("${spring.application.ui.title}") String appTitle,
			@Value("classpath:/loginUi.fxml") Resource loginUiFxml,
			ApplicationContext appContext) {
		this.appTitle = appTitle;
		this.loginUiFxml = loginUiFxml;
		this.appContext = appContext;
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		
		try {
			Stage stage = event.getStage();
			URL urlLogin = this.loginUiFxml.getURL();
			FXMLLoader fxmlLoginLoader = new FXMLLoader(urlLogin);
			fxmlLoginLoader.setControllerFactory(appContext::getBean);
			Parent login = fxmlLoginLoader.load();
			Scene sceneLogin = new Scene(login);
			stage.setScene(sceneLogin);
			stage.setTitle(this.appTitle);
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

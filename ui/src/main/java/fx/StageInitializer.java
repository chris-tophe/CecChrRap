package fx;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import fx.Main.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent>{

	@Value("classpath:/hellofx.fxml")
	private Resource chartResource;
	
	
	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		
		System.out.println("onEvent");
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(chartResource.getURL());
			System.out.println(fxmlLoader);
			Parent parent = fxmlLoader.load();
			Stage stage = event.getStage();
			stage.setScene(new Scene(parent, 800, 600));
			stage.show();
			
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
		
		
		
		
	}

}

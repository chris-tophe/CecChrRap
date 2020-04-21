package fx;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import vmc.ui.UiApplication;

public class Main extends Application {

	private ConfigurableApplicationContext applicationContext;
	
	@Override
	public void init() {
		
		applicationContext = new SpringApplicationBuilder(UiApplication.class).run();

		System.out.println();
		System.out.println("Context : "+applicationContext);
		
	}
	
	
    @Override
    public void start(Stage primaryStage) throws Exception{
        
    	System.out.println("start : "+primaryStage);
    	
    	StageReadyEvent stageReady = new StageReadyEvent(primaryStage);
    	
    	System.out.println("stageReady : "+stageReady);
    	
    	applicationContext.publishEvent(stageReady);
    	
    	System.out.println("new SRE");
    	
    	//lancement écran
    	//Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 400, 300));
        //primaryStage.show();
    }


    @Override
    public void stop() {
    	
    	applicationContext.close();
    	System.out.println("Close");
    	Platform.exit();
    	
    }
    
    static class StageReadyEvent extends ApplicationEvent{

		public StageReadyEvent(Stage stage) {
			super(stage);
			System.out.println("construct : "+stage);
			
		}
   	
		public Stage getStage() {
			System.out.println("getSource");
			return ((Stage) getSource());
						
		}
		
    }
    
}
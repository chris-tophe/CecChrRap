package fx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

@Controller
public class ControllerFX {

    @FXML
    private Label label;
    
    @Autowired
	private BuildingProxy buildingProxy;

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        
        //java.util.List<Building> buildings = buildingProxy.listBuildings();
        
        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    }
}
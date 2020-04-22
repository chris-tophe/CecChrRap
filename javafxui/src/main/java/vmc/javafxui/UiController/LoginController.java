package vmc.javafxui.UiController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import vmc.javafxui.beans.UserBean;
import vmc.javafxui.proxies.UserProxy;

@Component
@Controller
public class LoginController implements Initializable {

	@FXML
	Button connexionButton;
	@FXML
	TextField loginField;
	@FXML
	PasswordField passwordField;

	@Autowired
	UserProxy userproxy;
	
	Resource appMainuiFxml;

	public LoginController(@Value("classpath:/appMainUi.fxml") Resource appMainuiFxml) {
		this.appMainuiFxml = appMainuiFxml;
	}

	public void loginClick(Event e) throws Exception {
		UserBean user = null;
		List<UserBean> users = userproxy.listUsers();
		for (UserBean u : users) {
			if (u.getEmail().equals(loginField.getText()) && u.getPassword().equals(passwordField.getText())) {
				user = u ;
				break;
			}
		}
		if (user != null) {
			 Stage stage;
			 Parent root;
			 stage = (Stage) connexionButton.getScene().getWindow();
	         root = FXMLLoader.load(this.appMainuiFxml.getURL());
	         Scene scene = new Scene(root);
	         //stage.close();
	         stage.setScene(scene);
	         stage.show();
			
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loginField.setText("admin@mail");
		passwordField.setText("admin");
	}

}

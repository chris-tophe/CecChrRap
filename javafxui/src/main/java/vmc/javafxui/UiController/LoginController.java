package vmc.javafxui.UiController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
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
import vmc.javafxui.beans.UserBean;
import vmc.javafxui.proxies.UserProxy;

@Component
@Controller
public class LoginController implements Initializable {

	private final ApplicationContext appContext;
	
	@FXML
	Button connexionButton;
	@FXML
	TextField loginField;
	@FXML
	PasswordField passwordField;

	@Autowired
	UserProxy userproxy;
	
	Resource appMainuiFxml;

	public LoginController(@Value("classpath:/appMainUi.fxml") Resource appMainuiFxml, ApplicationContext appContext) {
		this.appContext = appContext;
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
			 FXMLLoader fxmlLoader = new FXMLLoader(this.appMainuiFxml.getURL());
			 fxmlLoader.setControllerFactory(appContext::getBean);
	         root = fxmlLoader.load();
	         AppMainUiController controller = fxmlLoader.getController();
	         controller.setUser(user);
	         Scene scene = new Scene(root);
	         stage.setScene(scene);
	         stage.show();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginField.setText("admin@gmail.fr");
		passwordField.setText("admin");
	}

}

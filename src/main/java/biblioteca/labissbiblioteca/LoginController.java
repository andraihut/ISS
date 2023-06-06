package biblioteca.labissbiblioteca;

import biblioteca.labissbiblioteca.domain.Admin;
import biblioteca.labissbiblioteca.domain.Client;
import biblioteca.labissbiblioteca.domain.User;
import biblioteca.labissbiblioteca.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private Service service;

    public void setService(Service service){
        this.service = service;
    }
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField parolaTxt;
    @FXML
    private TextField usernameTxt;
    @FXML
    void loginClick(MouseEvent event) throws IOException {
        String username = usernameTxt.getText();
        String password = parolaTxt.getText();
        User user = service.findUser(username,password);
        if(user == null)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController controller = fxmlLoader.getController();
            controller.setMessage("Datele de conectare sunt gresite!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        else if(user.getClass() == Client.class){
            Stage st = (Stage) btnLogin.getScene().getWindow();
            st.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
            Parent root = fxmlLoader.load();
            ClientController controller = fxmlLoader.getController();
            controller.setService(this.service);
            controller.setClient((Client) user);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Welcome Client: " + ((Client) user).getFirst_name());
            stage.show();
        }
        else if(user.getClass() == Admin.class){
            Stage st = (Stage) btnLogin.getScene().getWindow();
            st.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            Parent root = fxmlLoader.load();
            AdminController controller = fxmlLoader.getController();
            controller.setService(this.service);
            controller.setAdmin((Admin) user);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Welcome Admin: " + ((Admin) user).getFirst_name());
            stage.show();
        }
    }

}

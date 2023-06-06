package biblioteca.labissbiblioteca;

import biblioteca.labissbiblioteca.domain.Client;
import biblioteca.labissbiblioteca.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddClientController {
    private Service service;
    public void setService(Service sr){this.service = sr;}
    @FXML
    private TextField usernameTxt;
    @FXML
    private TextField parolaTxt;
    @FXML
    private TextField firstnameTxt;
    @FXML
    private TextField lastnameTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    void addClientClick(MouseEvent event) throws IOException {
        if( Objects.equals(usernameTxt.getText(), "") || Objects.equals(parolaTxt.getText(), "") || Objects.equals(firstnameTxt.getText(), "") || Objects.equals(lastnameTxt.getText(), "") || Objects.equals(emailTxt.getText(), "")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController controller = fxmlLoader.getController();
            controller.setMessage("Toate campurile trebuie completate corect!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }else{
            String username = usernameTxt.getText();
            String parola = parolaTxt.getText();
            String firstname = firstnameTxt.getText();
            String lastname = lastnameTxt.getText();
            String email = emailTxt.getText();
            Client client = new Client(0,username,parola,firstname,lastname,email);
            //add Client
            service.addClient(client);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController controller = fxmlLoader.getController();
            controller.setMessage("Clientul a fost adaugat cu succes!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

}

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

public class UpdateClientController {
    public Service service;
    public void setService(Service service) {
        this.service = service;
    }
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
    private TextField idTxt;
    @FXML
    void updateClientClick(MouseEvent event) throws IOException {
        if(Objects.equals(idTxt.getText(), "") || Objects.equals(usernameTxt.getText(), "") || Objects.equals(parolaTxt.getText(), "") || Objects.equals(firstnameTxt.getText(), "") || Objects.equals(lastnameTxt.getText(), "") || Objects.equals(emailTxt.getText(), "")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController controller = fxmlLoader.getController();
            controller.setMessage("Toate campurile trebuie completate!");
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
            try{
                int id = Integer.parseInt(idTxt.getText());
                try{
                    Client clientGasit = service.findClient(id);
                    Client client = new Client(clientGasit.getId(), username, parola, firstname, lastname, email);
                    service.updateClient(client);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
                    Parent root = fxmlLoader.load();
                    MessageController controller = fxmlLoader.getController();
                    controller.setMessage("Actualizare informatii client cu succes.");
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }
                catch (Exception e){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
                    Parent root = fxmlLoader.load();
                    MessageController controller = fxmlLoader.getController();
                    controller.setMessage("Clientul nu exista!");
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }
            }catch (Exception e){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController controller = fxmlLoader.getController();
                controller.setMessage("Toate campurile trebuie completate!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }

}

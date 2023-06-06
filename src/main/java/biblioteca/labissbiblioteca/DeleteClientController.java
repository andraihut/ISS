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

public class DeleteClientController {
    private Service service;

    public void setService(Service service) {
        this.service = service;
    }
    @FXML
    private TextField idClientTxt;
    @FXML
    void deleteClientClick(MouseEvent event) throws IOException {
        if(Objects.equals(idClientTxt.getText(), "")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController controller = fxmlLoader.getController();
            controller.setMessage("Introduceti id-ul!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }else{
            try{
                int id = Integer.parseInt(idClientTxt.getText());
                Client clientGasit = service.findClient(id);
                if(clientGasit.getId() == id) {
                    service.deleteClient(id);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
                    Parent root = fxmlLoader.load();
                    MessageController controller = fxmlLoader.getController();
                    controller.setMessage("Clientul a fost sters!");
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    Stage st = (Stage) idClientTxt.getScene().getWindow();
                    st.close();
                }
                else {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
                    Parent root = fxmlLoader.load();
                    MessageController controller = fxmlLoader.getController();
                    controller.setMessage("Nu exista client cu acest id!");
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    Stage st = (Stage) idClientTxt.getScene().getWindow();
                    st.close();
                }
            }catch (Exception e){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController controller = fxmlLoader.getController();
                controller.setMessage("Nu exista client cu acest id!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }

}

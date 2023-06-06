package biblioteca.labissbiblioteca;

import biblioteca.labissbiblioteca.domain.Client;
import biblioteca.labissbiblioteca.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ClientController {
    private Client client;
    public void setClient(Client client){
        this.client = client;
    }
    private Service service;
    public void setService(Service service){
        this.service = service;
    }

    @FXML
    void rentBookClick(MouseEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RentBookView.fxml"));
        Parent root = fxmlLoader.load();
        RentBookController controller = fxmlLoader.getController();
        controller.setService(service);
        controller.setClient(client);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void returnBookClick(MouseEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReturnBookView.fxml"));
        Parent root = fxmlLoader.load();
        ReturnBookController controller = fxmlLoader.getController();
        controller.setService(service);
        controller.setClient(client);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

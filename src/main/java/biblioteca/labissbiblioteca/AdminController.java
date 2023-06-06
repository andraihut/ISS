package biblioteca.labissbiblioteca;

import biblioteca.labissbiblioteca.domain.Admin;
import biblioteca.labissbiblioteca.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminController {
    private Admin admin;
    public void setAdmin(Admin admin){
        this.admin = admin;
    }
    private Service service;
    public void setService(Service service){
        this.service = service;
    }
    @FXML
    void addBookClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddBookView.fxml"));
        Parent root = fxmlLoader.load();
        AddBookController controller = fxmlLoader.getController();
        controller.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Adauga o carte.");
        stage.show();
    }

    @FXML
    void addClientClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddClientView.fxml"));
        Parent root = fxmlLoader.load();
        AddClientController controller = fxmlLoader.getController();
        controller.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Adauga un client.");
        stage.show();
    }

    @FXML
    void deleteBookClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteBookView.fxml"));
        Parent root = fxmlLoader.load();
        DeleteBookController controller = fxmlLoader.getController();
        controller.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sterge o carte.");
        stage.show();
    }

    @FXML
    void deleteClientClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteClientView.fxml"));
        Parent root = fxmlLoader.load();
        DeleteClientController controller = fxmlLoader.getController();
        controller.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sterge un client.");
        stage.show();
    }

    @FXML
    void updateClientClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateClientView.fxml"));
        Parent root = fxmlLoader.load();
        UpdateClientController controller = fxmlLoader.getController();
        controller.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Modifica un client.");
        stage.show();
    }

    @FXML
    void viewBooksClick(MouseEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewBooksView.fxml"));
        Parent root = fxmlLoader.load();
        ViewBooksController controller = fxmlLoader.getController();
        controller.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Lista tuturor cartilor.");
        stage.show();
    }

    @FXML
    void viewClientsClick(MouseEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewClientsView.fxml"));
        Parent root = fxmlLoader.load();
        ViewClientsController controller = fxmlLoader.getController();
        controller.setService(service);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Lista tuturor clientilor.");
        stage.show();
    }
}

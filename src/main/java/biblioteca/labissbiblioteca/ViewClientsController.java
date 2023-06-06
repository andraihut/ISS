package biblioteca.labissbiblioteca;
import biblioteca.labissbiblioteca.domain.Client;
import biblioteca.labissbiblioteca.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ViewClientsController {
    private Service service;
    ObservableList<Client> clients = FXCollections.observableArrayList();
    public void setService(Service service) throws SQLException
    {
        this.service = service;
        idColumn.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("username"));
        parolaColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("password"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("first_name"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("last_name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("email"));
        for(Client client: service.getAllClients())
            clients.add(client);
        table.setItems(clients);
    }
    @FXML
    private TableView<Client> table;
    @FXML
    private TableColumn<Client, Integer> idColumn;
    @FXML
    private TableColumn<Client, String> usernameColumn;
    @FXML
    private TableColumn<Client, String> parolaColumn;
    @FXML
    private TableColumn<Client, String> firstnameColumn;
    @FXML
    private TableColumn<Client, String> lastnameColumn;
    @FXML
    private TableColumn<Client, String> emailColumn;

}

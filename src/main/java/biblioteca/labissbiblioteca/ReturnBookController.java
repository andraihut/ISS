package biblioteca.labissbiblioteca;

import biblioteca.labissbiblioteca.domain.Book;
import biblioteca.labissbiblioteca.domain.Client;
import biblioteca.labissbiblioteca.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ReturnBookController {
    public Service service;
    public Client client;
    public void setService(Service service) {
        this.service = service;
    }
    public void setClient(Client client) throws SQLException {
        this.client = client;
        idColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("year"));
        for(Book book: service.getRentedBooks(client)){
            list.add(book);}
        table.setItems(list);
    }
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, Integer> yearColumn;
    ObservableList<Book> list = FXCollections.observableArrayList();
    @FXML
    void returnBookClick(MouseEvent event) throws IOException, SQLException {
        Book bookToReturn = table.getSelectionModel().getSelectedItem();
        service.returnBook(bookToReturn, client);
        Stage st = (Stage) table.getScene().getWindow();
        st.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
        Parent root = fxmlLoader.load();
        MessageController controller = fxmlLoader.getController();
        controller.setMessage("Returnarea cartii s-a efectuat cu succes.");
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}

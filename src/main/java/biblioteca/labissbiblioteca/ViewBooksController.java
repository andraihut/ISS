package biblioteca.labissbiblioteca;


import biblioteca.labissbiblioteca.domain.Book;
import biblioteca.labissbiblioteca.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ViewBooksController {
    private Service service;
    ObservableList<Book> books = FXCollections.observableArrayList();
    public void setService(Service service) throws SQLException {
        this.service = service;
        idColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("year"));
        for(Book book: service.getAllBooks()){
            books.add(book);
        }
        table.setItems(books);
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
}

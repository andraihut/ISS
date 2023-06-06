package biblioteca.labissbiblioteca;

import biblioteca.labissbiblioteca.domain.Book;
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

public class AddBookController {
    private Service service;
    public void setService(Service service){
        this.service = service;
    }
    @FXML
    private TextField titleTxt;
    @FXML
    private TextField authorTxt;
    @FXML
    private TextField yearTxt;
    @FXML
    void addBookClick(MouseEvent event) throws IOException {
        if(Objects.equals(titleTxt.getText(), "") || Objects.equals(authorTxt.getText(), "") || Objects.equals(yearTxt.getText(), "") ){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController controller = fxmlLoader.getController();
            controller.setMessage("Toate campurile trebuie completate corect!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }else{
            String titlu = titleTxt.getText();
            String autor = authorTxt.getText();
            int year = 0;
            try{
                year = Integer.parseInt(yearTxt.getText());
                Integer id = service.getMaxIdBook();
                Book book = new Book(id,titlu,autor,year);
                service.addBook(book);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController controller = fxmlLoader.getController();
                controller.setMessage("Cartea a fost adaugata cu succes!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                Stage st = (Stage) titleTxt.getScene().getWindow();
                st.close();

            }catch (Exception e){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController controller = fxmlLoader.getController();
                controller.setMessage("Toate campurile trebuie completate corect!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}

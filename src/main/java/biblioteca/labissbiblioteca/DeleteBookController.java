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

public class DeleteBookController {
    private Service service;
    public void setService(Service service) {
        this.service = service;
    }
    @FXML
    private TextField idBookTxt;
    @FXML
    void deleteBookClick(MouseEvent event) throws IOException {
        if(Objects.equals(idBookTxt.getText(), "")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController controller = fxmlLoader.getController();
            controller.setMessage("Introduceti id-ul cartii pe care o doriti stearsa.");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        else {
            try{
                int id = Integer.parseInt(idBookTxt.getText());
                Book bookGasit = service.findBook(id);
                if(bookGasit.getId() == id) {
                    service.deleteBook(id);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
                    Parent root = fxmlLoader.load();
                    MessageController controller = fxmlLoader.getController();
                    controller.setMessage("Cartea a fost stearsa!");
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    Stage st = (Stage) idBookTxt.getScene().getWindow();
                    st.close();
                }
                else{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
                    Parent root = fxmlLoader.load();
                    MessageController controller = fxmlLoader.getController();
                    controller.setMessage("Nu exista carte cu acest id!");
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    Stage st = (Stage) idBookTxt.getScene().getWindow();
                    st.close();
                }
            }catch (Exception e){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController controller = fxmlLoader.getController();
                controller.setMessage("Nu exista carte cu acest id!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }

}

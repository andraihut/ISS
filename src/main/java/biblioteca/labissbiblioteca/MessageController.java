package biblioteca.labissbiblioteca;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MessageController {
    public void setMessage(String txt){
        errorTxt.setText(txt);
    }
    @FXML
    private Button closeBtn;
    @FXML
    private Label errorTxt;
    @FXML
    void clickClose(MouseEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}

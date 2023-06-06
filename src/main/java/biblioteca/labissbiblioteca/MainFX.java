package biblioteca.labissbiblioteca;

import biblioteca.labissbiblioteca.repository.faraORM.AdminRepository2;
import biblioteca.labissbiblioteca.repository.faraORM.BookRepository2;
import biblioteca.labissbiblioteca.repository.faraORM.ClientRepository2;
import biblioteca.labissbiblioteca.repository.orm.hibernate.AdminRepository;
import biblioteca.labissbiblioteca.repository.orm.hibernate.BookRentalRepository;
import biblioteca.labissbiblioteca.repository.orm.hibernate.BookRepository;
import biblioteca.labissbiblioteca.repository.orm.hibernate.ClientRepository;
import biblioteca.labissbiblioteca.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.Properties;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BookRepository bookRepo = new BookRepository();
        ClientRepository clientRepo = new ClientRepository();
        AdminRepository adminRepo = new AdminRepository();
        BookRentalRepository bookRentalRepo = new BookRentalRepository();
        System.out.println(bookRepo.getAll().size());
        Service service = new Service(adminRepo, clientRepo, bookRepo, bookRentalRepo);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            controller.setService(service);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("World of books");
            stage.show();
        }catch(Exception e){
            System.out.println(e);
        }

    }
}

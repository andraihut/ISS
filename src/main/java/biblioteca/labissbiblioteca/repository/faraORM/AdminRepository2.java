package biblioteca.labissbiblioteca.repository.faraORM;

import biblioteca.labissbiblioteca.domain.Admin;
import biblioteca.labissbiblioteca.repository.IAdminRepository;
import biblioteca.labissbiblioteca.repository.JdbcUtils;
import biblioteca.labissbiblioteca.repository.orm.hibernate.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AdminRepository2 implements IAdminRepository {
    private JdbcUtils dbUtils;
    public AdminRepository2(Properties props) {
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        Admin angajat = null;
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Admin WHERE username = ? and password = ?")) {
            preStmt.setString(1, username);
            preStmt.setString(2,password);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id_angajat = result.getInt("id");
                    String u = result.getString("username");
                    String p = result.getString("password");
                    String first = result.getString("first_name");
                    String last = result.getString("last_name");
                    String email = result.getString("email");
                    angajat = new Admin(id_angajat, u, p,first, last,email);
                }
            } catch (SQLException e) {
                System.err.println("Error DB " + e);
            }
            return angajat;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Admin findOne(Integer integer) throws IllegalArgumentException {
        Admin angajat = null;
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Admin WHERE id = ?")) {
            preStmt.setInt(1, integer);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id_angajat = result.getInt("id");
                    String u = result.getString("username");
                    String p = result.getString("password");
                    String first = result.getString("first_name");
                    String last = result.getString("last_name");
                    String email = result.getString("email");
                    angajat = new Admin(id_angajat, u, p,first, last,email);
                }
            } catch (SQLException e) {
                System.err.println("Error DB " + e);
            }
            return angajat;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Admin> getAll() throws SQLException {
        Connection con=dbUtils.getConnection();
        List<Admin> angajati = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from angajati")){
            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()) {
                    int id_angajat = result.getInt("id");
                    String u = result.getString("username");
                    String p = result.getString("password");
                    String first = result.getString("first_name");
                    String last = result.getString("last_name");
                    String email = result.getString("email");
                    Admin angajat = new Admin(id_angajat, u, p,first, last,email);
                    angajati.add(angajat);
                }
            } catch (SQLException e) {
                System.err.println("Error DB " + e);
            }
            return angajati;
        }
    }
}

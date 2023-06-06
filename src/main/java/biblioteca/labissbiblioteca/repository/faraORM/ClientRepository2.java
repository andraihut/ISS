package biblioteca.labissbiblioteca.repository.faraORM;

import biblioteca.labissbiblioteca.domain.Admin;
import biblioteca.labissbiblioteca.domain.Client;
import biblioteca.labissbiblioteca.repository.IClientRepository;
import biblioteca.labissbiblioteca.repository.JdbcUtils;
import biblioteca.labissbiblioteca.repository.orm.hibernate.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class ClientRepository2 implements IClientRepository {
    private JdbcUtils dbUtils;
    public ClientRepository2(Properties props) {
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Client findByUsernameAndPassword(String username, String password) {
        Client angajat = null;
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Client WHERE username = ? and password = ?")) {
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
                    angajat = new Client(id_angajat, u, p,first, last,email);
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
    public Client findOne(Integer integer) throws IllegalArgumentException {
        Client angajat = null;
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Client WHERE id = ?")) {
            preStmt.setInt(1, integer);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id_angajat = result.getInt("id");
                    String u = result.getString("username");
                    String p = result.getString("password");
                    String first = result.getString("first_name");
                    String last = result.getString("last_name");
                    String email = result.getString("email");
                    angajat = new Client(id_angajat, u, p,first, last,email);
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
    public List<Client> getAll() {
        /*SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Client> clients = session.createQuery("from Client", Client.class).list();

            if (!(clients).isEmpty()) {
                for (Client cl : clients) {
                    if (cl != null) {
                        System.out.println(cl);
                    }
                }
            } else {
                System.out.println("Nu exista clienti in baza de date.");
            }
            session.getTransaction().commit();
            HibernateSession.close();
            return clients;
        }*/
        return null;
    }

    @Override
    public void save(Client entity) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into Client(id,username, password, first_name , last_name,email) values (?,?,?,?,?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setString(2,entity.getUsername());
            preStmt.setString(3,entity.getPassword());
            preStmt.setString(4,entity.getFirst_name());
            preStmt.setString(5,entity.getLast_name());
            preStmt.setString(6,entity.getEmail());
            int result = preStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
    }

    @Override
    public void update(Client entity) {
        /*SessionFactory sessionFactory = HibernateSession.getSessionFactory();


        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                Client client = session.load( Client.class, entity.getId() );
                System.out.println("------ID REPO UPDATE ------------"+ entity.getId());
                client.setFirst_name(entity.getFirst_name());
                client.setLast_name(entity.getLast_name());
                client.setEmail(entity.getEmail());
                client.setUsername(entity.getUsername());
                client.setPassword(entity.getPassword());

                session.update(client);
                tx.commit();

            } catch(RuntimeException ex){
                System.err.println("Eroare la update "+ex);
                if (tx!=null)
                    tx.rollback();
            }
        }

        HibernateSession.close();*/
    }

    @Override
    public void delete(Integer integer) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("DELETE FROM Client WHERE id = ?")) {
            preStmt.setInt(1, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
    }
}

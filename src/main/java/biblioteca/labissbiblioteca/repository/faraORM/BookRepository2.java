package biblioteca.labissbiblioteca.repository.faraORM;

import biblioteca.labissbiblioteca.domain.Book;
import biblioteca.labissbiblioteca.repository.IBookRepository;
import biblioteca.labissbiblioteca.repository.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookRepository2 implements IBookRepository {
    private JdbcUtils dbUtils;
    public BookRepository2(Properties props) {
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Book findOne(Integer integer) throws IllegalArgumentException {
        Book angajat = null;
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Book WHERE id = ?")) {
            preStmt.setInt(1, integer);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String b = result.getString("title");
                    String c = result.getString("author");
                    int r = result.getInt("year_of_publication");
                    angajat = new Book(id,b,c,r);
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
    public List<Book> getAll() throws SQLException {
        Connection con=dbUtils.getConnection();
        List<Book> angajati = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Book")){
            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()) {
                    int id = result.getInt("id");
                    String b = result.getString("title");
                    String c = result.getString("author");
                    int r = result.getInt("year_of_publication");
                    Book angajat = new Book(id,b,c,r);
                    angajati.add(angajat);
                }
            } catch (SQLException e) {
                System.err.println("Error DB " + e);
            }
            return angajati;
        }
    }

    @Override
    public void save(Book entity) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into Book(id,title, author, year_of_publication) values (?,?,?,?)")){
            preStmt.setInt(1,entity.getId());
            preStmt.setString(2,entity.getTitle());
            preStmt.setString(3,entity.getAuthor());
            preStmt.setInt(4, entity.getYear());
            int result = preStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
    }
    @Override
    public void delete(Integer integer) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("DELETE FROM Book WHERE id = ?")) {
            preStmt.setInt(1, integer);
            int result = preStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }

    }
}

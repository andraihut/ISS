package biblioteca.labissbiblioteca.repository.faraORM;

import biblioteca.labissbiblioteca.domain.BookRental;
import biblioteca.labissbiblioteca.repository.IBookRentalRepository;
import biblioteca.labissbiblioteca.repository.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookRentalRepository2 implements IBookRentalRepository {
    private JdbcUtils dbUtils;
    public BookRentalRepository2(Properties props) {
        dbUtils=new JdbcUtils(props);
    }
    @Override
    public void returnBook(BookRental entity) {
        Connection connection = dbUtils.getConnection();
        try(PreparedStatement preparedStatement =  connection.prepareStatement("UPDATE BookRental SET returned = ? WHERE id = ?"))
        {
            preparedStatement.setInt(1,entity.getReturned());
            preparedStatement.setInt(2,entity.getId());
            int result = preparedStatement.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }


    @Override
    public BookRental findOne(Integer integer) throws IllegalArgumentException {
        BookRental angajat = null;
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("select * from BookRental WHERE id = ?")) {
            preStmt.setInt(1, integer);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    int b = result.getInt("id_book");
                    int c = result.getInt("id_client");
                    int r = result.getInt("returned");
                    angajat = new BookRental(id,b,c,r);
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
    public List<BookRental> getAll() throws SQLException {
        Connection con=dbUtils.getConnection();
        List<BookRental> angajati = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from BookRental")){
            try(ResultSet result=preStmt.executeQuery()){
                while (result.next()) {
                    int id = result.getInt("id");
                    int b = result.getInt("id_book");
                    int c = result.getInt("id_client");
                    int r = result.getInt("returned");
                    BookRental angajat = new BookRental(id,b,c,r);
                    angajati.add(angajat);
                }
            } catch (SQLException e) {
                System.err.println("Error DB " + e);
            }
            return angajati;
        }

    }

    @Override
    public void save(BookRental entity) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into BookRental(id_book, id_client, returned) values (?,?,?)")){
            preStmt.setInt(1,entity.getId_book());
            preStmt.setInt(2,entity.getId_client());
            preStmt.setInt(3, entity.getReturned());
            int result = preStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error DB " + e);
        }
    }
}


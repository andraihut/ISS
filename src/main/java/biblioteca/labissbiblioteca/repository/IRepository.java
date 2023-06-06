package biblioteca.labissbiblioteca.repository;
import biblioteca.labissbiblioteca.domain.Entity;
import java.sql.SQLException;
import java.util.List;

public interface IRepository<ID, E extends Entity<ID>> {
    E findOne(ID id) throws IllegalArgumentException;
    List<E> getAll() throws SQLException;
}



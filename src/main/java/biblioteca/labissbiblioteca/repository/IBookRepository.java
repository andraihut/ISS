package biblioteca.labissbiblioteca.repository;

import biblioteca.labissbiblioteca.domain.Book;

public interface IBookRepository extends IRepository<Integer, Book>{
    void save(Book entity);
    void delete(Integer integer);

}

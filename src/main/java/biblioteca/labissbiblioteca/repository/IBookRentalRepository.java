package biblioteca.labissbiblioteca.repository;

import biblioteca.labissbiblioteca.domain.BookRental;

public interface IBookRentalRepository extends IRepository<Integer, BookRental> {
    void returnBook(BookRental bookRental);
    void save(BookRental entity);
}

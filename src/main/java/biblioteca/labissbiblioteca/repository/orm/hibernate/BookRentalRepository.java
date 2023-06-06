package biblioteca.labissbiblioteca.repository.orm.hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import biblioteca.labissbiblioteca.domain.BookRental;
import biblioteca.labissbiblioteca.repository.IBookRentalRepository;

import java.util.List;

public class BookRentalRepository implements IBookRentalRepository {
    public BookRentalRepository(){}
    @Override
    public void returnBook(BookRental entity) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                BookRental bookRental = session.load(BookRental.class, entity.getId());
                bookRental.setReturned(1);
                session.update(bookRental);
                tx.commit();
            } catch(RuntimeException ex){
                System.err.println("Eroare la update "+ex);
                if (tx!=null)
                    tx.rollback();
            }
        }
        HibernateSession.close();
    }

    @Override
    public BookRental findOne(Integer integer) throws IllegalArgumentException {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        BookRental bookRental = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<BookRental> bookRentals = session.createQuery("from BookRental", BookRental.class).list();
            for (BookRental book : bookRentals) {
                if(book.getId().equals(integer))
                    bookRental = new BookRental(book.getId(), book.getId_book(), book.getId_client(),book.getReturned());
            }
            session.getTransaction().commit();
        }
        HibernateSession.close();
        return bookRental;

    }

    @Override
    public List<BookRental> getAll() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<BookRental> bookRentals = session.createQuery("from BookRental", BookRental.class).list();
            session.getTransaction().commit();
            HibernateSession.close();
            return bookRentals;
        }

    }

    @Override
    public void save(BookRental entity) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
            } catch (RuntimeException ex) {
                HibernateSession.close();
                System.err.println("Eroare la inserare "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        HibernateSession.close();
    }
}

package biblioteca.labissbiblioteca.repository.orm.hibernate;
import biblioteca.labissbiblioteca.domain.Book;
import biblioteca.labissbiblioteca.repository.IBookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookRepository implements IBookRepository {

    public BookRepository(){}
    public SessionFactory sessionFactory = HibernateSession.getSessionFactory();
    @Override
    public Book findOne(Integer integer) throws IllegalArgumentException {
        Book book = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Book> books = session.createQuery("from Book", Book.class).list();
            for (Book bk : books) {
                if(bk.getId().equals(integer))
                    book = new Book(bk.getId(),bk.getTitle(),bk.getAuthor(),bk.getYear());
            }
            session.getTransaction().commit();
        }
        HibernateSession.close();
        return book;
    }

    @Override
    public List<Book> getAll() {
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Book> books = session.createQuery("from Book", Book.class).list();
            session.getTransaction().commit();
            HibernateSession.close();
            return books;
        }
    }
    @Override
    public void save(Book entity) {
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

    @Override
    public void delete(Integer integer) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Book crit = session.createQuery("from Book where id = :idBook", Book.class)
                        .setParameter("idBook", integer)
                        .setMaxResults(1)
                        .uniqueResult();
                session.delete(crit);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null) tx.rollback();
            }
        }
        HibernateSession.close();
    }
}

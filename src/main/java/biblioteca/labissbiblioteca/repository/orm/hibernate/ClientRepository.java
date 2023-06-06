package biblioteca.labissbiblioteca.repository.orm.hibernate;

import biblioteca.labissbiblioteca.domain.Client;
import biblioteca.labissbiblioteca.repository.IClientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ClientRepository implements IClientRepository {
    public ClientRepository(){}
    public SessionFactory sessionFactory = HibernateSession.getSessionFactory();
    @Override
    public Client findByUsernameAndPassword(String username, String password) {
        Client client = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Client> clients = session.createQuery("from Client", Client.class).list();
            for (Client cl : clients) {
                if(cl.getUsername().equals(username) && cl.getPassword().equals(password))
                    client = new Client(cl.getId(),cl.getUsername(),cl.getPassword(),cl.getFirst_name(), cl.getLast_name(),cl.getEmail());
            }
            session.getTransaction().commit();
        }
        HibernateSession.close();
        return client;
    }

    @Override
    public Client findOne(Integer integer) throws IllegalArgumentException {
        Client client = null;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Client> clients = session.createQuery("from Client", Client.class).list();
            for (Client cl : clients) {
                if(cl.getId().equals(integer))
                    client = new Client(cl.getId(),cl.getUsername(),cl.getPassword(),cl.getFirst_name(), cl.getLast_name(),cl.getEmail());
            }
            session.getTransaction().commit();
        }
        HibernateSession.close();
        return client;
    }

    @Override
    public List<Client> getAll() {
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Client> clients = session.createQuery("from Client", Client.class).list();
            session.getTransaction().commit();
            HibernateSession.close();
            return clients;
        }
    }

    @Override
    public void save(Client entity) {
        try(Session session = sessionFactory.openSession()){
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
    public void update(Client entity) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                Client client = session.load( Client.class, entity.getId() );
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
        HibernateSession.close();
    }

    @Override
    public void delete(Integer integer) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Client crit = session.createQuery("from Client where id = :id_client", Client.class)
                        .setParameter("id_client", integer)
                        .setMaxResults(1)
                        .uniqueResult();
                session.delete(crit);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        HibernateSession.close();
    }
}

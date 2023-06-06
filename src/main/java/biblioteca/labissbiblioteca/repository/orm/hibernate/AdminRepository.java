package biblioteca.labissbiblioteca.repository.orm.hibernate;

import biblioteca.labissbiblioteca.domain.Admin;
import biblioteca.labissbiblioteca.repository.IAdminRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class AdminRepository implements IAdminRepository {
    public static SessionFactory sessionFactory = HibernateSession.getSessionFactory();

    public AdminRepository()
    {
        initialize();
    }

    static void initialize() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exceptie " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        Admin admin = null;
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Admin> users = session.createQuery("FROM Admin ", Admin.class).list();
            for (Admin user : users) {
                if(user.getUsername().equals(username) && user.getPassword().equals(password))
                    admin = new Admin(user.getId(), user.getUsername(), user.getPassword() , user.getFirst_name(), user.getLast_name() , user.getEmail());
            }
            session.getTransaction().commit();
        }
        return admin;
    }

    @Override
    public Admin findOne(Integer integer) throws IllegalArgumentException {
        return null;
    }

    @Override
    public List<Admin> getAll() {
        return null;
    }
}

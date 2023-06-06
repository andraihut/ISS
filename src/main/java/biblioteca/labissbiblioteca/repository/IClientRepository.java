package biblioteca.labissbiblioteca.repository;

import biblioteca.labissbiblioteca.domain.Client;

public interface IClientRepository extends IRepository<Integer, Client> {
    Client findByUsernameAndPassword(String username , String password);
    void save(Client entity);
    void update(Client entity);
    void delete(Integer integer);
}

package biblioteca.labissbiblioteca.repository;

import biblioteca.labissbiblioteca.domain.Admin;

public interface IAdminRepository extends IRepository<Integer, Admin> {
    Admin findByUsernameAndPassword(String username , String password);
}

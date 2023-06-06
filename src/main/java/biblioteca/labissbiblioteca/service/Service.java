package biblioteca.labissbiblioteca.service;

import biblioteca.labissbiblioteca.domain.*;
import biblioteca.labissbiblioteca.repository.IAdminRepository;
import biblioteca.labissbiblioteca.repository.IBookRentalRepository;
import biblioteca.labissbiblioteca.repository.IBookRepository;
import biblioteca.labissbiblioteca.repository.IClientRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private IAdminRepository adminRepo;
    private IClientRepository clientRepo;
    private IBookRepository bookRepo;
    private IBookRentalRepository bookRentalRepo;

    public Service(IAdminRepository adminRepo,IClientRepository clientRepo,IBookRepository bookRepo, IBookRentalRepository bookRentalRepo) {
        this.adminRepo = adminRepo;
        this.clientRepo = clientRepo;
        this.bookRepo = bookRepo;
        this.bookRentalRepo = bookRentalRepo;
    }

    public User findUser(String username, String password){
        Admin userAdmin = adminRepo.findByUsernameAndPassword(username,password);
        Client userClient = clientRepo.findByUsernameAndPassword(username,password);
        if(userAdmin != null) return userAdmin;
        else return userClient;
    }
    public void addBook(Book book){
        bookRepo.save(book);
    }
    public List<Book> getAllBooks() throws SQLException {
        return bookRepo.getAll();
    }
    public Integer getMaxIdBook() throws SQLException {
        Integer id = 0;
        for (Book book : bookRepo.getAll()) {
            if(book.getId() > id) id = book.getId();
        }
        return id+1;
    }
    public List<Client> getAllClients() throws SQLException {
        return clientRepo.getAll();
    }
    public void addClient(Client client) {
        clientRepo.save(client);
    }
    public void deleteClient(int id){
        clientRepo.delete(id);
    }
    public void updateClient(Client client){
        clientRepo.update(client);
    }
    public void deleteBook(int id){
        bookRepo.delete(id);
    }
    public void rentBook(BookRental bookRental) {
        bookRentalRepo.save(bookRental);
    }
    public void returnBook(Book book, Client client) throws SQLException {
        for(BookRental rental: bookRentalRepo.getAll())
            if(rental.getId_book() == book.getId() && rental.getId_client() == client.getId())
                bookRentalRepo.returnBook(rental);
    }
    public List<Book> getNotRentedBooks() throws SQLException {
        List<Book> books = new ArrayList<Book>();
        for(Book b: bookRepo.getAll()){
            int ok = 1;
            for(BookRental bookRental: bookRentalRepo.getAll()){
                if(bookRental.getId_book() == b.getId() && bookRental.getReturned() == 0)
                    ok = 0;
            }
            if(ok == 1) books.add(b);
        }
        return books;
    }
    public Book getBook(Integer id){
        return bookRepo.findOne(id);
    }
    public List<Book> getRentedBooks(Client client) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        for(BookRental bookRental: bookRentalRepo.getAll())
            if(bookRental.getId_client() == client.getId() && bookRental.getReturned()==0){
                Book book = getBook(bookRental.getId_book());
                books.add(book);
            }
        return books;
    }

    public Client findClient(Integer id) {
        return clientRepo.findOne(id);
    }
    public Book findBook(Integer id) {
        return bookRepo.findOne(id);
    }
}

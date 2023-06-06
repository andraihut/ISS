package biblioteca.labissbiblioteca.domain;

public class BookRental implements Entity<Integer>{
    private int id;
    private int id_client;
    private int id_book;
    private int returned;
    public BookRental(){}
    public BookRental(int client, int book, int returned) {
        this.id_client = client;
        this.id_book = book;
        this.returned = returned;
    }
    public BookRental(int id, int client, int book, int returned) {
        this.id = id;
        this.id_client = client;
        this.id_book = book;
        this.returned = returned;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getReturned() {
        return returned;
    }
    public void setReturned(int returned) {
        this.returned = returned;
    }
    @Override
    public Integer getId() {
        return this.id;
    }
    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    @Override
    public String toString() {
        return "BookRental{" +
                "id=" + id +
                ", id_client=" + id_client +
                ", id_book=" + id_book +
                ", returned=" + returned +
                '}';
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }
}

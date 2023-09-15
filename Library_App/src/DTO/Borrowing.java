package DTO;

import java.util.Date;

public class Borrowing {
    private String Book_isbn ;

    private int borrower_id;

    private Date date_emprunt;

    private  int date_retourn;
    public int getDate_retourn() {
        return date_retourn;
    }

    public void setDate_retourn(int date_retourn) {
        this.date_retourn = date_retourn;
    }



    public void setDate_emprunt(Date date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    public Date getDate_emprunt() {
        return date_emprunt;
    }
    public Book getBook() {
        return book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    Book book = new Book();
    Borrower borrower = new Borrower();
    public String getBook_isbn() {
        return Book_isbn;
    }

    public void setBook_isbn(String book_isbn) {
        Book_isbn = book_isbn;
    }

    public int getBorrower_id() {
        return borrower_id;
    }

    public void setBorrower_id(int borrower_id) {
        this.borrower_id = borrower_id;
    }





}

package Interfaces;

import DTO.Book;
import DTO.Borrowing;

import java.util.List;

public interface Iborrowing {

    boolean create(Borrowing borrowing);
    String IsBookBorrowed(String isbn);
    List<Borrowing>  GetBorrowedBooks();

}

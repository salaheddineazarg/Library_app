package Interfaces;

import DTO.Book;

import java.util.List;

public interface Ibook {

    boolean create(Book book);
    int delete(String isbn);
    boolean update(Book book);
    Book getOne(String isbn);

    List<Book> searchBooks(String Keyword);

     int GetBorrowed();

      int GetLost();

     int GetAvailable();

     Boolean   RetourBook(String isbn);


}

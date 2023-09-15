package DAO;

import DTO.Book;
import DTO.Borrowing;
import DTO.Status;
import Database.ConnectionDB;
import Interfaces.Iborrowing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BorrowingD implements Iborrowing {

    Connection conn = ConnectionDB.ConnectionDB();
    Borrowing borrowing = new Borrowing();
    @Override
    public boolean create(Borrowing borrowing) {

        String status = IsBookBorrowed(borrowing.getBook_isbn());
                if ("Borrowed".equals(status)){
                    System.out.println("This book is already borrowed.");
                }else {
           try {
                 LocalDate DateNow =LocalDate.now();
               LocalDate  DateRetourn = DateNow.plusDays(borrowing.getDate_retourn());


        PreparedStatement statement = this.conn.prepareStatement("INSERT INTO borrowing (book_isbn,borrower_id,date_retour) VALUES (?,?,?)");

        statement.setString(1,borrowing.getBook_isbn());
        statement.setInt(2,borrowing.getBorrower_id());
        statement.setDate(3, Date.valueOf(DateRetourn));
        int executed = statement.executeUpdate();
               if (executed > 0 ) {
                 return  true;
               }
     }catch ( SQLException e ){

               System.out.println(e);
           }
    }
           return false;
    }

    @Override
    public String IsBookBorrowed(String isbn) {
         try {
             PreparedStatement statement = this.conn.prepareStatement("SELECT status  FROM book WHERE isbn = ?");





             statement.setString(1,isbn);


             ResultSet resultSet = statement.executeQuery();

             if (resultSet.next()) {
                 return resultSet.getString("status");
             } else {
                 // Handle the case where the book is not found
                 return "Not Found";
             }

         }catch ( SQLException e){
             System.out.println(e);

             return null;
         }


    }

    @Override
    public  List<Borrowing> GetBorrowedBooks() {

        List<Borrowing> books = new ArrayList<>();

              try {


        PreparedStatement statement = this.conn.prepareStatement("SELECT  * FROM book INNER JOIN borrowing ON book.isbn = borrowing.book_isbn INNER JOIN borrower ON borrower.id = borrowing.borrower_id WHERE status = 'Borrowed'");
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            Borrowing display = new Borrowing();
           display.getBook().setIsbn(resultSet.getString("isbn"));
           display.getBook().setTitle(resultSet.getString("title"));
           display.getBook().setAuthor(resultSet.getString("author"));
           display.getBook().setStatus(Status.valueOf(resultSet.getString("status")));
           display.getBorrower().setName(resultSet.getString("name"));
           display.getBorrower().setCih(resultSet.getString("cih"));
           display.getBorrower().setN_phone(resultSet.getString("n_phone"));
           display.getBorrower().setAdresse(resultSet.getString("adresse"));
           display.setDate_emprunt(resultSet.getDate("date_emprunt"));

            books.add(display);
        }

             return books;

    }catch (SQLException e){
                  System.out.println(e);
              }


        return null;
    }


}




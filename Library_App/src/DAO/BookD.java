package DAO;

import DTO.Book;
import DTO.Status;
import Database.ConnectionDB;
import Interfaces.Ibook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookD implements Ibook {
    Connection conn = ConnectionDB.ConnectionDB();
    @Override
    public  boolean create(Book book) {


        try {
            PreparedStatement statement = this.conn.prepareStatement("INSERT INTO book (isbn,title,author,status)  VALUES (?,?,?,?)");

            statement.setString(1,book.getIsbn());
            statement.setString(2,book.getTitle());
            statement.setString(3,book.getAuthor());
            statement.setString(4,book.getStatus().name());
           int executed = statement.executeUpdate();
           if(executed>0){
               return true;
           }
        }catch (SQLException e){

            System.out.println(e);


        }
        return false;
    }

    @Override
    public int delete(String isbn) {
        int  executed = 0 ;
        try {
            PreparedStatement statement = this.conn.prepareStatement("DELETE  FROM book WHERE isbn = ?");

            statement.setString(1,isbn);
              executed = statement.executeUpdate();


            if (executed > 0 ) {

            }
        }catch (SQLException e){

            System.out.println(e);
        }
        return  executed;

    }

    @Override
    public boolean update(Book book) {
        try {
            PreparedStatement statement = this.conn.prepareStatement("UPDATE book SET title = ?,author = ?, status = ? WHERE isbn = ?");

            statement.setString(1, book.getTitle());
            statement.setString(2,book.getAuthor());
            statement.setString(3,book.getStatus().name());
            statement.setString(4,book.getIsbn());
            int result = statement.executeUpdate();


            if(result>0) {

              return true;
            }

        }catch (SQLException e){

            System.out.println(e);

        }
        return false;
    }


    @Override
    public Book getOne(String isbn)
    {
        try{
            PreparedStatement statement = this.conn.prepareStatement("SELECT * FROM book WHERE isbn = ?");

            statement.setString(1,isbn);

            ResultSet resultSet = statement.executeQuery();
            Book book = new Book();
            while(resultSet.next()) {
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setStatus(Status.valueOf(resultSet.getString("status")));
            }


           return book;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();

        try {

            PreparedStatement statement = this.conn.prepareStatement("SELECT * FROM book WHERE title LIKE ? OR author LIKE ?");
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setStatus(Status.valueOf(resultSet.getString("status")));
                // Set other properties if needed

                books.add(book);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return books;
    }

    @Override

   public  int GetAvailable(){
           int count = 1;
         try{

             PreparedStatement statement = this.conn.prepareStatement("SELECT COUNT(*) FROM book WHERE status = 'Available'");

             ResultSet executed = statement.executeQuery();

             if(executed.next()){
                 count = executed.getInt(1);
                System.out.println("ssss");
             }

         }catch (SQLException e){

             System.out.println(e);

         }
       return count;
   }

    @Override
    public Boolean RetourBook(String isbn) {
         try{
             PreparedStatement statement = this.conn.prepareStatement("UPDATE book SET status = 'Available' WHERE isbn = ?");
             statement.setString(1,isbn);
             int executed = statement.executeUpdate();
             if (executed >0 ){
                 return true;
             }

         }catch (SQLException e){
          System.out.println(e);

             return false;
         }

        return null;
    }

    public  int GetBorrowed(){
       int count1 = 0;
       try{

           PreparedStatement statement = this.conn.prepareStatement("SELECT COUNT(*) FROM book WHERE status = 'Borrowed'");

           ResultSet executedBorrowed= statement.executeQuery();

           if(executedBorrowed.next()){
               count1 = executedBorrowed.getInt(1);

           }



       }catch (SQLException e){

           System.out.println(e);

       }
       return count1;
   }
   public  int GetLost(){
       int count2 = 0;
       try{

           PreparedStatement statement = this.conn.prepareStatement("SELECT COUNT(*) FROM book WHERE status = 'Lost'");

           ResultSet executedLost = statement.executeQuery();

           if(executedLost.next()){
               count2 = executedLost.getInt(1);
           }


       }catch (SQLException e){

           System.out.println(e);

       }
       return count2;
   }


}







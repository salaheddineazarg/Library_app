import DAO.BookD;
import DAO.BorrowerD;
import DAO.BorrowingD;
import DTO.Book;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;
import java.util.SplittableRandom;

import DTO.Borrower;
import DTO.Borrowing;
import DTO.Status;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

     static Book  book = new Book();
     static Borrower borrower = new Borrower();
     static BookD bookD = new BookD();
     static BorrowerD borrowerD = new BorrowerD();

     static  Borrowing  borrowing = new Borrowing();
     static  BorrowingD borrowingD = new BorrowingD();

     static Scanner scanner = new Scanner(System.in);

     public static void main(String[] args){

          // the colors
          String ANSI_RESET = "\u001B[0m";
          String ANSI_RED = "\u001B[31m";
          String ANSI_GRAY = "\u001B[90m";
          // the select

     System.out.println("------------------------------------------ \n");
     System.out.println( ANSI_RED +"Select Your Option :\n "+ANSI_RESET );System.out.println("───────────────────────────────");
         System.out.println("|   Library Management System  |");
         System.out.println("───────────────────────────────");
         System.out.println("| 1 - Books Management         |");
         System.out.println("| 2 - Borrowers Management     |");
         System.out.println("| 3 - Borrowing Management     |");
         System.out.println("| 4 - Search Books             |");
         System.out.println("| 5 - Return Book              |");
         System.out.println("───────────────────────────────");
     System.out.println(ANSI_GRAY+ "***************************" +ANSI_RESET);
     // the option
      System.out.print("Enter Your option : ");
     Scanner input = new Scanner(System.in);
     int option = input.nextInt();



     //Switch
         //Menu Pricipal

          switch (option){

               case 1 :

                    System.out.println("------------------------------------------ \n");
                    System.out.println( ANSI_RED +"Select Your Option :\n "+ANSI_RESET );
                    System.out.println("1 - Add Book                                 2 - Delete Book");
                    System.out.println("3 - Update Book                              4 - Statistic Books");
                    System.out.println("5 - Display The Borrowed Book                6 - Change the Status ");

                    System.out.println(ANSI_GRAY+ "***************************" +ANSI_RESET);


                    int option1 = input.nextInt();

                    switch(option1){

                         case 1:

                         System.out.print("ISBN: ");
                         book.setIsbn(scanner.next());
                         System.out.print("Title: ");
                         book.setTitle(scanner.next());
                         System.out.print("Author: ");
                         book.setAuthor(scanner.next());
                         Scanner status = new   Scanner(System.in);
                         System.out.println("Choose the status for the book:");
                         System.out.println("1. Available");
                         System.out.println("2. Lost");
                         System.out.println("3. Borrowed");
                         int choice = status.nextInt();
                              switch (choice) {
                                   case 1:
                                        book.setStatus(Status.Available);
                                        break;
                                   case 2:
                                        book.setStatus(Status.Lost);
                                        break;
                                   case 3:
                                        book.setStatus(Status.Borrowed);
                                        break;
                                   default:
                                        System.out.println("Invalid choice. The status remains unchanged.");
                                        break;
                              }

                              boolean BookInsert = bookD.create(book);

                              if (BookInsert){
                                  System.out.println("The Book is Inserted ");
                              }

                              break;

                         case 2 :

                              System.out.print("Please Enter Number ISBN Of The Book You Want Delete: ");
                              book.setIsbn(scanner.next());
                             int bookdelete = bookD.delete(book.getIsbn());
                             if(bookdelete>0){
                                 System.out.println("The Book is deleted");
                             }

                             break;

                         case 3 :
                              System.out.print("Please Enter Number ISBN Of The Book You Want Update: ");
                              book.setIsbn(scanner.next());
                             Book getOne = bookD.getOne(book.getIsbn());
                              System.out.print("The Informations Of This Book :"+"\n");
                              System.out.print("ISBN: "+getOne.getIsbn()+"\n");
                              System.out.print("Title: "+getOne.getTitle()+"\n");
                              System.out.print("Author: "+getOne.getAuthor()+"\n");
                              System.out.print("Status: "+getOne.getStatus()+"\n");
                              System.out.print("Please Enter The Informations You Want To Update It :\n");
                              System.out.print("Title: ");
                              book.setTitle(scanner.next());
                              System.out.print("Author: ");
                              book.setAuthor(scanner.next());
                              Scanner update = new   Scanner(System.in);
                              System.out.println("Choose the status for the book:");
                              System.out.println("1. Available");
                              System.out.println("2. Lost");
                              System.out.println("3. Borrowed");
                              choice = update.nextInt();
                              switch (choice) {
                                   case 1:
                                        book.setStatus(Status.Available);
                                        break;
                                   case 2:
                                        book.setStatus(Status.Lost);
                                        break;
                                   case 3:
                                        book.setStatus(Status.Borrowed);
                                        break;
                                   default:
                                        System.out.println("Invalid choice. The status remains unchanged.");
                                        break;
                              }

                               boolean bookupdate =  bookD.update(book);
                              if (bookupdate){
                                  System.out.println("The Book is Updated");
                              }
                              break;

                        case 4 :
                              BookD  countBook = new BookD();
                            System.out.println("The Nombre Of The Available Books: "+ countBook.GetAvailable());
                            System.out.println("The Nombre Of The Borrowed Books: " + countBook.GetBorrowed());
                            System.out.println("The Nombre Of The Lost Books: " +countBook.GetLost());


                            break;

                        case 5 :
                            BorrowingD booksBorrowed = new BorrowingD();
                            List<Borrowing> borrowedbooks = booksBorrowed.GetBorrowedBooks();


                            for( Borrowing borbook : borrowedbooks){

                                System.out.println("Isbn: " + borbook.getBook().getIsbn());
                                System.out.println("Title: " + borbook.getBook().getTitle());
                                System.out.println("Author: " + borbook.getBook().getAuthor());
                                System.out.println("Status: " + borbook.getBook().getStatus());
                                System.out.println("Name: " +borbook.getBorrower().getName());
                                System.out.println("N°Phone: "+borbook.getBorrower().getN_phone());
                                System.out.println("Cih: "+borbook.getBorrower().getCih());
                                System.out.println("Adresse: "+borbook.getBorrower().getAdresse());
                                System.out.println("*****************************************************");
                            }

                          break;
                    }




                    break;
               case 2 :
                    System.out.println("------------------------------------------ \n");
                    System.out.println( ANSI_RED +"Select Your Option :\n "+ANSI_RESET );
                    System.out.println("1 - Add Borrower                   2 - Delete Borrower");
                    System.out.println("3 - Update Borrower                                     ");


                    System.out.println(ANSI_GRAY+ "***************************" +ANSI_RESET);


                     option1 = input.nextInt();

                     switch(option1){

                          case 1 :
                               System.out.print("Name: ");
                               borrower.setName(scanner.next());
                               System.out.print("N° Phone:");
                               borrower.setN_phone(scanner.next());
                               System.out.print("Cih: ");
                               borrower.setCih(scanner.next());
                               System.out.print("Adresse: ");
                               borrower.setAdresse(scanner.next());
                              Borrower borrowerInsert = borrowerD.create(borrower);
                              if (borrowerInsert != null) {
                                  System.out.println("Borrower inserted successfully!");
                              }


                          break;

                          case 2 :
                               System.out.print("Please Enter Id Of The Borrower You Want TO Delete: ");
                               borrower.setId(scanner.nextInt());
                             int borrowerDelet =  borrowerD.delete(borrower.getId());
                             if(borrowerDelet>0){
                                 System.out.println("The Borrower is Deleted");
                             }

                               break;

                         case 3 :
                             System.out.print("Please Enter Number Member Of The Borrower You Want To Update: ");
                             borrower.setId(scanner.nextInt());
                             Borrower getOne = borrowerD.getOne(borrower.getId());
                             System.out.print("The Informations Of This Borrower :"+"\n");
                             System.out.print("Name: "+getOne.getName()+"\n");
                             System.out.print("N°Phone: "+getOne.getN_phone()+"\n");
                             System.out.print("Cih: "+getOne.getCih()+"\n");
                             System.out.print("Adresse: "+getOne.getAdresse()+"\n");
                             System.out.print("Please Enter The Informations You Want To Update It :\n");
                             System.out.print("Name: ");
                             borrower.setName(scanner.next());
                             System.out.print("N°Phone: ");
                             borrower.setN_phone(scanner.next());
                             System.out.print("Cih: ");
                             borrower.setCih(scanner.next());
                             System.out.print("Adresse: ");
                             borrower.setAdresse(scanner.next());
                            boolean borrowerUpdated = borrowerD.update(borrower);

                            if(borrowerUpdated){
                                System.out.println("The Borrower is Updated");
                            }
                             break;
                     }

                     break;

               case 3 :


               /* System.out.println("------------------------------------------ \n");
                   System.out.println( ANSI_RED +"Select Your Option :\n "+ANSI_RESET );
                   System.out.println("1 - Add Borrowing");
                   System.out.println(ANSI_GRAY+ "***************************" +ANSI_RESET); */
                   System.out.println("Does This User Have A Memeber Number: ");
                    System.out.print("1-Yes \n");
                    System.out.println("2-No");
                      int choice2 = scanner.nextInt();
                      switch (choice2){

                          case 1:
                              System.out.print("Please Enter The Number ISBN Of the Book: ");
                              borrowing.setBook_isbn(scanner.next());
                              System.out.print("Please Enter The Memnber Number: ");
                              borrowing.setBorrower_id(scanner.nextInt());
                              System.out.print("Please Enter The Borrowing Days: ");
                              borrowing.setDate_retourn(scanner.nextInt());
                              break;

                          case 2:
                              System.out.print("Please Enter The Informations :\n");
                              System.out.print("Name: ");
                              borrower.setName(scanner.next());
                              System.out.print("N°Phone: ");
                              borrower.setN_phone(scanner.next());
                              System.out.print("Cih: ");
                              borrower.setCih(scanner.next());
                              System.out.print("Adresse: ");
                              borrower.setAdresse(scanner.next());

                             Borrower borrower_id = borrowerD.create(borrower);

                              borrowing.setBorrower_id(borrower_id.getId());
                              System.out.print("Please Enter The Number ISBN Of the Book: ");
                              borrowing.setBook_isbn(scanner.next());


                         break;
                      }


                   borrowingD.create(borrowing);



                   break;

              case 4 :
                System.out.print("PLEASE ENTER THE TITLE OR THE AUTHOR OF THE BOOK: ");
                  List<Book> searchResults = bookD.searchBooks(scanner.next());
                  for (Book book : searchResults) {
                      System.out.println("ISBN: " + book.getIsbn() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Status:" + book.getStatus());
                  }
                 break;
              case 5:
                  BookD retourn = new BookD();
                  System.out.print("Please Enter The Number ISBN Of The Book: ");
                   boolean check = retourn.RetourBook(scanner.next());
                   if(check){
                       System.out.println("The Book is Returned");
                   }else{
                       System.out.println("There's A Problem");
                   }

                  break;
          }




     }



}
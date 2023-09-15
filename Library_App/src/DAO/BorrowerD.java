package DAO;

import DTO.Book;
import DTO.Borrower;
import DTO.Status;
import Database.ConnectionDB;
import Interfaces.Iborrower;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowerD implements Iborrower {
    Connection conn = ConnectionDB.ConnectionDB();
    @Override
    public Borrower create(Borrower borrower) {

        try {
            PreparedStatement statement = this.conn.prepareStatement("INSERT INTO borrower(name,n_phone,cih,adresse)  VALUES(?,?,?,?) RETURNING id,name,n_phone,cih,adresse");

            statement.setString(1,borrower.getName());
            statement.setString(2,borrower.getN_phone());
            statement.setString(3,borrower.getCih());
            statement.setString(4, borrower.getAdresse());

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                borrower.setId(resultSet.getInt("id"));
                borrower.setName(resultSet.getString("name"));
                borrower.setN_phone(resultSet.getString("n_phone"));
                borrower.setCih(resultSet.getString("cih"));
                borrower.setAdresse(resultSet.getString("adresse"));
            }

            return borrower;


        }catch (SQLException e){

            System.out.println(e);
        }
        return null;

    }

    @Override
    public int delete(int id) {
      int executed = 0;
        try {
            PreparedStatement statement = this.conn.prepareStatement("DELETE  FROM borrower WHERE id = ? ");

            statement.setInt(1,id);
           executed = statement.executeUpdate();
            if (executed > 0 ) {
                System.out.println("Statement executed successfully.");
            } else {
                System.out.println("Statement did not execute successfully.");
            }

        }catch (SQLException e){

            System.out.println(e);
        }
        return executed;
    }

    @Override
    public boolean update(Borrower borrower) {

        try {
            PreparedStatement statement = this.conn.prepareStatement("UPDATE borrower SET name = ?,n_phone = ?, cih = ?,adresse = ? WHERE id = ?");

            statement.setString(1,borrower.getName());
            statement.setString(2,borrower.getN_phone());
            statement.setString(3,borrower.getCih());
            statement.setString(4,borrower.getAdresse());
            statement.setInt(5,borrower.getId());
            int executed =  statement.executeUpdate();

            if(executed>0){
                return true;
            }
        }catch (SQLException e){

            System.out.println(e);

        }

return false;

    }

    public Borrower getOne(int id){
        Borrower borrower= new Borrower();
        try{
            PreparedStatement statement = this.conn.prepareStatement("SELECT * FROM borrower WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                borrower.setName(resultSet.getString("name"));
                borrower.setN_phone(resultSet.getString("n_phone"));
                borrower.setCih(resultSet.getString("cih"));
                borrower.setAdresse(resultSet.getString("adresse"));
            }


            return borrower;
        }catch (SQLException e){
            System.out.println(e.getMessage());

            return null;
        }

    }

}

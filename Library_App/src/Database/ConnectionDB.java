package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

    public  static Connection ConnectionDB() {
         String dbname = "library_sas";
         String user = "postgres";
         String password = "sadinzar29";
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);


        } catch (Exception e) {
            System.out.println(e);
        }
        return conn ;

    }

}

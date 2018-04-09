package danrosg;

import java.sql.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class App {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt=null;
        ResultSet rs = null;

        int numOfRecords = 1000000;

        Random rnd =new Random(1000000);



        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/testing?" +
                            "user=root");





            stmt = conn.createStatement();

            stmt.executeUpdate("TRUNCATE TABLE Employment");

            for (int i=1;i<numOfRecords+1;i++)
            {
                double salary = ThreadLocalRandom.current().nextDouble(20000,1000000);

                stmt.executeUpdate("INSERT INTO Employment(Id, Salary) VALUES( "+i+","+salary+")");

            }


            /*while(rs.next())
            {
                float salary = rs.getFloat("Salary");
                System.out.println("Salary:" +salary);

            }*/


            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }

    }

}

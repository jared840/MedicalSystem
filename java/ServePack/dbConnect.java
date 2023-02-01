package ServePack;

import java.sql.*;

public class dbConnect {
    public Connection conn;
    public PreparedStatement prep;
    public ResultSet result;
    boolean flag;

    public void dbConnect(){

        conn=null;
        prep=null;
        result=null;
        flag=false;

try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");

    
}catch (SQLException se) {
    se.printStackTrace();
   // printer.println("Error Occured");
} catch (Exception e) {
    e.printStackTrace();
    //printer.println("Unknown Exceptron Occured");
 } 

    }
}

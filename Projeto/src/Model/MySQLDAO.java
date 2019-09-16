package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class MySQLDAO {
    
    public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String DBURL="jdbc:mysql:///EngSw";
    private static Connection con;
    
    public static Connection getConnection(){
        if(con==null){
            try{
                Class.forName(DRIVER).newInstance();
                con = DriverManager.getConnection(DBURL, "root", "123456");
            }catch(Exception e){
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }
}

package test;  
  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
  
public class MySQLConnection {  
  
    public static Connection getConnection() throws InstantiationException,  
            IllegalAccessException, ClassNotFoundException, SQLException {  
        String driver = "com.mysql.jdbc.Driver";  
        String database = "MySQLDB";  
        String user = "teamtalk";  
        String password = "test@123";  
        String url = "jdbc:mysql://192.168.0.108:3306/" +database+ "?user=" + user  
                + "&password=" + password;  
  
        Class.forName(driver).newInstance();  
  
        Connection conn = DriverManager.getConnection(url);  
  
        return conn;  
    }  
  
    /** 
     *  
     */  
    public MySQLConnection() {  
        super();  
        // TODO 自动生成构造函数存根  
    }  
  
    public static void main(String[] args) {  
        try {  
            getConnection();  
            System.out.println("success");  
        } catch (Exception e) {  
            System.out.println("failure!");  
            e.printStackTrace();  
        }  
    }  
}  
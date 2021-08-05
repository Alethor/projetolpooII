
package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static Connection conn = null;
    
    public static Connection getConnection(){
        if(null == conn){
            try{
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                conn = DriverManager.getConnection(url,properties);
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        
        }
        return conn;
    }
    
    public static void closeConnection(){
        if(null != conn){
            try{
                conn.close();
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }
    
    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties properties = new Properties();
            properties.load(fs);
            return properties;
        }catch(IOException e ){
            throw new DbException(e.getMessage());
        }
    } 
}

package connection;
  
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
  
public class DbManager {
  
    public static final int LoginTimeout = 10;
    private static final String ConfigFile = "config/db.properties";
  
    public static Connection createConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties prop = new Properties();
        String host;
        String username;
        String password;
        Connection connection = null;
        try {
        	String path = new File(ConfigFile)
                    .getAbsolutePath();
            prop.load(new java.io.FileInputStream(path));
            host = prop.getProperty("host").toString();
            username = prop.getProperty("username").toString();
            password = prop.getProperty("password").toString();
            DriverManager.setLoginTimeout(LoginTimeout);
            connection = DriverManager.getConnection(host, username, password);
            
        } catch (IOException e) {
        	System.err.println(e.getMessage());
        }
        return connection;
    }
}
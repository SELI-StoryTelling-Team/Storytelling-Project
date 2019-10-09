package connection;
  
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
  
public class DbManager {
  
    private static final int LoginTimeout = 10;
  
    public DbManager() {
    	Connection connection;
		try {
			connection = createConnection();
			connection.isValid(LoginTimeout);
			if(connection.isValid(LoginTimeout))
				System.out.println("You are connected!");
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println(e.getMessage());
		}
    }
  
    public Connection createConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties prop = new Properties();
        String host;
        String username;
        String password;
        String driver;
        try {
        	String path = new File("config/db.properties")
                    .getAbsolutePath();
        	System.out.println(path);
            prop.load(new java.io.FileInputStream(path));
  
            host = prop.getProperty("host").toString();
            username = prop.getProperty("username").toString();
            password = prop.getProperty("password").toString();
            driver = prop.getProperty("driver").toString();
        } catch (IOException e) {
            host = "Unknown HOST";
            username = "Unknown USER";
            password = "Unknown PASSWORD";
            driver = "Unknown DRIVER";
        }
  
        System.out.println("host: " + host + "\nusername: " + username + "\npassword: " + password + "\ndriver: " + driver);
  
        Class.forName(driver);
        System.out.println("--------------------------");
        System.out.println("DRIVER: " + driver);
        System.out.println("Set Login Timeout: " + LoginTimeout);
        DriverManager.setLoginTimeout(LoginTimeout);
        Connection connection = DriverManager.getConnection(host, username, password);
        System.out.println("CONNECTION: " + connection);
  
        return connection;
    }
}
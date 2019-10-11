package connection;
  
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
  
public class DbManager {
  
    public static final int LoginTimeout = 10;
    
    private static final String ConfigFile = "config/database.properties";
  
    private static boolean isInitialized = false;
    private static String host;
    private static String username;
    private static String password;
    private static String shema;
    
    public static void init() {
    	Properties prop = new Properties();
    	try {
        	String path = new File(ConfigFile)
                    .getAbsolutePath();
            prop.load(new java.io.FileInputStream(path));
            host = prop.getProperty("host").toString();
            username = prop.getProperty("username").toString();
            password = prop.getProperty("password").toString();
            shema = prop.getProperty("shema");
        } catch (IOException e) {
        	System.err.println(e.getMessage());
        }
    }
    
    public static Connection createConnection() throws IOException, ClassNotFoundException, SQLException {
    	if(!isInitialized)
    		init();
    	DriverManager.setLoginTimeout(LoginTimeout);
        Connection connection = DriverManager.getConnection(host, username, password);
        return connection;
    }
    
    public static boolean userValidation(String username, String password) {
    	boolean valid = false;
    	if(!isInitialized)
    		init();
    	String query = "SELECT \"" + shema + "\".\"userValidation\"(?, ?);";
    	try(Connection connection = createConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			result.next();
			valid = result.getBoolean(1);
			connection.close();
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return valid;
    }
}
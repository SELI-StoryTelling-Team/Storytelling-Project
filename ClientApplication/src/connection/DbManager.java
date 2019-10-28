package connection;
  
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

import model.Action;
import model.Rol;
import model.User;
  
public class DbManager extends Thread {
  
    public static final int LoginTimeout = 10;
    private static int process_id;
    private static final String ConfigFile = "config/database.properties";
    private static DbManager instance;
    
    private boolean isInitialized = false;
    private String host;
    private String username;
    private String password;
    private String shema;
    private boolean stopRunning;
    private Queue<Action<String>> actions;
    
    public DbManager() {
    	process_id = 1;
    	actions = new LinkedList<Action<String>>();
    	stopRunning = false;
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
    
    public void run(){
    	while(!stopRunning) {
    		try {
    			if(actions.size() > 0)
    				actions.poll().apply("Loading process #" + process_id++);
    			else
    				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    public static void init() {
    	if(instance == null) {
	    	instance = new DbManager();
	    	instance.start();
    	}
    }
    
    public void stopRunning() {
    	stopRunning = true;
		instance = null;
    }
    
    public Connection createConnection() throws IOException, ClassNotFoundException, SQLException {
    	DriverManager.setLoginTimeout(LoginTimeout);
        Connection connection = DriverManager.getConnection(host, username, password);
        return connection;
    }
    
    public static DbManager getInstance() {
    	if(instance == null || !instance.isInitialized)
    		init();
    	return instance;
    }
    
    public static void getUser(Action<User> action, Long id) {
    	if(instance == null || !instance.isInitialized)
    		init();
    	instance.actions.add(msg -> 
    	{
    		System.out.println(msg);
    		User user = null;
    		String query = "SELECT * FROM \"" + instance.shema + "\".\"" + User.getTableName() + "\" WHERE "
    				+ User.getIdUserField()+"='" + id + "';";
    		
    		try(Connection connection = instance.createConnection()) {
    			
    			PreparedStatement statement = connection.prepareStatement(query);
    			ResultSet result = statement.executeQuery();
    			
    			result.next();
    			user = new User();
    			user.setIdUser(result.getLong(1));
    			user.setIdRol(result.getLong(2));
    			user.setName(result.getString(3));
    			user.setPassword(result.getString(4));
    			user.setActive(result.getBoolean(5));
    			
    			query = "SELECT * FROM \"" + instance.shema + "\".\"" + Rol.getTableName() + "\" WHERE "
        				+ Rol.getIdRolField()+"='" + user.getIdRol() + "';";
    			
    			statement = connection.prepareStatement(query);
    			result = statement.executeQuery();
    			result.next();
    			
    			Rol rol = new Rol();
    			rol.setIdRol(user.getIdRol());
    			rol.setName(result.getString(2));
    			rol.setActive(result.getBoolean(3));
    			
    			user.setRol(rol);
    			
    			//valid = result.getBoolean(1);
    			connection.close();
    			
    		} catch (ClassNotFoundException | IOException | SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			user = null;
    		}
    		action.apply(user);
    		System.out.println("End Process");
    	});
    }
    
    public static void userValidation(Action<Boolean> action, String username, String password) {
    	if(instance == null || !instance.isInitialized)
    		init();
    	instance.actions.add(msg -> 
    	{
    		System.out.println(msg);
    		boolean valid = false;
    		String query = "SELECT \"" + instance.shema + "\".\"userValidation\"(?, ?);";
        	try(Connection connection = instance.createConnection()) {
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
        	action.apply(valid);
        	System.out.println("End Process");
    	});
    }
}
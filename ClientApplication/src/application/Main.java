package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import view.LoginPageView;
import model.CryptoUtil;
import model.LoginPageModel;
import model.SystemMacAddress;
import model.TokenModel;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Enumeration;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import connection.DbManager;
import controller.LoginPageController;
import controller.TokenController;
import javafx.scene.Cursor;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Stage primaryStage;
	private static LoginPageModel loginModel;
	
	@Override
	public void start(Stage primaryStage) {
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("resources/icons/icon.png");
		addTrayIcon(image);
		
		Main.loginModel = new LoginPageModel();
		LoginPageController loginController = new LoginPageController(loginModel);
		LoginPageView loginView = new LoginPageView(loginController);
		
		
		try {
			Scene scene = new Scene(loginView,400,400);
			primaryStage.setScene(scene);
			Main.primaryStage = primaryStage;
			Platform.setImplicitExit(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void hideLoginPage() {
		DbManager.getInstance().stopRunning();
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.primaryStage.close();
			}
		});
	}
	
	public static void showLoginPage() {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.primaryStage.show();
			}
		});
	}
	
	public static void setCursor(Cursor cursor) {
		primaryStage.getScene().setCursor(cursor);
	}
	
	private void addTrayIcon(Image image) {
		if (SystemTray.isSupported()) {
		   TrayIcon trayIcon = new TrayIcon(image);
		   trayIcon.setImageAutoSize(true);

		   trayIcon.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mouseClicked(MouseEvent e) {
		    	  if(loginModel.getLoginState()) {
		    		  System.out.println("Clicked");
		    	  }
		    	  else
		    		  showLoginPage();
		      }
		   });
		   try {
		      SystemTray.getSystemTray().add(trayIcon);
		   } catch (AWTException ex) {
		      System.err.println("Error while creating tray icon.");
		   }
		} else {
		   System.err.println("Tray icons are not supported on this System.");
		}
	}
}

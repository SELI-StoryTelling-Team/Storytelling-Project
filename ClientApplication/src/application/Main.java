package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginPageView;
import model.LoginPageModel;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.LoginPageController;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("resources/icons/icon.png");
		addTrayIcon(image);
		
		LoginPageModel loginModel = new LoginPageModel();
		LoginPageController loginController = new LoginPageController(loginModel);
		LoginPageView loginView = new LoginPageView(loginController);
		
		
		try {
			Scene scene = new Scene(loginView,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void addTrayIcon(Image image) {
		if (SystemTray.isSupported()) {
		   TrayIcon trayIcon = new TrayIcon(image);
		   trayIcon.setImageAutoSize(true);

		   trayIcon.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mouseClicked(MouseEvent e) {
		         System.out.println("Clicked");
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

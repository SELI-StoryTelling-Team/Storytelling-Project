package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginPageView;
import model.LoginPageModel;
import controller.LoginPageController;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
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
}

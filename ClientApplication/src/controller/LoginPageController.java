package controller;

import application.Main;
import connection.DbManager;
import javafx.scene.Cursor;
import model.Action;
import model.LoginPageModel;
import model.TokenModel;

public class LoginPageController {
	
	private LoginPageModel model;
	
	public LoginPageController(LoginPageModel model) {
		this.model = model;
		TokenController.init();
		TokenModel token = TokenController.getToken();
		DbManager.userValidation(valid -> 
		{
			System.out.println(valid);
			model.setLoginState(valid);
		}, token.getUserName(), token.getPassword());
	}
	
	public void onLoginAction(Action<Boolean> action, String username, String password){
		Main.setCursor(Cursor.WAIT);
		DbManager.userValidation(valid -> 
		{
			if(valid) {
				TokenModel token = TokenController.getToken();
				token.setUserName(username);
				token.setPassword(password);
				TokenController.saveToken();
			}
			Main.setCursor(Cursor.DEFAULT);
			System.out.println(valid);
			model.setLoginState(valid);
			action.apply(valid);
		}, username, password);
	}
}

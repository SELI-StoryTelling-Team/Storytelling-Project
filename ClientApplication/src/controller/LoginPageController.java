package controller;

import connection.DbManager;
import model.LoginPageModel;

public class LoginPageController {
	
	private LoginPageModel model;
	
	public LoginPageController(LoginPageModel model) {
		this.model = model;
	}
	
	public void onLoginAction(String username, String password){
		boolean loginState = DbManager.userValidation(username, password);
		model.setLoginState(loginState);
	}
}

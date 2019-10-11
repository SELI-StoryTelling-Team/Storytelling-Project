package model;

public class LoginPageModel {
	
	private boolean isLogin;
	
	public LoginPageModel() {
		isLogin = false;
	}
	
	public void setLoginState(boolean state) {
		isLogin = state;
	}
	
	public boolean getLoginState() {
		return isLogin;
	}
	
}

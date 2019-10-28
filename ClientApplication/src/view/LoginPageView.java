package view;

import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import application.Main;
import controller.LoginPageController;

public class LoginPageView extends GridPane {
	
	private LoginPageController controller;
	private Button btnSubmit;
	private TextField tfName;
	private PasswordField pfPwd;
	
	public LoginPageView(LoginPageController controller){
		super();
		this.controller = controller;
		setAlignment(Pos.CENTER);  // Override default
        setHgap(10);
        setVgap(12);
        
        // Use column constraints to set properties for columns in the grid
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);  // Override default
        getColumnConstraints().add(column1); 

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.LEFT);  // Override default
        getColumnConstraints().add(column2); 

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.setAlignment(Pos.CENTER);  // Aligns HBox and controls in HBox


        btnSubmit = new Button("Login");
        btnSubmit.setDefaultButton(true);
        
        Label lblName = new Label("User name:");
        tfName = new TextField();
        Label lblPwd = new Label("Password:");
        pfPwd = new PasswordField();
        
        hbButtons.getChildren().addAll(btnSubmit);
        add(lblName, 0, 0);
        add(tfName, 1, 0);
        add(lblPwd, 0, 1);
        add(pfPwd, 1, 1);
        add(hbButtons, 0, 2, 2, 1);
        
        setControllerLoginAction();
	}
	
	public void setEnable(boolean enable) {
		for(Node node : getChildren()) {
			node.setDisable(!enable);
		}
	}
	
	private void setControllerLoginAction() {
		btnSubmit.setOnMouseClicked(args -> 
		{
			setEnable(false);
			controller.onLoginAction(valid -> 
			{
				setEnable(true);
				if(valid)
					Main.hideLoginPage();
			}, tfName.getText(), pfPwd.getText());
			tfName.setText("");
			pfPwd.setText("");
		});
	}
}

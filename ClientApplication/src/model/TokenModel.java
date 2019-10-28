package model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.Serializable;

public class TokenModel implements Serializable {
	
	private String userName;
	private String password;
	private CryptoUtil cryptoUtil;
	
	public TokenModel() {
		cryptoUtil = new CryptoUtil();
		setUserName("root");
		setPassword("root");
	}
	
	public TokenModel(String encUsername, String encPassword) {
		cryptoUtil = new CryptoUtil();
		userName = encUsername;
		password = encPassword;
	}
	
	public void setUserName(String userName) {
		try {
			this.userName = cryptoUtil.encrypt(SystemMacAddress.getSystemMac(), userName);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | UnsupportedEncodingException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public void setPassword(String password) {
		try {
			this.password = cryptoUtil.encrypt(SystemMacAddress.getSystemMac(), password);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | UnsupportedEncodingException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public String getEncPassword() {
		return password;
	}
	
	public String getEncUserName() {
		return userName;
	}
	
	public String getPassword() {
		String pass = "";
		try {
			pass = cryptoUtil.decrypt(SystemMacAddress.getSystemMac(), password);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return pass;
	}
	
	public String getUserName() {
		String uname = "";
		try {
			uname = cryptoUtil.decrypt(SystemMacAddress.getSystemMac(), userName);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return uname;
	}
}

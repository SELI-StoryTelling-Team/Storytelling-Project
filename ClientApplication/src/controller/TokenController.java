package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import connection.DbManager;
import model.CryptoUtil;
import model.SystemMacAddress;
import model.TokenModel;

public class TokenController {
	
	private static TokenModel token;
	private static final String PATH = "app_data.token";
	
	public static void init() {
		
		
		DbManager.getUser(user -> 
		{
			if(user != null)
				System.out.println(user.getRol().getName());
		}, 1l);
		
		
		File file = new File(PATH);
		JSONObject json = null;
		if(file.exists())
		{
			CryptoUtil crypt = new CryptoUtil();
			InputStream is;
			try {
				is = new FileInputStream(file);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
				JSONParser parser = new JSONParser();
				json = (JSONObject)parser.parse(crypt.decrypt(SystemMacAddress.getSystemMac(), buf.readLine()));
				buf.close();
				is.close();
			} catch (Exception e) {
			}
		}
		if(json == null) 
			token = new TokenModel();
		else 
			token = new TokenModel((String)json.get("username"), (String)json.get("password"));
	}
	
	public static void saveToken() {
		if(token != null) {
			File file = new File(PATH);
			JSONObject json = new JSONObject();
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("username", token.getEncUserName());
			map.put("password", token.getEncPassword());
			json.putAll(map);
			
			if(file.exists())
				file.delete();
			FileOutputStream fo;
			try {
				CryptoUtil crypt = new CryptoUtil();
				fo = new FileOutputStream(file);
				BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(fo));
				buf.write(crypt.encrypt(SystemMacAddress.getSystemMac(), json.toString()));
				buf.flush();
				buf.close();
				fo.close();
			} catch (Exception e) {
			}
		}
	}
	
	public static TokenModel getToken() {
		return token;
	}
}

package model;

public class User extends DatabaseEntity<Long> {
	
	private long idUser;
	private long idRol;
	private String name;
	private String password;
	private boolean active;
	
	private Rol rol;
	
	public long getIdUser() {
		return idUser;
	}
	
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	public long getIdRol() {
		return idRol;
	}
	
	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Rol getRol() {
		return rol;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public static String getTableName() {
		return "User";
	}
	
	public static String getUsernameField() {
		return "name";
	}
	
	public static String getPasswordField() {
		return "password";
	}
	
	public static String getIdUserField() {
		return "id_user";
	}


	@Override
	public Long getPrimaryKey() {
		// TODO Auto-generated method stub
		return idUser;
	}
}

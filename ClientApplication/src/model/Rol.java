package model;

public class Rol extends DatabaseEntity<Long> {
	
	private long idRol;
	private String name;
	private boolean active;
	
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
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public static String getTableName() {
		return "Rol";
	}
	
	public static String getIdRolField() {
		return "id_rol";
	}

	@Override
	public Long getPrimaryKey() {
		return idRol;
	}
}

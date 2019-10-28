package model;

public abstract class DatabaseEntity<K extends Comparable<K>> implements Comparable<DatabaseEntity<K>> {
	
	public abstract K getPrimaryKey();
	
	@Override
	public int compareTo(DatabaseEntity<K> o) {
		return getPrimaryKey().compareTo(o.getPrimaryKey());
	}
}

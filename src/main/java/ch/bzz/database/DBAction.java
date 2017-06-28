package ch.bzz.database;

import org.hibernate.Session;

public abstract class DBAction<T> {
	
	public abstract T run(Session s) throws Exception;

}

package ch.bzz.database;

import org.hibernate.Session;

public abstract class DBAction {
	
	public abstract Object run(Session s);

}

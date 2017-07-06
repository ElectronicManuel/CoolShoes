package ch.bzz.database;

import org.hibernate.Session;

/**
 * 
 * Diese Klasse ist eine Callback Klasse um die Hibernate Transaktionen einheitlich zu starten / beenden
 * 
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 *
 * @param <T> - Der Typ der Zurückgegeben werden soll
 */
public abstract class DBAction<T> {
	
	/**
	 * Jede Implementation dieser Klasse hat diese Methode, in ihr kann eine Transaktion aus der session bearbeitet werden und ein Objekt des Types T zurückgegeben werden
	 * 
	 * @param s - Hibernate Session
	 * @return ein Objekt des Typs T
	 * @throws Exception
	 */
	public abstract T run(Session s) throws Exception;

}

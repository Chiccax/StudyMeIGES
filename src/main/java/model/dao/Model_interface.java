package model.dao;

import java.sql.SQLException;
import java.util.Collection;
/**
 * Interfaccia  
 **/
public interface Model_interface<A> {
	/**
	 * Inserisce
	 * @param entity
	 * @return 
	 * @throws SQLException
	 **/
	public void  insert(A entity) throws SQLException;
	/**
	 * Modifica
	 * @param entity
	 * @return 
	 * @throws SQLException
	 **/
	public void update(A entity) throws SQLException;
	/**
	 * Rimuove
	 * @param codiceP codice del pacchetto
	 * @throws SQLException
	 **/
	public boolean remove(Object codiceP) throws SQLException;
	/**
	 * Ricerca trsmite il codiceP
	 * @param codiceP codice del pacchetto
	 * @return A
	 * @throws SQLException
	 **/
	public A findByKey(Object codiceP) throws SQLException;
	/**
	 * Ricerca totale
	 * @param 
	 * @return Collection<A> 
	 * @throws SQLException
	 **/
	public Collection<A> findAll() throws SQLException;

}
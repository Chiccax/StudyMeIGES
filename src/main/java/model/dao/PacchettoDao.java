package model.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.DriverManagerConnectionPool;
import model.bean.CategoriaBean;
import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.RecensioneBean;
import model.bean.UtenteBean;
import model.dao.SottocategoriaDao;

public class PacchettoDao {
	public PacchettoDao() {
		
	}
	/**
	 * Recupera tutti i pacchetti dal database
	 * @param
	 * @return ArrayList<PacchettoBean> un array di pacchetti
	 * context PacchettoDao::ArrayList<PacchettoBean> getAllPacchetti()
	 **/
	public ArrayList<PacchettoBean> getAllPacchetti(){
		try {
			
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto");
			//stm.setBoolean(1, true);
			ResultSet res = stm.executeQuery();
			ArrayList<PacchettoBean> pacchetti= new ArrayList<PacchettoBean>();
			while(res.next()) {
				PacchettoBean pa= new PacchettoBean();
				pa.setCodicePacchetto(res.getString(1));
				pa.setCatagoria(res.getString(2));
				pa.setSottocategoria(res.getString(3));
				pa.setPrezzo(res.getDouble(4));
				pa.setDescrizione(res.getString(5));
				pa.setTitolo(res.getString(6));
				pa.setApprovato(res.getInt(9));
				
				pacchetti.add(pa);
				
			}
			return pacchetti;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	/**
	 * Recupera un singolo pacchetto dal database
	 * @param codiceP codice del pacchetto
	 * @return PacchettoBean un pacchetto
	 * context PacchettoDao::getPacchetto(String codiceP)
	 * @pre codiceP != null
	 **/
	public PacchettoBean getPacchetto(String codiceP) {
		Connection conn;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, codiceP);
			ResultSet res=stm.executeQuery();
			conn.commit();
			if(res.next()) {
				PacchettoBean pa= new PacchettoBean();
				pa.setCodicePacchetto(res.getString(1));
				pa.setCatagoria(res.getString(2));
				pa.setSottocategoria(res.getString(3));
				pa.setPrezzo(res.getDouble(4));
				pa.setDescrizione(res.getString(5));
				pa.setTitolo(res.getString(6));
				pa.setFoto(res.getString(7));
				pa.setApprovato(res.getInt(9));
				
				return pa;
			}
			else {
				return null;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	/**
	 * Recupera tutti i pacchetti con un determinato titolo dal database
	 * @param titolo titolo del pacchetto
	 * @return PacchettoBean un pacchetto
	 * context PacchettoDao::getPacchettoByTitolo(String titolo)
	 * @pre titolo != null && titolo presente nel db
	 **/
	public PacchettoBean getPacchettoByTitolo(String titolo) {
		Connection conn;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE titolo = ?");
			stm.setString(1, titolo);
			ResultSet res=stm.executeQuery();
			conn.commit();
			if(res.next()) {
				PacchettoBean pa= new PacchettoBean();
				pa.setCodicePacchetto(res.getString(1));
				pa.setCatagoria(res.getString(2));
				pa.setSottocategoria(res.getString(3));
				pa.setPrezzo(res.getDouble(4));
				pa.setDescrizione(res.getString(5));
				pa.setTitolo(res.getString(6));
				pa.setFoto(res.getString(7));
				
				return pa;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Elimina il pacchetto
	 * @param codiceP codice del pacchetto
	 * @return true se elimina il pacchetto, false altrimenti
	 * context PacchettoDao::delPacchetto(String codiceP)
	 * @pre codiceP !=null
	 * @post pacchetto eliminato
	 **/
	public boolean delPacchetto(String codiceP) {
		Connection conn;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, codiceP);
			ResultSet res=stm.executeQuery();
			conn.commit();
			if(res.next()) {
				return false;
			}
			else {
				return true;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}	
	}
	/**
	 * Recupera le informazioni base sui corsi che hanno un nome corrispondente alla stringa si ricerca
	 * @param word stringa di ricerca
	 * @return ArrayList<PacchettoBean> array di pacchetti
	 * context PacchettoDao::searchPackage(String word)
	 * @pre word != null
	 * 
	 **/
	public ArrayList<PacchettoBean> searchPackage(String word){
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM pacchetto " + "WHERE titolo like ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, "%" + word + "%");
			ResultSet res = stm.executeQuery();
			conn.commit();

			ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();

			while (res.next()) {
				PacchettoBean pacchetto = new PacchettoBean();
				pacchetto.setCodicePacchetto(res.getString(1));
				pacchetto.setCatagoria(res.getString(2));
				pacchetto.setSottocategoria(res.getString(3));
				pacchetto.setPrezzo(res.getDouble(4));
				pacchetto.setDescrizione(res.getString(5));
				pacchetto.setTitolo(res.getString(6));
				pacchetto.setFoto(res.getString(7));

				pacchetti.add(pacchetto);
			}
			return pacchetti;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Raggruppa tutti i pacchetti che appartengono ad una determinata categoria
	 * @param categoria categoria di appartenenza 
	 * @return Map<String,ArrayList<PacchettoBean>> 
	 * context PacchettoDao::getCategoriaRaggruppato(String categoria)
	 * @pre categoria != null
	 * **/
	public Map<String,ArrayList<PacchettoBean>> getCategoriaRaggruppato(String categoria) {
		Map<String, ArrayList<PacchettoBean>> result = new HashMap<String, ArrayList<PacchettoBean>>();
		Map<String, String>  sottocategorie = new HashMap<String, String>();
		
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM pacchetto " + "WHERE categoria = ? AND nelCatalogo = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, categoria);
			stm.setBoolean(2, true);
			ResultSet res = stm.executeQuery();
			conn.commit();

			SottocategoriaDao manager = new SottocategoriaDao();
			
			while (res.next()) {
				PacchettoBean pacchetto1 = new PacchettoBean();
				pacchetto1.setCodicePacchetto(res.getString(1));
				pacchetto1.setCatagoria(res.getString(2));
				pacchetto1.setSottocategoria(res.getString(3));
				pacchetto1.setPrezzo(res.getDouble(4));
				pacchetto1.setDescrizione(res.getString(5));
				pacchetto1.setTitolo(res.getString(6));
				pacchetto1.setFoto(res.getString(7));
				pacchetto1.setApprovato(res.getInt(9));

				String valueSottocategoria = null;
				
				if(sottocategorie.containsKey(pacchetto1.getSottocategoria())) {
					valueSottocategoria = sottocategorie.get(pacchetto1.getSottocategoria());
				} else {
					valueSottocategoria = manager.findByKey(pacchetto1.getSottocategoria()).getNomeSott();
				}
				
				if(!result.containsKey(valueSottocategoria)) {
					result.put(valueSottocategoria, new ArrayList<PacchettoBean>());
				}
				
				result.get(valueSottocategoria).add(pacchetto1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
	/**
	 * Raggruppa tutti i pacchetti che appartengono ad una determinata categoria che sono approvati
	 * @param categoria categoria di appartenenza 
	 * @return Map<String,ArrayList<PacchettoBean>> 
	 * context PacchettoDao:: getCategoriaRaggruppatoApprovato(String categoria)
	 * @pre categoria != null
	**/
	public Map<String,ArrayList<PacchettoBean>> getCategoriaRaggruppatoApprovato(String categoria) {
		Map<String, ArrayList<PacchettoBean>> result = new HashMap<String, ArrayList<PacchettoBean>>();
		Map<String, String>  sottocategorie = new HashMap<String, String>();
		
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM pacchetto " + "WHERE categoria = ? AND nelCatalogo = ? AND approvato = 1";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, categoria);
			stm.setBoolean(2, true);
			ResultSet res = stm.executeQuery();
			conn.commit();

			SottocategoriaDao manager = new SottocategoriaDao();
			
			while (res.next()) {
				PacchettoBean pacchetto1 = new PacchettoBean();
				pacchetto1.setCodicePacchetto(res.getString(1));
				pacchetto1.setCatagoria(res.getString(2));
				pacchetto1.setSottocategoria(res.getString(3));
				pacchetto1.setPrezzo(res.getDouble(4));
				pacchetto1.setDescrizione(res.getString(5));
				pacchetto1.setTitolo(res.getString(6));
				pacchetto1.setFoto(res.getString(7));
				pacchetto1.setApprovato(res.getInt(9));

				String valueSottocategoria = null;
				
				//Se ho gi� estratto il valore della sottocategoria dal db lo vado a prendere dalla mappa
				//altrimenti lo vado dal db
				if(sottocategorie.containsKey(pacchetto1.getSottocategoria())) {
					valueSottocategoria = sottocategorie.get(pacchetto1.getSottocategoria());
				} else {
					valueSottocategoria = manager.findByKey(pacchetto1.getSottocategoria()).getNomeSott();
				}
				
				if(!result.containsKey(valueSottocategoria)) {
					result.put(valueSottocategoria, new ArrayList<PacchettoBean>());
				}
				
				result.get(valueSottocategoria).add(pacchetto1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	/**
	 * Recupera la categoria di un pacchetto
	 * @param categoria categoria del pacchetto 
	 * @return categoria 
	 * context PacchettoDao::getBeanCategoria(String categoria)
	 * @pre categoria != null
	 **/
	public CategoriaBean getBeanCategoria(String categoria) {
		
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM categoria " + "WHERE nomeCategoria = ? ";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1,categoria);
			ResultSet res = stm.executeQuery();
			conn.commit();
			
		if(res.next()) {
				CategoriaBean  cate = new CategoriaBean();
				cate.setFotoCategoria(res.getString(2));
					return cate;
		}else 
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Recupera tutte le lezioni del pacchetto
	 * @param codicePacchetto codice del pacchetto
	 * @return ArrayList<LezioniBean> array di lezioni 
	 * context PacchettoDao::getLezioni(String codicePacchetto)
	 * @pre codicePacchetto != null && codicePacchetto presente nel database
	 **/
	public ArrayList<LezioniBean> getLezioni(String codicePacchetto){
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM lezioni " + "WHERE codiceP = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, codicePacchetto);
			ResultSet res = stm.executeQuery();
			conn.commit();

			ArrayList<LezioniBean> lezioni = new ArrayList<LezioniBean>();
			
			while (res.next()) {
				LezioniBean lezione = new LezioniBean();
				lezione.setUrl(res.getString(1));
				lezione.setTitolo(res.getString(2));
				lezione.setDurata(res.getString(3));
				lezione.setPacchetto(res.getString(4));
				
				lezione.setApprovato(res.getInt(5));

				lezioni.add(lezione);
			}
			return lezioni;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Recupera tutte le lezioni tramite url
	 * @param url url della lezione
	 * @return ArrayList<LezioniBean> array di lezioni 
	 * context PacchettoDao::getLezioniByURl(String url
	 * @pre url != null && url presente nel database
	 **/
	public ArrayList<LezioniBean> getLezioniByURl(String url){
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM lezioni " + "WHERE url = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, url);
			ResultSet res = stm.executeQuery();
			conn.commit();

			ArrayList<LezioniBean> lezioni = new ArrayList<LezioniBean>();
			
			while (res.next()) {
				LezioniBean lezione = new LezioniBean();
				lezione.setUrl(res.getString(1));
				lezione.setTitolo(res.getString(2));
				lezione.setDurata(res.getString(3));
				lezione.setPacchetto(res.getString(4));
				
				lezione.setApprovato(res.getInt(5));

				lezioni.add(lezione);
			}
			return lezioni;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Recupera tutte le lezioni tramite il titolo
	 * @param titolo titolo della lezione
	 * @return ArrayList<LezioniBean> array di lezioni 
	 * context PacchettoDao::getLezioniByTitolo(String titolo)
	 * @pre titolo != null && titolo presente nel database
	 **/
	public ArrayList<LezioniBean> getLezioniByTitolo(String titolo){
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM lezioni " + "WHERE titolo = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, titolo);
			ResultSet res = stm.executeQuery();
			conn.commit();

			ArrayList<LezioniBean> lezioni = new ArrayList<LezioniBean>();
			
			while (res.next()) {
				LezioniBean lezione = new LezioniBean();
				lezione.setUrl(res.getString(1));
				lezione.setTitolo(res.getString(2));
				lezione.setDurata(res.getString(3));
				lezione.setPacchetto(res.getString(4));
				
				lezione.setApprovato(res.getInt(5));

				lezioni.add(lezione);
			}
			return lezioni;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Recupera tutte le lezioni del pacchetto che sono state approvate
	 * @param codicePacchetto codice del pacchetto
	 * @return ArrayList<LezioniBean> array di lezioni 
	 * context PacchettoDao:: getLezioniApprovate(String codicePacchetto)
	 * @pre codicePacchetto != null && codicePacchetto non presente nel database
	 **/
	public ArrayList<LezioniBean> getLezioniApprovate(String codicePacchetto){
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM lezioni " + "WHERE codiceP = ? AND approvato = 1";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, codicePacchetto);
			ResultSet res = stm.executeQuery();
			conn.commit();

			ArrayList<LezioniBean> lezioni = new ArrayList<LezioniBean>();
			
			while (res.next()) {
				LezioniBean lezione = new LezioniBean();
				lezione.setUrl(res.getString(1));
				lezione.setTitolo(res.getString(2));
				lezione.setDurata(res.getString(3));
				lezione.setPacchetto(res.getString(4));
				
				lezioni.add(lezione);
			}
			return lezioni;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Recupera tutte le recensioni del pacchetto
	 * @param codicePacchetto codice del pacchetto
	 * @return ArrayList<RecensioneBean> array di recensioni
	 * context PacchettoDao::getRecensioni(String codicePacchetto)
	 * @pre codicePacchetto != null
	 **/
	public ArrayList<RecensioneBean> getRecensioni(String codicePacchetto){
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM recensione " + "WHERE codiceP = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, codicePacchetto);
			ResultSet res = stm.executeQuery();
			conn.commit();

			ArrayList<RecensioneBean> recensioni = new ArrayList<RecensioneBean>();
			
			while (res.next()) {
				RecensioneBean recensione = new RecensioneBean();
				recensione.setIdRecensione(res.getString(1));
				recensione.setCliente(res.getString(2));
				recensione.setPacchetto(res.getString(3));
				recensione.setCommento(res.getString(4));
				recensione.setTitolo(res.getString(5));

				recensioni.add(recensione);
			}
			return recensioni;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Recupera tutti gli acquirenti che hanno acquistato un determinato pacchetto
	 * @param codicePacchetto codice del pacchetto
	 * @return ArrayList<UtenteBean> array di utenti
	 * context PacchettoDao::getAcquirenti(String codicePacchetto)
	 * @pre codicePacchetto != null
	 **/
	public ArrayList<UtenteBean> getAcquirenti(String codicePacchetto){
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT acquisto.numOrdine, ordine.nomeCliente, utente.*\r\n" + 
						 "FROM studyme.acquisto, studyme.ordine, studyme.utente\r\n" + 
						 "WHERE acquisto.codiceP = ? AND ordine.numOrdine = acquisto.numOrdine AND utente.nomeUtente = ordine.nomeCliente";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, codicePacchetto);
			ResultSet res = stm.executeQuery();
			conn.commit();

			ArrayList<UtenteBean> acquirenti = new ArrayList<UtenteBean>();
			
			while (res.next()) {
				UtenteBean acquirente = new UtenteBean();
				acquirente.setNomeUtente(res.getString(3));
				acquirente.setPassword(res.getString(4));
				acquirente.setEmail(res.getString(5));
				acquirente.setTipo(res.getString(6));

				acquirenti.add(acquirente);
			}
			return acquirenti;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
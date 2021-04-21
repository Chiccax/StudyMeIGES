package model.manager;

import model.bean.RecensioneBean;
import model.dao.RecensioneDao;

import java.sql.SQLException;

public class RecensioneManager {
	/**
	 * Costruttore vuoto. 
	 **/
	public RecensioneManager(){	
	}
	/**
	 * Aggiungi una recesnione.
	 * @param nomeUtente
	 * @param codicePacchetto
	 * @param titoloRecensione
	 * @param testoRecensione
	 * @return
	 * @see RecensioneDao
	 **/
	public void aggiungiRecensione(String nomeUtente, String codicePacchetto,String titoloRecensione,String testoRecensione){
		RecensioneDao r;
		if(dao != null) {
			r = dao;
		} else {
			r = new RecensioneDao();
		}
		r.aggiungiRecensione(nomeUtente, codicePacchetto, titoloRecensione, testoRecensione);
	}


	/**
	 * Modifica la recensione di un determinato pacchetto
	 * @param nomeUtente
	 * @param codicePacchetto
	 * @param titoloRecensione
	 * @param testoRecensione
	 * @throws SQLException
	 * @see RecensioneDao
	 */
	public void modificaRecensione(String nomeUtente, String codicePacchetto,String titoloRecensione,String testoRecensione) throws SQLException {
		RecensioneDao r;
		if(dao != null) {
			r = dao;
		} else {
			r = new RecensioneDao();
		}
		r.updateRecensione(nomeUtente,codicePacchetto,titoloRecensione,testoRecensione);
	}

	public void setDao(RecensioneDao r) {
		this.dao = r;
	}
	
	private RecensioneDao dao;
}

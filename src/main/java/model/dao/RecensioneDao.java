package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import control.util.StartupUtility;
import model.DriverManagerConnectionPool;
import model.bean.LezioniBean;
import model.bean.RecensioneBean;


public class RecensioneDao {

	public RecensioneDao() {}
	/**
	 * Aggiunge una recensione al pacchetto se il video è gia stato visto
	 * @param nomeUtente nome utente dell'acquirente 
	 * @param codicePacchetto codice del pacchetto
	 * @param titoloRecensione titolo della recensione
	 * @param testoRecensione testo della recensione
	 * @return 
	 * context RecensioneDao::aggiungiRecensione(String nomeUtente, String codicePacchetto, String titoloRecensione, String testoRecensione)
	 * @pre isAlwreadyReviewed == true
	 * @post recensione presente nel db
	 **/
	public void aggiungiRecensione(String nomeUtente, String codicePacchetto, String titoloRecensione, String testoRecensione) {
		try {
			
			if(isAlwreadyReviewed(nomeUtente, codicePacchetto)) {
				return;
			}
			
		 	Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("INSERT INTO recensione(userCliente, codiceP, commento, titolo) values(?,?,?,?)");
			
			stm.setString(1, nomeUtente);
			stm.setString(2, codicePacchetto);
			stm.setString(3, testoRecensione);
			stm.setString(4, titoloRecensione);
			
			stm.executeUpdate();
			conn.commit();		
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Verifica che un corso è già stato visto dall'acquirente 
	 * @param nomeUtente nome utente dell'acquirente
	 * @param codicePacchetto codice del pacchetto
	 * @return true se il pacchetto è stato visualizzato, false altrimenti
	 * @throws SQLException
	 * context RecensioneDao::isAlwreadyReviewed(String nomeUtente, String codicePacchetto)
	 * @pre nomeUtente != null && codicePacchetto != null
	 **/
	public boolean isAlwreadyReviewed(String nomeUtente, String codicePacchetto) throws SQLException{
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM recensione WHERE userCliente = ? AND codiceP = ?");
			
			stm.setString(1, nomeUtente);
			stm.setString(2, codicePacchetto);
			ResultSet res = stm.executeQuery();

			return res.next();	
	}

	/**
	 * Aggiorna il valore del titolo e del commento di una recensione.
	 * @param nomeUtente
	 * @param codicePacchetto
	 * @param newTitolo
	 * @param newCommento
	 * @throws SQLException
	 */
	public void updateRecensione(String nomeUtente, String codicePacchetto, String newTitolo, String newCommento) throws SQLException{
		Connection conn = DriverManagerConnectionPool.getConnection();
		PreparedStatement stm = conn.prepareStatement("UPDATE recensione SET titolo = ? WHERE (codiceP = ? AND userCliente = ?) ");
		stm.setString(1, newTitolo);
		stm.setString(2, codicePacchetto);
		stm.setString(3,nomeUtente);
		stm.executeUpdate();
		conn.commit();

		Connection conn1 = DriverManagerConnectionPool.getConnection();
		PreparedStatement stm1 = conn1.prepareStatement("UPDATE recensione SET commento = ? WHERE (codiceP = ? AND userCliente = ?) ");
		stm1.setString(1, newCommento);
		stm1.setString(2, codicePacchetto);
		stm1.setString(3,nomeUtente);
		stm1.executeUpdate();
		conn1.commit();
	}


	/**
	 * Dato un pacchetto, preleva il titolo dalla recensione di un singolo cliente
	 * @param nomeUtente
	 * @param codicePacchetto
	 * @return
	 * @throws SQLException
	 */
	public String getTitoloRecensioneFromUtente(String nomeUtente, String codicePacchetto) throws SQLException{
		Connection conn = DriverManagerConnectionPool.getConnection();
		PreparedStatement stm = conn.prepareStatement("SELECT titolo FROM recensione WHERE userCliente = ? AND codiceP = ?");

		stm.setString(1, nomeUtente);
		stm.setString(2, codicePacchetto);
		ResultSet res = stm.executeQuery();

		int columIndex = res.findColumn("titolo");
		return res.getString(columIndex);
	}

	/**
	 * Dato un pacchetto, preleva il commento dalla recensione di un singolo cliente
	 * @param nomeUtente
	 * @param codicePacchetto
	 * @return
	 * @throws SQLException
	 */
	public String getCommentoRecensioneFromUtente(String nomeUtente, String codicePacchetto) throws SQLException{
		Connection conn = DriverManagerConnectionPool.getConnection();
		PreparedStatement stm = conn.prepareStatement("SELECT commento FROM recensione WHERE userCliente = ? AND codiceP = ?");

		stm.setString(1, nomeUtente);
		stm.setString(2, codicePacchetto);
		ResultSet res = stm.executeQuery();

		int columIndex = res.findColumn("commento");
		return res.getString(columIndex);
	}
}

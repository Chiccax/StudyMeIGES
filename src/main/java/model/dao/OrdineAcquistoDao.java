package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DriverManagerConnectionPool;
import model.bean.OrdineAcquistoBean;
import model.bean.PacchettoBean;

public class OrdineAcquistoDao {
	/**
	 * Recupera gli ordini dei clienti 
	 * @param nomeCliente nome del cliente 
	 * @return ArrayList<OrdineAcquistoBean> arry di acquisti
	 * @throws SQLException
	 * context OrdineAcquistoDao::ArrayList<OrdineAcquistoBean> findByNomeCliente(Object nomeCliente)
	 * @pre nomeCleinte presente nel database
	 **/
	public ArrayList<OrdineAcquistoBean> findByNomeCliente(Object nomeCliente) throws SQLException {
		if(!(nomeCliente instanceof String))
			throw new IllegalArgumentException("La chiave primaria deve essere di tipo String");
		
		String cliente = (String) nomeCliente;
		
		Connection conn = DriverManagerConnectionPool.getConnection();

		String sql = "SELECT * " + "FROM ordine " + "WHERE nomeCliente = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, cliente);
		ResultSet res = stm.executeQuery();
		conn.commit();
		
		PacchettoDao pacchettoDao = new PacchettoDao();
		
		ArrayList<OrdineAcquistoBean> ordineCompleto = new ArrayList<OrdineAcquistoBean>();
		
		/* Estraggo l'ordine */
		while(res.next()) {
			OrdineAcquistoBean ordine= new OrdineAcquistoBean();
			ordine.setNumOrdine(res.getInt(1));
			ordine.setCliente(res.getString(2));
			ordine.setData(res.getDate(3));
			
			ArrayList<PacchettoBean> listaPacchetti = new ArrayList<PacchettoBean>();
			
			String sql1 = "SELECT * FROM acquisto WHERE numOrdine = ?";
			stm = conn.prepareStatement(sql1);
			stm.setInt(1, ordine.getNumOrdine());
			
			ResultSet ris1 = stm.executeQuery();
			
			/* Estraggo i dettagli dell'ordine in base al numero d'ordine */
			while(ris1.next()) {
				String codiceP = ris1.getString(3);
				
				PacchettoBean pacchetto = new PacchettoBean();
				
				pacchetto.setCodicePacchetto(codiceP);
				pacchetto.setTitolo(ris1.getString(4));
				pacchetto.setPrezzo(ris1.getDouble(5));
				
				PacchettoBean pacchettoCompleto = pacchettoDao.getPacchetto(codiceP);
				
				pacchetto.setDescrizione(pacchettoCompleto.getDescrizione());
				pacchetto.setFoto(pacchettoCompleto.getFoto());
				pacchetto.setCatagoria(pacchettoCompleto.getCatagoria());
				pacchetto.setSottocategoria(pacchettoCompleto.getSottocategoria());
				
				listaPacchetti.add(pacchetto);
			}
			
			ordine.setPacchettiAcquistati(listaPacchetti);
			
			ordineCompleto.add(ordine);
		}
		return ordineCompleto;
	}
	/**
	 * Recupera gli ordini di tutti i clienti
	 * @param
	 * @return ArrayList<OrdineAcquistoBean>  array di ordini
	 * @throws SQLException
	 * context OrdineAcquistoDao::ArrayList<OrdineAcquistoBean> findAllClient()
	 **/
public ArrayList<OrdineAcquistoBean> findAllClient() throws SQLException {
	Connection conn = DriverManagerConnectionPool.getConnection();

	String sql = "SELECT * " + "FROM ordine";
	PreparedStatement stm = conn.prepareStatement(sql);
	ResultSet res = stm.executeQuery();
	conn.commit();
	
	PacchettoDao pacchettoDao = new PacchettoDao();
	
	ArrayList<OrdineAcquistoBean> ordineCompleto = new ArrayList<OrdineAcquistoBean>();
	
	/* Estraggo l'ordine */
	while(res.next()) {
		OrdineAcquistoBean ordine= new OrdineAcquistoBean();
		ordine.setNumOrdine(res.getInt(1));
		ordine.setCliente(res.getString(2));
		ordine.setData(res.getDate(3));
		
		ArrayList<PacchettoBean> listaPacchetti = new ArrayList<PacchettoBean>();
		
		String sql1 = "SELECT * FROM acquisto WHERE numOrdine = ?";
		stm = conn.prepareStatement(sql1);
		stm.setInt(1, ordine.getNumOrdine());
		
		ResultSet ris1 = stm.executeQuery();
		
		/* Estraggo i dettagli dell'ordine in base al numero d'ordine */
		while(ris1.next()) {
			String codiceP = ris1.getString(3);
			
			PacchettoBean pacchetto = new PacchettoBean();
			
			pacchetto.setCodicePacchetto(codiceP);
			pacchetto.setTitolo(ris1.getString(4));
			pacchetto.setPrezzo(ris1.getDouble(5));
			
			PacchettoBean pacchettoCompleto = pacchettoDao.getPacchetto(codiceP);
			
			pacchetto.setDescrizione(pacchettoCompleto.getDescrizione());
			pacchetto.setFoto(pacchettoCompleto.getFoto());
			pacchetto.setCatagoria(pacchettoCompleto.getCatagoria());
			pacchetto.setSottocategoria(pacchettoCompleto.getSottocategoria());
			
			listaPacchetti.add(pacchetto);
		}
		
		ordine.setPacchettiAcquistati(listaPacchetti);
		
		ordineCompleto.add(ordine);
	}
		return ordineCompleto;
	}
}
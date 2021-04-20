package model.bean;

import java.util.ArrayList;
/**
 * Classe identidicante una classe Carrello
 * @author Claudia Buono 
 * @author Francesca Perillo
 */
public class CarrelloBean {

	 ArrayList<PacchettoBean> oggetti_carrello;
		/**
		 * Costruttore generico del Carrello 
		 * 
		 */
	    public CarrelloBean() {
	            oggetti_carrello = new ArrayList<PacchettoBean>();
	    }
	    /**
	     * Restituisce la rappresentazione tesutale in forma di stringa
	     * */     
	    @Override
		public String toString() {
			return "Carrello [oggetti_carrello=" + oggetti_carrello + "]";
		}
	    /**
	     * Aggiunge un pacchetto al carrello.
		 * @param pacchetto pacchetto da aggiungere al carrello
	     * */
		public void aggiungi(PacchettoBean pacchetto) {
	            oggetti_carrello.add(pacchetto);
	    }
		/**
		 *Rimuove un oggetto dal carrello.
		 * @param i valore dell'i-esimo oggetto da rimuovere nel carrello
		 * */
	    public void rimuovi(int i) {
	            oggetti_carrello.remove(i);
	    } 
	    /**
	     * Preleva gli oggetti del carrello.
	     * @return ArrayList<PacchettoBean>: oggetti_carrello 
	     */
	    public ArrayList<PacchettoBean> getOggettiCarrello() {
	            return oggetti_carrello;
	    }
	    /**
	     * Rimuove tutti gli oggetti dal carrello.
	     * */
	    public void rimuovitutto(){
	    	oggetti_carrello.clear();
	    }


}

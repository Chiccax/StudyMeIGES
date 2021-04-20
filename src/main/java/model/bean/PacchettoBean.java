package model.bean;
/**
 * Classe identificante una classe Pacchetto
 * @author Claudia Buono
 * @author Francesca Perillo
 */
public class PacchettoBean{
	/**
	 * Costruttore generico del Pacchetto
	 * 
	 */
	public PacchettoBean() {
	}
	/**
	 * Preleva il codice del pacchetto.
	 * @return String: codicePacchetto 
	 */
	public String getCodicePacchetto() {
		return codicePacchetto;
	}
	/**
	 * Preleva la descrzione del pacchetto.
	 * @return String: descrzione 
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * Preleva iltitolo del pacchetto.
	 * @return String: titolo 
	 */
	
	public int getNelCatalogo() {
		return nelCatalogo;
	}
	
	public String getTitolo() {
		return titolo;
	}
	/**
	 * Preleva la categoria del pacchetto.
	 * @return String: categoria 
	 */
	public String getCatagoria() {
		return catagoria;
	}
	/**
	 * Preleva la sottocategoria del pacchetto.
	 * @return String: sottocategoria 
	 */
	public String getSottocategoria() {
		return sottocategoria;
	}
	/**
	 * Preleva il prezzo del pacchetto.
	 * @return Double: prezzo 
	 */
	public double getPrezzo() {
		return prezzo;
	}
	/**
	 * Preleva la foto del pacchetto
	 * @return String: foto
	 */
	public String getFoto() {
		return foto;
	}
	/**
	 * Preleva il valore dell'approvazione del pacchetto.
	 * @return int: approvato 
	 */
	public  int getApprovato() {
		return approvato;
	}
	/**
	 * Modifica il codice del pacchetto con il valore del parametro
	 * @param codicePacchetto nuovo codice del pacchetto
	 */
	public void setCodicePacchetto(String codicePacchetto) {
		this.codicePacchetto = codicePacchetto;
	}
	/**
	 * Modifica la descrizione del pacchetto con il valore del parametro
	 * @param descrizione modifica la descrizione del pacchetto
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * Modifica il titolo del pacchetto con il valore del parametro
	 * @param titolo modifica titolo del pacchetto
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	/**
	 * Modifica la categria del pacchetto con il valore del parametro
	 * @param catagoria nuova categoria del pacchetto
	 */
	public void setCatagoria(String catagoria) {
		this.catagoria = catagoria;
	}
	/**
	 * Modifica la sottocategoria del pacchetto con il valore del parametro
	 * @param sottocategoria nuova sotto categoria del pacchetto
	 */
	public void setSottocategoria(String sottocategoria) {
		this.sottocategoria = sottocategoria;
	}
	/**
	 * Modifica il prezzo del pacchetto con il valore del parametro
	 * @param prezzo nuovo prezzo del pacchetto
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	/**
	 * Modifica la foto del pacchetto con il valore del parametro
	 * @param foto nuova foto del pacchetto
	 */
	public void setFoto(String foto) {
		this.foto=foto;
	}
	/**
	 * Modifica l'approvazione del pacchetto con il valore del parametro
	 * @param approvato approvazione del pacchetto (default:0)
	 */
	public void setApprovato(int approvato) {
		this.approvato=approvato;
		
	}
	
	private String codicePacchetto, descrizione, titolo, catagoria, sottocategoria,foto;
	private double prezzo;
	private int approvato, nelCatalogo;

}
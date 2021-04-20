package model.bean;
/**
 * Classe identificante una classe Acquisto 
 * @author Claudia Buono
 * @author Francesca Perillo
 */
public class AcquistoBean {
	/**
	 * Costruttore generico dell' Acquisto
	 */
	public AcquistoBean() {
	}
	/**
	 * Modifica il valore del numero di acquisto con il valore del parametro
	 * @param numAcquisto nuovo numero di acquisto
	 */
	public void setNumAcquisto(int numAcquisto) {
		this.numAcquisto = numAcquisto;
	}
	/**
	 * Preleva il valore del numero dell'acquisto.
	 * @return int numAcquisto
	 */
	public int getNumAcquisto() {
		return numAcquisto;
	}
	/**
	 * Preleva il valore del numero dell'ordine.
	 * @return int numOrdine 
	 */
	public int getNumOrdine() {
		return numOrdine;
	}
	/**
	 * Preleva il valore del numero dell'importo.
	 * @return double importo 
	 */
	public double getImporto() {
		return importo;
	}
	/**
	 * Preleva il valore del numero del codice del pacchetto.
	 * @return String codiceP (codice del pacchetto)
	 */
	public String getCodiceP() {
		return codiceP;
	}
	/**
	 * Modifica il valore del codice del pacchetto con il valore del parametro
	 * @param codiceP nuovo codice del pacchetto
	 */
	public void setCodiceP(String codiceP) {
		this.codiceP = codiceP;
	}
	/**
	 * Modifica il valore del numero dell'ordine con il valore del parametro
	 * @param numOrdine nuovo numero dell'ordine
	 */
	public void setNumOrdine(int numOrdine) {
		this.numOrdine = numOrdine;
	}
	/**
	 * Modifica il valore dell'importo con il valore del parametro
	 * @param importo nuovo importo
	 */
	public void setImporto(double importo) {
		this.importo = importo;
	}
	/**
	 * Preleva il titolo del pacchetto.
	 * @return String titoloPacchetto
	 */
	public String getTitoloPacchetto() {
		return titoloPacchetto;
	}
	/**
	 * Modifica il titolo del pacchetto con il valore del parametro
	 * @param titoloPacchetto titolo del nuovo pacchetto
	 */
	public void setTitoloPacchetto(String titoloPacchetto) {
		this.titoloPacchetto = titoloPacchetto;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AcquistoBean))
			return false;
		
		AcquistoBean other = (AcquistoBean) obj;
		
		return this.numAcquisto == other.getNumAcquisto();
	}

	private int numOrdine, numAcquisto;
	private String codiceP,titoloPacchetto;
	private double importo;

}
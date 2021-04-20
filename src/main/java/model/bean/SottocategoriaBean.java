package model.bean;
/**
 * Classe identificante una classe Sottocategoria
 * @author Claudia Buono
 * @author Francesca Perillo
 */
public class SottocategoriaBean {
	/**
	 * Costruttore generico della Sottocategoria
	 * 
	 */
	public SottocategoriaBean() {}
	/**
	 * Preleva l'id della sottocategoria.
	 * @return String: idSottoCat
	 */
	public String getIdSottoCat() {
		return idSottoCat;
	}
	/**
	 * Preleva il nome della sottocategoria.
	 * @return String: nomeSott
	 */
	public String getNomeSott() {
		return nomeSott;
	}
	/**
	 * Modifica l'id della sottocategoria con il valore del parametro
	 * @param idSottoCat nuovo id della sottocategoria
	 */
	public void setIdSottoCat(String idSottoCat) {
		this.idSottoCat = idSottoCat;
	}
	/**
	 * Modifica il nome della sottocategoria con il valore del parametro
	 * @param nomeSott nuovo nome della sottocategotia
	 */
	public void setNomeSott(String nomeSott) {
		this.nomeSott = nomeSott;
	}

	private String idSottoCat, nomeSott;


}

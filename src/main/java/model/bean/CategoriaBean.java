package model.bean;
/**
 * Classe identificante una classe Categoria
 * @author Claudia Buono
 * @author Francesca Perillo
 */
public class CategoriaBean {
	/**
	 * Costruttore generico della Categoria
	 */
	public CategoriaBean() {
	}
	/**
	 * Preleva il nome della categoria.
	 * @return String: nomeCategoria 
	 */
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	/**
	 * Prelevo l'insegnante.
	 * @return String: insegnante 
	 */
	public String getInsegnante(){
		return insegnante;
	}
	/**
	 * Preleva il valore della foto della categoria.
	 * @return String: fotoCat 
	 */
	public String getFotoCategoria() {
		return fotoCat;
	}
	/**
	 * Modifica l'insegnante con il valore del parametro
	 * @param insegnante nuovo valore dell'insegnante
	 */
	public void setInsegnante(String insegnante){
		this.insegnante= insegnante;
	}
	/**
	 * Modifica il nome della categoria con il valore del parametro
	 * @param nomeCategoria nuovo valore della categoria
	 */
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	/**
	 * Modifica il valore della foto categoria con il valore del parametro
	 * @param fotoCat nuovo valore della foto della categoria
	 */
	public void setFotoCategoria(String fotoCat) {
		this.fotoCat= fotoCat;
	}

	private String nomeCategoria,fotoCat,insegnante;
}
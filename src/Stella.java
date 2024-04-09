/**
 * Rappresenta una stella nel sistema celeste.
 * Estende la classe CorpoCeleste.
 */
public class Stella extends CorpoCeleste {
	/**
	 * Costruttore di un nuovo oggetto Stella con il nome e la massa specificati.
	 *
	 * @param nome  il nome della stella.
	 * @param Massa la massa della stella.
	 */
	public Stella(String nome, double massa) {
		super(nome, massa, 0, 0);
	}
}

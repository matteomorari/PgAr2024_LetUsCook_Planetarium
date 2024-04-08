/**
 * Represents a star in the celestial system.
 * Extends the CorpoCeleste class.
 */
public class Stella extends CorpoCeleste {
	/**
	 * Constructs a new Stella object with the specified name and mass.
	 * 
	 * @param nome The name of the star.
	 * @param massa The mass of the star.
	 */
	public Stella(String nome, double massa) {
		super(nome, massa, 0, 0);
	}
}

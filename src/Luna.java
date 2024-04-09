/**
 * Represents a celestial body called Luna.
 * Luna is a subclass of the CorpoCeleste class.
 */
public class Luna extends CorpoCeleste {
  /**
   * Constructs a Luna object with the specified name, mass, and position.
   *
   * @param nome  the name of the Luna
   * @param massa the mass of the Luna
   * @param pos_x the x-coordinate of the Luna's position
   * @param pos_y the y-coordinate of the Luna's position
   */
  public Luna(String nome, double massa, double pos_x, double pos_y) {
    super(nome, massa, pos_x, pos_y);
  }
}

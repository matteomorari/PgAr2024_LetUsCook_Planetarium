/**
 * Rappresenta un corpo celeste chiamato Luna.
 * Luna è una sottoclasse della classe Corpoceleste.
 */
public class Luna extends CorpoCeleste {
  /**
   * Costruttore di un oggetto Luna con il nome, la massa e la posizione specificati.
   *
   * @param nome  il nome della Luna
   * @param Massa la massa della Luna
   * @param pos_x la coordinata X della posizione di Luna
   * @param pos_y la coordinata della posizione di Luna
   */
  public Luna(String nome, double massa, double pos_x, double pos_y) {
    super(nome, massa, pos_x, pos_y);
  }
}

import java.util.ArrayList;

/**
 * Represents a planet in the solar system.
 * Extends the CorpoCeleste class.
 */
public class Pianeta extends CorpoCeleste {
  private ArrayList<Luna> listaLune = new ArrayList<Luna>();

  /**
   * Constructs a new Pianeta object with the specified parameters.
   * 
   * @param nome   the name of the planet
   * @param massa  the mass of the planet
   * @param pos_x  the x-coordinate of the planet's position
   * @param pos_y  the y-coordinate of the planet's position
   */
  public Pianeta(String nome, double massa, double pos_x, double pos_y) {
    super(nome, massa, pos_x, pos_y);
  }

  /**
   * Adds a Luna object to the list of moons orbiting the planet.
   * 
   * @param luna  the Luna object to be added
   */
  public void addLuna(Luna luna) {
    listaLune.add(luna);
  }

  /**
   * Removes a Luna object from the list of moons orbiting the planet.
   * 
   * @param luna  the Luna object to be removed
   */
  public void rimuoviLuna(Luna luna) {
    for (int i = 0; i < listaLune.size(); i++) {
      if (luna.getId().equals(listaLune.get(i).getId())) {
        listaLune.remove(i);
        break;
      }
    }
  }

  /**
   * Returns the list of moons orbiting the planet.
   * 
   * @return the list of moons
   */
  public ArrayList<Luna> getListaLune() {
    return listaLune;
  }

  /**
   * Returns a string representation of the names of the moons orbiting the planet.
   * 
   * @return a string representation of the moon names
   */
  public String getStringaListaLune() {
    ArrayList<String> listaNomiLune = new ArrayList<String>();
    for (Luna luna : listaLune) {
      listaNomiLune.add(luna.getNome());
    }

    return String.join(", ", listaNomiLune);
  }

  /**
   * Returns a string representation of the planet.
   * If the planet has moons, the string includes the names of the moons.
   * 
   * @return a string representation of the planet
   */
  @Override
  public String toString() {

    if (listaLune.size() > 0) {
      String stringaReturn = this.getNome() + ": ( ";
      ArrayList<String> listaNomiLune = new ArrayList<String>();
      for (Luna luna : listaLune) {
        listaNomiLune.add(luna.getNome());
      }
      stringaReturn += String.join(", ", listaNomiLune);
      stringaReturn = stringaReturn + " )";
      return stringaReturn;
    }

    return this.getNome();
  }
}

import java.util.ArrayList;

/**
 * Rappresenta un pianeta nel sistema solare.
 * Estende la classe CorpoCeleste.
 */
public class Pianeta extends CorpoCeleste {
  private ArrayList<Luna> listaLune = new ArrayList<Luna>();

  /**
   * Costruttore di un nuovo oggetto Pianeta con i parametri specificati.
   *
   * @param nome  il nome del pianeta
   * @param Massa la massa del pianeta
   * @param pos_x la coordinata X della posizione del pianeta
   * @param pos_y la coordinata Y della posizione del pianeta
   */
  public Pianeta(String nome, double massa, double pos_x, double pos_y) {
    super(nome, massa, pos_x, pos_y);
  }

  /**
   * Aggiunge un oggetto Luna all'elenco delle lune orbitanti nel pianeta.
   *
   * @param Luna l'oggetto Luna da aggiungere
   */
  public void addLuna(Luna luna) {
    listaLune.add(luna);
  }

  /**
   * Rimuove un oggetto Luna dall'elenco delle lune orbitanti al pianeta.
   *
   * @param Luna L'oggetto Luna da rimuovere
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
   * Restituisce l'elenco di lune orbitanti al pianeta.
   *
   * @return l'elenco delle lune
   */
  public ArrayList<Luna> getListaLune() {
    return listaLune;
  }

  /**
   * Restituisce una rappresentazione di stringa dei nomi delle lune orbitanti
   * pianeta.
   *
   * @return Una rappresentazione di stringa dei nomi della luna
   */
  public String getStringaListaLune() {
    ArrayList<String> listaNomiLune = new ArrayList<String>();
    for (Luna luna : listaLune) {
      listaNomiLune.add(luna.getNome());
    }

    return String.join(", ", listaNomiLune);
  }

  /**
   * Restituisce una rappresentazione di stringa del pianeta.
   * Se il pianeta ha lune, la stringa include i nomi delle lune.
   *
   * @return Una rappresentazione di stringa del pianeta
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

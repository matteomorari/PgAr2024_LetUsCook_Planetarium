import java.util.ArrayList;

public class Pianeta extends CorpoCeleste {
  private ArrayList<Luna> listaLune = new ArrayList<Luna>();

  public Pianeta(String nome, int massa, int pos_x, int pos_y) {
    super(nome, massa, pos_x, pos_y);
  }

  // TODO: metterlo nel costruttore della Luna?
  public void addLuna(Luna luna) {
    listaLune.add(luna);
  }

  public void rimuoviLuna(String nomeLuna) {
    for (int i = 0; i < listaLune.size(); i++) {
      if (nomeLuna.equals(listaLune.get(i).getNome())) {
        listaLune.remove(i);
        break;
      }
    }
  }

  public ArrayList<Luna> getListaLune() {
    return listaLune;
  }

  public String getStringaListaLune() {
    ArrayList<String> listaNomiLune = new ArrayList<String>();
    for (Luna luna : listaLune) {
      listaNomiLune.add(luna.getNome());
    }

    return String.join(", ", listaNomiLune);
  }

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

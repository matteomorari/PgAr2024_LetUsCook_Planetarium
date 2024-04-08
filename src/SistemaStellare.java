import java.util.ArrayList;
import java.util.Vector;

public class SistemaStellare {
  private Stella stella;
  private ArrayList<Pianeta> listaPianeti = new ArrayList<Pianeta>();

  public SistemaStellare(Stella stella) {
    this.stella = stella;
  }

  public void addPianeta(Pianeta pianeta) {
    listaPianeti.add(pianeta);
  }

  public String getCentroDiMassa() {
    double massaTotale = 0;
    double posizioneXPesata = 0;
    double posizioneYPesata = 0;
    double centroMassaX;
    double centroMassaY;
    String stringaReturn;

    massaTotale += stella.getMassa();
    posizioneXPesata += stella.getMassa() * stella.getPosizioneX();
    posizioneYPesata += stella.getMassa() * stella.getPosizioneY();

    for (Pianeta pianeta : listaPianeti) {
      massaTotale += pianeta.getMassa();
      posizioneXPesata += pianeta.getMassa() * pianeta.getPosizioneX();
      posizioneYPesata += pianeta.getMassa() * pianeta.getPosizioneY();
      for (Luna luna : pianeta.getListaLune()) {
        massaTotale += luna.getMassa();
        posizioneXPesata += luna.getMassa() * luna.getPosizioneX();
        posizioneYPesata += luna.getMassa() * luna.getPosizioneY();
      }
    }

    centroMassaX = posizioneXPesata / massaTotale;
    centroMassaY = posizioneYPesata / massaTotale;

    // TODO: rendere più carina la stringa e decidere eventualmente di ritornare
    // classe punto???
    stringaReturn = String.format("Il centro di massa è in posizione: (%.2f; %.2f)", centroMassaX, centroMassaY);

    return stringaReturn;
  }

  public void rimuoviPianeta(String nomePianeta) {
    for (int i = 0; i < listaPianeti.size(); i++) {
      if (nomePianeta.equals(listaPianeti.get(i).getNome())) {
        listaPianeti.remove(i);
        break;
      }
    }
  }

  public ArrayList<Pianeta> getListaPianeti() {
    return listaPianeti;
  }

  @Override
  public String toString() {
    ArrayList<String> listaNomiPianeti = new ArrayList<String>();

    for (Pianeta pianeta : listaPianeti) {
      listaNomiPianeti.add(pianeta.toString());
    }

    return "{" +
        " stella='" + stella.getNome() + "'" +
        ", listaPianeti='" + String.join(", ",
            listaNomiPianeti)
        + "'" +
        "}";
  }

  public boolean corpoCelestePresente(String nome) {
    if (nome.equals(this.stella.getNome())) {
      return true;
    }
    for (Pianeta pianeta : listaPianeti) {
      if (nome.equals(pianeta.getNome())) {
        return true;
      }
      for (Luna luna : pianeta.getListaLune()) {
        if (nome.equals(luna.getNome())) {
          return true;
        }
      }
    }

    return false;
  }

  public Pianeta getPianeta(String nome) {
    for (Pianeta pianeta : listaPianeti) {
      if (nome.equals(pianeta.getNome())) {
        return pianeta;
      }
    }

    return listaPianeti.get(0); // TODO: to fix
  }

  public String percorsoLuna(String nomeLuna) {
    for (Pianeta pianeta : listaPianeti) {
      if (pianeta.getStringaListaLune().indexOf(nomeLuna) != -1) {
        return this.stella.getNome() + " > " + pianeta.getNome() + " > " + nomeLuna;
      }
    }
    return "Errore"; // TODO: to fix
  }

  public Pianeta getPianetaDiLuna(CorpoCeleste luna) {
    String nomePianeta = this.percorsoLuna(luna.getNome()).split(" > ")[1];
    return this.getPianeta(nomePianeta);
  }

  // TODO: to test
  public double calcolaDistanza(CorpoCeleste corpo1, CorpoCeleste corpo2) {
    double deltaX = corpo1.getPosizioneX() - corpo2.getPosizioneX();
    double deltaY = corpo1.getPosizioneY() - corpo2.getPosizioneY();
    return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
  }

  // TODO: eventualmente da migliorare con altre metodi di supporto
  public String calcolaRotta(CorpoCeleste partenza, CorpoCeleste arrivo) {
    ArrayList<CorpoCeleste> listaPercorso = new ArrayList<CorpoCeleste>();
    double distanzaTotale = 0;

    if (partenza.equals(arrivo)) {
      // se sono lo stesso corpo celeste
      return "Bro un po' più di attenzione, hai inserito lo stesso corpo celeste";
    } else if (partenza instanceof Pianeta && arrivo instanceof Pianeta) {
      // entrambi di tipo Pianeta
      listaPercorso.add(partenza);
      listaPercorso.add(this.stella);
      listaPercorso.add(arrivo);
    } else if (partenza instanceof Luna && arrivo instanceof Luna) {
      // entrambi di tipo Luna
      Pianeta pianetaPartenza = this.getPianetaDiLuna(partenza);
      Pianeta pianetaArrivo = this.getPianetaDiLuna(arrivo);

      // se appartengono allo stesso pianeta
      if (pianetaPartenza.equals(pianetaArrivo)) {
        listaPercorso.add(partenza);
        listaPercorso.add(pianetaPartenza);
        listaPercorso.add(arrivo);
      } else {
        // se le lune appartengono a pianeti diversi
        listaPercorso.add(partenza);
        listaPercorso.add(pianetaPartenza);
        listaPercorso.add(this.stella);
        listaPercorso.add(pianetaArrivo);
        listaPercorso.add(arrivo);
      }
    } else if (partenza instanceof Luna) {
      if (arrivo instanceof Pianeta) {
        listaPercorso.add(partenza);
        listaPercorso.add(this.getPianetaDiLuna(partenza));
        listaPercorso.add(this.stella);
        listaPercorso.add(arrivo);
      } else {
        // se arrivo è una stella
        listaPercorso.add(partenza);
        listaPercorso.add(this.getPianetaDiLuna(partenza));
        listaPercorso.add(this.stella);
      }
    } else if (partenza instanceof Pianeta) {
      if (arrivo instanceof Luna && partenza.equals(arrivo)) {
        listaPercorso.add(partenza);
        listaPercorso.add(this.getPianetaDiLuna(partenza));
        listaPercorso.add(arrivo);
      } else if (arrivo instanceof Luna) {
        listaPercorso.add(partenza);
        listaPercorso.add(this.stella);
        listaPercorso.add(this.getPianetaDiLuna(arrivo));
        listaPercorso.add(arrivo);
      } else {
        // se arrivo è una stella
        listaPercorso.add(partenza);
        listaPercorso.add(this.stella);
      }
    } else {
      // se partenza è una stella
      if (arrivo instanceof Luna) {
        listaPercorso.add(this.stella);
        listaPercorso.add(this.getPianetaDiLuna(arrivo));
        listaPercorso.add(arrivo);
      } else {
        // se arrivo è di tipo Pianeta
        listaPercorso.add(this.stella);
        listaPercorso.add(arrivo);
      }
    }

    ArrayList<String> listaNomi = new ArrayList<String>();
    for (CorpoCeleste corpoCeleste : listaPercorso) {
      listaNomi.add(corpoCeleste.getNome());
    }

    // calcoliamo distanza totale da percorre
    for (int i = 0; i < listaPercorso.size() - 1; i++) {
      distanzaTotale += this.calcolaDistanza(listaPercorso.get(i), listaPercorso.get(i + 1));
    }

    return String.format(
        "Percorso: %s, con distanza di: %.2f", String.join(" > ", listaNomi), distanzaTotale);
  }

  public String getPossibiliCollisioni() {
    ArrayList<String> collisioni = new ArrayList<String>();

    for (int i = 0; i < listaPianeti.size(); i++) {
      // per ogni i-esimo pianeta controlliamo se ha la stessa distanza dalla stella
      // degli j-esimi pianeti
      for (int j = i + 1; j < listaPianeti.size(); j++) {
        double distanzaI = this.calcolaDistanza(this.stella, this.listaPianeti.get(i));
        double distanzaJ = this.calcolaDistanza(this.stella, this.listaPianeti.get(j));
        if (MyMath.doubleUguali(distanzaI, distanzaJ)) {
          collisioni.add(listaPianeti.get(i).getNome() + "-" + listaPianeti.get(j).getNome());
        }
      }

      ArrayList<Luna> listaLunePianetaI = this.listaPianeti.get(i).getListaLune(); // lista luna di ogni i-esimo pianeta

      // iteriamo ogni luna dell'i-esimo pianeta
      for (int j = 0; j < listaLunePianetaI.size(); j++) {
        // due lune dello stesso pianeta collidono se e solo se hanno lo stesso raggio
        for (int k = j + 1; k < listaLunePianetaI.size(); k++) {
          double distanzaJ = this.calcolaDistanza(listaPianeti.get(i), listaLunePianetaI.get(j));
          double distanzaK = this.calcolaDistanza(listaPianeti.get(i), listaLunePianetaI.get(k));
          if (MyMath.doubleUguali(distanzaJ, distanzaK)) {
            collisioni.add(listaLunePianetaI.get(i).getNome() + "-" + listaLunePianetaI.get(j).getNome());
          }
        }

        // luna collide con stella se e solo se distanza luna-pianeta == distanza
        // pianeta-stella
        double distanzaLunaJ_PianetaI = this.calcolaDistanza(listaLunePianetaI.get(j), listaPianeti.get(i));
        double distanzaPianetaI_Stella = this.calcolaDistanza(listaPianeti.get(i), stella);
        if (MyMath.doubleUguali(distanzaLunaJ_PianetaI, distanzaPianetaI_Stella)) {
          ArrayList<CorpoCeleste> copioCorpiCollidenti = new ArrayList<CorpoCeleste>();
          copioCorpiCollidenti.add(listaLunePianetaI.get(j));
          copioCorpiCollidenti.add(stella);
          collisioni.add(listaLunePianetaI.get(j).getNome() + "-" + stella.getNome());
        }

        // due lune di pianeti diversi collidono se e solo se la distanza dei due
        // pianeti è minore o uguale della somma della somma delle distanza di ciascuna
        // luna dal proprio pianeta.
        for (int k = i + 1; k < listaPianeti.size(); k++) {
          for (Luna lunaK : listaPianeti.get(k).getListaLune()) {
            double distanzaPianeti = this.calcolaDistanza(this.getPianetaDiLuna(listaLunePianetaI.get(j)),
                this.getPianetaDiLuna(lunaK));
            double distanzaLunaK_PianetaK = this.calcolaDistanza(listaPianeti.get(k), lunaK);
            if (distanzaPianeti >= (distanzaLunaJ_PianetaI + distanzaLunaK_PianetaK)) {
              collisioni.add(listaLunePianetaI.get(j).getNome() + "-" + lunaK.getNome());
            }
          }
        }
      }

    }

    if (collisioni.size() == 0) {
      return "Non ci sono collisioni.";
    } else {
      return "Le possibili collisioni sono: " + String.join(", ", collisioni);
    }
  }

}

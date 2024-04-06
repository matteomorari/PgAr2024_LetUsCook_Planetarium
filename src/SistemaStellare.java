import java.util.ArrayList;

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

  public String cercaCorpoCeleste(String nome) {
    if (nome.equals(this.stella.getNome())) {
      return "Il corpo celeste cercato è la stella";
    }
    for (Pianeta pianeta : listaPianeti) {
      if (nome.equals(pianeta.getNome())) {
        return "Il corpo celeste cercato è un pianeta";
      }
      for (Luna luna : pianeta.getListaLune()) {
        if (nome.equals(luna.getNome())) {
          return "Il corpo celeste cercato è un una luna che gira intorno a " + pianeta.getNome();
        }
      }
    }

    return "Non è stato trovato nessun corpo celeste con questo nome";
  }

  public String percorsoLuna(String nomeLuna) {
    for (Pianeta pianeta : listaPianeti) {
      if (pianeta.getStringaListaLune().indexOf(nomeLuna) != -1) {
        return "Il percorso per raggiungere la luna \"" + nomeLuna + "\" è: " + this.stella.getNome() + " > "
            + pianeta.getNome() + " > " + nomeLuna;
      }
    }

    return "Non esiste nessuna luna con questo nome";
  }

  // TODO: convertiore paramettro in luna?
  public String getPianetaDiLuna(CorpoCeleste luna) {
    return this.percorsoLuna(luna.getNome()).split(" > ")[1];
  }

  // TODO: to test
  public double calcolaDistanza(CorpoCeleste corpo1, CorpoCeleste corpo2) {
    return Math.sqrt(Math.pow(corpo1.getPosizioneX() - corpo2.getPosizioneX(), 2) - Math.pow(
        corpo1.getPosizioneY() - corpo2.getPosizioneY(), 2));
  }

  // TODO: creare array to join dopo
  //! TODO: da finire
  // public String calcolaRotta(CorpoCeleste partenza, CorpoCeleste arrivo) {
  //   ArrayList<CorpoCeleste> listaPercorso = new ArrayList<CorpoCeleste>();

  //   if (partenza.equals(arrivo)) {
  //     // se sono lo stesso corpo celeste
  //     return "Bro un po' più di attenzione, hai inserito lo stesso corpo celeste";
  //   } else if (partenza instanceof Pianeta && arrivo instanceof Pianeta) {
  //     // entrambi di tipo Pianeta
  //     listaPercorso.add(partenza);
  //     listaPercorso.add(this.stella);
  //     listaPercorso.add(arrivo);
  //   } else if (partenza instanceof Luna && arrivo instanceof Luna) {
  //     // entrambi di tipo Luna
  //     String pianetaPartenza = this.getPianetaDiLuna(partenza);
  //     String pianetaArrivo = this.getPianetaDiLuna(arrivo);

  //     // se appartengono allo stesso pianeta
  //     if (pianetaPartenza.equals(pianetaArrivo)) {
  //       listaPercorso.add(partenza);
  //       listaPercorso.add(pianetaPartenza);
  //       listaPercorso.add(arrivo);
  //       // return partenza.getNome() + " > " + pianetaPartenza + " > " + arrivo.getNome();
  //     } else {
  //       listaPercorso.add(partenza);
  //       listaPercorso.add(pianetaPartenza);
  //       listaPercorso.add(this.stella);
  //       listaPercorso.add(pianetaArrivo);
  //       listaPercorso.add(arrivo);
  //       // return partenza.getNome() + " > " + pianetaPartenza + " > " + this.stella.getNome() + " > " + pianetaArrivo
  //       //     + " > " + arrivo.getNome();
  //     }
  //   } else if (partenza instanceof Luna) {
  //     if (arrivo instanceof Pianeta) {
  //       listaPercorso.add(partenza);
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       return partenza.getNome() + " > " + this.getPianetaDiLuna(arrivo) + " > " + this.stella.getNome() + " > "
  //           + arrivo.getNome();
  //     } else {
  //       // se arrivo è una stella
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       return partenza.getNome() + " > " + this.getPianetaDiLuna(arrivo) + " > " + this.stella.getNome();
  //     }
  //   } else if (partenza instanceof Pianeta) {
  //     if (arrivo instanceof Luna) {
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       return partenza.getNome() + " > " + this.getPianetaDiLuna(arrivo) + " > " + this.percorsoLuna(arrivo.getNome());
  //     } else {
  //       // se arrivo è una stella
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       return partenza.getNome() + " > " + this.stella.getNome();
  //     }
  //   } else {
  //     // se partenza è una stella

  //     if (arrivo instanceof Luna) {
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       return this.percorsoLuna(arrivo.getNome());
  //     } else {
  //       // se arrivo è di tipo Pianeta
  //       listaPercorso.add();
  //       listaPercorso.add();
  //       return partenza.getNome() + " > " + arrivo.getNome();
  //     }
  //   }

  //   // calcoliamo distanza totale da percorre

  // }

}

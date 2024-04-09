import java.util.ArrayList;

/**
 * Rappresenta un sistema stellare.
 */
public class SistemaStellare {
  private Stella stella;
  private ArrayList<Pianeta> listaPianeti = new ArrayList<Pianeta>();

/**
   * Costruttore di un nuovo sistema stellare con la stella data.
   *
   * @param stella la stella del sistema
   */
  public SistemaStellare(Stella stella) {
    this.stella = stella;
  }

  /**
   * Controlla se un pianeta con il nome dato esiste nel sistema stellare.
   *
   * @param nome il nome del pianeta per controllare
   * @return vero se esiste un pianeta con il nome dato, falso altrimenti
   */
  public boolean pianetaEsistente(String nome) {
    for (Pianeta pianeta : listaPianeti) {
      if (pianeta.getId().equalsIgnoreCase(nome)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Verifica se esiste una luna con il nome dato nel sistema stellare.
   *
   * @param nome il nome della luna da controllare
   * @return vero se esiste una luna con il nome dato, falso altrimenti
   */
  public boolean lunaEsistente(String nome) {
    for (Pianeta pianeta : listaPianeti) {
      for (Luna luna : pianeta.getListaLune()) {
        if (luna.getId().equalsIgnoreCase(nome)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Verifica se nel sistema stellare esiste un corpo celeste con il nome dato.
   *
   * @param nome Il nome del corpo celeste da controllare
   * @return vero se esiste un corpo celeste con il nome dato, falso altrimenti
   */
  public boolean corpoCelesteEsistente(String nome) {

    if (this.stella.getId().equalsIgnoreCase(nome) || this.pianetaEsistente(nome) || this.lunaEsistente(nome)) {
      return true;
    }

    return false;
  }

  /**
   * Restituisce l'elenco dei pianeti nel sistema stellare.
   *
   * @return l'elenco dei pianeti
   */
  public ArrayList<Pianeta> getListaPianeti() {
    return listaPianeti;
  }

  /**
   * Rappresenta un pianeta nel sistema solare.
   */
  public Pianeta getPianetaByName(String nome) {
    Pianeta pianeta = null;
    for (Pianeta pianetaCorrente : listaPianeti) {
      if (nome.equalsIgnoreCase(pianetaCorrente.getId())) {
        pianeta = pianetaCorrente;
      }
    }

    return pianeta;
  }

  /**
   * Aggiunge un pianeta all'elenco dei pianeti nel sistema stellare.
   *
   * @param pianeta il pianeta da aggiungere
   */
  public void addPianeta(Pianeta pianeta) {
    listaPianeti.add(pianeta);
  }

  /**
   * Rimuove un pianeta dall'elenco dei pianeti nel sistema stellare.
   *
   * @param pianeta il nome del pianeta da rimuovere
   */
  public void rimuoviPianeta(Pianeta pianeta) {
    for (int i = 0; i < listaPianeti.size(); i++) {
      if (pianeta.equals(listaPianeti.get(i))) {
        listaPianeti.remove(i);
        break;
      }
    }
  }

  /**
   * Rappresenta una luna nel sistema planetario.
   */
  public Luna getLunaByName(String nome) {
    Luna luna = null;
    for (Pianeta pianeta : listaPianeti) {
      for (Luna lunaCorrente : pianeta.getListaLune()) {
        if (lunaCorrente.getId().equalsIgnoreCase(nome)) {
          luna = lunaCorrente;
        }
      }
    }

    return luna;
  }

  /**
   * Rappresenta un corpo celeste nel sistema solare.
   */
  public CorpoCeleste getCorpoCelesteByName(String nome) {
    if (nome.equalsIgnoreCase(this.stella.getId())) {
      return this.stella;
    } else {
      if (this.pianetaEsistente(nome)) {
        return this.getPianetaByName(nome);
      } else {
        return this.getLunaByName(nome);
      }
    }
  }

  /**
   * Restituisce l'oggetto Stella associato a questo sistema stellare.
   *
   * @return l'oggetto Stella associato a questo sistema stellare
   */
  // TODO: metodo mai utilizzato
  public Stella getStella() {
    return this.stella;
  };

  /**
   * Sostituisce la stella corrente con una nuova stella nel sistema stellare.
   *
   * @param nuovaStella La nuova stella
   */
  // TODO: metodo mai utilizzato
  public void replaceStella(Stella nuovaStella) {
    this.stella = nuovaStella;
  }

  /**
   * Restituisce il centro della massa del sistema stellare.
   *
   * @return Una rappresentazione di stringa del centro della posizione di massa
   *         nel formato:
   *         "Il Centro di Massa è in posizione: (x; y)"
   */
  public String getCentroDiMassa() {
    double massaTotale = 0;
    double posizioneXPesata = 0;
    double posizioneYPesata = 0;
    double centroMassaX;
    double centroMassaY;
    String stringaReturn;

    massaTotale += stella.getMassa();
    posizioneXPesata += stella.getMassa() * stella.getPosizione().get("x");
    posizioneYPesata += stella.getMassa() * stella.getPosizione().get("y");

    for (Pianeta pianeta : listaPianeti) {
      massaTotale += pianeta.getMassa();
      posizioneXPesata += pianeta.getMassa() * pianeta.getPosizione().get("x");
      posizioneYPesata += pianeta.getMassa() * pianeta.getPosizione().get("y");
      for (Luna luna : pianeta.getListaLune()) {
        massaTotale += luna.getMassa();
        posizioneXPesata += luna.getMassa() * luna.getPosizione().get("x");
        posizioneYPesata += luna.getMassa() * luna.getPosizione().get("y");
      }
    }

    centroMassaX = posizioneXPesata / massaTotale;
    centroMassaY = posizioneYPesata / massaTotale;

    stringaReturn = String.format("Il centro di massa è in posizione: (%.2f; %.2f)", centroMassaX, centroMassaY);

    return stringaReturn;
  }

  /**
   * Restituisce una rappresentazione di stringa dell'oggetto sistema stellare.
   * La rappresentazione della stringa include il nome della stella e un elenco
   * dei pianeti con le relative lune.
   *
   * @return Una rappresentazione di stringa dell'oggetto sistema stellare
   */
  @Override
  public String toString() {
    ArrayList<String> listaNomiPianeti = new ArrayList<String>();

    for (Pianeta pianeta : listaPianeti) {
      listaNomiPianeti.add(pianeta.toString());
    }

    return "{" +
        " stella='" + stella.getNome() + "'" +
        ", listaPianeti='" + String.join(", ", listaNomiPianeti)
        + "'" +
        "}";
  }

  /**
   * Restituisce il percorso della luna data all'interno del sistema stellare.
   *
   * @param luna la luna per la quale trovare il percorso.
   * @return il percorso della luna all'interno del sistema stellare, nel formato:
   *         [Nome stellare]> [Nome del pianeta]> [Nome luna].
   *         Se la luna non si trova in nessun pianeta, restituisce "errori".
   */
  public String percorsoLuna(Luna luna) {
    return this.stella.getNome() + " > " + this.getPianetaDiLuna(luna).getNome() + " > " + luna.getNome();
  }

  /**
   * Restituisce il pianeta che contiene la luna data.
   *
   * @param luna la luna per trovare il pianeta.
   * @return il pianeta che contiene la luna data.
   */
  public Pianeta getPianetaDiLuna(Luna luna) {
    Pianeta pianeta = null;
    for (Pianeta pianetaCorrente : listaPianeti) {
      if (pianetaCorrente.getStringaListaLune().indexOf(luna.getNome()) != -1) {
        pianeta = pianetaCorrente;
      }
    }
    return pianeta;
  }

  private Pianeta getPianetaDiLuna(CorpoCeleste _luna) {
    Luna luna = (Luna) _luna;
    return this.getPianetaDiLuna(luna);
  }

  /**
   * Calcola la distanza tra due corpi celesti.
   *
   * @param corpo1 il primo corpo celeste
   * @param corpo2 il secondo corpo celeste
   * @return la distanza tra i due corpi celesti
   */
  public double calcolaDistanza(CorpoCeleste corpo1, CorpoCeleste corpo2) {
    double deltaX = corpo1.getPosizione().get("x") - corpo2.getPosizione().get("x");
    double deltaY = corpo1.getPosizione().get("y") - corpo2.getPosizione().get("y");
    return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
  }

  /**
   * Calcola il percorso tra due corpi celesti e restituisce il percorso come
   * stringa.
   * Viene anche calcolata la distanza totale da percorrere.
   *
   * @param Partenza il corpo celeste iniziale.
   * @param arrivo   il corpo celeste di destinazione.
   * @return Stringa del percorso tra i due corpi celesti, compresa la distanza
   *         totale.
   */
  public String calcolaRotta(CorpoCeleste partenza, CorpoCeleste arrivo) {
    ArrayList<CorpoCeleste> listaPercorso = new ArrayList<CorpoCeleste>();
    listaPercorso.add(partenza);
    double distanzaTotale = 0;

    if (partenza.equals(arrivo)) {
      // se sono lo stesso corpo celeste
      return "Bro un po' più di attenzione, hai inserito lo stesso corpo celeste";
    } else if (partenza instanceof Pianeta && arrivo instanceof Pianeta) {
      // entrambi di tipo Pianeta
      listaPercorso.add(this.stella);
    } else if (partenza instanceof Luna && arrivo instanceof Luna) {
      // entrambi di tipo Luna
      Pianeta pianetaPartenza = this.getPianetaDiLuna(partenza);
      Pianeta pianetaArrivo = this.getPianetaDiLuna(arrivo);

      // se appartengono allo stesso pianeta
      if (pianetaPartenza.equals(pianetaArrivo)) {
        listaPercorso.add(pianetaPartenza);
      } else {
        // se le lune appartengono a pianeti diversi
        listaPercorso.add(pianetaPartenza);
        listaPercorso.add(this.stella);
        listaPercorso.add(pianetaArrivo);
      }
    } else if (partenza instanceof Luna) {
      if (arrivo instanceof Pianeta) {
        if (!arrivo.equals(this.getPianetaDiLuna(partenza))) {
          // se la luna appartiene ad un pianeta che non è quello di arrivo
          listaPercorso.add(this.getPianetaDiLuna(partenza));
          listaPercorso.add(this.stella);
        }
      } else {
        // se arrivo è una stella
        listaPercorso.add(this.getPianetaDiLuna(partenza));
      }
    } else if (partenza instanceof Pianeta) {
      if (arrivo instanceof Luna) {
        if (!partenza.equals(this.getPianetaDiLuna(arrivo))) {
          // se pianeta partenza e pianeta della luna di arrivo sono diversi
          listaPercorso.add(this.stella);
          listaPercorso.add(this.getPianetaDiLuna(arrivo));
        }
      }
    } else {
      // se partenza è una stella
      if (arrivo instanceof Luna) {
        listaPercorso.add(this.getPianetaDiLuna(arrivo));
      }
    }

    listaPercorso.add(arrivo);

    ArrayList<String> listaNomi = new ArrayList<String>();
    for (CorpoCeleste corpoCeleste : listaPercorso) {
      listaNomi.add(corpoCeleste.getNome());
    }

    // calcoliamo distanza totale da percorre
    for (int i = 0; i < listaPercorso.size() - 1; i++) {
      distanzaTotale += this.calcolaDistanza(listaPercorso.get(i), listaPercorso.get(i + 1));
    }

    return String.format("Percorso: %s, con distanza di: %.2f", String.join(" > ", listaNomi), distanzaTotale);
  }

  /**
   * Restituisce una rappresentazione delle possibili collisioni.
   * 
   * @return una stringa che rappresenta le possibili collisioni.
   */
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
            collisioni.add(listaLunePianetaI.get(j).getNome() + "-" + listaLunePianetaI.get(k).getNome());
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

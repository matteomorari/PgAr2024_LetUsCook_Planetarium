import java.util.ArrayList;

/**
 * Represents a star system.
 */
public class SistemaStellare {
  private Stella stella;
  private ArrayList<Pianeta> listaPianeti = new ArrayList<Pianeta>();

  public SistemaStellare(Stella stella) {
    this.stella = stella;
  }

  /**
   * Checks if a planet with the given name exists in the star system.
   *
   * @param nome the name of the planet to check
   * @return true if a planet with the given name exists, false otherwise
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
   * Checks if a moon with the given name exists in the star system.
   * 
   * @param nome the name of the moon to check
   * @return true if a moon with the given name exists, false otherwise
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
   * Checks if a celestial body with the given name exists in the star system.
   *
   * @param nome the name of the celestial body to check
   * @return true if a celestial body with the given name exists, false otherwise
   */
  public boolean corpoCelesteEsistente(String nome) {

    if (this.stella.getId().equalsIgnoreCase(nome) || this.pianetaEsistente(nome) || this.lunaEsistente(nome)) {
      return true;
    }

    return false;
  }

  /**
   * Returns the list of planets in the stellar system.
   *
   * @return the list of planets
   */
  public ArrayList<Pianeta> getListaPianeti() {
    return listaPianeti;
  }

  /**
   * Represents a planet in the solar system.
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
   * Adds a planet to the list of planets in the stellar system.
   *
   * @param pianeta the planet to be added
   */
  public void addPianeta(Pianeta pianeta) {
    listaPianeti.add(pianeta);
  }

  /**
   * Removes a planet from the list of planets in the star system.
   * 
   * @param nomePianeta the name of the planet to be removed
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
   * Represents a moon in the planetary system.
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
   * Represents a celestial body in the solar system.
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
   * Returns the stella object associated with this SistemaStellare.
   *
   * @return the stella object associated with this SistemaStellare
   */
  // TODO: metodo mai utilizzato
  public Stella getStella() {
    return this.stella;
  };

  /**
   * Replaces the current star with a new star in the stellar system.
   * 
   * @param nuovaStella the new star to be set in the stellar system
   */
  // TODO: metodo mai utilizzato
  public void replaceStella(Stella nuovaStella) {
    this.stella = nuovaStella;
  }

  /**
   * Returns the center of mass of the stellar system.
   *
   * @return a string representation of the center of mass position in the format:
   *         "Il centro di massa è in posizione: (x; y)"
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
   * Returns a string representation of the SistemaStellare object.
   * The string representation includes the name of the star and a list of planet
   * names.
   *
   * @return a string representation of the SistemaStellare object
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
   * Returns the path of the given moon within the star system.
   *
   * @param luna The moon for which to find the path.
   * @return The path of the moon within the star system, in the format:
   *         [star name] > [planet name] > [moon name].
   *         If the moon is not found within any planet, returns "Errore".
   */
  public String percorsoLuna(Luna luna) {
    return this.stella.getNome() + " > " + this.getPianetaDiLuna(luna).getNome() + " > " + luna.getNome();
  }

  /**
   * Returns the planet that contains the given moon.
   *
   * @param luna The moon to find the planet for.
   * @return The planet that contains the given moon.
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
   * Calculates the distance between two celestial bodies.
   * 
   * @param corpo1 the first celestial body
   * @param corpo2 the second celestial body
   * @return the distance between the two celestial bodies
   */
  public double calcolaDistanza(CorpoCeleste corpo1, CorpoCeleste corpo2) {
    double deltaX = corpo1.getPosizione().get("x") - corpo2.getPosizione().get("x");
    double deltaY = corpo1.getPosizione().get("y") - corpo2.getPosizione().get("y");
    return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
  }

  /**
   * Calculates the route between two celestial bodies and returns the route as a
   * string.
   * The route is determined based on the type of celestial bodies (Planet, Moon,
   * or Star) and their relationships.
   * The distance between each celestial body in the route is also calculated.
   *
   * @param partenza The starting celestial body.
   * @param arrivo   The destination celestial body.
   * @return A string representation of the route between the two celestial
   *         bodies, including the total distance.
   */
  //TODO: partenza e arrivo è inutile riscriverli tutte le volte
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
        if (arrivo.equals(this.getPianetaDiLuna(partenza))) {
          listaPercorso.add(partenza);
          listaPercorso.add(arrivo);
        } else {
          // se la luna appartiene ad un pianeta che non è quello di arrivo
          listaPercorso.add(partenza);
          listaPercorso.add(this.getPianetaDiLuna(partenza));
          listaPercorso.add(this.stella);
          listaPercorso.add(arrivo);
        }
      } else {
        // se arrivo è una stella
        listaPercorso.add(partenza);
        listaPercorso.add(this.getPianetaDiLuna(partenza));
        listaPercorso.add(this.stella);
      }
    } else if (partenza instanceof Pianeta) {
      if (arrivo instanceof Luna) {
        if (partenza.equals(this.getPianetaDiLuna(arrivo))) {
          listaPercorso.add(partenza);
          listaPercorso.add(arrivo);
        } else {
          // se la luna appartiene ad un pianeta che non è quello di arrivo
          listaPercorso.add(partenza);
          listaPercorso.add(this.stella);
          listaPercorso.add(this.getPianetaDiLuna(arrivo));
          listaPercorso.add(arrivo);
        }
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

    return String.format("Percorso: %s, con distanza di: %.2f", String.join(" > ", listaNomi), distanzaTotale);
  }

  /**
   * Returns a string representation of the possible collisions in the stellar
   * system.
   * 
   * @return A string representing the possible collisions.
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

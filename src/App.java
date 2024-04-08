import it.kibo.fp.lib.*;

//TODO: traduzione commenti
//TODO: trasformare stringhe in costanti
//TODO: controlare che ci siano tutte le richieste della consegna
/**
 * The main class of the Planetarium application.
 * This class provides various methods for interacting with a stellar system.
 */
public class App {

  /**
   * Represents a moon in a star system.
   */
  public static Luna cercaLuna(SistemaStellare sistemaStellare) {
    String nomeLuna = InputData.readNonEmptyString("Inserisci nome della luna: ", false);
    while (!sistemaStellare.lunaEsistente(nomeLuna)) {
      System.out.println("ERRORE! Non è presente nessuna luna con questo nome."); // TODO: da fare rosso
      nomeLuna = InputData.readNonEmptyString("Inserisci nome della luna: ", false);
    }
    Luna luna = sistemaStellare.getLunaByName(nomeLuna);
    return luna;
  }

  /**
   * Represents a planet in a star system.
   */
  public static Pianeta cercaPianeta(SistemaStellare sistemaStellare) {
    String nomePianeta = InputData.readNonEmptyString("Inserisci nome del pianeta: ", false);
    while (!sistemaStellare.pianetaEsistente(nomePianeta)) {
      System.out.println("ERRORE! Non è presente nessun pianeta con questo nome."); // TODO: da fare rosso
      nomePianeta = InputData.readNonEmptyString("Inserisci nome del pianeta: ", false);
    }
    Pianeta pianeta = sistemaStellare.getPianetaByName(nomePianeta);
    return pianeta;
  }

  /**
   * Represents a celestial body in the star system.
   */
  public static CorpoCeleste cercaCorpoCeleste(SistemaStellare sistemaStellare) {
    String nome = InputData.readNonEmptyString("Inserisci nome di un corpo celeste: ", false);
    while (!sistemaStellare.corpoCelesteEsistente(nome)) {
      System.out.println("ERRORE! Non è presente corpo celeste con questo nome."); // TODO: da fare rosso
      nome = InputData.readNonEmptyString("Inserisci nome di un corpo celeste: ", false);
    }
    CorpoCeleste corpoCeleste = sistemaStellare.getCorpoCelesteByName(nome);
    return corpoCeleste;
  }

  /**
   * Adds a new planet to the given stellar system.
   *
   * @param sistemaStellare the stellar system to which the planet will be added
   */
  public static void aggiungiPianeta(SistemaStellare sistemaStellare) {
    String nome = InputData.readNonEmptyString("Inserisci nome del pianeta: ", false);
    while (sistemaStellare.corpoCelesteEsistente(nome)) {
      System.out.println("ERRORE! È già presente un corpo celeste con lo stesso nome."); // TODO: da fare rosso
      nome = InputData.readNonEmptyString("Inserisci nome del pianeta: ", false);
    }
    double massa = InputData.readDoubleWithMinimum("Inserisci massa del pianeta: ", 0);
    double posizioneX = InputData.readDouble("Inserisci ascissa posizione pianeta: ");
    double posizioneY = InputData.readDouble("Inserisci ordinata posizione pianeta: ");
    Pianeta pianeta = new Pianeta(nome, massa, posizioneX, posizioneY);
    sistemaStellare.addPianeta(pianeta);
  };

  /**
   * Adds a new moon to the given stellar system.
   * 
   * @param sistemaStellare the stellar system to add the moon to
   */
  public static void aggiungiLuna(SistemaStellare sistemaStellare) {
    String nomeLuna = InputData.readNonEmptyString("Inserisci nome della luna: ", false);
    while (sistemaStellare.corpoCelesteEsistente(nomeLuna)) {
      System.out.println("ERRORE! È già presente un corpo celeste con lo stesso nome."); // TODO: da fare rosso
      nomeLuna = InputData.readNonEmptyString("Inserisci nome della luna: ", false);
    }
    double massa = InputData.readDoubleWithMinimum("Inserisci massa della luna: ", 0);
    double posizioneX = InputData.readDouble("Inserisci ascissa posizione luna: ");
    double posizioneY = InputData.readDouble("Inserisci ordinata posizione luna: ");
    Luna luna = new Luna(nomeLuna, massa, posizioneX, posizioneY);

    Pianeta pianeta = cercaPianeta(sistemaStellare);
    pianeta.addLuna(luna);
  };

  /**
   * Removes a planet from the given stellar system.
   *
   * @param sistemaStellare the stellar system from which to remove the planet
   */
  public static void rimuoviPianeta(SistemaStellare sistemaStellare) {
    Pianeta pianeta = cercaPianeta(sistemaStellare);
    sistemaStellare.rimuoviPianeta(pianeta.getId()); // TODO: metodo con parametro di tipo Pianeta
  }

  /**
   * Represents a planet in a star system.
   */
  public static Pianeta cercaPianetaDiLuna(SistemaStellare sistemaStellare) {
    Luna luna = cercaLuna(sistemaStellare);
    Pianeta pianeta = sistemaStellare.getPianetaDiLuna(luna);
    return pianeta;
  }

  /**
   * Removes a moon from the given stellar system.
   * 
   * @param sistemaStellare the stellar system from which to remove the moon
   */
  public static void rimuoviLuna(SistemaStellare sistemaStellare) {
    Luna luna = cercaLuna(sistemaStellare);
    Pianeta pianeta = cercaPianetaDiLuna(sistemaStellare);
    pianeta.rimuoviLuna(luna);
  }

  /**
   * Checks if a celestial body exists in the given stellar system.
   * 
   * @param sistemaStellare The stellar system to search in.
   * @return A message indicating whether the celestial body exists or not.
   */
  public static String corpoCelesteEsistente(SistemaStellare sistemaStellare) {
    String nome = InputData.readNonEmptyString("Inserisci nome corpo celesti che ricerchi: ", false);
    boolean presente = sistemaStellare.corpoCelesteEsistente(nome);
    if (presente) {
      return "> Corpo celeste esiste";
    } else {
      return "> Corpo celeste non esiste";
    }
  }

  /**
   * Returns a string representation of the moons of a given planet in a star system.
   *
   * @param sistemaStellare the star system containing the planet
   * @return a string representation of the moons of the planet
   */
  public static String visualizzaLuneDiPianeta(SistemaStellare sistemaStellare) {
    Pianeta pianeta = cercaPianeta(sistemaStellare);
    return pianeta.getStringaListaLune();
  }

  /**
   * Returns the path to the moon in the given star system.
   *
   * @param sistemaStellare the star system to search for the moon in
   * @return the path to the moon
   */
  public static String visualizzaPercorsoLuna(SistemaStellare sistemaStellare) {
    Luna luna = cercaLuna(sistemaStellare);
    return sistemaStellare.percorsoLuna(luna);
  }

  /**
   * Returns the calculated route between two celestial bodies in the given star system.
   *
   * @param sistemaStellare the star system containing the celestial bodies
   * @return the calculated route between the starting and ending celestial bodies
   */
  public static String visualizzaRotta(SistemaStellare sistemaStellare) {
    CorpoCeleste partenza = cercaCorpoCeleste(sistemaStellare);
    CorpoCeleste arrivo = cercaCorpoCeleste(sistemaStellare);
    return sistemaStellare.calcolaRotta(partenza, arrivo);
  }

  /**
   * Calculates and returns the distance between two celestial bodies in the given star system.
   *
   * @param sistemaStellare the star system containing the celestial bodies
   * @return the distance between the two celestial bodies
   */
  public static double visualizzaDistanza(SistemaStellare sistemaStellare) {
    CorpoCeleste corpo1 = cercaCorpoCeleste(sistemaStellare);
    CorpoCeleste corpo2 = cercaCorpoCeleste(sistemaStellare);
    return sistemaStellare.calcolaDistanza(corpo1, corpo2);
  }

  /**
   * Returns a string representation of the possible collisions in the given stellar system.
   *
   * @param sistemaStellare the stellar system to analyze
   * @return a string representation of the possible collisions
   */
  public static String visualizzaCollisioni(SistemaStellare sistemaStellare) {
    return sistemaStellare.getPossibiliCollisioni();
  }

  /**
   * The main method is the entry point of the program.
   */
  public static void main(String[] args) {
    System.out.println(PrettyStrings.frame(
        "BENVENUTO NEL PROGRAMMA PER CENSIRE UN SISTEMA SOLARE VOLUTO DAL CONSIGLIO INTERGALATTICO",
        100, true, true));

    // creiamo un sistema solare con la relativa stella
    System.out.println("Per iniziare è necessario inserire la stella del sistema solare: ");
    String nomeStella = InputData.readNonEmptyString("Inserisci il nome della stella: ", false);
    double massaStella = InputData.readDoubleWithMinimum("Inserisci massa della stella: ", 0);
    Stella stella = new Stella(nomeStella, massaStella);
    SistemaStellare sistemaStellare = new SistemaStellare(stella);

    // TODO: trasformalo in un enum
    // TODO: possibili cose da aggiungere (ma anche no): modificare massa, posizione, nome di un corpo celeste
    String[] opzioniMenu = {
        "Aggiungi pianeta",
        "Aggiungi luna",
        "Rimuovi pianeta (e relative luna)",
        "Rimuovi luna",
        "Calcola centro di massa",
        "Esiste corpo celeste con un certo nome",
        "Pianeta di una luna",
        "Lune che girano intorno ad un pianeta",
        "Elenco corpi celesti",
        "Percorso luna",
        "Rotta tra due corpi celesti",
        "Distanza tra due corpi celesti",
        "Calcola possibili collisioni",
    };
    Menu menu = new Menu("SCEGLI UNA OPZIONE", opzioniMenu, true, true, false);
    int opzioneScelta;
    do {
      opzioneScelta = menu.choose();
      switch (opzioneScelta) {
        // TODO: cambiare colore ai System.out.println
        //TODO: da testare
        case 1:
          // "Aggiungi pianeta"
          aggiungiPianeta(sistemaStellare);
          System.out.println("> Pianeta aggiunto correttamente.");
          break;
        case 2:
          // "Aggiungi luna"
          aggiungiLuna(sistemaStellare);
          System.out.println("> Luna aggiunta correttamente.");
          break;
        case 3:
          // "Rimuovi pianeta (e relative luna)"
          rimuoviPianeta(sistemaStellare);
          System.out.println("> Pianeta eliminato definitivamente. o7");
          break;
        case 4:
          // "Rimuovi luna"
          rimuoviLuna(sistemaStellare);
          System.out.println("> Luna eliminato definitivamente. o7");
          break;
        case 5:
          // "Calcola centro di massa"
          System.out.println("> " + sistemaStellare.getCentroDiMassa());
          break;
        case 6:
          // "Esiste corpo celeste con un certo nome"
          System.out.println("> " + corpoCelesteEsistente(sistemaStellare));
          break;
        case 7:
          // "Pianeta di una luna"
          Pianeta pianeta = cercaPianetaDiLuna(sistemaStellare);
          System.out.println("> Il pianeta della luna cercata è: " + pianeta.getNome());
          break;
        case 8:
          // "Lune che girano intorno ad un pianeta"
          System.out.println("> " + visualizzaLuneDiPianeta(sistemaStellare));
          break;
        case 9:
          // "Elenco corpi celesti"
          System.out.println("> " + sistemaStellare.toString());
          break;
        case 10:
          // "Percorso luna"
          System.out.println("> " + visualizzaPercorsoLuna(sistemaStellare));
          break;
        case 11:
          // "Rotta tra due corpi celesti"
          System.out.println("> " + visualizzaRotta(sistemaStellare));
          break;
        case 12:
          // "Distanza tra due corpi celesti"
          System.out.println(String.format("> La distanza vale %.2f", visualizzaDistanza(sistemaStellare)));
          break;
        case 13:
          // "Calcola possibili collisioni"
          System.out.println("> " + visualizzaCollisioni(sistemaStellare));
          break;
        default:
          // opzioneScelta == 0
          break;
      }
      System.out.println("\n\n");
    } while (opzioneScelta != 0);
  }
}

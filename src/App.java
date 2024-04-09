import it.kibo.fp.lib.*;

//TODO: controlare che ci siano tutte le richieste della consegna
/**
 * La classe principale dell'applicazione del planetario.
 * Questa classe fornisce vari metodi per interagire con un sistema stellare.
 */
public class App {

  private static final String SCEGLI_OPZIONE = "SCEGLI UNA OPZIONE";
  private static final String VALORE_DISTANZA = "> La distanza vale %.2f";
  private static final String PIANETA_LUNA_CERCATA = "> Il pianeta della luna cercata è: ";
  private static final String LUNA_ELIMINATA = "> Luna eliminata definitivamente. o7";
  private static final String PIANETA_ELIMINATO = "> Pianeta eliminato definitivamente. o7";
  private static final String LUNA_AGGIUNTA_CORRETTAMENTE = "> Luna aggiunta correttamente.";
  private static final String PIANETA_AGGIUNTO_CORRETTAMENTE = "> Pianeta aggiunto correttamente.";
  private static final String INSERISCI_MASSA_STELLA = "Inserisci massa della stella: ";
  private static final String INSERISCI_NOME_STELLA = "Inserisci il nome della stella: ";
  private static final String CREAZIONE_STELLA = "Per iniziare è necessario inserire la stella del sistema solare: ";
  private static final String BENVENUTO = "BENVENUTO NEL PROGRAMMA PER CENSIRE UN SISTEMA SOLARE VOLUTO DAL CONSIGLIO INTERGALATTICO";
  private static final String CORPO_CELESTE_NON_ESISTE = "> Corpo celeste non esiste";
  private static final String CORPO_CELESTE_ESISTE = "> Corpo celeste esiste";
  private static final String INSERISCI_NOME_CORPO_CELESTI_RICERCATO = "Inserisci nome corpo celesti che ricerchi: ";
  private static final String INSERISCI_ORDINATA_POSIZIONE_LUNA = "Inserisci ordinata posizione luna: ";
  private static final String INSERISCI_ASCISSA_POSIZIONE_LUNA = "Inserisci ascissa posizione luna: ";
  private static final String INSERISCI_MASSA_LUNA = "Inserisci massa della luna: ";
  private static final String INSERISCI_ORDINATA_POSIZIONE_PIANETA = "Inserisci ordinata posizione pianeta: ";
  private static final String INSERISCI_ASCISSA_POSIZIONE_PIANETA = "Inserisci ascissa posizione pianeta: ";
  private static final String INSERISCI_MASSA_PIANETA = "Inserisci massa del pianeta: ";
  private static final String ERRORE_CORPO_CELESTE_GIA_ESISTENTE = AnsiColors.RED + "ERRORE!" + AnsiColors.RESET
      + " È già presente un corpo celeste con lo stesso nome.";
  private static final String ERRORE_CORPO_CELESTE_NON_TROVATO = AnsiColors.RED + "ERRO RE!" + AnsiColors.RESET
      + " Non è presente corpo celeste con questo nome.";
  private static final String INSERISCI_NOME_CORPO_CELESTE = "Inserisci nome di un corpo celeste: ";
  private static final String ERRORE_PIANETA_NON_TROVATO = AnsiColors.RED + "ERRORE!" + AnsiColors.RESET
      + " Non è presente nessun pianeta con questo nome.";
  private static final String INSERISCI_NOME_PIANETA = "Inserisci nome del pianeta: ";
  private static final String ERRORE_LUNA_NON_TROVATA = AnsiColors.RED + "ERRORE!" + AnsiColors.RESET
      + " Non è presente nessuna luna con questo nome.";
  private static final String INSERISCI_NOME_LUNA = "Inserisci nome della luna: ";

  /**
   * Cerca una luna da linea di comando.
   */
  public static Luna cercaLuna(SistemaStellare sistemaStellare) {
    String nomeLuna = InputData.readNonEmptyString(INSERISCI_NOME_LUNA, false);
    while (!sistemaStellare.lunaEsistente(nomeLuna)) {
      System.out.println(ERRORE_LUNA_NON_TROVATA);
      nomeLuna = InputData.readNonEmptyString(INSERISCI_NOME_LUNA, false);
    }
    Luna luna = sistemaStellare.getLunaByName(nomeLuna);
    return luna;
  }

  /**
   * Cerca un pianeta da linea di comando.
   */
  public static Pianeta cercaPianeta(SistemaStellare sistemaStellare) {
    String nomePianeta = InputData.readNonEmptyString(INSERISCI_NOME_PIANETA, false);
    while (!sistemaStellare.pianetaEsistente(nomePianeta)) {
      System.out.println(ERRORE_PIANETA_NON_TROVATO);
      nomePianeta = InputData.readNonEmptyString(INSERISCI_NOME_PIANETA, false);
    }
    Pianeta pianeta = sistemaStellare.getPianetaByName(nomePianeta);
    return pianeta;
  }

  /**
   * Cerca un corpo celeste qualsiasi da linea di comando.
   */
  public static CorpoCeleste cercaCorpoCeleste(SistemaStellare sistemaStellare) {
    String nome = InputData.readNonEmptyString(INSERISCI_NOME_CORPO_CELESTE, false);
    while (!sistemaStellare.corpoCelesteEsistente(nome)) {
      System.out.println(ERRORE_CORPO_CELESTE_NON_TROVATO);
      nome = InputData.readNonEmptyString(INSERISCI_NOME_CORPO_CELESTE, false);
    }
    CorpoCeleste corpoCeleste = sistemaStellare.getCorpoCelesteByName(nome);
    return corpoCeleste;
  }

  /**
   * Aggiunge un nuovo pianeta al sistema stellare dato.
   */
  public static void aggiungiPianeta(SistemaStellare sistemaStellare) {
    String nome = InputData.readNonEmptyString(INSERISCI_NOME_PIANETA, false);
    while (sistemaStellare.corpoCelesteEsistente(nome)) {
      System.out.println(ERRORE_CORPO_CELESTE_GIA_ESISTENTE);
      nome = InputData.readNonEmptyString(INSERISCI_NOME_PIANETA, false);
    }
    double massa = InputData.readDoubleWithMinimum(INSERISCI_MASSA_PIANETA, 0);
    double posizioneX = InputData.readDouble(INSERISCI_ASCISSA_POSIZIONE_PIANETA);
    double posizioneY = InputData.readDouble(INSERISCI_ORDINATA_POSIZIONE_PIANETA);
    Pianeta pianeta = new Pianeta(nome, massa, posizioneX, posizioneY);
    sistemaStellare.addPianeta(pianeta);
  };

  /**
   * Aggiunge una luna nuova al sistema stellare dato.
   */
  public static void aggiungiLuna(SistemaStellare sistemaStellare) {
    String nomeLuna = InputData.readNonEmptyString(INSERISCI_NOME_LUNA, false);
    while (sistemaStellare.corpoCelesteEsistente(nomeLuna)) {
      System.out.println(
          ERRORE_CORPO_CELESTE_GIA_ESISTENTE);
      nomeLuna = InputData.readNonEmptyString(INSERISCI_NOME_LUNA, false);
    }
    double massa = InputData.readDoubleWithMinimum(INSERISCI_MASSA_LUNA, 0);
    double posizioneX = InputData.readDouble(INSERISCI_ASCISSA_POSIZIONE_LUNA);
    double posizioneY = InputData.readDouble(INSERISCI_ORDINATA_POSIZIONE_LUNA);
    Luna luna = new Luna(nomeLuna, massa, posizioneX, posizioneY);

    Pianeta pianeta = cercaPianeta(sistemaStellare);
    pianeta.addLuna(luna);
  };

  /**
   * Rimuove un pianeta dal sistema stellare dato.
   */
  public static void rimuoviPianeta(SistemaStellare sistemaStellare) {
    Pianeta pianeta = cercaPianeta(sistemaStellare);
    sistemaStellare.rimuoviPianeta(pianeta);
  }

  /**
   * Visualizza il pianeta di una linea.
   */
  public static Pianeta cercaPianetaDiLuna(SistemaStellare sistemaStellare) {
    Luna luna = cercaLuna(sistemaStellare);
    Pianeta pianeta = sistemaStellare.getPianetaDiLuna(luna);
    return pianeta;
  }

  /**
   * Rimuove una luna dal sistema stellare dato.
   */
  public static void rimuoviLuna(SistemaStellare sistemaStellare) {
    Luna luna = cercaLuna(sistemaStellare);
    Pianeta pianeta = cercaPianetaDiLuna(sistemaStellare);
    pianeta.rimuoviLuna(luna);
  }

  /**
   * Verifica se esiste un corpo celeste nel sistema stellare dato.
   */
  public static String corpoCelesteEsistente(SistemaStellare sistemaStellare) {
    String nome = InputData.readNonEmptyString(INSERISCI_NOME_CORPO_CELESTI_RICERCATO, false);
    boolean presente = sistemaStellare.corpoCelesteEsistente(nome);
    if (presente) {
      return CORPO_CELESTE_ESISTE;
    } else {
      return CORPO_CELESTE_NON_ESISTE;
    }
  }

  /**
   * Visualizza tutte le luna di un pianeta.
   */
  public static String visualizzaLuneDiPianeta(SistemaStellare sistemaStellare) {
    Pianeta pianeta = cercaPianeta(sistemaStellare);
    return pianeta.getStringaListaLune();
  }

  /**
   * Restituisce il percorso verso la luna nel sistema stellare dato.
   */
  public static String visualizzaPercorsoLuna(SistemaStellare sistemaStellare) {
    Luna luna = cercaLuna(sistemaStellare);
    return sistemaStellare.percorsoLuna(luna);
  }

  /**
   * Restituisce il percorso calcolato tra due corpi celesti e la distanza totale
   * percorsa
   */
  public static String visualizzaRotta(SistemaStellare sistemaStellare) {
    CorpoCeleste partenza = cercaCorpoCeleste(sistemaStellare);
    CorpoCeleste arrivo = cercaCorpoCeleste(sistemaStellare);
    return sistemaStellare.calcolaRotta(partenza, arrivo);
  }

  /**
   * Calcola e restituisce la distanza tra due corpi celesti
   */
  public static double visualizzaDistanza(SistemaStellare sistemaStellare) {
    CorpoCeleste corpo1 = cercaCorpoCeleste(sistemaStellare);
    CorpoCeleste corpo2 = cercaCorpoCeleste(sistemaStellare);
    return sistemaStellare.calcolaDistanza(corpo1, corpo2);
  }

  /**
   * Restituisce una rappresentazione di stringa delle possibili collisioni
   */
  public static String visualizzaCollisioni(SistemaStellare sistemaStellare) {
    return sistemaStellare.getPossibiliCollisioni();
  }

  /**
   * Il metodo principale è il punto di ingresso del programma.
   */
  public static void main(String[] args) {
    System.out.println(PrettyStrings.frame(
        BENVENUTO, 100, true, true));

    // creiamo un sistema solare con la relativa stella
    System.out.println(CREAZIONE_STELLA);
    String nomeStella = InputData.readNonEmptyString(INSERISCI_NOME_STELLA, false);
    double massaStella = InputData.readDoubleWithMinimum(INSERISCI_MASSA_STELLA, 0);
    Stella stella = new Stella(nomeStella, massaStella);
    SistemaStellare sistemaStellare = new SistemaStellare(stella);

    // TODO: possibili cose da aggiungere (ma anche no): modificare massa,
    // posizione, nome di un corpo celeste
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
    Menu menu = new Menu(SCEGLI_OPZIONE, opzioniMenu, true, true, false);
    int opzioneScelta;
    do {
      opzioneScelta = menu.choose();
      switch (opzioneScelta) {
        // TODO: cambiare colore ai System.out.println
        // TODO: da testare bene
        case 1:
          // "Aggiungi pianeta"
          aggiungiPianeta(sistemaStellare);
          System.out.println(PIANETA_AGGIUNTO_CORRETTAMENTE);
          break;
        case 2:
          // "Aggiungi luna"
          aggiungiLuna(sistemaStellare);
          System.out.println(LUNA_AGGIUNTA_CORRETTAMENTE);
          break;
        case 3:
          // "Rimuovi pianeta (e relative luna)"
          rimuoviPianeta(sistemaStellare);
          System.out.println(PIANETA_ELIMINATO);
          break;
        case 4:
          // "Rimuovi luna"
          rimuoviLuna(sistemaStellare);
          System.out.println(LUNA_ELIMINATA);
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
          System.out.println(PIANETA_LUNA_CERCATA + pianeta.getNome());
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
          System.out.println(String.format(VALORE_DISTANZA, visualizzaDistanza(sistemaStellare)));
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

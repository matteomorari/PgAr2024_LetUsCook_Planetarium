import java.util.HashMap;

/**
 * La classe Corpoceleste rappresenta un corpo celeste.
 * Contiene informazioni sul nome, sulla massa e sulla posizione del corpo
 * celeste.
 */
public class CorpoCeleste {

  // variabile id usate come codice univoco (nome in lowerCase)
  private String id;
  private String nome;
  private double massa;
  private HashMap<String, Double> posizione = new HashMap<String, Double>();

  /**
   * Costruttore di un oggetto Corpoceleste con il nome, la massa e la posizione
   * specificati.
   *
   * @param nome       Il nome del corpo celeste.
   * @param Massa      la massa del corpo celeste.
   * @param posizioneX la coordinata X della posizione del corpo celeste.
   * @param posizioneY la coordinata Y della posizione del corpo celeste.
   */
  public CorpoCeleste(String nome, double massa, double posizioneX, double posizioneY) {
    this.nome = nome;
    this.massa = massa;
    this.id = nome.toLowerCase();
    this.posizione.put("x", posizioneX);
    this.posizione.put("y", posizioneY);
  }

  /**
   * Restituisce il nome del corpo celeste.
   *
   * @return il nome del corpo celeste.
   */
  public String getNome() {
    return nome;
  }

  /**
   * Imposta il nome del corpo celeste.
   *
   * @param nome Il nome del corpo celeste.
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Restituisce la massa del corpo celeste.
   *
   * @return la massa del corpo celeste.
   */
  public double getMassa() {
    return massa;
  }

  /**
   * Imposta la massa del corpo celeste.
   *
   * @param Massa la massa del corpo celeste.
   */
  public void setMassa(double massa) {
    this.massa = massa;
  }

  /**
   * Imposta la posizione del corpo celeste.
   *
   * @param x la coordinata X della posizione del corpo celeste.
   * @param y la coordinata Y della posizione del corpo celeste.
   */
  public void setPosizione(double x, double y) {
    this.posizione.put("x", x);
    this.posizione.put("y", y);
  }

  /**
   * Restituisce la posizione del corpo celeste.
   *
   * @return la posizione del corpo celeste.
   */
  public HashMap<String, Double> getPosizione() {
    return posizione;
  }

  /**
   * Restituisce l'identificatore unico del corpo celeste.
   *
   * @return l'identificatore univoco del corpo celeste.
   */
  public String getId() {
    return this.id;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

}

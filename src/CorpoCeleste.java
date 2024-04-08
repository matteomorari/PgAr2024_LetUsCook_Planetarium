import java.util.HashMap;

/**
 * The CorpoCeleste class represents a celestial body.
 * It contains information about the name, mass, and position of the celestial body.
 */
public class CorpoCeleste {

  // variabile id usate come codice univoco (nome in lowerCase)
  private String id;
  private String nome;
  private double massa;
  private HashMap<String, Double> posizione = new HashMap<String, Double>();

  /**
   * Constructs a CorpoCeleste object with the specified name, mass, and position.
   * 
   * @param nome The name of the celestial body.
   * @param massa The mass of the celestial body.
   * @param posizioneX The x-coordinate of the celestial body's position.
   * @param posizioneY The y-coordinate of the celestial body's position.
   */
  public CorpoCeleste(String nome, double massa, double posizioneX, double posizioneY) {
    this.nome = nome;
    this.massa = massa;
    this.id = nome.toLowerCase();
    this.posizione.put("x", posizioneX);
    this.posizione.put("y", posizioneY);
  }

  /**
   * Returns the name of the celestial body.
   * 
   * @return The name of the celestial body.
   */
  public String getNome() {
    return nome;
  }

  /**
   * Sets the name of the celestial body.
   * 
   * @param nome The name of the celestial body.
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Returns the mass of the celestial body.
   * 
   * @return The mass of the celestial body.
   */
  public double getMassa() {
    return massa;
  }

  /**
   * Sets the mass of the celestial body.
   * 
   * @param massa The mass of the celestial body.
   */
  public void setMassa(double massa) {
    this.massa = massa;
  }

  /**
   * Sets the position of the celestial body.
   * 
   * @param x The x-coordinate of the celestial body's position.
   * @param y The y-coordinate of the celestial body's position.
   */
  public void setPosizione(double x, double y) {
    this.posizione.put("x", x);
    this.posizione.put("y", y);
  }

  /**
   * Returns the position of the celestial body.
   * 
   * @return The position of the celestial body.
   */
  public HashMap<String, Double> getPosizione() {
    return posizione;
  }

  /**
   * Returns the unique identifier of the celestial body.
   * 
   * @return The unique identifier of the celestial body.
   */
  public String getId() {
    return this.id;
  }

  @Override
  public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    return super.equals(obj);
  }

}

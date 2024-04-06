import java.util.HashMap;

public class CorpoCeleste {

  // TODO: codice univoco
  private String nome;
  private double massa;
  private HashMap<String, Double> posizione = new HashMap<String, Double>();

  public CorpoCeleste(String nome, double massa, double posizioneX, double posizioneY){
    this.nome = nome;
    this.massa = massa;
    this.posizione.put("x", posizioneX);
    this.posizione.put("y", posizioneY);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public double getMassa() {
    return massa;
  }

  public void setMassa(double massa) {
    this.massa = massa;
  }

  public double getPosizioneX() {
    return posizione.get("x");
  }

  public double getPosizioneY() {
    return posizione.get("y");
  }

  // TODO
  // public void setPosizione() {
  //   this.posizione = posizione;
  // }

  @Override
  public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    return super.equals(obj);
  }

  
}

import java.util.ArrayList;


public class SistemaStellare {
  private Stella stella;
  private ArrayList<Pianeta> listaPianeti = new ArrayList<Pianeta>();

  public SistemaStellare (Stella stella) {
    this.stella = stella;
  }

  public void addPianeta(Pianeta pianeta) {
    listaPianeti.add(pianeta);
  }

  public String getCentroDiMassa () {
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
      for(Luna luna : pianeta.getListaLune()){
        massaTotale += luna.getMassa();
        posizioneXPesata += luna.getMassa() * luna.getPosizioneX();
        posizioneYPesata += luna.getMassa() * luna.getPosizioneY();
      }
    }

    centroMassaX = posizioneXPesata/massaTotale;
    centroMassaY = posizioneYPesata/massaTotale;

    // TODO: rendere più carina la stringa e decidere eventualmente di ritornare classe punto???
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

  public ArrayList<Pianeta> getListaPianeti(){
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
            listaNomiPianeti) + "'" +
      "}";
  }

}

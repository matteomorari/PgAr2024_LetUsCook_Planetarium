import it.kibo.fp.lib.*;

// TODO: toLowerCase un po' ovunque
// TODO: aggiungere commenti ovunque
public class App {
    public static void main(String[] args) {
        Stella sole = new Stella("Sole", 100);
        SistemaStellare sistemaStellare = new SistemaStellare(sole);
        Pianeta venere = new Pianeta("Venere", 20, 2, 6);
        sistemaStellare.addPianeta(venere);
        Pianeta giove = new Pianeta("Giove", 30, 8, 7);
        sistemaStellare.addPianeta(giove);
        Pianeta plutone = new Pianeta("Plutone", 5, 10, 15);
        sistemaStellare.addPianeta(plutone);


        Luna luna1 = new Luna("Luna1", 5, 6, 5);
        venere.addLuna(luna1);
        Luna luna2 = new Luna("Luna2", 3, 2, 5);
        giove.addLuna(luna2);


        System.out.println(AnsiColors.PURPLE + sistemaStellare.getCentroDiMassa() + AnsiColors.RESET);

        
        venere.rimuoviLuna("Luna1");
        System.out.println(sistemaStellare.toString());

        System.out.println(sistemaStellare.cercaCorpoCeleste("Sole"));

        System.out.println(sistemaStellare.percorsoLuna("Luna2"));


    } 
}

import it.kibo.fp.lib.*;

// TODO: toLowerCase un po' ovunque
// TODO: aggiungere commenti ovunque
public class App {
    public static void main(String[] args) {
        Stella sole = new Stella("Sole", 30);
        SistemaStellare sistemaStellare = new SistemaStellare(sole);
        Pianeta pianeta1 = new Pianeta("Venere", 5, 0, -3);
        sistemaStellare.addPianeta(pianeta1);
        Pianeta pianeta2 = new Pianeta("Giove", 7, 3, 3);
        sistemaStellare.addPianeta(pianeta2);


        Luna luna1 = new Luna("Luna1", 1, -1, -4);
        pianeta1.addLuna(luna1);
        Luna luna2 = new Luna("Luna2", 2, 2, 3);
        pianeta2.addLuna(luna2);
        Luna luna3 = new Luna("Luna3", 1, 4, 4);
        pianeta2.addLuna(luna3);


        // System.out.println(AnsiColors.PURPLE + sistemaStellare.getCentroDiMassa() + AnsiColors.RESET);

        
        // venere.rimuoviLuna("Luna1");
        // System.out.println(sistemaStellare.toString());

        // System.out.println(sistemaStellare.cercaCorpoCeleste("Sole"));

        // System.out.println(sistemaStellare.percorsoLuna("Luna2"));

        // System.out.println(sistemaStellare.calcolaRotta(luna1, giove));

        System.out.println(sistemaStellare.getPossibiliCollisioni());


    } 
}

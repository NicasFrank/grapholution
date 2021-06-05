package eanton;

import java.util.Scanner;

//Habe Kommentare eher grob gehalten
public class App {

    Scanner eingabe = new Scanner(System.in);

    public static void main(String[] args) {
        App app = new App();
        app.startGame(10, 10, 10);
    }

    private void startGame(int x, int y, int minen) {

        Spielfeld spielfeld = new Spielfeld(x,y, minen);

        int spielzuege = (x*y) - minen;
        int tryX;
        int tryY;

        //Ende entweder ueber maximal moegliche Zuege ohne Minen zu treffen oder globale "ende" Variable
        while(spielzuege >= 0 && !spielfeld.logik.ende) {
            do {
                spielfeld.spielstand();
                System.out.println("Eingabe x-Koordinate (links nach rechts) zwischen 1 und " + x + ":");
                tryX = eingabe.nextInt()-1;
                System.out.println("Eingabe y-Koordinate (oben nach unten) zwischen 1 und " + y + ":");
                tryY = eingabe.nextInt()-1;
            } while(tryX >= x || tryX < 0 || tryY >= y || tryY < 0); //wäre mit ner if else Anfrage nicer gewesen
            spielfeld.logik.check(tryX, tryY);
            spielzuege--;
        }
        if(!spielfeld.logik.ende) {
            System.out.print("Nice, du hast es geschafft");
        }
    }

}

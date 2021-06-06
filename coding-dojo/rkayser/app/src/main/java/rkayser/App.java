package rkayser;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groesse,mienenAnzahl;
        System.out.print("Bitte geben Sie an wie groß das Spielfeld sein soll:");
        groesse=scanner.nextInt();
        System.out.print("Bitte geben Sie an mit wie vielen Minen Sie spielen wollen:");
        mienenAnzahl=scanner.nextInt();
        Game game = new Game(groesse,mienenAnzahl);
        game.play();
        scanner.close();
    }
}

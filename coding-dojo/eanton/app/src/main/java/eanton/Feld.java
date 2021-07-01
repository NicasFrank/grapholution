package eanton;

public class Feld {

    public String status; //# = verdeckt, Zahl = aufgedeckt
    public boolean art; //True = Mine

    public Feld() {
        status = "#";
        art = false;
    }
}

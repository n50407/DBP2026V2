package PVorbereitung;

public class GeschlechtPunkteTuple {

    private String Geschlecht;
    private double Punkte;

    public String getGeschlecht() {
        return Geschlecht;
    }

    public GeschlechtPunkteTuple(String geschlecht, double punkte) {
        Geschlecht = geschlecht;
        Punkte = punkte;
    }

    @Override
    public String toString() {
        return "GeschlechtPunkteTuple{" +
                "Geschlecht='" + Geschlecht + '\'' +
                ", Punkte=" + Punkte +
                '}';
    }

    public GeschlechtPunkteTuple() {

    }

    public double getPunkte() {
        return Punkte;
    }
}

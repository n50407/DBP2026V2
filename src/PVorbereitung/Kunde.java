package PVorbereitung;

public class Kunde {
    private int KDNR;
    private String Vorname;
    private String Nachname;

    public int getKDNR() {
        return KDNR;
    }

    public void setKDNR(int KDNR) {
        this.KDNR = KDNR;
    }

    public Kunde() {
    }

    public Kunde(int KDNR, String vorname, String nachname, String geschlecht, int bonuspunkte) {
        this.KDNR = KDNR;
        Vorname = vorname;
        Nachname = nachname;
        Geschlecht = geschlecht;
        Bonuspunkte = bonuspunkte;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public String getGeschlecht() {
        return Geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        Geschlecht = geschlecht;
    }

    public int getBonuspunkte() {
        return Bonuspunkte;
    }

    public void setBonuspunkte(int bonuspunkte) {
        Bonuspunkte = bonuspunkte;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "KDNR=" + KDNR +
                ", Vorname='" + Vorname + '\'' +
                ", Nachname='" + Nachname + '\'' +
                ", Geschlecht='" + Geschlecht + '\'' +
                ", Bonuspunkte=" + Bonuspunkte +
                '}';
    }

    private String Geschlecht;
    private int Bonuspunkte;
}

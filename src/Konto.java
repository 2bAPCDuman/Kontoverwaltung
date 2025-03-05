public abstract class Konto {
    private static int naechsteKontonummer = 1000; // Startwert für Kontonummern
    protected String kontoinhaber;
    protected int kontonummer;
    protected double kontostand;
    protected double gebuehren;
    protected double startBetrag;

    public Konto(String kontoinhaber, double startBetrag) {
        this.kontoinhaber = kontoinhaber;
        this.kontostand = startBetrag;
        this.kontonummer = naechsteKontonummer++; // Automatische Vergabe der Kontonummer
        this.gebuehren = 0;
    }


    public Konto(String kontonummer, String inhaber, double startBetrag) {

        try {
            this.kontonummer = Integer.parseInt(kontonummer);
        } catch (NumberFormatException e) {
            this.kontonummer = naechsteKontonummer++;
        }
        this.kontoinhaber = inhaber;
        this.gebuehren = gebuehren;
        this.kontostand = kontostand;
        this.startBetrag=startBetrag;
    }

    public Konto(String kontonummer, String inhaber, double v, double v1) {

    }


    public void einzahlen(double betrag) {
        if (betrag > 0) {
            kontostand += betrag;
            System.out.println(betrag + "€ eingezahlt. Neuer Kontostand: " + kontostand + "€");
        }
    }


    public abstract void abheben(double betrag);

    public void kontoauszug() {
        System.out.println(getClass().getSimpleName() + " (" + kontonummer + ") von " + kontoinhaber + ": " + kontostand + "€");
    }

    public double getKontostand() {
        return kontostand;
    }

    public int getKontonummer() {
        return kontonummer;
    }

    public void ueberweisen(Konto zielKonto, double betrag) {
        if (betrag > 0 && kontostand >= betrag) {
            this.abheben(betrag);
            zielKonto.einzahlen(betrag);
            System.out.println("Überweisung von " + betrag + "€ von Konto " + kontonummer + " auf Konto " + zielKonto.getKontonummer() + " erfolgreich.");
        } else {
            System.out.println("Überweisung fehlgeschlagen. Nicht genügend Guthaben.");
        }
    }

    public String getKontoinhaber() {
        return kontoinhaber;
    }

    public void setKontonummer(int kontonummer) {
        this.kontonummer = kontonummer;
    }

    public void setInhaber(String inhaber) {
        this.kontoinhaber = inhaber;
    }
    public double getGebuehren() {
        return gebuehren;
    }

    public void setGebuehren(double gebuehren) {
        this.gebuehren = gebuehren;
    }

    public void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }

    public String getInhaber() {

        return kontoinhaber;
    }

}

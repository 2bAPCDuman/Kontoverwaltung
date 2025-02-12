public abstract class Konto {
    private static int naechsteKontonummer = 1000; // Startwert für Kontonummern
    protected String kontoinhaber;
    protected int kontonummer;
    protected double kontostand;

    public Konto(String kontoinhaber, double startBetrag) {
        this.kontoinhaber = kontoinhaber;
        this.kontostand = startBetrag;
        this.kontonummer = naechsteKontonummer++; // Automatische Vergabe der Kontonummer
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
}

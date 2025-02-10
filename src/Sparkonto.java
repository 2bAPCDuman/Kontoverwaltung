public class Sparkonto {
    private String kontoinhaber;
    private double kontostand;

    public Sparkonto(String kontoinhaber, double startBetrag) {
        this.kontoinhaber = kontoinhaber;
        this.kontostand = startBetrag;
    }

    public void einzahlen(double betrag) {
        if (betrag > 0) {
            kontostand += betrag;
            System.out.println(betrag + "€ eingezahlt. Neuer Kontostand: " + kontostand + "€");
        }
    }

    public void abheben(double betrag) {
        if (betrag > 0 && kontostand >= betrag) {
            kontostand -= betrag;
            System.out.println(betrag + "€ abgehoben. Neuer Kontostand: " + kontostand + "€");
        } else {
            System.out.println("Abhebung nicht möglich! Keine Überziehung erlaubt.");
        }
    }

    public void kontoauszug() {
        System.out.println("Sparkonto von " + kontoinhaber + ": " + kontostand + "€");
    }
}

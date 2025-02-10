public class Girokonto {
    private String kontoinhaber;
    private double kontostand;
    private double ueberziehungsrahmen;

    public Girokonto(String kontoinhaber, double startBetrag, double ueberziehungsrahmen) {
        this.kontoinhaber = kontoinhaber;
        this.kontostand = startBetrag;
        this.ueberziehungsrahmen = ueberziehungsrahmen;
    }

    public void einzahlen(double betrag) {
        if (betrag > 0) {
            kontostand += betrag;
            System.out.println(betrag + "€ eingezahlt. Neuer Kontostand: " + kontostand + "€");
        }
    }

    public void abheben(double betrag) {
        if (betrag > 0 && (kontostand + ueberziehungsrahmen) >= betrag) {
            kontostand -= betrag;
            System.out.println(betrag + "€ abgehoben. Neuer Kontostand: " + kontostand + "€");
        } else {
            System.out.println("Abhebung nicht möglich! Überziehungsrahmen überschritten.");
        }
    }

    public void kontoauszug() {
        System.out.println("Girokonto von " + kontoinhaber + ": " + kontostand + "€");
    }
}

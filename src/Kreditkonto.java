public class Kreditkonto {
    private String kontoinhaber;
    private double kontostand;
    private double kreditlimit;

    public Kreditkonto(String kontoinhaber, double startBetrag, double kreditlimit) {
        this.kontoinhaber = kontoinhaber;
        this.kontostand = startBetrag;
        this.kreditlimit = kreditlimit;
    }

    public void einzahlen(double betrag) {
        if (betrag > 0) {
            kontostand += betrag;
            System.out.println(betrag + "€ eingezahlt. Neuer Kontostand: " + kontostand + "€");
        }
    }

    public void abheben(double betrag) {
        if (betrag > 0 && (kontostand - betrag) >= -kreditlimit) {
            kontostand -= betrag;
            System.out.println(betrag + "€ abgehoben. Neuer Kontostand: " + kontostand + "€");
        } else {
            System.out.println("Abhebung nicht möglich! Kreditlimit überschritten.");
        }
    }

    public void kontoauszug() {
        System.out.println("Kreditkonto von " + kontoinhaber + ": " + kontostand + "€");
    }
}

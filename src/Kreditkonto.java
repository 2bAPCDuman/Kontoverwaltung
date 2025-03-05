public class Kreditkonto extends Konto {
    private double kreditlimit;

    public Kreditkonto(String kontoinhaber, String inhaber, double startBetrag, double kreditlimit) {
        super(kontoinhaber, startBetrag);
        this.kreditlimit = kreditlimit;
    }


    @Override
    public void abheben(double betrag) {
        if (betrag > 0 && (kontostand - betrag) >= -kreditlimit) {
            kontostand -= betrag;
            System.out.println(betrag + "€ abgehoben. Neuer Kontostand: " + kontostand + "€");
        } else {
            System.out.println("Abhebung nicht möglich! Kreditlimit überschritten.");
        }
    }
}

public class Girokonto extends Konto {
    private double ueberziehungsrahmen;

    public Girokonto(String kontoinhaber, String inhaber, double startBetrag, double ueberziehungsrahmen) {
        super(kontoinhaber, startBetrag);
        this.ueberziehungsrahmen = ueberziehungsrahmen;
    }



    @Override
    public void abheben(double betrag) {
        if (betrag > 0 && (kontostand + ueberziehungsrahmen) >= betrag) {
            kontostand -= betrag;
            System.out.println(betrag + "€ abgehoben. Neuer Kontostand: " + kontostand + "€");
        } else {
            System.out.println("Abhebung nicht möglich! Überziehungsrahmen überschritten.");
        }
    }
}

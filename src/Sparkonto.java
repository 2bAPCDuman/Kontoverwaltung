public class Sparkonto extends Konto {
    public Sparkonto(String kontoinhaber, double startBetrag) {
        super(kontoinhaber, startBetrag);
    }

    @Override
    public void abheben(double betrag) {
        if (betrag > 0 && kontostand >= betrag) {
            kontostand -= betrag;
            System.out.println(betrag + "€ abgehoben. Neuer Kontostand: " + kontostand + "€");
        } else {
            System.out.println("Abhebung nicht möglich! Keine Überziehung erlaubt.");
        }
    }
}

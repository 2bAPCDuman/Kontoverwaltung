import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Konto> konten = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static String inhaber;

    public static void main(String[] args) {
        int auswahl;
        do {
            System.out.println("\n=== Kontoverwaltung ===");
            System.out.println("1. Konto anlegen");
            System.out.println("2. Geld einzahlen");
            System.out.println("3. Geld abheben");
            System.out.println("4. Kontoauszug anzeigen");
            System.out.println("5. Überweisung");
            System.out.println("6. Alle Konten anzeigen");
            System.out.println("0. Programm beenden");
            System.out.print("Wähle eine Option: ");
            auswahl = scanner.nextInt();

            switch (auswahl) {
                case 1 -> kontoAnlegen();
                case 2 -> geldEinzahlen();
                case 3 -> geldAbheben();
                case 4 -> kontoAuszug();
                case 5 -> ueberweisung();
                case 6 -> alleKontenAnzeigen();
                case 0 -> System.out.println("Programm beendet.");
                default -> System.out.println("Ungültige Eingabe.");
            }
        } while (auswahl != 0);
        scanner.close();
    }

    private static void kontoAnlegen() {
        System.out.println("Kontotyp wählen: 1. Girokonto  2. Sparkonto  3. Kreditkonto");
        int typ = scanner.nextInt();
        System.out.print("Name des Kontoinhabers: ");
        String name = scanner.next();
        System.out.print("Startbetrag: ");
        double startBetrag = scanner.nextDouble();

        switch (typ) {
            case 1 -> {
                System.out.print("Überziehungsrahmen: ");
                double ueberziehung = scanner.nextDouble();
                konten.add(new Girokonto(name, inhaber, startBetrag, ueberziehung));
            }

            case 3 -> {
                System.out.print("Kreditlimit: ");
                double kreditlimit = scanner.nextDouble();
                konten.add(new Kreditkonto(name, inhaber, startBetrag, kreditlimit));
            }
            default -> System.out.println("Ungültige Auswahl.");
        }
    }

    private static void geldEinzahlen() {
        Konto konto = kontoAuswaehlen();
        if (konto != null) {
            System.out.print("Betrag: ");
            konto.einzahlen(scanner.nextDouble());
        }
    }

    private static void geldAbheben() {
        Konto konto = kontoAuswaehlen();
        if (konto != null) {
            System.out.print("Betrag: ");
            konto.abheben(scanner.nextDouble());
        }
    }

    private static void kontoAuszug() {
        Konto konto = kontoAuswaehlen();
        if (konto != null) konto.kontoauszug();
    }

    private static void ueberweisung() {
        System.out.println("Von welchem Konto?");
        Konto vonKonto = kontoAuswaehlen();
        System.out.println("Auf welches Konto?");
        Konto aufKonto = kontoAuswaehlen();
        if (vonKonto != null && aufKonto != null) {
            System.out.print("Betrag: ");
            vonKonto.ueberweisen(aufKonto, scanner.nextDouble());
        }
    }

    private static void alleKontenAnzeigen() {
        for (Konto k : konten) {
            k.kontoauszug();
        }
    }

    private static Konto kontoAuswaehlen() {
        if (konten.isEmpty()) {
            System.out.println("Keine Konten vorhanden.");
            return null;
        }
        for (int i = 0; i < konten.size(); i++) {
            System.out.println((i + 1) + ". " + konten.get(i).kontoinhaber);
        }
        System.out.print("Wähle ein Konto: ");
        int index = scanner.nextInt() - 1;
        return (index >= 0 && index < konten.size()) ? konten.get(index) : null;
    }
}

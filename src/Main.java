import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Beispiel-Konten erstellen
        Girokonto giro = new Girokonto("Max Mustermann", 1000, 500);
        Sparkonto spar = new Sparkonto("Lisa Müller", 2000);
        Kreditkonto kredit = new Kreditkonto("Tom Schneider", 500, 1000);

        int auswahl;
        do {
            System.out.println("\n=== Kontoverwaltung ===");
            System.out.println("1. Geld einzahlen");
            System.out.println("2. Geld abheben");
            System.out.println("3. Kontoauszug anzeigen");
            System.out.println("4. Programm beenden");
            System.out.print("Wähle eine Option: ");
            auswahl = scanner.nextInt();

            if (auswahl >= 1 && auswahl <= 3) {
                System.out.println("Welches Konto?");
                System.out.println("1. Girokonto");
                System.out.println("2. Sparkonto");
                System.out.println("3. Kreditkonto");
                System.out.print("Wähle eine Option: ");
                int kontoWahl = scanner.nextInt();

                switch (auswahl) {
                    case 1: // Einzahlen
                        System.out.print("Betrag eingeben: ");
                        double einzahlenBetrag = scanner.nextDouble();
                        if (kontoWahl == 1) giro.einzahlen(einzahlenBetrag);
                        else if (kontoWahl == 2) spar.einzahlen(einzahlenBetrag);
                        else if (kontoWahl == 3) kredit.einzahlen(einzahlenBetrag);
                        break;

                    case 2: // Abheben
                        System.out.print("Betrag eingeben: ");
                        double abhebenBetrag = scanner.nextDouble();
                        if (kontoWahl == 1) giro.abheben(abhebenBetrag);
                        else if (kontoWahl == 2) spar.abheben(abhebenBetrag);
                        else if (kontoWahl == 3) kredit.abheben(abhebenBetrag);
                        break;

                    case 3: // Kontoauszug anzeigen
                        if (kontoWahl == 1) giro.kontoauszug();
                        else if (kontoWahl == 2) spar.kontoauszug();
                        else if (kontoWahl == 3) kredit.kontoauszug();
                        break;
                }
            }
        } while (auswahl != 4);

        System.out.println("Programm beendet.");
        scanner.close();
    }
}

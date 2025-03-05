import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainGui {
    private JButton kontoAnlegenButton;
    private JButton geldEinzahlenButton;
    private JButton geldAbhebenButton;
    private JButton kontoauszugAnzeigenButton;
    private JButton überweisungButton;
    private JComboBox<String> comboBox1;
    private JTextArea textArea1;
    private JButton alleKontenAnzeigenButton;
    private JPanel mainPanel;

    // Liste von Konten
    private ArrayList<Konto> konten;

    public MainGui() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        initializeComponents();
        konten = new ArrayList<>();
    }

    private void initializeComponents() {
        // Initialize and layout components here
        kontoAnlegenButton = new JButton("Konto anlegen");
        geldEinzahlenButton = new JButton("Geld einzahlen");
        geldAbhebenButton = new JButton("Geld abheben");
        kontoauszugAnzeigenButton = new JButton("Kontoauszug anzeigen");
        überweisungButton = new JButton("Überweisung");
        alleKontenAnzeigenButton = new JButton("Alle Konten anzeigen");

        comboBox1 = new JComboBox<>();
        textArea1 = new JTextArea();
        textArea1.setEditable(false);

        mainPanel.add(comboBox1, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(textArea1), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        buttonPanel.add(kontoAnlegenButton);
        buttonPanel.add(geldEinzahlenButton);
        buttonPanel.add(geldAbhebenButton);
        buttonPanel.add(kontoauszugAnzeigenButton);
        buttonPanel.add(überweisungButton);
        buttonPanel.add(alleKontenAnzeigenButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addActionListeners();
    }

    private void addActionListeners() {
        kontoAnlegenButton.addActionListener(e -> createAccount());
        geldEinzahlenButton.addActionListener(e -> depositMoney());
        geldAbhebenButton.addActionListener(e -> withdrawMoney());
        kontoauszugAnzeigenButton.addActionListener(e -> showAccountStatement());
        überweisungButton.addActionListener(e -> transferMoney());
        alleKontenAnzeigenButton.addActionListener(e -> displayAllAccounts());
    }

    private void createAccount() {
        try {
            String name = JOptionPane.showInputDialog("Name des Kontoinhabers:");
            String kontotyp = JOptionPane.showInputDialog("Kontotyp (Girokonto/Sparkonto/Kreditkonto):");
            double startBetrag = Double.parseDouble(JOptionPane.showInputDialog("Startbetrag:"));

            if ("Girokonto".equalsIgnoreCase(kontotyp)) {
                double ueberziehungsrahmen = Double.parseDouble(JOptionPane.showInputDialog("Überziehungsrahmen:"));
                konten.add(new Girokonto(name, name, startBetrag, ueberziehungsrahmen));
            } else if ("Sparkonto".equalsIgnoreCase(kontotyp)) {
                konten.add(new Sparkonto(name, name, startBetrag));
            } else if ("Kreditkonto".equalsIgnoreCase(kontotyp)) {
                double kreditlimit = Double.parseDouble(JOptionPane.showInputDialog("Kreditlimit:"));
                konten.add(new Kreditkonto(name, name, startBetrag, kreditlimit));
            } else {
                JOptionPane.showMessageDialog(null, "Ungültiger Kontotyp.");
            }
            updateComboBox();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fehler bei der Eingabe: " + ex.getMessage());
        }
    }

    private void depositMoney() {
        try {
            Konto konto = getSelectedKonto();
            if (konto != null) {
                double betrag = Double.parseDouble(JOptionPane.showInputDialog("Betrag:"));
                konto.einzahlen(betrag);
                SwingUtilities.invokeLater(() -> textArea1.append("Einzahlung erfolgreich: " + betrag + "€\n"));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fehler bei der Einzahlung: " + ex.getMessage());
        }
    }

    private void withdrawMoney() {
        try {
            Konto konto = getSelectedKonto();
            if (konto != null) {
                double betrag = Double.parseDouble(JOptionPane.showInputDialog("Betrag:"));
                konto.abheben(betrag);
                SwingUtilities.invokeLater(() -> textArea1.append("Abhebung erfolgreich: " + betrag + "€\n"));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fehler bei der Abhebung: " + ex.getMessage());
        }
    }

    private void showAccountStatement() {
        try {
            Konto konto = getSelectedKonto();
            if (konto != null) {
                SwingUtilities.invokeLater(() -> textArea1.append("Kontostand: " + konto.getKontostand() + "€\n"));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fehler beim Kontoauszug: " + ex.getMessage());
        }
    }

    private void transferMoney() {
        try {
            Konto vonKonto = getSelectedKonto();
            if (vonKonto != null) {
                String zielKontonummer = JOptionPane.showInputDialog("Zielkontonummer:");
                Konto zielKonto = getKontoByKontonummer(Integer.parseInt(zielKontonummer));
                if (zielKonto != null) {
                    double betrag = Double.parseDouble(JOptionPane.showInputDialog("Betrag:"));
                    vonKonto.ueberweisen(zielKonto, betrag);
                    SwingUtilities.invokeLater(() -> textArea1.append("Überweisung erfolgreich: " + betrag + "€ von Konto " +
                            vonKonto.getKontonummer() + " zu Konto " + zielKonto.getKontonummer() + "\n"));
                } else {
                    JOptionPane.showMessageDialog(null, "Zielkonto nicht gefunden!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fehler bei der Überweisung: " + ex.getMessage());
        }
    }

    private void displayAllAccounts() {
        SwingUtilities.invokeLater(() -> {
            textArea1.setText("");
            for (Konto k : konten) {
                textArea1.append("Konto " + k.getKontonummer() + " (" + k.getInhaber() + "): " +
                        k.getKontostand() + "€\n");
            }
        });
    }

    private void updateComboBox() {
        SwingUtilities.invokeLater(() -> {
            comboBox1.removeAllItems();
            for (Konto k : konten) {
                comboBox1.addItem(k.getKontonummer() + " - " + k.getInhaber());
            }
        });
    }

    private Konto getSelectedKonto() {
        String selectedItem = (String) comboBox1.getSelectedItem();
        if (selectedItem != null) {
            String[] parts = selectedItem.split(" - ");
            int kontonummer = Integer.parseInt(parts[0]);
            return getKontoByKontonummer(kontonummer);
        }
        return null;
    }

    private Konto getKontoByKontonummer(int kontonummer) {
        for (Konto k : konten) {
            if (k.getKontonummer() == kontonummer) {
                return k;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Kontoverwaltung");
            frame.setContentPane(new MainGui().mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

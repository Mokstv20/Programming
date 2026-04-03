import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class menu {

        public static void main(String[] args) {
            SwingUtilities.invokeLater(MainMenu::new);
        }

        static class MainMenu extends JFrame {

            MainMenu() {
                setTitle("Main Menu");
                setSize(400, 300);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setLayout(new BorderLayout());

                // ── Menu Bar ──────────────────────────────────────
                JMenuBar menuBar = new JMenuBar();

                JMenu exercisesMenu = new JMenu("Exercises");

                JMenuItem lab1Item = new JMenuItem("Lab Exercise 1");
                JMenuItem lab2Item = new JMenuItem("Lab Exercise 2");
                JMenuItem lab3Item = new JMenuItem("Lab Exercise 3");
                JMenuItem lab4Item = new JMenuItem("Lab Exercise 4");

                lab1Item.addActionListener(e -> { setVisible(false); new Lab1(this); });
                lab2Item.addActionListener(e -> { setVisible(false); new Lab2(this); });
                lab3Item.addActionListener(e -> { setVisible(false); new Lab3Menu(this); });
                lab4Item.addActionListener(e -> { setVisible(false); new Lab4(this); });

                exercisesMenu.add(lab1Item);
                exercisesMenu.add(lab2Item);
                exercisesMenu.add(lab3Item);
                exercisesMenu.add(lab4Item);
                menuBar.add(exercisesMenu);
                setJMenuBar(menuBar);

                // ── Center content ────────────────────────────────
                JPanel center = new JPanel(new GridBagLayout());
                JLabel welcome = new JLabel("Welcome! Select an exercise from the Exercises menu.");
                welcome.setHorizontalAlignment(SwingConstants.CENTER);
                center.add(welcome);
                add(center, BorderLayout.CENTER);

                setVisible(true);
            }
        }


        static class Lab1 extends JFrame {

            Lab1(JFrame parent) {
                setTitle("Lab Exercise 1 – Greeting");
                setSize(420, 220);
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                setLocationRelativeTo(null);
                setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(8, 12, 8, 12);
                gbc.fill   = GridBagConstraints.HORIZONTAL;

                // Instruction label
                JLabel instrLabel = new JLabel("Please enter your name:");
                gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
                add(instrLabel, gbc);

                // Text field
                JTextField nameField = new JTextField(20);
                gbc.gridy = 1;
                add(nameField, gbc);

                // Result label
                JLabel resultLabel = new JLabel(" ");
                resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 14f));
                gbc.gridy = 2;
                add(resultLabel, gbc);

                // Buttons
                JButton greetBtn = new JButton("Greet Me");
                JButton clearBtn = new JButton("Clear");
                JButton exitBtn  = new JButton("Exit");

                JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
                btnPanel.add(greetBtn);
                btnPanel.add(clearBtn);
                btnPanel.add(exitBtn);

                gbc.gridy = 3;
                add(btnPanel, gbc);

                // ── Actions ──────────────────────────────────────
                greetBtn.addActionListener(e -> {
                    String name = nameField.getText().trim();
                    if (name.isEmpty()) {
                        resultLabel.setText("Please enter your name first.");
                    } else {
                        resultLabel.setText("Hello, " + name + "! Welcome!");
                    }
                });

                clearBtn.addActionListener(e -> {
                    nameField.setText("");
                    resultLabel.setText(" ");
                });

                exitBtn.addActionListener(e -> {
                    dispose();
                    parent.setVisible(true);
                });

                setVisible(true);
            }
        }


        static class Lab2 extends JFrame {

            Lab2(JFrame parent) {
                setTitle("Lab Exercise 2 – Font Styles");
                setSize(460, 250);
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                setLocationRelativeTo(null);
                setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(8, 12, 8, 12);
                gbc.fill   = GridBagConstraints.HORIZONTAL;

                // Instruction label
                JLabel instrLabel = new JLabel("Enter a word below:");
                gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 5;
                add(instrLabel, gbc);

                // Text field
                JTextField wordField = new JTextField(22);
                wordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
                gbc.gridy = 1;
                add(wordField, gbc);

                // Preview label
                JLabel previewLabel = new JLabel("Preview will appear here");
                previewLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
                previewLabel.setHorizontalAlignment(SwingConstants.CENTER);
                previewLabel.setBorder(BorderFactory.createEtchedBorder());
                gbc.gridy = 2;
                add(previewLabel, gbc);

                // Buttons
                JButton boldBtn      = new JButton("Bold");
                JButton italicBtn    = new JButton("Italic");
                JButton boldItlcBtn  = new JButton("Bold-Italic");
                JButton clearBtn     = new JButton("Clear");
                JButton exitBtn      = new JButton("Exit");

                JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
                btnPanel.add(boldBtn);
                btnPanel.add(italicBtn);
                btnPanel.add(boldItlcBtn);
                btnPanel.add(clearBtn);
                btnPanel.add(exitBtn);

                gbc.gridy = 3;
                add(btnPanel, gbc);

                // ── Helper to apply style ────────────────────────
                Runnable applyBold = () -> {
                    String text = wordField.getText();
                    previewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                    previewLabel.setText(text.isEmpty() ? "Preview will appear here" : text);
                };
                Runnable applyItalic = () -> {
                    String text = wordField.getText();
                    previewLabel.setFont(new Font("SansSerif", Font.ITALIC, 18));
                    previewLabel.setText(text.isEmpty() ? "Preview will appear here" : text);
                };
                Runnable applyBoldItalic = () -> {
                    String text = wordField.getText();
                    previewLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 18));
                    previewLabel.setText(text.isEmpty() ? "Preview will appear here" : text);
                };

                // ── Actions ──────────────────────────────────────
                boldBtn.addActionListener(e -> applyBold.run());
                italicBtn.addActionListener(e -> applyItalic.run());
                boldItlcBtn.addActionListener(e -> applyBoldItalic.run());

                clearBtn.addActionListener(e -> {
                    wordField.setText("");
                    previewLabel.setText("Preview will appear here");
                    previewLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
                });

                exitBtn.addActionListener(e -> {
                    dispose();
                    parent.setVisible(true);
                });

                setVisible(true);
            }
        }


        static class Lab3Menu extends JFrame {

            Lab3Menu(JFrame parent) {
                setTitle("Lab Exercise 3 – Conversion Menu");
                setSize(380, 340);
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                setLocationRelativeTo(null);
                setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(6, 20, 6, 20);
                gbc.fill   = GridBagConstraints.HORIZONTAL;
                gbc.gridwidth = 1;

                JLabel title = new JLabel("Choose a conversion:", SwingConstants.CENTER);
                title.setFont(title.getFont().deriveFont(Font.BOLD, 14f));
                gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
                add(title, gbc);
                gbc.gridwidth = 1;

                String[][] convs = {
                        {"Inches to Centimeter",  "in-cm"},
                        {"Feet to Meter",         "ft-m"},
                        {"Pound to Kilogram",     "lb-kg"},
                        {"Gallon to Liter",       "gal-l"},
                        {"Fahrenheit to Celsius", "f-c"},
                        {"Celsius to Fahrenheit", "c-f"}
                };

                int row = 1;
                for (String[] conv : convs) {
                    JButton btn = new JButton(conv[0]);
                    String key = conv[1];
                    gbc.gridx = (row - 1) % 2;
                    gbc.gridy = 1 + (row - 1) / 2;
                    btn.addActionListener(e -> new Lab3Converter(this, conv[0], key));
                    add(btn, gbc);
                    row++;
                }

                JButton exitBtn = new JButton("Exit");
                gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
                exitBtn.addActionListener(e -> { dispose(); parent.setVisible(true); });
                add(exitBtn, gbc);

                setVisible(true);
            }
        }

        // ─── Individual Converter Window ──────────────────────────
        static class Lab3Converter extends JDialog {

            Lab3Converter(JFrame parent, String title, String convType) {
                super(parent, title, true);
                setSize(380, 220);
                setLocationRelativeTo(parent);
                setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(8, 12, 8, 12);
                gbc.fill   = GridBagConstraints.HORIZONTAL;

                String[] labels = conversionLabels(convType);
                JLabel inputLabel = new JLabel("Enter value in " + labels[0] + ":");
                gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
                add(inputLabel, gbc);

                JTextField inputField = new JTextField(15);
                gbc.gridy = 1;
                add(inputField, gbc);

                JLabel resultLabel = new JLabel("Result: —");
                resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 13f));
                gbc.gridy = 2;
                add(resultLabel, gbc);

                JButton convertBtn = new JButton("Convert");
                JButton clearBtn   = new JButton("Clear");
                JButton exitBtn    = new JButton("Exit");

                JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
                btnPanel.add(convertBtn);
                btnPanel.add(clearBtn);
                btnPanel.add(exitBtn);

                gbc.gridy = 3;
                add(btnPanel, gbc);

                // ── Actions ──────────────────────────────────────
                convertBtn.addActionListener(e -> {
                    try {
                        double val = Double.parseDouble(inputField.getText().trim());
                        double result = convert(convType, val);
                        resultLabel.setText(String.format("%.4f %s = %.4f %s",
                                val, labels[0], result, labels[1]));
                    } catch (NumberFormatException ex) {
                        resultLabel.setText("Please enter a valid number.");
                    }
                });

                clearBtn.addActionListener(e -> {
                    inputField.setText("");
                    resultLabel.setText("Result: —");
                });

                exitBtn.addActionListener(e -> dispose());

                setVisible(true);
            }

            private String[] conversionLabels(String type) {
                switch (type) {
                    case "in-cm": return new String[]{"inches", "cm"};
                    case "ft-m":  return new String[]{"feet", "meters"};
                    case "lb-kg": return new String[]{"pounds", "kg"};
                    case "gal-l": return new String[]{"gallons", "liters"};
                    case "f-c":   return new String[]{"°F", "°C"};
                    case "c-f":   return new String[]{"°C", "°F"};
                    default:      return new String[]{"", ""};
                }
            }

            private double convert(String type, double val) {
                switch (type) {
                    case "in-cm": return val * 2.54;
                    case "ft-m":  return val * 0.3048;
                    case "lb-kg": return val * 0.453592;
                    case "gal-l": return val * 3.78541;
                    case "f-c":   return (val - 32) * 5.0 / 9.0;
                    case "c-f":   return val * 9.0 / 5.0 + 32;
                    default:      return 0;
                }
            }
        }


        static class Lab4 extends JFrame {

            // Menu data
            static final String[] COMBO_NAMES = {
                    "Combo A – Burger + Fries + Drink",
                    "Combo B – Chicken + Rice + Drink",
                    "Combo C – Pasta + Bread + Drink",
                    "Combo D – Pizza Slice + Salad + Drink"
            };
            static final double[] COMBO_PRICES = {120.00, 135.00, 110.00, 150.00};

            // Order state
            ArrayList<int[]> order = new ArrayList<>(); // [comboIndex, qty]

            // ── UI references ─────────────────────────────────────
            JPanel    mainPanel;
            CardLayout cards;

            // Combo-selection card
            JList<String>   comboList;
            JTextField      qtyField;
            JLabel          qtyLabel;
            JTextArea       orderSummary;

            // Payment card
            JLabel          billLabel;
            JTextField      paymentField;
            JLabel          changeLabel;

            Lab4(JFrame parent) {
                setTitle("Lab Exercise 4 – Combo Meals");
                setSize(500, 480);
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                setLocationRelativeTo(null);

                cards     = new CardLayout();
                mainPanel = new JPanel(cards);

                mainPanel.add(buildMenuCard(parent), "menu");
                mainPanel.add(buildPaymentCard(),    "payment");

                add(mainPanel);
                cards.show(mainPanel, "menu");
                setVisible(true);
            }

            // ── Combo-selection card ──────────────────────────────
            private JPanel buildMenuCard(JFrame parent) {
                JPanel p = new JPanel(new BorderLayout(8, 8));
                p.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));

                JLabel title = new JLabel("Combo Meals Menu", SwingConstants.CENTER);
                title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
                p.add(title, BorderLayout.NORTH);

                // Combo list with prices
                DefaultListModel<String> model = new DefaultListModel<>();
                for (int i = 0; i < COMBO_NAMES.length; i++) {
                    model.addElement(String.format("%-42s ₱%.2f", COMBO_NAMES[i], COMBO_PRICES[i]));
                }
                comboList = new JList<>(model);
                comboList.setFont(new Font("Monospaced", Font.PLAIN, 13));
                comboList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                p.add(new JScrollPane(comboList), BorderLayout.CENTER);

                // Bottom section
                JPanel bottom = new JPanel(new GridBagLayout());
                GridBagConstraints g = new GridBagConstraints();
                g.insets = new Insets(4, 6, 4, 6);
                g.fill   = GridBagConstraints.HORIZONTAL;

                qtyLabel = new JLabel("Quantity:");
                g.gridx = 0; g.gridy = 0; g.gridwidth = 1; g.weightx = 0;
                bottom.add(qtyLabel, g);

                qtyField = new JTextField("1", 5);
                g.gridx = 1; g.weightx = 0.3;
                bottom.add(qtyField, g);

                JButton addBtn = new JButton("Add to Order");
                g.gridx = 2; g.weightx = 0;
                bottom.add(addBtn, g);

                // Order summary
                orderSummary = new JTextArea(6, 35);
                orderSummary.setEditable(false);
                orderSummary.setFont(new Font("Monospaced", Font.PLAIN, 12));
                orderSummary.setBorder(BorderFactory.createTitledBorder("Current Order"));
                g.gridx = 0; g.gridy = 1; g.gridwidth = 3; g.weightx = 1;
                bottom.add(new JScrollPane(orderSummary), g);

                // Action buttons
                JButton checkoutBtn = new JButton("Proceed to Payment");
                JButton clearOrderBtn = new JButton("Clear Order");
                JButton exitBtn     = new JButton("Exit");

                JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
                btnRow.add(checkoutBtn);
                btnRow.add(clearOrderBtn);
                btnRow.add(exitBtn);

                g.gridy = 2;
                bottom.add(btnRow, g);

                p.add(bottom, BorderLayout.SOUTH);

                // ── Actions ──────────────────────────────────────
                addBtn.addActionListener(e -> {
                    int idx = comboList.getSelectedIndex();
                    if (idx < 0) {
                        JOptionPane.showMessageDialog(this, "Please select a combo first.");
                        return;
                    }
                    int qty;
                    try { qty = Integer.parseInt(qtyField.getText().trim()); }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid quantity.");
                        return;
                    }
                    if (qty < 1) { JOptionPane.showMessageDialog(this, "Quantity must be at least 1."); return; }

                    // Check if already in order
                    boolean found = false;
                    for (int[] item : order) {
                        if (item[0] == idx) { item[1] += qty; found = true; break; }
                    }
                    if (!found) order.add(new int[]{idx, qty});
                    refreshSummary();
                });

                clearOrderBtn.addActionListener(e -> {
                    order.clear();
                    orderSummary.setText("");
                });

                checkoutBtn.addActionListener(e -> {
                    if (order.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Your order is empty.");
                        return;
                    }
                    showPaymentCard();
                });

                exitBtn.addActionListener(e -> { dispose(); parent.setVisible(true); });

                return p;
            }

            private void refreshSummary() {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%-38s %4s %10s %10s%n", "Item", "Qty", "Price", "Subtotal"));
                sb.append("-".repeat(66)).append("\n");
                double subtotal = 0;
                for (int[] item : order) {
                    double lineTotal = item[1] * COMBO_PRICES[item[0]];
                    subtotal += lineTotal;
                    sb.append(String.format("%-38s %4d %10.2f %10.2f%n",
                            COMBO_NAMES[item[0]], item[1], COMBO_PRICES[item[0]], lineTotal));
                }
                double vatableAmt = subtotal / 1.12;
                double vat        = subtotal - vatableAmt;
                sb.append("-".repeat(66)).append("\n");
                sb.append(String.format("%-53s %10.2f%n", "Vatable Amount:", vatableAmt));
                sb.append(String.format("%-53s %10.2f%n", "VAT (12%):",      vat));
                sb.append(String.format("%-53s %10.2f%n", "TOTAL:",          subtotal));
                orderSummary.setText(sb.toString());
            }

            // ── Payment card ──────────────────────────────────────
            private JPanel buildPaymentCard() {
                JPanel p = new JPanel(new GridBagLayout());
                p.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));

                GridBagConstraints g = new GridBagConstraints();
                g.insets = new Insets(8, 8, 8, 8);
                g.fill   = GridBagConstraints.HORIZONTAL;

                JLabel title = new JLabel("Payment", SwingConstants.CENTER);
                title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
                g.gridx = 0; g.gridy = 0; g.gridwidth = 2;
                p.add(title, g);

                billLabel = new JLabel();
                billLabel.setBorder(BorderFactory.createEtchedBorder());
                billLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
                g.gridy = 1;
                p.add(billLabel, g);

                JLabel payLabel = new JLabel("Enter payment amount (₱):");
                g.gridy = 2; g.gridwidth = 1; g.weightx = 0;
                p.add(payLabel, g);

                paymentField = new JTextField(12);
                g.gridx = 1; g.weightx = 1;
                p.add(paymentField, g);

                JButton payBtn  = new JButton("Pay");
                JButton backBtn = new JButton("Back to Order");

                JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
                btnRow.add(payBtn);
                btnRow.add(backBtn);

                g.gridx = 0; g.gridy = 3; g.gridwidth = 2; g.weightx = 1;
                p.add(btnRow, g);

                changeLabel = new JLabel(" ", SwingConstants.CENTER);
                changeLabel.setFont(changeLabel.getFont().deriveFont(Font.BOLD, 14f));
                g.gridy = 4;
                p.add(changeLabel, g);

                // ── Actions ──────────────────────────────────────
                payBtn.addActionListener(e -> {
                    double total = computeTotal();
                    double payment;
                    try { payment = Double.parseDouble(paymentField.getText().trim()); }
                    catch (NumberFormatException ex) {
                        changeLabel.setForeground(Color.RED);
                        changeLabel.setText("Please enter a valid payment amount.");
                        return;
                    }
                    if (payment < total) {
                        changeLabel.setForeground(Color.RED);
                        changeLabel.setText(String.format(
                                "Insufficient! Short by ₱%.2f", total - payment));
                    } else {
                        changeLabel.setForeground(new Color(0, 128, 0));
                        changeLabel.setText(String.format(
                                "Payment: ₱%.2f   Change: ₱%.2f   Thank you!", payment, payment - total));
                    }
                });

                backBtn.addActionListener(e -> {
                    paymentField.setText("");
                    changeLabel.setText(" ");
                    cards.show(mainPanel, "menu");
                });

                return p;
            }

            private double computeTotal() {
                double subtotal = 0;
                for (int[] item : order) subtotal += item[1] * COMBO_PRICES[item[0]];
                return subtotal;
            }

            private void showPaymentCard() {
                double subtotal   = computeTotal();
                double vatableAmt = subtotal / 1.12;
                double vat        = subtotal - vatableAmt;
                billLabel.setText(String.format(
                        "<html><pre>Vatable Amount : ₱%10.2f%nVAT (12%%)      : ₱%10.2f%nTotal Due      : ₱%10.2f</pre></html>",
                        vatableAmt, vat, subtotal));
                paymentField.setText("");
                changeLabel.setText(" ");
                cards.show(mainPanel, "payment");
            }
        }
    }

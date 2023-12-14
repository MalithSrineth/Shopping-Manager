import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class ShoppingCartGUI extends JFrame {
    private JTable productsTable;
    private JLabel totalLabel, totalValue;
    private JLabel discountLabel, discountValue;
    private JLabel finalTotalLabel, finalTotalValue;
    private JButton buyNowButton, shopMoreButton;
    private JScrollPane scrollPane;

    public ShoppingCartGUI(LoggingSession loggingSession) {
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        initComponents(loggingSession);
        layoutComponents();
        pack();
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private void initComponents(LoggingSession loggingSession) {
        // Table for shopping cart items
        Map<Product, Integer> shoppingItems = loggingSession.getShoppingCart().getProducts();
        String[] columnNames = {"Product", "Quantity", "Price"};
        DefaultTableModel model = new DefaultTableModel(convertListToData(shoppingItems), columnNames);
        productsTable = new JTable(model);
        scrollPane = new JScrollPane(productsTable);

        // Labels for totals and discount
        totalLabel = new JLabel("Total:");
        totalValue = new JLabel("£0.00");
        discountLabel = new JLabel("Discount:");
        discountValue = new JLabel("£0.00");
        finalTotalLabel = new JLabel("Final Total:");
        finalTotalValue = new JLabel("£0.00");

        // Aligning label text to the right
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        discountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        finalTotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Buttons
        buyNowButton = new JButton("Buy Now");
        shopMoreButton = new JButton("Shop More");
    }

    private void layoutComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
    
        // Table
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(scrollPane, gbc);
    
        // Panel for labels and values with GridLayout
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE; // Change fill to NONE for alignment purposes
        gbc.anchor = GridBagConstraints.LINE_END; // Align to the end of the line (right side)
        JPanel labelsPanel = new JPanel(new GridLayout(3, 2, 10, 4)); // 3 rows, 2 cols, hgap, vgap
        labelsPanel.add(totalLabel);
        labelsPanel.add(totalValue);
        labelsPanel.add(discountLabel);
        labelsPanel.add(discountValue);
        labelsPanel.add(finalTotalLabel);
        labelsPanel.add(finalTotalValue);
        add(labelsPanel, gbc);
    
        // Panel for Buttons
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(buyNowButton);
        buttonPanel.add(shopMoreButton);
        add(buttonPanel, gbc);
    }

    private Object[][] convertListToData(Map<Product, Integer> shoppingItems) {
        Object[][] data = new Object[shoppingItems.size()][3];
        int i = 0;
        for (Product product : shoppingItems.keySet()) {
            data[i][0] = product.getProductName();
            data[i][1] = shoppingItems.get(product);
            data[i][2] = "£" + product.getProductPrice();
            i++;
        }
        return data;
    }
}

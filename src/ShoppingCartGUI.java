import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCartGUI extends JFrame {
    private JTable cartTable;
    private JLabel totalLabel, totalValue;
    private JLabel discountLabel, discountValue;
    private JLabel finalTotalLabel, finalTotalValue;
    private JButton clearCartButton, checkoutButton;
    private JScrollPane scrollPane;

    public ShoppingCartGUI(LoggingSession loggingSession) {
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        initComponents(loggingSession);
        layoutComponents(loggingSession);
        setMinimumSize(new Dimension(400, 250));
        setMaximumSize(new Dimension(400, 500));
        pack();
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
        
    }

    private void initComponents(LoggingSession loggingSession) {
        // Table for shopping cart items
        Map<Product, Integer> shoppingItems = loggingSession.getShoppingCart().getProducts();
        String[] columnNames = {"Product", "Quantity", "Price"};
        DefaultTableModel model = new DefaultTableModel(convertListToData(shoppingItems), columnNames);
        cartTable = new JTable(model);
        scrollPane = new JScrollPane(cartTable);

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

        // Setting Values for totals
        updateTotals(loggingSession);

        // Buttons
        clearCartButton = new JButton("Clear Cart");
        checkoutButton = new JButton("Checkout");

    }

    private void layoutComponents(LoggingSession loggingSession) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
    
        // Table
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Adjust the height of the table according to its row count
        adjustTableHeight(cartTable, scrollPane);
        
    
        // Panel for labels and values with GridLayout
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0.0;
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
        buttonPanel.add(clearCartButton);
        clearCartButton.addActionListener(e -> {
            loggingSession.getShoppingCart().emptyCart();
            updateCart(loggingSession);
            updateTotals(loggingSession);
        });
        buttonPanel.add(checkoutButton);

        checkoutButton.addActionListener(e -> {
            Purchase purchase = new Purchase(loggingSession);
            loggingSession.getUser().addPurchase(purchase);
            loggingSession.getUser().getShoppingCart().emptyCart();
            loggingSession.setShoppingCart(new ShoppingCart());
            updateCart(loggingSession);
            updateTotals(loggingSession);
        });
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

    public void updateCart(LoggingSession loggingSession) {
        Map<Product, Integer> shoppingItems = new LinkedHashMap<>();
        shoppingItems =loggingSession.getShoppingCart().getProducts();
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.setDataVector(convertListToData(shoppingItems), new String[] {"Product", "Quantity", "Price"});
        model.fireTableDataChanged();
        cartTable.setModel(model);
        adjustTableHeight(cartTable, scrollPane);
        
        
        // totalValue.setText("£" + String.format("%.2f", loggingSession.getShoppingCart().calculateTotal()));
        // discountValue.setText("£" + String.format("%.2f", loggingSession.getShoppingCart().calculateDiscount()));
        // finalTotalValue.setText("£" + String.format("%.2f", loggingSession.getShoppingCart().calculateFinalTotal()));
    }

    public void updateTotals(LoggingSession loggingSession) {
        double total = loggingSession.getShoppingCart().getTotal();
        totalValue.setText("£" + String.format("%.2f", total));

        double discount = loggingSession.getShoppingCart().getDiscount();
        if (loggingSession.getUser().getPurchases().size() == 0) {
            // discount = total * 10 / 100;
            // loggingSession.getShoppingCart().setDiscount(discount);
            discountValue.setText("£" + String.format("%.2f", discount));
        }
        else {
            discountValue.setText("£" + String.format("%.2f", discount));
        }

        double finalTotal = total - discount;
        finalTotalValue.setText("£" + String.format("%.2f", finalTotal));
    }

    private void adjustTableHeight(JTable table, JScrollPane scrollPane) {
        int rowHeight = table.getRowHeight();
        int headerHeight = table.getTableHeader().getPreferredSize().height;
        int rowsToShow = table.getRowCount();
        int totalRowHeight = rowHeight * rowsToShow + headerHeight;
    
        // Set the preferred size of the scroll pane based on the total height of the rows and the header.
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, totalRowHeight+3));

        // After adjusting the size, revalidate and repaint to apply changes
        // cartTable.revalidate();
        // cartTable.repaint();
        // scrollPane.revalidate();
        // scrollPane.repaint();

        pack();
        setVisible(true);
    }
 
}

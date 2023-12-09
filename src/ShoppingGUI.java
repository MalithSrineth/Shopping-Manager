import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShoppingGUI extends JFrame {

    private JComboBox<String> categoryComboBox;
    private JTable productsTable;
    private JLabel productIdLabel, categoryLabel, nameLabel, sizeLabel, colorLabel, availableLabel, productCategoryLabel;
    private JButton shoppingCartButton, addToCartButton;

    public ShoppingGUI() {
        setTitle("Westminster Shopping Centre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        initComponents();
        layoutComponents();

        pack();
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private void initComponents() {
        // Label for the category combo box
        productCategoryLabel = new JLabel("Product Category");
        
        // Dropdown for product categories
        categoryComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});

        // Shopping cart button
        shoppingCartButton = new JButton("Shopping Cart");

        // Table for product listings
        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Info"};

        // Initialize your ArrayList<Product> and populate it with Product instances
        ArrayList<Product> products = WestminsterShoppingManager.getProducts();
        DefaultTableModel model = new DefaultTableModel(convertListToData(products), columnNames);
        productsTable = new JTable(model);

        // Labels for product details
        productIdLabel = new JLabel();
        categoryLabel = new JLabel();
        nameLabel = new JLabel();
        sizeLabel = new JLabel();
        colorLabel = new JLabel();
        availableLabel = new JLabel();

        // Button to add product to the shopping cart
        addToCartButton = new JButton("Add to Shopping Cart");
    }

    private void layoutComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label for the category combo box
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(productCategoryLabel, gbc);

        // Category combo box next to the label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(categoryComboBox, gbc);

        // Shopping cart button at the top-right
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(shoppingCartButton, gbc);

        // Reset to default for the rest
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Table in the center
        add(new JScrollPane(productsTable), gbc);

        // Product details below the table
        gbc.gridy = 2;
        gbc.weighty = 0.0;
        add(new JLabel("Selected Product - Details"), gbc);

        // Product ID
        gbc.gridy++;
        add(productIdLabel, gbc);

        // Category
        gbc.gridy++;
        add(categoryLabel, gbc);

        // Name
        gbc.gridy++;
        add(nameLabel, gbc);

        // Size
        gbc.gridy++;
        add(sizeLabel, gbc);

        // Color
        gbc.gridy++;
        add(colorLabel, gbc);

        // Items available
        gbc.gridy++;
        add(availableLabel, gbc);

        // Add to cart button
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(addToCartButton, gbc);
    }

    private Object[][] convertListToData(ArrayList<Product> productList) {
        Object[][] data = new Object[productList.size()][5];
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            data[i][0] = product.getProductID();
            data[i][1] = product.getProductName();
            data[i][2] = product.getClass().getName();
            data[i][3] = product.getProductPrice();
            if (product instanceof Electronics) {
                data[i][4] = ((Electronics) product).getBrandName() + ", " + ((Electronics) product).getWarrantyPeriod();
            } else if (product instanceof Clothing) {
                data[i][4] = ((Clothing) product).getSize() + ", " + ((Clothing) product).getColor();
            } else {
                data[i][4] = "";
            }
        }
        return data;
    }

    public void run () {
        SwingUtilities.invokeLater(ShoppingGUI::new);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShoppingGUI::new);
    }
}

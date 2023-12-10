import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ShoppingGUI extends JFrame {

    private JComboBox<String> categoryComboBox;
    private JTable productsTable;
    private JLabel productIdLabel, categoryLabel, nameLabel, sizeLabel, colorLabel, availableLabel, productCategoryLabel;
    private JButton shoppingCartButton, sorButton, addToCartButton;
    private JScrollPane scrollPane;

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

        // Sort button
        sorButton = new JButton("Sort");

        // Table for product listings
        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Info"};

        //Populating the table with data
        ArrayList<Product> products = WestminsterShoppingManager.getProducts();
        DefaultTableModel model = new DefaultTableModel(convertListToData(products), columnNames);
        productsTable = new JTable(model);
        scrollPane = new JScrollPane(productsTable);

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
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(productCategoryLabel, gbc);

        // Category combo box 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(categoryComboBox, gbc);

        categoryComboBox.addActionListener(categoryComboBoxActionEvent -> {
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            ArrayList<Product> products = WestminsterShoppingManager.getProducts();
            if (selectedCategory.equals("All")) {
                productsTable.setModel(new DefaultTableModel(convertListToData(products), new String[]{"Product ID", "Name", "Category", "Price(£)", "Info"}));
            } else if (selectedCategory.equals("Electronics")) {
                ArrayList<Product> electronics = new ArrayList<>();
                for (Product product : products) {
                    if (product instanceof Electronics) {
                        electronics.add(product);
                    }
                }
                productsTable.setModel(new DefaultTableModel(convertListToData(electronics), new String[]{"Product ID", "Name", "Category", "Price(£)", "Info"}));
            } else if (selectedCategory.equals("Clothing")) {
                ArrayList<Product> clothing = new ArrayList<>();
                for (Product product : products) {
                    if (product instanceof Clothing) {
                        clothing.add(product);
                    }
                }
                productsTable.setModel(new DefaultTableModel(convertListToData(clothing), new String[]{"Product ID", "Name", "Category", "Price(£)", "Info"}));
            }
        });

        // Shopping cart button at the top-right
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(shoppingCartButton, gbc);

        // Sort button at the top-right
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(sorButton, gbc);

        sorButton.addActionListener(sortButtonActionEvent -> {
            ArrayList<Product> products = new ArrayList<Product>();
            for (Product product : WestminsterShoppingManager.getProducts()) {
                products.add(product);
            }
            products.sort((Product p1, Product p2) -> p1.getProductID().compareTo(p2.getProductID()));
            if (categoryComboBox.getSelectedItem().equals("Electronics")) {
                ArrayList<Product> electronics = new ArrayList<>();
                for (Product product : products) {
                    if (product instanceof Electronics) {
                        electronics.add(product);
                    }
                }
                productsTable.setModel(new DefaultTableModel(convertListToData(electronics), new String[]{"Product ID", "Name", "Category", "Price(£)", "Info"}));
            } else if (categoryComboBox.getSelectedItem().equals("Clothing")) {
                ArrayList<Product> clothing = new ArrayList<>();
                for (Product product : products) {
                    if (product instanceof Clothing) {
                        clothing.add(product);
                    }
                }
                productsTable.setModel(new DefaultTableModel(convertListToData(clothing), new String[]{"Product ID", "Name", "Category", "Price(£)", "Info"}));
            } else
            productsTable.setModel(new DefaultTableModel(convertListToData(products), new String[]{"Product ID", "Name", "Category", "Price(£)", "Info"}));
        });

        // Reset to default for the rest
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Table in the center
        add(scrollPane, gbc);

        productsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = productsTable.rowAtPoint(evt.getPoint());
                int col = productsTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    String productID = (String) productsTable.getValueAt(row, 0);
                    Product product = WestminsterShoppingManager.getProduct(productID);
                    productIdLabel.setText("Product ID: " + product.getProductID());
                    categoryLabel.setText("Category: " + product.getClass().getName());
                    nameLabel.setText("Name: " + product.getProductName());
                    if (product instanceof Electronics) {
                        sizeLabel.setText("Brand Name: " + ((Electronics) product).getBrandName());
                        colorLabel.setText("Warranty Period: " + ((Electronics) product).getWarrantyPeriod());
                    } else if (product instanceof Clothing) {
                        sizeLabel.setText("Size: " + ((Clothing) product).getSize());
                        colorLabel.setText("Color: " + ((Clothing) product).getColor());
                    } else {
                        sizeLabel.setText("");
                        colorLabel.setText("");
                    }
                    availableLabel.setText("Items available: " + product.getProductQuantity());
                }
            }
        });


        // Product details below the table
        gbc.gridy = 3;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    static Scanner input = new Scanner(System.in);
    private static ArrayList<Product> products;
    private static ArrayList<User> users;
    //public static Object getProducts;

    public WestminsterShoppingManager() {
        WestminsterShoppingManager.products = new ArrayList<>(50);
        WestminsterShoppingManager.users = new ArrayList<>();

        // Creating 3 Clothing objects
        Clothing clothing1 = new Clothing("C01", "T-Shirt", 19.99, 50, "M", "Blue");
        Clothing clothing2 = new Clothing("C02", "Jeans", 39.99, 30, "L", "Black");
        Clothing clothing3 = new Clothing("C03", "Jacket", 59.99, 20, "XL", "Red");

        // Creating 3 Electronics objects
        Electronics electronics1 = new Electronics("E01", "Smartphone", 499.99, 15, "XYZ Brand", "2 Years");
        Electronics electronics2 = new Electronics("E02", "Laptop", 899.99, 10, "ABC Brand", "1 Year");
        Electronics electronics3 = new Electronics("E03", "Headphones", 89.99, 25, "PQR Brand", "6 Months");
        
        // Adding the objects to the products ArrayList
        // products.add(electronics1);
        // products.add(electronics2);
        // products.add(electronics3);
        // products.add(clothing1);
        // products.add(clothing2);
        // products.add(clothing3);

        fileReader();

        
    }

    @Override
    public void addProduct(Product product, int choice) {
        if (products.size() >= 1) {
            System.out.println("Product limit reached");
            return;
        } else {
            System.out.println("\nEnter the product ID");
            input.nextLine();
            String productID = input.nextLine();
            product.setProductID(productID);

            System.out.println("\nEnter the product name");
            String productName = input.nextLine();
            product.setProductName(productName);

            System.out.println("\nEnter the product price");
            double productPrice = getVerifiedDoubleInput();
            product.setProductPrice(productPrice);

            System.out.println("\nEnter the product quantity");
            int productQuantity = getVerifiedIntInput();
            product.setProductQuantity(productQuantity);
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nEnter the brand name");
                    String brandName = input.nextLine();
                    System.out.println("\nEnter the warranty period");
                    String warrantyPeriod = input.nextLine();
                    product = new Electronics(product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductQuantity(), brandName, warrantyPeriod);
                    products.add(product);
                    System.out.println("Product: "+product.getProductName()+" - "+product.getProductID()+" is added successfully\n");
                    break;
                case 2:
                    System.out.println("\nEnter the size");
                    String size = input.nextLine();
                    System.out.println("\nEnter the Color");
                    String color = input.nextLine();
                    product = new Clothing(product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductQuantity(), size, color);
                    products.add(product);
                    System.out.println("Product: "+product.getProductName()+" - "+product.getProductID()+" is added successfully\n");
                    break;
                default:
                    System.out.println("Invalid choice\n");
                    break;
            }
        }
        
    }

    @Override
    public void removeProduct(Product product) {
        products.remove(product);
        String details = ("\n=======================================\n"+
                          "Product Details\n"+
                          "=======================================\n"+
                          "* Product Type: "+product.getClass().getName()+"\n"+
                          "* Product ID: "+product.getProductID()+"\n"+
                          "* Product Name: "+product.getProductName()+"\n"+
                          "=======================================\n"+
                          "This Product is removed successfully\n"+
                          "=======================================\n"+
                          "\nProducts Remaining in the System - "+products.size()+"\n");
        System.out.println(details);
    }

    @Override
    public void emptyCart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'emptyCart'");
    }

    @Override
    public void printProducts() {
        //sort products alphabetically by product ID
        products.sort((Product p1, Product p2) -> p1.getProductID().compareTo(p2.getProductID()));
        System.out.println("\n=======================================");
        System.out.println("Product List       : "+products.size()+" Products");
        System.out.println("=======================================");
        for (Product product : products) {
            System.out.println("* Product Type     : "+product.getClass().getName());
            System.out.println("* Product ID       : "+product.getProductID());
            System.out.println("* Product Name     : "+product.getProductName());
            System.out.println("* Product Price    : "+product.getProductPrice());
            System.out.println("* Product Quantity : "+product.getProductQuantity());
            if (product instanceof Electronics) {
                Electronics electronics = (Electronics) product;
                System.out.println("* Brand Name       : "+electronics.getBrandName());
                System.out.println("* Warranty Period  : "+electronics.getWarrantyPeriod()+"\n");
            } else if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                System.out.println("* Size             : "+clothing.getSize());
                System.out.println("* Color            : "+clothing.getColor()+"\n");
            }
            System.out.println("=======================================");
        }
    }

    @Override
    public void viewGUI() {
        new LoginGUI();
        //new ShoppingGUI();
        
    }

    @Override
    public void fileWriter() throws IOException {
        File file = new File("src/products.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Product product : products) {
            if (product instanceof Electronics) {
                Electronics electronics = (Electronics) product;
                fileWriter.write("E"+","+
                                 electronics.getProductID()+","+
                                 electronics.getProductName()+","+
                                 electronics.getProductPrice()+","+
                                 electronics.getProductQuantity()+","+
                                 electronics.getBrandName()+","+
                                 electronics.getWarrantyPeriod()+"\n");

            } else if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                fileWriter.write("C"+","+
                                 clothing.getProductID()+","+
                                 clothing.getProductName()+","+
                                 clothing.getProductPrice()+","+
                                 clothing.getProductQuantity()+","+
                                 clothing.getSize()+","+
                                 clothing.getColor()+"\n");
            }
        }
        bufferedWriter.close();
        fileWriter.close();
        System.out.println("Products are saved successfully");
        System.out.println("=======================================");

    }

    @Override
    public void fileReader() {
        File file = new File("src/products.txt");
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals("E")) {
                    Electronics electronics = new Electronics(values[1], values[2], Double.parseDouble(values[3]), Integer.parseInt(values[4]), values[5], values[6]);
                    products.add(electronics);
                } else if (values[0].equals("C")) {
                    Clothing clothing = new Clothing(values[1], values[2], Double.parseDouble(values[3]), Integer.parseInt(values[4]), values[5], values[6]);
                    products.add(clothing);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println("Products are loaded successfully");
    }

    

    public static int getVerifiedIntInput() {
        int inputInt = -1;
        do {
            try {
                inputInt = input.nextInt();

            } catch (Exception e) {
                input.next();
                System.out.println("Invalid Input: Please Enter a Number");
            }
        } while (inputInt == -1);
        return inputInt;
    }

    public static double getVerifiedDoubleInput() {
        double inputDouble = -1.0;
        do {
            try {
                inputDouble = input.nextDouble();

            } catch (Exception e) {
                input.next(); // Clear the invalid input
                System.out.println("Invalid Input: Please Enter a Number\n");
            }
        } while (inputDouble == -1.0);
        return inputDouble;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static Product getProduct(String productID) {
        Product product = null;
        for (Product p : products) {
            if (p.getProductID().equals(productID)) {
                product = p;
                break;
            }
        }
        return product;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getUser(String username) {
        User user = null;
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                user = u;
                break;
            }
        }
        return user;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    
    
}

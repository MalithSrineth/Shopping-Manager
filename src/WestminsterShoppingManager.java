import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    static Scanner input = new Scanner(System.in);
    private static ArrayList<Product> products;

    public WestminsterShoppingManager() {
        WestminsterShoppingManager.products = new ArrayList<>(50);
    }

    @Override
    public void addProduct(Product product, int choice) {
        if (products.size() >= 1) {
            System.out.println("Product limit reached");
            return;
        } else {
            System.out.println("\nEnter the product ID");
            String productID = input.next();
            product.setProductID(productID);

            System.out.println("\nEnter the product name");
            String productName = input.next();
            product.setProductName(productName);

            System.out.println("\nEnter the product price");
            double productPrice = getVerifiedDoubleInput();
            product.setProductPrice(productPrice);

            System.out.println("\nEnter the product quantity");
            int productQuantity = getVerifiedIntInput();
            product.setProductQuantity(productQuantity);

            switch (choice) {
                case 1:
                    System.out.println("\nEnter the brand name");
                    String brandName = input.next();
                    System.out.println("\nEnter the warranty period");
                    String warrantyPeriod = input.next();
                    product = new Electronics(product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductQuantity(), brandName, warrantyPeriod);
                    products.add(product);
                    System.out.println("Product: "+product.getProductName()+" - "+product.getProductID()+" is added successfully\n");
                    break;
                case 2:
                    System.out.println("\nEnter the size");
                    String size = input.next();
                    System.out.println("\nEnter the material");
                    String material = input.next();
                    product = new Clothing(product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductQuantity(), size, material);
                    products.add(product);
                    System.out.println("Product: "+product.getProductName()+" - "+product.getProductID()+" is added successfully\n");
                    break;
                default:
                    System.out.println("Invalid choice/n");
                    break;
            }
        }
        
    }

    @Override
    public void removeProduct(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeProduct'");
    }

    @Override
    public void emptyCart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'emptyCart'");
    }

    @Override
    public void printProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printProducts'");
    }

    @Override
    public void fileWriter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fileWriter'");
    }

    @Override
    public void fileReader() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fileReader'");
    }

    private static int getVerifiedIntInput() {
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

    private static double getVerifiedDoubleInput() {
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

    public static void main(String[] args) {
        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();

        String menu = "=======================================\n" +
                      "Welcome to Westminster Shopping Manager\n" +
                      "=======================================\n" +
                      "1. Add a product\n" +
                      "2. Remove a product\n" +
                      "3. Print the list of products\n" +
                      "4. Print the list of products in the cart\n" +
                      "5. Empty the cart\n" +
                      "6. Print the total price of the cart\n" +
                      "7. Save the list of products to a file\n" +
                      "8. Load the list of products from a file\n" +
                      "9. Exit\n" +
                      "=======================================\n" +
                      "\nEnter your choice: ";
        
        int menuChoice = -1;

        do {
            System.out.print(menu);
            menuChoice = getVerifiedIntInput();

            switch (menuChoice) {
                case 1:
                    if (products.size() >= 1) {
                        System.out.println("\nProduct limit reached\n");
                    } else {
                        int choice = -1;
                        Product product = null;

                        do {
                            System.out.println("\n=====================================");
                            System.out.println("Select the product type to add");
                            System.out.println("1. Electronics");
                            System.out.println("2. Clothes\n");
                        
                            choice = getVerifiedIntInput();
                        
                            switch (choice) {
                                case 1:
                                    product = new Electronics();
                                    break;
                                case 2:
                                    product = new Clothing();
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please enter 1 for Electronics or 2 for Clothes.\n");
                                    break;
                            }
                        } while (product == null);

                        westminsterShoppingManager.addProduct(product, choice);
                    }
                    break;

                case 2:
                    // removeProduct(westminsterShoppingManager);
                    break;
                case 3:
                    // printProducts(westminsterShoppingManager);
                    break;
                case 4:
                    // printProductsInCart(westminsterShoppingManager);
                    break;
                case 5:
                    // emptyCart(westminsterShoppingManager);
                    break;
                case 6:
                    // printTotalPrice(westminsterShoppingManager);
                    break;
                case 7:
                    // saveProductsToFile(westminsterShoppingManager);
                    break;
                case 8:
                    // loadProductsFromFile(westminsterShoppingManager);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                    break;
            }
        } while (menuChoice != 9);


        System.out.println("=====================================");
        System.out.println("Select the product type to add");
        System.out.println("1. Electronics");
        System.out.println("2. Clothes");
            
        int choice = getVerifiedIntInput();
        Product product = null;

        do {
            System.out.println("=====================================");
            System.out.println("Select the product type to add");
            System.out.println("1. Electronics");
            System.out.println("2. Clothes");
        
            choice = getVerifiedIntInput();
        
            switch (choice) {
                case 1:
                    product = new Electronics();
                    break;
                case 2:
                    product = new Clothing();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 for Electronics or 2 for Clothes.");
                    break;
            }
        } while (product == null);

        westminsterShoppingManager.addProduct(product, choice);
    }

    
}

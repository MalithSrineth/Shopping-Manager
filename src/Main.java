import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
        ArrayList<Product> products = WestminsterShoppingManager.getProducts();

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
            menuChoice = WestminsterShoppingManager.getVerifiedIntInput();

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
                        
                            choice = WestminsterShoppingManager.getVerifiedIntInput();
                        
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
                    System.out.println("Enter the product ID to remove");
                    String productID = WestminsterShoppingManager.input.next();
                    Product product = null;
                    for (Product p : products) {
                        if (p.getProductID().equals(productID)) {
                            product = p;
                            break;
                        } else {
                            System.out.println("Product not found");
                        }
                    }
                    if (product != null) {
                        westminsterShoppingManager.removeProduct(product);
                    }

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
    }
}

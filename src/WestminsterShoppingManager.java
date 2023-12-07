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
        if (products.size() >= 50) {
            System.out.println("Product limit reached");
            return;
        } else {
            System.out.println("Enter the product ID");
            String productID = input.next();
            product.setProductID(productID);

            System.out.println("Enter the product name");
            String productName = input.next();
            product.setProductName(productName);

            System.out.println("Enter the product price");
            double productPrice = getVerifiedDoubleInput();
            product.setProductPrice(productPrice);

            System.out.println("Enter the product quantity");
            int productQuantity = getVerifiedIntInput();
            product.setProductQuantity(productQuantity);

            switch (choice) {
                case 1:
                    System.out.println("Enter the brand name");
                    String brandName = input.next();
                    System.out.println("Enter the warranty period");
                    String warrantyPeriod = input.next();
                    product = new Electronics(product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductQuantity(), brandName, warrantyPeriod);
                    products.add(product);
                    System.out.println("Product: "+product.getProductName()+" - "+product.getProductID()+" is added successfully");
                    break;
                case 2:
                    System.out.println("Enter the size");
                    String size = input.next();
                    System.out.println("Enter the material");
                    String material = input.next();
                    product = new Clothing(product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductQuantity(), size, material);
                    products.add(product);
                    System.out.println("Product: "+product.getProductName()+" - "+product.getProductID()+" is added successfully");
                    break;
                default:
                    System.out.println("Invalid choice");
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
                System.out.println("Invalid Input: Please Enter a Number");
            }
        } while (inputDouble == -1.0);
        return inputDouble;
    }

    public static void main(String[] args) {
        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();

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

import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    static Scanner input = new Scanner(System.in);
    private static ArrayList<Product> products;
    public static Object getProducts;

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
                    System.out.println("\nEnter the material");
                    String material = input.nextLine();
                    product = new Clothing(product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductQuantity(), size, material);
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
    
}

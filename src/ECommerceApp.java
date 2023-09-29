import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ECommerceApp {
	
	public static void main(String[] args) {
//		Product product1 = new Product(1, "Product1", "Description", 10.99, 50);
//		Product product2 = new Product(2, "Product2", "Description", 10.99, 25);
//		Product product3 = new Product(3, "Product3", "Description", 20.99, 50);
//		Product product4 = new Product(4, "Product4", "Description", 39.99, 25);
//		Product product5 = new Product(5, "Product5", "Description", 42.99, 50);
//		Product product6 = new Product(6, "Product6", "Description", 720.99, 10);
//		AvailableProducts.addProduct(product1);
//        AvailableProducts.addProduct(product2);
//        AvailableProducts.addProduct(product3);
//        AvailableProducts.addProduct(product4);
//        AvailableProducts.addProduct(product5);
//        AvailableProducts.addProduct(product6);
		ArrayList<Product> availableProducts = ProductFactory.createProductsFromFile("Products.csv");
		Cart cart = new Cart(availableProducts);
		
//		cart.addProduct(2);
//        cart.addProduct(2);
        
        while (true) {
            System.out.println("\nE-commerce Cart System Menu:");
            System.out.println("1. View Available Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. Reduce Quantity of Item in Cart");
            System.out.println("5. Reorder Items in Cart");
            System.out.println("6. View Cart Summary");
            System.out.println("7. Proceed to Checkout");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");  

            try {
                Scanner scanner = new Scanner(System.in);
            	int choice = scanner.nextInt();
            	
            	switch (choice) {
                case 1:
                    // Display available products
                    cart.printAvailableProducts();
                    break;
                case 2:
                    // Add product to cart
                    System.out.print("Enter the product ID to add (or -1 to exit): ");
                    int productId = scanner.nextInt();
                    if (productId == -1) {break;}
                    System.out.print("Enter the quantity to add (or -1 to exit): ");
                    int quantity = scanner.nextInt();
                    if (quantity == -1) {break;}
                    cart.addProduct(productId, quantity);
                    break;
                case 3:
                    // Remove product from cart
                	System.out.print("Enter the product ID to remove (or -1 to exit): ");
                    int removeProductId = scanner.nextInt();
                    if (removeProductId == -1) {break;}
                    cart.removeProduct(removeProductId);
                    break;
                case 4:
                    // Reduce quantity of item in cart
                	System.out.print("Enter the product ID to reduce (or -1 to exit): ");
                    int reduceProductId = scanner.nextInt();
                    if (reduceProductId == -1) {break;}
                    System.out.print("Enter the quantity to reduce (or -1 to exit): ");
                    int reduceQuantity = scanner.nextInt();
                    if (reduceQuantity == -1) {break;}
                    cart.reduceProduct(reduceProductId, reduceQuantity);
                    break;
                case 5:
                    // Reorder items in cart
                    System.out.print("Enter the index of the first item to reorder (or -1 to exit): ");
                    int firstIndex = scanner.nextInt();
                    if (firstIndex == -1) {break;}
                    System.out.print("Enter the index of the second item to reorder (or -1 to exit): ");
                    int secondIndex = scanner.nextInt();
                    if (secondIndex == -1) {break;}
                    cart.changeOrder(firstIndex, secondIndex);
                    break;
                case 6:
                    // View Cart Summary
                    cart.getSummary();
                    break;
                case 7:
                    // Proceed to checkout
                    System.out.print("Are you sure you want to proceed to checkout? (yes/no): ");
                    String confirmCheckout = scanner.next();
                    if (confirmCheckout.equalsIgnoreCase("yes")) {
                        cart.checkout();
                        System.out.println("Checkout successful. Thank you for your purchase!");
                    } else {
                        System.out.println("Checkout canceled.");
                    }
                    break;
                case 8:
                    // Exit the program
                    System.out.println("Thank you for using the E-commerce Cart System. Goodbye!");
                    scanner.close();
                    ProductFactory.writeProductsText(cart.getAvailableProducts(), "Products.csv");
                    System.out.println("Saved the updated data to the CSV file.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            	}
            } catch(Exception e) {
            	System.out.println("Invalid input type.");
            }
        }
	}
}


public class Product {
	private int id;
	private String name;
	private String description;
	private double price;
	private int stockCount;
	private int quantity;
	
	Product(int id, String name, String description, double price, int stockCount) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stockCount = stockCount;
		this.quantity = 0;
	}
	
	 public int getId() {return this.id;}	 
	 public String getName() {return this.name;}
	 public String getDescription() {return this.description;}
	 public double getPrice() {return this.price;}
	 public int getStockCount() {return this.stockCount;}
	 public int getQuantity() {return this.quantity;}
	 
	 public void setId(int id) {this.id = id;}
	 public void setName(String name) {this.name = name;}
	 public void setDescription(String description) {this.description = description;}
	 public void setPrice(double price) {this.price = price;}
	 public void setStockCount(int stockCount) {this.stockCount = stockCount;}
	 public void setQuantity(int quantity) {this.quantity = quantity;}
	 
	 @Override
     public String toString() {
		 return String.format("ID: %d - %s: %s - $%.2f (%d left in stock)", this.id, this.name, this.description, this.price, this.stockCount);
     }
}


public class Cart {
	private LinkedList<Product> items;
	private ArrayList<Product> availableProducts;
	
//	public Cart() {
//		this.items = new LinkedList<>();
//		this.availableProducts = ProductFactory.getAvailableProducts();
//	}
	
	public Cart(ArrayList<Product> availableProducts) {
	    this.items = new LinkedList<>();
	    this.availableProducts = availableProducts;
	}
	
	public LinkedList<Product> getItems() {return this.items;}
	public int size() {return this.items.size();}
	public boolean isEmpty() {return this.size() == 0;}
	
	public Product getProduct(int productId) {
		Product product = null;
		
		for (int i = 0; i < this.availableProducts.size(); i++) {
			if (this.availableProducts.get(i) instanceof Product) {
				Product item = this.availableProducts.get(i);
				if (item.getId() == productId) {
					product = item;
					return product;
				}
			}
		}
		
		return product;
	}
	
	public int getItemCount(int productId) {
		return this.getProduct(productId).getQuantity();
	}
	
	public ArrayList<Product> getAvailableProducts() {
		return this.availableProducts;
	}
	
	public void printAvailableProducts() {
		for (int i = 0; i < this.availableProducts.size(); i++) {
			if (this.availableProducts.get(i) instanceof Product) {
				Product product = this.availableProducts.get(i);
				if (product.getStockCount() > 0) {
					System.out.println(product);
				}
			}	
		}
	}
	
	public boolean addProduct(int productId) {
		return this.addProduct(productId, 1);
	}
	
	public boolean addProduct(int productId, int quantity) {
		boolean added = false;

		try {
			Product product = this.getProduct(productId);
			if (product == null || productId < 0) {
				throw new IllegalArgumentException("Invalid Product ID: " + productId);
			} 
			if (quantity < 0) {
				throw new IllegalArgumentException("Invalid quantity: " + quantity);
			}
			if (this.items.contains(product)) {
//					Product item = this.items.get(this.items.indexOf(product));
				Product item = product;
				if (item.getQuantity() + quantity > item.getStockCount()) {
					throw new IllegalArgumentException("Quantity exceeds the stock count: " + item);
				}
				item.setQuantity(item.getQuantity() + quantity);
				added = true;
			} else {
//					Product item = new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStockCount());
				Product item = product;
				if (item.getQuantity() + quantity > item.getStockCount()) {
					throw new IllegalArgumentException("Quantity exceeds the stock count: " + item);
				}
				added = this.items.add(item);
				item.setQuantity(quantity);
			}
			System.out.println("Adding product of ID " + productId + " by " + quantity + ". Currently have " + product.getQuantity() + " of " + product.getName() + " in the cart.");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return added;
	}
	
//	public boolean removeProduct(int index) {
//		this.checkIndex(index);
//		this.items.get(index).setQuantity(0);
//		return(this.items.remove(index));
//	}
	
	public boolean removeProduct(int productId) {
		boolean removed = false;
		
		Product item = this.getProduct(productId);
		if (item != null) {
			item.setQuantity(0);
			removed = (this.items.remove(item));
			System.out.println("Removed product of ID " + productId + " from the cart.");
		}
		
		return removed;	
	}
	
	public boolean reduceProduct(int productId, int quantity) {
		boolean reduced = false;
		
		try {
			Product item = this.getProduct(productId);
			if (item == null || productId < 0) {
				throw new IllegalArgumentException("Invalid Product ID: " + productId);
			} 
			if (quantity < 0) {
				throw new IllegalArgumentException("Invalid quantity: " + quantity);
			}
			if (item.getQuantity() - quantity < 0) {
				throw new IllegalArgumentException("Reduce amount is more than existing quantity: " + item);
			}
			if (item != null) {
				item.setQuantity(item.getQuantity() - quantity);
				reduced = true;
				if (item.getQuantity() == 0) {
					this.items.remove(item);
				}
			}
			System.out.println("Reducing product of ID " + productId + " by " + quantity + ". Currently have " + item.getQuantity() + " of " + item.getName() + " in the cart.");
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
 		
		
		return reduced;
	}
	
	public boolean changeOrder(int firstIndex, int secondIndex) {
		try {
			this.checkIndex(firstIndex);
			this.checkIndex(secondIndex);
			return(this.items.changeOrder(firstIndex, secondIndex));
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		} catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void clear() {
		this.items.clear();
	}
	
	public double getTotalPrice() {
		double sum = 0;
		
		for (int i = 0; i < this.items.size(); i++) {
			Product item = this.items.get(i);
			sum += item.getPrice() * (double) item.getQuantity();
		}
		
		return sum;
	}
	
	public void checkout() {
		for (int i = 0; i < this.items.size(); i++) {
			Product item = this.items.get(i);
			if (item != null) { 
	            int remainingStock = item.getStockCount() - item.getQuantity();
	            item.setStockCount(remainingStock);
	        }
        }
		
		this.clear();
	}
	
	public void getInvoice() {
		System.out.println("Invoice Summary:");
		
		for (int i = 0; i < this.items.size(); i++)  {
            System.out.println("Product: " + this.items.get(i).getName() + " - Price: $" + this.items.get(i).getPrice());
        }
		
        System.out.println("Total Cost: $" + this.getTotalPrice());
	}
	
	public void getSummary() {
		System.out.println("Cart Invoice Summary:");

		for (int i = 0; i < this.items.size(); i++) {
	        Product item = this.items.get(i);
	        System.out.println("Product: " + item.getName() + " x " + item.getQuantity() + " - Price: $" + item.getPrice() * item.getQuantity());
	    }

	    System.out.println("Total Cost: $" + this.getTotalPrice());
    }
	
	public void checkIndex(int index) {
        if (index < 0 || index >= this.items.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds. Index: " + index + ", Size: " + this.items.size());
        }
    }
	
	public String toString() {
        StringBuilder string = new StringBuilder("Cart Contents:\n");
        for (int i = 0; i < this.items.size(); i++) {
            Product item = this.items.get(i);
            string.append("Product: ").append(item.getName()).append(" - Price: $").append(" x" ).append(item.getQuantity()).append(item.getPrice()).append("\n");
        }
        string.append("Total Cost: $").append(getTotalPrice());
        return string.toString();
    }

	
}

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class ProductFactory {

//    private static ArrayList<Product> availableProducts = new ArrayList<>();
//
//    public static ArrayList<Product> getAvailableProducts() {
//        return availableProducts;
//    }
//    
//    public static Product getProduct(int productId) {
//    	if (productId > availableProducts.size()) {
//    		throw new IllegalArgumentException("" + productId);
//    	}
//    	return availableProducts.get(productId);
//    }
// 
//    public static void addProduct(Product product) {
//        availableProducts.add(product);
//    }
	
	public static ArrayList<Product> createProductsFromFile(String filepath) {
		ArrayList<Product> availableProducts = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] arguments = line.split(",");
				
				int productId;
	            try {
	                productId = Integer.parseInt(arguments[0].trim());
	            } catch (NumberFormatException e) {
	                continue; 
	            }

	            String name = arguments[1].trim();
	            String description = arguments[2].trim();
	            double price;
	            int stockCount;
	            
	            try {
	                price = Double.parseDouble(arguments[3].trim());
	                stockCount = Integer.parseInt(arguments[4].trim());
	            } catch (NumberFormatException e) {
	                continue; 
	            }

	            availableProducts.add(new Product(productId, name, description, price, stockCount));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {br.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return availableProducts;
	}
	
	public static void writeProductsText(ArrayList<Product> availableProducts, String filepath) throws IOException {
		BufferedWriter writer = null;
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filepath);
    		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
    		writer = new BufferedWriter(outputStreamWriter);
    		writer.append("Product ID,Product Name,Description,Price,Stock Count");
    		writer.newLine();
    		for (int i = 0; i < availableProducts.size(); i++) {
    			Product product = availableProducts.get(i);
    			writer.append(Integer.toString(product.getId()) + ',' + product.getName() + ',' + product.getDescription() + ',' + Double.toString(product.getPrice()) + ',' + Integer.toString(product.getStockCount()));
    			if (i != availableProducts.size() -1) {writer.newLine();}
    		}
		} catch (IOException e) {
			throw new IOException();
		} finally {
			try {
				if (writer != null) {writer.close();}
			} catch (IOException e) {throw new IOException();}
		}
	}

}
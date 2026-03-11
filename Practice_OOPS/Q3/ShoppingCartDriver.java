package oops_Qs;

// ShoppingCart.java
import java.util.*;

public class ShoppingCartDriver implements ShoppingCart {
	// productId -> quantity in cart
	private final Map<String, Integer> productToQuantity;
	// productId -> Product (for price lookup)
	private final Map<String, Product> idToProduct;

	public ShoppingCartDriver() {
		this.productToQuantity = new HashMap<>();
		this.idToProduct = new HashMap<>();
	}

	@Override
	public boolean addProduct(Product product, int quantity) {
		String prodID = product.getProductId();
		double price = product.getPrice();
		if (price <= 0 || quantity <= 0 || product == null)
			return false;
		idToProduct.put(product.getProductId(), product);
		int oldQty = productToQuantity.getOrDefault(prodID, 0);
		productToQuantity.put(prodID, oldQty + quantity);

		return true;

	}

	@Override
	public boolean removeProduct(String productId, int quantityToRemove) {
		if (!productToQuantity.containsKey(productId) || quantityToRemove <= 0) {
			return false;
		}
		int oldQty = productToQuantity.getOrDefault(productId, 0);
		if (oldQty - quantityToRemove < 0) {
			productToQuantity.put(productId, oldQty - quantityToRemove);
		} else {
			productToQuantity.remove(productId);
		}
		return true;
	}

	@Override
	public int getQuantity(String productId) {
		return productToQuantity.getOrDefault(productId, 0);
	}

	@Override
	public double getTotalPrice() {
		double total = 0.0;
		for (Map.Entry<String, Integer> map : productToQuantity.entrySet()) {
			Product p = idToProduct.get(map.getKey());
			if (p != null) {
				total += p.getPrice() * map.getValue();
			}
		}
		return total;
	}

	@Override
	public Map<String, Integer> getCartSnapshot() {
		return new HashMap<>(productToQuantity);
	}

	public static void main(String[] args) {
		ShoppingCartDriver cart = new ShoppingCartDriver();

		Product p1 = new Product("p1", "Laptop", 75000.0);
		Product p2 = new Product("p2", "Mouse", 500.0);

		// 1. Add products
		cart.addProduct(p1, 1); // should succeed
		cart.addProduct(p2, 2); // should succeed

		System.out.println("Q(p1): " + cart.getQuantity("p1")); // 1
		System.out.println("Q(p2): " + cart.getQuantity("p2")); // 2

		System.out.println("Total: " + cart.getTotalPrice());
		// 75000.0 * 1 + 500.0 * 2 = 75000 + 1000 = 76000.0

		// 2. Remove 1 mouse
		cart.removeProduct("p2", 1);
		System.out.println("Q(p2) after remove: " + cart.getQuantity("p2")); // 1
		System.out.println("Total after remove 1 mouse: " + cart.getTotalPrice());
		// 75000 + 500 = 75500.0

	}
}

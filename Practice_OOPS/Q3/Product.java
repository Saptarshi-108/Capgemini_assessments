package oops_Qs;

//ShoppingCartDesign.java

import java.util.List;
import java.util.Map;

interface ShoppingCart {
	/**
	 * Adds a product to the cart (or increases its quantity if already present).
	 * Returns false if product is invalid (e.g., price <= 0).
	 */
	boolean addProduct(Product product, int quantity);
	
	/**
	 * Removes a product from the cart (or reduces quantity). Returns true if
	 * product was present and quantity > 0 after removal. Returns false if product
	 * not in cart or removal would make quantity <= 0.
	 */
	boolean removeProduct(String productId, int quantityToRemove);

	/**
	 * Returns current quantity of a given product in the cart. Returns 0 if product
	 * is not in the cart.
	 */
	int getQuantity(String productId);

	/**
	 * Returns the total price of all items in the cart.
	 */
	double getTotalPrice();

	/**
	 * Returns a snapshot of the cart as a map: productId -> quantity.
	 */
	Map<String, Integer> getCartSnapshot();
}

public final class Product {
	private final String productId;
	private final String name;
	private final double price;

	public Product(String productId, String name, double price) {
		this.productId = productId;
		this.name = name;
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
}

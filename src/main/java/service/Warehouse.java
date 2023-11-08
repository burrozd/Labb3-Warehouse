package service;

import entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Warehouse {
    private final Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.id(), product);
    }

    public void modifyProduct(int productId, String name, Product.Category category, int rating) {
        if (products.containsKey(productId)) {
            Product existingProduct = products.get(productId);
            Product modifiedProduct = new Product(existingProduct.id(), name, category, rating, existingProduct.createdDate(), LocalDate.now());
            products.put(productId, modifiedProduct);
        }
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(int productId) {
        return products.get(productId);
    }

    public List<Product> getProductsByCategorySortedByName(Product.Category category) {
        return products.values().stream()
                .filter(product -> product.category() == category)
                .sorted((p1, p2) -> p1.name().compareToIgnoreCase(p2.name()))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsCreatedAfter(LocalDate date) {
        return products.values().stream()
                .filter(product -> product.createdDate().isAfter(date))
                .collect(Collectors.toList());
    }


}

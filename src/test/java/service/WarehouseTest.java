package service;

import entities.Product;
import entities.Product.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WarehouseTest {
    private Warehouse warehouse;

    @BeforeEach
    public void setUp() {
        warehouse = new Warehouse();
        warehouse.addProduct(new Product(1, "Product 1", Category.ELECTRONICS, 8, LocalDate.now(), LocalDate.now()));
        warehouse.addProduct(new Product(2, "Product 2", Category.CLOTHING, 6, LocalDate.now(), LocalDate.now()));
    }

    @Test
    public void testAddProduct() {
        warehouse.addProduct(new Product(3, "Product 3", Category.BOOKS, 7, LocalDate.now(), LocalDate.now()));
        List<Product> products = warehouse.getAllProducts();
        assertEquals(3, products.size());
    }

    @Test
    public void testModifyProduct() {
        warehouse.modifyProduct(2, "New Name", Category.FOOD, 9);
        Product modifiedProduct = warehouse.getProductById(2);
        assertNotNull(modifiedProduct);
        assertEquals("New Name", modifiedProduct.name());
        assertEquals(Category.FOOD, modifiedProduct.category());
        assertEquals(9, modifiedProduct.rating());
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = warehouse.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    public void testGetProductById() {
        Product product = warehouse.getProductById(1);
        assertNotNull(product);
        assertEquals(1, product.id());
    }

    @Test
    public void testGetProductsByCategorySortedByName() {
        List<Product> products = warehouse.getProductsByCategorySortedByName(Category.CLOTHING);
        assertEquals(1, products.size());
        assertEquals("Product 2", products.get(0).name());
    }

    @Test
    public void testGetProductsCreatedAfter() {
        List<Product> products = warehouse.getProductsCreatedAfter(LocalDate.now().minusDays(1));
        assertEquals(2, products.size());
    }

}

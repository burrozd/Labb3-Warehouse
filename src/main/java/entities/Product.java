package entities;

import java.time.LocalDate;

public record Product(int id, String name, Category category, int rating, LocalDate createdDate, LocalDate lastModifiedDate) {

    public enum Category {
        ELECTRONICS,
        CLOTHING,
        BOOKS,
        FOOD,
    }

    public Product {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
    }
}

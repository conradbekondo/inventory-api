package ca.quickdo.springintro.dtos;

public class ProductDTO {
    private String name;
    private String color;
    private String description;
    private int quantity;

    // Private constructor to enforce the use of the builder
    private ProductDTO() {
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Builder class
    public static class Builder {
        private String name;
        private String color;
        private String description;
        private int quantity;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductDTO build() {
            ProductDTO productDTO = new ProductDTO();
            productDTO.name = this.name;
            productDTO.color = this.color;
            productDTO.description = this.description;
            productDTO.quantity = this.quantity;
            return productDTO;
        }
    }

    // Static method to obtain a new instance of the builder
    public static Builder builder() {
        return new Builder();
    }
}
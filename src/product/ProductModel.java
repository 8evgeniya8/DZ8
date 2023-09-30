package product;

import java.time.LocalDate;

public class ProductModel implements IProduct {
    private final String type;
    private final double price;
    private final boolean discount;
    private final LocalDate addedDate;

    private ProductModel(Builder builder) {
        this.type = builder.type;
        this.price = builder.price;
        this.discount = builder.discount;
        this.addedDate = builder.addedDate;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isDiscount() {
        return discount;
    }

    public double getDiscounted() {
        if (discount) {
            return price * 0.9;
        }
        return price;
    }

    public LocalDate getDate() {
        return addedDate;
    }

    public boolean hasDiscount() {
        return discount;
    }

    public static class Builder {
        private String type;
        private double price;
        private boolean discount;
        private LocalDate addedDate;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder discount(boolean discount) {
            this.discount = discount;
            return this;
        }

        public Builder addedDate(LocalDate addedDate) {
            this.addedDate = addedDate;
            return this;
        }

        public ProductModel build() {
            return new ProductModel(this);
        }
    }
}


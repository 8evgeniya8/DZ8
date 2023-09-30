package product;

import java.time.LocalDate;

public interface IProduct {
    String getType();

    double getPrice();

    boolean isDiscount();

    double getDiscounted();

    LocalDate getDate();
}


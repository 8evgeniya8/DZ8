import product.IConstants;
import product.IProduct;
import product.ProductModel;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<IProduct> iProducts = createSampleProducts();

        List<IProduct> expensiveBooks = iProducts.stream()
                .filter(product -> IConstants.BOOK_TYPE.equals(product.getType())
                        && product.getPrice() > IConstants.MAX_BOOK_PRICE)
                .collect(Collectors.toList());

        List<IProduct> discounted = iProducts.stream()
                .filter(product -> IConstants.BOOK_TYPE.equals(product.getType())
                        && product.isDiscount())
                .collect(Collectors.toList());

        discounted.forEach(product -> {
            System.out.println("Book: " + product.getType() +
                    ", Price: " + product.getPrice() +
                    (product.isDiscount() ? ", Discounted Price: " +
                            product.getDiscounted() : "") +
                    ", Added Date: " + product.getDate());
        });

        Optional<IProduct> cheapestBook = iProducts.stream()
                .filter(product -> IConstants.BOOK_TYPE.equals(product.getType()))
                .min(Comparator.comparingDouble(IProduct::getPrice));

        if (cheapestBook.isPresent()) {
            System.out.println("Cheapest Book: " + cheapestBook.get().getType() +
                    ", Price: " + cheapestBook.get().getPrice());
        } else {
            System.out.println("No cheap books found.");
        }

        List<IProduct> lastAddedProducts = iProducts.stream()
                .sorted(Comparator.comparing(IProduct::getDate).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Last Three Added Products:");
        lastAddedProducts.forEach(product -> {
            System.out.println("Type: " + product.getType() +
                    ", Price: " + product.getPrice() +
                    ", Added Date: " + product.getDate());
        });

        Map<String, List<IProduct>> typeGroups = iProducts.stream()
                .collect(Collectors.groupingBy(IProduct::getType));

        System.out.println("Product Type Groups:");
        typeGroups.forEach((type, products) -> {
            System.out.println("Type: " + type);
            products.forEach(product -> {
                System.out.println("  Price: " + product.getPrice() +
                        ", Added Date: " + product.getDate());
            });
        });
    }

    private static List<IProduct> createSampleProducts() {
        List<IProduct> iProducts = new ArrayList<>();

        iProducts.add(new ProductModel.Builder()
                .type(IConstants.BOOK_TYPE)
                .price(300)
                .discount(true)
                .addedDate(LocalDate.of(2022, 1, 1))
                .build());

        iProducts.add(new ProductModel.Builder()
                .type(IConstants.BOOK_TYPE)
                .price(200)
                .discount(false)
                .addedDate(LocalDate.of(2022, 1, 2))
                .build());

        iProducts.add(new ProductModel.Builder()
                .type("Toy")
                .price(50)
                .discount(true)
                .addedDate(LocalDate.of(2022, 10, 10))
                .build());

        return iProducts;
    }
}
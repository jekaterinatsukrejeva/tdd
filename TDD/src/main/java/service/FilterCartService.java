package service;

import model.Product;
import java.util.List;
import java.util.stream.Collectors;

public class FilterCartService {

    public List<Product> filterBasedOnType(List<Product> cart, String type) {
        return cart.stream()
                .filter(p -> p.getType().equals(type))
                .collect(Collectors.toList());
    }

    public List<Product> filterBasedOnColor(List<Product> cart, String color) {
        return cart.stream()
                .filter(p -> p.getColor().equals(color))
                .collect(Collectors.toList());

    }

    public List<Product> filterItemsThatCostLess(List<Product> cart, double price) {
        return cart.stream()
                .filter(p -> p.getPrice() <= price)
                .sorted((p1, p2) -> {
                    if (p1.getPrice() > p2.getPrice()) {
                        return 1;
                    } else if (p1.getPrice() < p2.getPrice()) {
                        return -1;
                    }
                    return 0;
                }).collect(Collectors.toList());
    }

    public List<Product> filterItemsThatCostMore(List<Product> cart, double price) {
        return cart.stream()
                .filter(p -> p.getPrice() >= price)
                .sorted((p1, p2) -> {
                    if (p1.getPrice() > p2.getPrice()) {
                        return -1;
                    } else if (p1.getPrice() < p2.getPrice()) {
                        return 1;
                    }
                    return 0;
                }).collect(Collectors.toList());

    }
}

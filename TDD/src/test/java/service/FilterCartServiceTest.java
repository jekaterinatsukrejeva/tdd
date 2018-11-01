package service;

import model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class FilterCartServiceTest {

    private FilterCartService shoppingService;

    @Before
    public void init() {
        shoppingService = new FilterCartService();
    }

    @Test
    public void testFilterCartItemsBasedOnColor() {
        Product greenProduct = mockProduct("green", "glasses", 1.99);
        Product yellowProduct = mockProduct("yellow", "watches", 1.99);
        Product redProduct = mockProduct("red", "jewelery", 1.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterBasedOnColor(cart, "yellow");
        assertEquals(1, filteredCart.size());
        assertEquals(filteredCart.get(0).getColor(), "yellow");
    }

    @Test
    public void testFilterCartItemsBasedOnColor_twoYellowProductsFound() {
        Product greenProduct = mockProduct("yellow", "glasses", 1.99);
        Product yellowProduct = mockProduct("yellow", "watches", 1.99);
        Product redProduct = mockProduct("red", "jewelery", 1.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterBasedOnColor(cart, "yellow");
        assertEquals(2, filteredCart.size());
        filteredCart.forEach(p -> assertEquals(p.getColor(), "yellow"));
    }

    @Test
    public void testFilterCartItemsBasedOnColor_noYellowProductsFound() {
        Product greenProduct = mockProduct("red", "glasses", 1.99);
        Product yellowProduct = mockProduct("red", "watches", 1.99);
        Product redProduct = mockProduct("red", "jewelery", 1.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterBasedOnColor(cart, "yellow");
        assertEquals(0, filteredCart.size());
    }

    @Test
    public void testFilterCartItemsBasedOnType() {
        Product greenProduct = mockProduct("green", "glasses", 1.99);
        Product yellowProduct = mockProduct("yellow", "watches", 1.99);
        Product redProduct = mockProduct("red", "jewelery", 1.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterBasedOnType(cart, "glasses");
        assertEquals(1, filteredCart.size());
        assertEquals(filteredCart.get(0).getType(), "glasses");
    }

    @Test
    public void testFilterCartItemsBasedOnType_twoOfTheSameTypeFound() {
        Product greenProduct = mockProduct("green", "glasses", 1.99);
        Product yellowProduct = mockProduct("yellow", "glasses", 1.99);
        Product redProduct = mockProduct("red", "jewelery", 1.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterBasedOnType(cart, "glasses");
        assertEquals(2, filteredCart.size());
        filteredCart.forEach(p -> assertEquals(p.getType(), "glasses"));
    }

    @Test
    public void testFilterCartItemsBasedOnType_noMatchingTypesFound() {
        Product greenProduct = mockProduct("green", "watches", 1.99);
        Product yellowProduct = mockProduct("yellow", "watches", 1.99);
        Product redProduct = mockProduct("red", "jewelery", 1.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterBasedOnType(cart, "glasses");
        assertEquals(0, filteredCart.size());
    }

    @Test
    public void testFilterCartItemsThatCostLess() {
        Product greenProduct = mockProduct("green", "glasses", 1.99);
        Product yellowProduct = mockProduct("yellow", "watches", 10.99);
        Product redProduct = mockProduct("red", "jewelery", 100.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterItemsThatCostLess(cart, 15.99);
        assertEquals(2, filteredCart.size());
        assertEquals(filteredCart.get(0).getColor(), "green");
        assertEquals(filteredCart.get(1).getColor(), "yellow");
    }

    @Test
    public void testFilterCartItemsThatCostLess_smallerPriceInFront() {
        Product greenProduct = mockProduct("green", "glasses", 25.99);
        Product yellowProduct = mockProduct("yellow", "watches", 50.99);
        Product redProduct = mockProduct("red", "jewelery", 35.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterItemsThatCostLess(cart, 50.99);
        assertEquals(3, filteredCart.size());
        assertEquals(filteredCart.get(0).getPrice(), 25.99);
        assertEquals(filteredCart.get(1).getPrice(), 35.99);
        assertEquals(filteredCart.get(2).getPrice(), 50.99);

    }

    @Test
    public void testFilterCartItemsThatCostLess_twoProductPriceTied() {
        Product greenProduct = mockProduct("green", "glasses", 1.99);
        Product yellowProduct = mockProduct("yellow", "watches", 1.99);
        Product redProduct = mockProduct("red", "jewelery", 15.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterItemsThatCostLess(cart, 15.99);
        assertEquals(3, filteredCart.size());
        assertEquals(filteredCart.get(0).getPrice(), 1.99);
        assertEquals(filteredCart.get(1).getPrice(), 1.99);
        assertEquals(filteredCart.get(2).getPrice(), 15.99);
    }

    @Test
    public void testFilterCartItemsThatCostMore() {
        Product greenProduct = mockProduct("green", "glasses", 1.99);
        Product yellowProduct = mockProduct("yellow", "watches", 10.99);
        Product redProduct = mockProduct("red", "jewelery", 100.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterItemsThatCostMore(cart, 15.99);
        assertEquals(1, filteredCart.size());
        assertEquals(filteredCart.get(0).getColor(), "red");
        assertEquals(filteredCart.get(0).getType(), "jewelery");
    }

    @Test
    public void testFilterCartItemsThatCostMore_twoProductProducedTied() {
        Product greenProduct = mockProduct("green", "glasses", 1.99);
        Product yellowProduct = mockProduct("yellow", "watches", 1.99);
        Product redProduct = mockProduct("red", "jewelery", 100.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterItemsThatCostMore(cart, 0.99);
        assertEquals(3, filteredCart.size());
        assertEquals(filteredCart.get(0).getPrice(), 100.99);
        assertEquals(filteredCart.get(1).getPrice(), 1.99);
        assertEquals(filteredCart.get(2).getPrice(), 1.99);
    }

    @Test
    public void testFilterCartItemsThatCostMore_sortedHighestToLowest() {
        Product greenProduct = mockProduct("green", "glasses", 25.99);
        Product yellowProduct = mockProduct("yellow", "watches", 1.99);
        Product redProduct = mockProduct("red", "jewelery", 100.99);

        List<Product> cart = Arrays.asList(greenProduct, yellowProduct, redProduct);

        List<Product> filteredCart = shoppingService.filterItemsThatCostMore(cart, 0.99);
        assertEquals(3, filteredCart.size());
        assertEquals(filteredCart.get(0).getPrice(), 100.99);
        assertEquals(filteredCart.get(1).getPrice(), 25.99);
        assertEquals(filteredCart.get(2).getPrice(), 1.99);
    }

    private Product mockProduct(String color, String type, double price) {
        Product greenProduct = new Product();
        greenProduct.setColor(color);
        greenProduct.setType(type);
        greenProduct.setPrice(price);
        return greenProduct;
    }
}

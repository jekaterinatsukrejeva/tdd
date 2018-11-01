import exception.CheckoutException;
import model.Product;
import model.ShoppingCart;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ShoppingCartTest {

    private ShoppingCart cart;
    private Product product;

    @Before
    public void init() {
        cart = new ShoppingCart();
        product = new Product();
    }

    @Test
    public void testGetCart() {
        assertNotNull(cart.getCart());
    }

    @Test
    public void addProductToShoppingCart() {
        cart.addProduct(product);
        assertEquals(1, cart.getCart().size());
    }

    @Test
    public void addTwoProductToShoppingCart() {
        cart.addProduct(product);
        cart.addProduct(product);
        assertEquals(2, cart.getCart().size());
    }

    @Test
    public void checkoutCart_checkoutFailsDueToEmptyCart() {
        try {
            cart.checkout();
            fail("Should throw exception");
        } catch (CheckoutException ex) {
            assertEquals(ex.getMessage(), "Shopping cart is empty");
        }
    }

    @Test
    public void checkoutCart_checkoutSuccessful() {
        product.setPrice(500.00);
        cart.addProduct(product);
        assertEquals(cart.checkout(), "Checkout successful");

    }

    @Test
    public void checkoutCart_withTenPercentDiscount() {
        Product hundredEuroProduct = mockProduct("black", "glasses", 100.00);
        Product thousandEuroProduct = mockProduct("white", "apron", 1000.00);
        cart.addProduct(hundredEuroProduct);
        cart.addProduct(thousandEuroProduct);
        assertEquals(cart.checkout(), "Checkout successful with 10% discount");
        assertEquals(cart.getTotalPrice(), 990.00, 0.001);
    }

    @Test
    public void checkoutCart_withLowTotalAmount_addShippingFee() {
        Product tenEuroProduct = mockProduct("black", "glasses", 10.00);
        Product oneEuroProduct = mockProduct("white", "apron", 1.99);
        cart.addProduct(tenEuroProduct);
        cart.addProduct(oneEuroProduct);
        assertEquals(cart.checkout(), "Checkout successful and added 20.0€ shipping fee");
        assertEquals(cart.getTotalPrice(), 31.99, 0.001);
    }

    private Product mockProduct(String color, String type, double price) {
        Product greenProduct = new Product();
        greenProduct.setColor(color);
        greenProduct.setType(type);
        greenProduct.setPrice(price);
        return greenProduct;
    }


}

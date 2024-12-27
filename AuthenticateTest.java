import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticateTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("testUser", new OrderManager(), new Menu());
    }

    @Test
    void testValidLogin() {
        assertTrue(customer.authenticate("testUser"), "Valid login should succeed");
    }


    @Test
    void testInvalidUsername() {
        assertFalse(customer.authenticate("wrongUser"), "Login with incorrect username should fail");
    }


}

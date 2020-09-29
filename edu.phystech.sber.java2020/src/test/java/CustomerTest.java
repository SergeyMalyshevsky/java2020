import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @org.junit.jupiter.api.Test
    void openAccount() {
        Customer customer = new Customer("John", "Smith");
        assertTrue(customer.openAccount(1));
        assertFalse(customer.openAccount(2));
    }

    @org.junit.jupiter.api.Test
    void closeAccount() {
        Customer customer = new Customer("John", "Smith");
        customer.openAccount(1);
        assertTrue(customer.closeAccount());
        assertFalse(customer.closeAccount());
    }

    @org.junit.jupiter.api.Test
    void fullName() {
        Customer customer = new Customer("John", "Smith");
        assertEquals("John Smith", customer.fullName());
    }

    @org.junit.jupiter.api.Test
    void withdrawFromCurrentAccount() {
        Customer customer = new Customer("John", "Smith");
        customer.openAccount(1);
        customer.addMoneyToCurrentAccount(1000);
        assertTrue(customer.withdrawFromCurrentAccount(500));
        assertFalse(customer.withdrawFromCurrentAccount(1000));
        assertFalse(customer.withdrawFromCurrentAccount(-1000));
    }

    @org.junit.jupiter.api.Test
    void addMoneyToCurrentAccount() {
        Customer customer = new Customer("John", "Smith");
        customer.openAccount(1);
        assertTrue(customer.addMoneyToCurrentAccount(1000));
        assertFalse(customer.addMoneyToCurrentAccount(-1000));
    }
}
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @org.junit.jupiter.api.Test
    void withdraw() {
        Account account = new Account(1);

        account.add(1000);
        assertTrue(account.withdraw(500));
        assertFalse(account.withdraw(1000));
        assertFalse(account.withdraw(-1000));
    }

    @org.junit.jupiter.api.Test
    void add() {
        Account account = new Account(1);

        assertTrue(account.add(1000));
        assertFalse(account.add(-1000));
    }
}
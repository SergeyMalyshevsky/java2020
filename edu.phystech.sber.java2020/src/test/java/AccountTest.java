import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void withdraw() {
    }

    @Test
    void withdrawCash() {
    }

    @Test
    void addCash() {
        TransactionManager transactionManager = new TransactionManager();
        Account account = new Account(1, transactionManager);

        assertTrue(account.add(1000));
        account.rollbackLastTransaction();
        assertFalse(account.add(-1000));
    }

    @Test
    void add() {
    }

    @Test
    void history() {
    }

    @Test
    void balanceOn() {
        TransactionManager transactionManager = new TransactionManager();
        Account account = new Account(1, transactionManager);

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        assertEquals(0, account.balanceOn(tomorrow));

        account.add(1000);
        assertEquals(1000, account.balanceOn(tomorrow));

        account.rollbackLastTransaction();
        assertEquals(0, account.balanceOn(tomorrow));
    }

    @Test
    void rollbackLastTransaction() {
        TransactionManager transactionManager = new TransactionManager();
        Account account = new Account(1, transactionManager);

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        account.add(1000);
        account.rollbackLastTransaction();
        assertEquals(0, account.balanceOn(tomorrow));
    }

    @BeforeEach
    void setUp() {
        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new Account(1, transactionManager);
        Account account2 = new Account(2, transactionManager);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testWithdraw() {
    }

    @Test
    void testWithdrawCash() {
    }

    @Test
    void testAddCash() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void testHistory() {
    }

    @Test
    void testBalanceOn() {
    }

    @Test
    void testRollbackLastTransaction() {
    }

    @Test
    void testAddEntry() {
    }
}
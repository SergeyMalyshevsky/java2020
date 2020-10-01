import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void addCash() {
        TransactionManager transactionManager = new TransactionManager();
        Account account = new Account(1, transactionManager);

        assertTrue(account.add(1000, null));
        account.rollbackLastTransaction();
        assertFalse(account.add(-1000, null));
    }

    @Test
    void add() {
        TransactionManager transactionManager = new TransactionManager();
        Account account = new Account(1, transactionManager);

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        account.add(1000, null);
        account.add(2000, null);
        account.add(3500, null);
        assertEquals(6500, account.balanceOn(tomorrow));
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

        account.addCash(1000);
        assertEquals(1000, account.balanceOn(tomorrow));

        account.rollbackLastTransaction();
        assertEquals(0, account.balanceOn(tomorrow));
    }

    @Test
    void rollbackLastTransaction() {
        TransactionManager transactionManager = new TransactionManager();
        Account account = new Account(1, transactionManager);

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        account.add(1000, null);
        account.rollbackLastTransaction();
        assertEquals(0, account.balanceOn(tomorrow));
    }

    @Test
    void testWithdraw() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        TransactionManager transactionManager = new TransactionManager();
        Account account1 = new Account(1, transactionManager);
        Account account2 = new Account(2, transactionManager);

        account1.addCash(1000);
        account2.addCash(2000);

        assertTrue(account1.withdraw(500, account2));
        assertEquals(500, account1.balanceOn(tomorrow));
        assertEquals(2500, account2.balanceOn(tomorrow));
    }

    @Test
    void testWithdrawCash() {
        TransactionManager transactionManager = new TransactionManager();
        Account account = new Account(1, transactionManager);

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        assertFalse(account.withdrawCash(1000));

        account.addCash(1000);

        assertTrue(account.withdrawCash(500));
        assertEquals(500, account.balanceOn(tomorrow));
    }

    @Test
    void testHistory() {
    }
}
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void addCash() {
        TransactionManager transactionManager = new TransactionManager();
        DebitCard debitCard = new DebitCard(1, transactionManager);

        assertTrue(debitCard.add(1000, null));
        debitCard.rollbackLastTransaction();
        assertFalse(debitCard.add(-1000, null));
    }

    @Test
    void add() {
        TransactionManager transactionManager = new TransactionManager();
        DebitCard debitCard = new DebitCard(1, transactionManager);

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        debitCard.add(1000, null);
        debitCard.add(2000, null);
        debitCard.add(3500, null);
        assertEquals(6500, debitCard.balanceOn(tomorrow));
    }

    @Test
    void history() {
    }

    @Test
    void balanceOn() {
        TransactionManager transactionManager = new TransactionManager();
        DebitCard debitCard = new DebitCard(1, transactionManager);

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        assertEquals(0, debitCard.balanceOn(tomorrow));

        debitCard.addCash(1000);
        assertEquals(1000, debitCard.balanceOn(tomorrow));

        debitCard.rollbackLastTransaction();
        assertEquals(0, debitCard.balanceOn(tomorrow));
    }

    @Test
    void rollbackLastTransaction() {
        TransactionManager transactionManager = new TransactionManager();
        DebitCard debitCard = new DebitCard(1, transactionManager);

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        debitCard.add(1000, null);
        debitCard.rollbackLastTransaction();
        assertEquals(0, debitCard.balanceOn(tomorrow));
    }

    @Test
    void testWithdraw() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        TransactionManager transactionManager = new TransactionManager();
        DebitCard debitCard1 = new DebitCard(1, transactionManager);
        DebitCard debitCard2 = new DebitCard(2, transactionManager);

        debitCard1.addCash(1000);
        debitCard2.addCash(2000);

        assertTrue(debitCard1.withdraw(500, debitCard2));
        assertEquals(500, debitCard1.balanceOn(tomorrow));
        assertEquals(2500, debitCard2.balanceOn(tomorrow));
    }

    @Test
    void testWithdrawCash() {
        TransactionManager transactionManager = new TransactionManager();
        DebitCard debitCard = new DebitCard(1, transactionManager);

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        assertFalse(debitCard.withdrawCash(1000));

        debitCard.addCash(1000);

        assertTrue(debitCard.withdrawCash(500));
        assertEquals(500, debitCard.balanceOn(tomorrow));
    }

    @Test
    void testHistory() {
    }
}
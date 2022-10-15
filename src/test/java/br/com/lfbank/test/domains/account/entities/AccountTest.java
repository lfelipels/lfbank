package br.com.lfbank.test.domains.account.entities;

import br.com.lfbank.domains.account.entities.AccountInterface;
import br.com.lfbank.domains.account.entities.SavingsAccount;
import br.com.lfbank.domains.account.exceptions.OverDrawException;
import br.com.lfbank.domains.institutional.entities.Agency;
import br.com.lfbank.domains.client.entities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void testCreateASavingsAccount() {
        Agency agency = new Agency();
        Client client = new Client();

        AccountInterface account = new SavingsAccount(
                1,
                client,
                agency
        );

        Assertions.assertEquals(1, account.getNumber());
        Assertions.assertEquals(0d, account.getBalance());
        Assertions.assertNotNull(account.getClient());
        Assertions.assertNotNull(account.getAgency());
    }

    @Test
    public void testANewSavingsAccountShouldBeStatusInactive(){
        Agency agency = new Agency();
        Client client = new Client();

        AccountInterface account = new SavingsAccount(
                1,
                client,
                agency
        );

        Assertions.assertEquals(1, account.getNumber());
        Assertions.assertEquals(0d, account.getBalance());
        Assertions.assertNotNull(account.getClient());
        Assertions.assertNotNull(account.getAgency());
        Assertions.assertFalse(account.isActive());
    }

    @Test
    public void testDepositInAAccount(){
        Agency agency = new Agency();
        Client client = new Client();

        AccountInterface account = new SavingsAccount(
                1,
                client,
                agency
        );

        account.deposit(100d);
        Assertions.assertEquals(100d, account.getBalance());
    }

    @Test
    public void testDepositNegativeAmountInAAccountShouldThrowAnException(){
        Agency agency = new Agency();
        Client client = new Client();

        AccountInterface account = new SavingsAccount(
                1,
                client,
                agency
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> account.deposit(-100d));

        Assertions.assertEquals(0d, account.getBalance());
    }

    @Test
    public void testDepositInAInactiveAccountShouldActiveAccount() {
        Agency agency = new Agency();
        Client client = new Client();

        AccountInterface account = new SavingsAccount(
                1,
                client,
                agency
        );

        account.deposit(100d);
        Assertions.assertEquals(100d, account.getBalance());
        Assertions.assertTrue(account.isActive());
    }


    @Test
    public void testWithDrawInAnAccount(){
        Agency agency = new Agency();
        Client client = new Client();

        AccountInterface account = new SavingsAccount(
                1,
                client,
                agency
        );
        account.deposit(100d);
        Assertions.assertDoesNotThrow(() -> {
            account.withDraw(50d);
        });
        Assertions.assertEquals(50d, account.getBalance());
    }

    @Test
    public void testWithDrawInAnAccountWithNegativeValueShouldThrowException(){
        Agency agency = new Agency();
        Client client = new Client();

        AccountInterface account = new SavingsAccount(
                1,
                client,
                agency
        );
        account.deposit(100d);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> account.withDraw(-50d));
        Assertions.assertEquals(100d, account.getBalance());
    }

    @Test
    public void testWithDrawInAnAccountWithValueGreaterThanBalanceShouldThrowException(){
        Agency agency = new Agency();
        Client client = new Client();

        AccountInterface account = new SavingsAccount(
                1,
                client,
                agency
        );
        account.deposit(100d);
        Assertions.assertThrows(OverDrawException.class, ()-> account.withDraw(150d));
        Assertions.assertEquals(100d, account.getBalance());
    }
}

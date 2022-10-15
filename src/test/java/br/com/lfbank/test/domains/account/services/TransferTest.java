package br.com.lfbank.test.domains.account.services;

import br.com.lfbank.domains.account.entities.AccountInterface;
import br.com.lfbank.domains.account.entities.SavingsAccount;
import br.com.lfbank.domains.account.enums.AccountLimit;
import br.com.lfbank.domains.account.exceptions.LimitByDayToTransferExceededException;
import br.com.lfbank.domains.account.exceptions.OverDrawException;
import br.com.lfbank.domains.account.services.TransferService;
import br.com.lfbank.domains.client.entities.Client;
import br.com.lfbank.domains.institutional.entities.Agency;
import br.com.lfbank.infrastructure.TransferRepositoryInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferTest {

    private TransferService transferService;

    @BeforeEach
    void setUp() {
        this.transferService = new TransferService(new TransferRepositoryInMemory());
    }

    @Test
    public void testMakeATransferBetweenTwoAccounts(){
        AccountInterface account1 = new SavingsAccount(
                1,
                new Client(),
                new Agency()
        );
        account1.deposit(100d);

        AccountInterface account2 = new SavingsAccount(
                1,
                new Client(),
                new Agency()
        );

        account2.deposit(100d);

        Double amount = 50d;
        Assertions.assertDoesNotThrow(()-> this.transferService.execute(amount, account1, account2));
        Assertions.assertEquals(50, account1.getBalance());
        Assertions.assertEquals(150, account2.getBalance());
    }

    @Test
    public void testShouldThrowAnExceptionIfTheLimitForTransferByDayExceeded() throws OverDrawException, LimitByDayToTransferExceededException {
        AccountInterface account1 = new SavingsAccount(
                1,
                new Client(),
                new Agency()
        );
        account1.deposit(100d);
        account1.addLimit(AccountLimit.LIMIT_BY_DAY_TO_TRANSFER, 4);

        AccountInterface account2 = new SavingsAccount(
                1,
                new Client(),
                new Agency()
        );
        account2.deposit(100d);

        Double amount = 20d;
        this.transferService.execute(amount, account1, account2);
        this.transferService.execute(amount, account1, account2);
        this.transferService.execute(amount, account1, account2);
        this.transferService.execute(amount, account1, account2);

        Assertions.assertThrows(LimitByDayToTransferExceededException.class, ()-> {
            this.transferService.execute(amount, account1, account2);
        });
    }
}

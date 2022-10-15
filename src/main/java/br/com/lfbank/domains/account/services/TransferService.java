package br.com.lfbank.domains.account.services;

import br.com.lfbank.domains.account.entities.AccountInterface;
import br.com.lfbank.domains.account.entities.Transfer;
import br.com.lfbank.domains.account.enums.AccountLimit;
import br.com.lfbank.domains.account.exceptions.LimitByDayToTransferExceededException;
import br.com.lfbank.domains.account.exceptions.OverDrawException;
import br.com.lfbank.domains.account.repositories.TransferRepositoryInterface;

import java.util.List;

public class TransferService {
    private TransferRepositoryInterface transferRepository;

    public TransferService(TransferRepositoryInterface transferRepository) {
        this.transferRepository = transferRepository;
    }

    public Transfer execute(
            Double amount,
            AccountInterface fromAccount,
            AccountInterface toAccount
    ) throws OverDrawException, LimitByDayToTransferExceededException {
        List<Transfer> transfers = this.transferRepository.getTransfersByAccount(fromAccount);
        if(
                !transfers.isEmpty() &&
                transfers.size() >= fromAccount.getLimits().get(AccountLimit.LIMIT_BY_DAY_TO_TRANSFER)
        ){
            throw new LimitByDayToTransferExceededException();
        }

        fromAccount.withDraw(amount);
        toAccount.deposit(amount);
        Transfer transfer = new Transfer(
                fromAccount,
                toAccount,
                amount
        );
        this.transferRepository.save(transfer);
        return transfer;
    }
}

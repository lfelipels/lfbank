package br.com.lfbank.domains.account.services;

import br.com.lfbank.domains.account.entities.AccountInterface;
import br.com.lfbank.domains.account.entities.Transfer;
import br.com.lfbank.domains.account.enums.AccountLimit;
import br.com.lfbank.domains.account.exceptions.LimitByDayToTransferExceededException;
import br.com.lfbank.domains.account.exceptions.OverDrawException;
import br.com.lfbank.domains.account.repositories.TransferRepositoryInterface;

import java.time.LocalDate;
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

        Double limitByDayToTransfer = fromAccount.getLimits().get(AccountLimit.LIMIT_BY_DAY_TO_TRANSFER);
        if(amount > limitByDayToTransfer){
            throw new LimitByDayToTransferExceededException();
        }

        List<Transfer> transfersOfDay = this.transferRepository.getTransfersByAccountAndDate(fromAccount, LocalDate.now());
        if(!transfersOfDay.isEmpty()){
            Double amountDay = transfersOfDay
                    .stream()
                    .mapToDouble((Transfer transfer) -> transfer.getAmount())
                    .sum();
            if(amountDay + amount > limitByDayToTransfer){
                throw new LimitByDayToTransferExceededException();
            }
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

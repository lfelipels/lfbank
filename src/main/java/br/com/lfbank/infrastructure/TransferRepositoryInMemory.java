package br.com.lfbank.infrastructure;

import br.com.lfbank.domains.account.entities.AccountInterface;
import br.com.lfbank.domains.account.entities.Transfer;
import br.com.lfbank.domains.account.repositories.TransferRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class TransferRepositoryInMemory implements TransferRepositoryInterface {

    private List<Transfer> transfers;

    public TransferRepositoryInMemory() {
        transfers = new ArrayList<>();
    }


    public List<Transfer> getTransfersByAccount(AccountInterface account) {
        return transfers.stream()
                .filter((Transfer transfer) -> transfer.getFromAccount().getNumber() == account.getNumber())
                .toList();
    }

    @Override
    public void save(Transfer transfer) {
        this.transfers.add(transfer);
    }
}

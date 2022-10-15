package br.com.lfbank.domains.account.repositories;

import br.com.lfbank.domains.account.entities.AccountInterface;
import br.com.lfbank.domains.account.entities.Transfer;

import java.util.List;

public interface TransferRepositoryInterface {
    public List<Transfer> getTransfersByAccount(AccountInterface account);
    public void save(Transfer transfer);
}

package br.com.lfbank.domains.account.repositories;

import br.com.lfbank.domains.account.entities.AccountInterface;
import br.com.lfbank.domains.account.entities.Transfer;

import java.time.LocalDate;
import java.util.List;

public interface TransferRepositoryInterface {
    public List<Transfer> getTransfersByAccount(AccountInterface account);
    public List<Transfer> getTransfersByAccountAndDate(AccountInterface account, LocalDate date);
    public void save(Transfer transfer);
}

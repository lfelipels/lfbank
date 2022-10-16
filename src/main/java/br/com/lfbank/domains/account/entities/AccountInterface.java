package br.com.lfbank.domains.account.entities;

import br.com.lfbank.domains.account.enums.AccountLimit;
import br.com.lfbank.domains.account.exceptions.OverDrawException;
import br.com.lfbank.domains.client.entities.Client;
import br.com.lfbank.domains.institutional.entities.Agency;

import java.util.Map;

public interface AccountInterface {
    public void active();
    public Boolean isActive();
    public Double getBalance();
    public Client getClient();
    public Integer getNumber();
    public Agency getAgency();
    public void deposit(Double amount);
    public void withDraw(double amount) throws OverDrawException;
    public Map<AccountLimit, Double> getLimits();
    public void addLimit(AccountLimit limitType, Double limit);
}

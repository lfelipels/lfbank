package br.com.lfbank.domains.account.entities;

import br.com.lfbank.domains.client.entities.Client;
import br.com.lfbank.domains.institutional.entities.Agency;

public interface AccountInterface {

    public void active();
    public Boolean isActive();
    public Double getBalance();
    public Client getClient();
    public Integer getNumber();
    public Agency getAgency();
    public void deposit(Double amount);
}

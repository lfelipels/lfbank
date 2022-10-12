package br.com.lfbank.domains.account.entities;

import br.com.lfbank.domains.client.entities.Client;
import br.com.lfbank.domains.institutional.entities.Agency;

public class SavingsAccount extends Account {

    public SavingsAccount(Integer number, Client client, Agency agency) {
        super(number, client, agency);
    }
}

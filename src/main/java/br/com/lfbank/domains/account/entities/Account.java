package br.com.lfbank.domains.account.entities;

import br.com.lfbank.domains.account.enums.AccountStatus;
import br.com.lfbank.domains.client.entities.Client;
import br.com.lfbank.domains.institutional.entities.Agency;

public abstract  class Account implements AccountInterface{

    private Integer number;
    private Client client;
    private Agency agency;
    private Double balance;

    private AccountStatus status;

    protected Account(
        Integer number,
        Client client,
        Agency agency
    ) {
        this.number = number;
        this.client = client;
        this.agency = agency;
        this.balance = 0d;
        this.status = AccountStatus.INACTIVE;
    }

    @Override
    public void deposit(Double amount) throws IllegalArgumentException{
        if(amount <= 0){
            throw new IllegalArgumentException("The amount should be a positive value");
        }
        this.balance += amount;

        if(!this.isActive()){
            this.active();
        }
    }

    @Override
    public void active() {
        this.status = AccountStatus.ACTIVE;
    }

    public Boolean isActive(){
        return this.status == AccountStatus.ACTIVE;
    }

    @Override
    public Integer getNumber() {
        return number;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public Agency getAgency() {
        return agency;
    }

    @Override
    public Double getBalance() {
        return balance;
    }
}

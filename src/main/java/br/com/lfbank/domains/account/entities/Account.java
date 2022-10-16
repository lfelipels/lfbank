package br.com.lfbank.domains.account.entities;

import br.com.lfbank.domains.account.enums.AccountLimit;
import br.com.lfbank.domains.account.enums.AccountStatus;
import br.com.lfbank.domains.account.exceptions.OverDrawException;
import br.com.lfbank.domains.client.entities.Client;
import br.com.lfbank.domains.institutional.entities.Agency;

import java.util.HashMap;
import java.util.Map;

public abstract  class Account implements AccountInterface{

    protected Map<AccountLimit, Double> limits;

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
        this.limits = new HashMap<>();
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
    public void withDraw(double amount) throws OverDrawException {
        if(amount <= 0){
            throw new IllegalArgumentException("The value should be a positive value");
        }
        if(amount > this.balance){
            throw new OverDrawException();
        }
        this.balance -= amount;
    }

    public void addLimit(AccountLimit limitType, Double limit){
        this.limits.put(limitType, limit);
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

    @Override
    public Map<AccountLimit, Double> getLimits() {
        return this.limits;
    }
}

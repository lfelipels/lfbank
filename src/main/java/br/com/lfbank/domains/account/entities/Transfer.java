package br.com.lfbank.domains.account.entities;

import java.time.LocalDateTime;

public class Transfer {
    private AccountInterface fromAccount;
    private AccountInterface toAccount;
    private Double amount;
    private LocalDateTime createdAt;

    public Transfer(AccountInterface fromAccount, AccountInterface toAccount, Double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public AccountInterface getFromAccount() {
        return fromAccount;
    }

    public AccountInterface getToAccount() {
        return toAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

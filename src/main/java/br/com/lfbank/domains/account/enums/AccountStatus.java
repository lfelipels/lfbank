package br.com.lfbank.domains.account.enums;

public enum AccountStatus {
    ACTIVE(1),
    INACTIVE(2);

    private Integer status;

    AccountStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }
}

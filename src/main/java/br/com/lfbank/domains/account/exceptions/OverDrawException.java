package br.com.lfbank.domains.account.exceptions;

public class OverDrawException extends Exception{
    public OverDrawException() {
        super("You try to withdraw more money than you have in your account");
    }
}

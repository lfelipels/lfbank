package br.com.lfbank.domains.account.exceptions;

public class LimitByDayToTransferExceededException extends Exception {
    public LimitByDayToTransferExceededException() {
        super("The limit by day for tranferency exceeded");
    }
}

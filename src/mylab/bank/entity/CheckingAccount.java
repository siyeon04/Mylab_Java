package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {
    private double withdrawalLimit; // 1회 출금 한도

    public CheckingAccount(String accountNumber, String ownerName, double balance, double withdrawalLimit) {
        super(accountNumber, ownerName, balance);
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() { return withdrawalLimit; }
    public void setWithdrawalLimit(double withdrawalLimit) { this.withdrawalLimit = withdrawalLimit; }
    
    // 체킹계좌 출금 : 한도 초과 체크 -> 잔액 부족 체크
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        // 한도 초과 체크
    	if (amount > withdrawalLimit) throw new WithdrawalLimitExceededException(withdrawalLimit);
        // 잔액 부족 체크
    	if (amount > getBalance()) throw new InsufficientBalanceException("잔액이 부족합니다.");
        setBalance(getBalance() - amount);
        System.out.println(amount + "원이 출금되었습니다. 현재 잔액: " + getBalance() + "원");
    }

    @Override
    public String toString() {
        return "계좌번호: " + getAccountNumber() + ", 소유자: " + getOwnerName()
                + ", 잔액: " + getBalance() + "원, 출금 한도: " + withdrawalLimit + "원";
    }
}
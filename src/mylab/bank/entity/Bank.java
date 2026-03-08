package mylab.bank.entity;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;
    private int nextAccountNumber;

    public Bank() {
        accounts = new ArrayList<>();
        nextAccountNumber = 1000;
    }

    private String generateAccountNumber() {
        return "AC" + nextAccountNumber++;
    }

    public SavingsAccount createSavingsAccount(String ownerName, double initialBalance, double interestRate) {
        SavingsAccount acc = new SavingsAccount(generateAccountNumber(), ownerName, initialBalance, interestRate);
        accounts.add(acc);
        System.out.println("Saving(저축) 계좌가 생성되었습니다: " + acc);
        return acc;
    }

    public CheckingAccount createCheckingAccount(String ownerName, double initialBalance, double withdrawalLimit) {
        CheckingAccount acc = new CheckingAccount(generateAccountNumber(), ownerName, initialBalance, withdrawalLimit);
        accounts.add(acc);
        System.out.println("체킹 계좌가 생성되었습니다: " + acc);
        return acc;
    }

    // 계좌 조회: 없으면 예외 처리
    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account a : accounts)
            if (a.getAccountNumber().equals(accountNumber)) return a;
        throw new AccountNotFoundException(accountNumber);
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        findAccount(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        findAccount(accountNumber).withdraw(amount);
    }
    
    // 이체 = 출금 + 입금 순으로 처리
    // 출금에서 예외 발생 -> 입금 실행 X
    public void transfer(String fromNumber, String toNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account from = findAccount(fromNumber);
        Account to = findAccount(toNumber);
        from.withdraw(amount); // 출금
        to.deposit(amount); // 출금 성공 시 입금
        System.out.println(amount + "원이 " + fromNumber + "에서 " + toNumber + "로 송금되었습니다.");
    }

    public void printAllAccounts() {
        System.out.println("=== 모든 계좌 목록 ===");
        for (Account a : accounts) System.out.println(a);
        System.out.println("===================");
    }
}
package mylab.bank.control;

import mylab.bank.entity.*;
import mylab.bank.exception.*;

public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();

        System.out.println("=== 계좌 생성 ===");
        SavingsAccount sa1 = bank.createSavingsAccount("홍길동", 10000, 3.0);
        CheckingAccount ca1 = bank.createCheckingAccount("김철수", 20000, 5000);
        SavingsAccount sa2 = bank.createSavingsAccount("이영희", 30000, 2.0);

        System.out.println();
        bank.printAllAccounts();

        try {
            System.out.println("\n=== 입금/출금 테스트 ===");
            bank.deposit(sa1.getAccountNumber(), 5000);
            bank.withdraw(ca1.getAccountNumber(), 3000);

            System.out.println("\n=== 이자 적용 테스트 ===");
            sa1.applyInterest();

            System.out.println("\n=== 계좌 이체 테스트 ===");
            bank.transfer(sa2.getAccountNumber(), ca1.getAccountNumber(), 5000);

            System.out.println();
            bank.printAllAccounts();

            // 예외 테스트
            bank.withdraw(ca1.getAccountNumber(), 6000);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            bank.transfer(ca1.getAccountNumber(), sa1.getAccountNumber(), 6000);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            bank.findAccount("AC9999");
        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}
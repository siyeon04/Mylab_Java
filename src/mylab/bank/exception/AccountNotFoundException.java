package mylab.bank.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String accountNumber) {
        super("계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
    }
}
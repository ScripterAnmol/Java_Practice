package week2;

import week2.helperFunction.BankAccount;

public class question1 {

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount();

        account1.AccountBalance();
        account2.AccountBalance();
    }
}

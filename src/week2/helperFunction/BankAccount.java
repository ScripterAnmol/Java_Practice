package week2.helperFunction;

public class BankAccount{
    private int balance;
    public BankAccount(){
        this(0);

    }
    public BankAccount(int balance){
        this.balance=balance;
    }
    public void AccountBalance(){
        System.out.println("Account Balance is: "+this.balance);
    }
}

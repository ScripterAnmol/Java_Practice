package week2.helperFunction;

public class BankAccount{
    private int balance;
    public BankAccount(){
        this.balance=0;
    }
    public BankAccount(int bal){
        this.balance=bal;
    }
    public void AccountBalance(){
        System.out.println("Account Balance is: "+this.balance);
    }
}

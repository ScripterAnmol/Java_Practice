package week2;

import week2.helperFunction.Developer;
import week2.helperFunction.Manager;

public class question6 {
    public static void main(String[] args) {
        Manager alice = new Manager("Alice", 80000);

        Developer bob = new Developer("Bob", 60000);

        alice.calculateSalary();
        bob.calculateSalary();
    }
}

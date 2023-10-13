package week2.helperFunction;

public class Developer extends Employee{
    public Developer(String name, double salary) {
        super(name, salary);
    }
    @Override
    public void calculateSalary() {
        System.out.println("Calculating salary of Developer");
    }
}

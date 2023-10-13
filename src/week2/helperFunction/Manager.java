package week2.helperFunction;

public class Manager extends Employee{
    public Manager(String name, double salary) {
        super(name, salary);
    }

    @Override
    public void calculateSalary(){
        System.out.println("Calculating salary of Manager");
    }
}

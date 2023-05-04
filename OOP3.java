package OOP3;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class OOP3 {



 

    public static void main(String[] args) {

        Worker worker = new Worker("Анатолий", "Шестаков", 80000);
        System.out.println(worker);

        Employee[] employees = new Employee[10];
        for (int i = 0; i < employees.length; i++)
            employees[i] = generateEmployee();

        for (Employee employee : employees){
            System.out.println(employee);
        }

        //Arrays.sort(employees, new NameComparator());
        Arrays.sort(employees);

        System.out.printf("\n*** Отсортированный массив сотрудников ***\n\n");

        for (Employee employee : employees){
            System.out.println(employee);
        }

    }

}

class SalaryComparator implements Comparator<Employee> {


    @Override
    public int compare(Employee o1, Employee o2) {

        //return o1.calculateSalary() == o2.calculateSalary() ? 0 : (o1.calculateSalary() > o2.calculateSalary() ? 1 : -1);
        return Double.compare( o2.calculateSalary(), o1.calculateSalary());
    }
}

class NameComparator implements Comparator<Employee> {


    @Override
    public int compare(Employee o1, Employee o2) {
        //return o1.calculateSalary() == o2.calculateSalary() ? 0 : (o1.calculateSalary() > o2.calculateSalary() ? 1 : -1);
        int res = o1.name.compareTo(o2.name);
        if (res == 0){
            res = Double.compare( o2.calculateSalary(), o1.calculateSalary());
        }
        return res;
    }
}

abstract class Employee implements Comparable<Employee>{

    protected String name;
    protected String surName;
    protected double salary;

    public Employee(String name, String surName, double salary) {
        this.name = name;
        this.surName = surName;
        this.salary = salary;
    }

    public abstract  double calculateSalary();

    @Override
    public String toString() {
        return String.format("Сотрудник: %s %s; Среднемесячная заработная плата: %.2f", name, surName, salary);
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare( o.calculateSalary(), calculateSalary());
    }
}

class Worker extends Employee{

    public Worker(String name, String surName, double salary) {
        super(name, surName, salary);
    }

    @Override
    public double calculateSalary() {
        return salary ;
        //TODO: Для фрилансера - return 20 * 5 * salary
    }

    @Override
    public String toString() {
        return String.format("%s %s; Рабочий; Среднемесячная заработная плата (фиксированная месячная оплата): %.2f (руб.)", name, surName, salary);
    }
}

/**
 * TODO: 1. Доработать самостоятельно в рамках домашней работы
 */
class Freelancer extends Employee{

    public Freelancer(String name, String surName, double salary) {
        super(name, surName, salary);
    }

    @Override
    public double calculateSalary() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%-15s: %-10s%-10s | Возраст: %-3d| Ставка в  час : %-7.2f |" +
                        " Всего часов: %-3d | З/п в месяц: %.2f"
                ,getClassEmployee(),name,lastName,old,salary,hours,calculateSalary());
    }
    public String getClassEmployee(){
        return "Freelancer";
    }


    public class Generator {
        static String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Miller"
                , "Davis", "Garcia", "Rodriguez", "Martinez", "Hernandez", "Lopez"
                , "Gonzalez", "Perez", "Taylor"};
        static String[] firstNames = {"Michael", "Christopher", "Matthew", "Joshua", "Daniel"
                , "David", "James", "Joseph", "John", "Nicholas", "Andrew", "Brandon",
                "Tyler", "William", "Ryan", "Jessica", "Ashley", "Emily", "Samantha", "Amanda",
                "Brittany", "Megan", "Stephanie", "Jennifer", "Elizabeth", "Lauren",
                "Nicole", "Rachel", "Hannah", "Katty"};
    
    
    
        public static Employee randomGenerate(){
            Random random1 = new Random();
            Random random = new Random();
            int index = random1.nextInt(2);
            if(index == 0){
                return new Worker(firstNames[random.nextInt(30)],
                        lastNames[random.nextInt(15)], random.nextInt(18,55),
                        random.nextDouble(5000, 10000), random.nextInt(21));
            }
            else {
                return new Freelancer(firstNames[random.nextInt(30)],
                        lastNames[random.nextInt(15)],random.nextInt(18,55),
                        random.nextDouble(500, 5000), random.nextInt(160));
            }
        }
    }

    public class SortOfSalary implements Comparator<Employee>{

        @Override
        public int compare(Employee o1, Employee o2) {
            return Double.compare(o1.calculateSalary(),o2.calculateSalary());
        }
        @Override
        public Comparator<Employee> reversed() {
            return Comparator.super.reversed();
        }
    }
}

    


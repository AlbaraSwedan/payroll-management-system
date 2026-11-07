import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PayrollSystem {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new FullTimeEmployee(
                "E01", "Ali", 5000, LocalDate.of(2021, 5, 10), 1000));
        employees.add(new ContractorEmployee(
                "E02", "Sara", LocalDate.of(2020, 2, 15), 40, 120));
        employees.add(new FullTimeEmployee(
                "E03", "Omar", 4000, LocalDate.of(2023, 1, 20), 500));
        employees.add(new ContractorEmployee(
                "E04", "Mona", LocalDate.of(2019, 8, 1), 50, 100));

        System.out.println("Employees sorted by net salary (descending):");
        Collections.sort(employees);
        printEmployees(employees);

        System.out.println("\nEmployees sorted by join date (oldest first):");
        Comparator<Employee> byJoinDate =
                Comparator.comparing(Employee::getJoinDate);
        employees.sort(byJoinDate);
        printEmployees(employees);

        double totalTax = 0;
        double totalBonus = 0;
        double performanceScore = 4.5;

        for (Employee employee : employees) {
            totalTax += employee.calculateTax();
            totalBonus += employee.calculateBonus(performanceScore);
        }

        System.out.println("\nTotal tax: " + totalTax);
        System.out.println("Total bonus: " + totalBonus);
    }

    public static void printEmployees(ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}

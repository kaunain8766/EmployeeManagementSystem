import java.io.*;
import java.util.*;

class EmployeeManagement {

    static final String FILE_NAME = "employees.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int choice;

        do {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    // CREATE
    static void addEmployee() throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME, true);

        System.out.print("Enter ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Salary: ");
        String salary = sc.nextLine();

        fw.write(id + "," + name + "," + salary + "\n");
        fw.close();

        System.out.println("Employee added successfully ✅");
    }

    // READ
    static void viewEmployees() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String line;

        System.out.println("\nID\tName\tSalary");
        System.out.println("------------------------");

        while ((line = br.readLine()) != null) {
            String[] emp = line.split(",");
            System.out.println(emp[0] + "\t" + emp[1] + "\t" + emp[2]);
        }
        br.close();
    }

    // UPDATE
    static void updateEmployee() throws IOException {
        System.out.print("Enter Employee ID to update: ");
        String id = sc.nextLine();

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        FileWriter fw = new FileWriter(tempFile);

        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {
            String[] emp = line.split(",");
            if (emp[0].equals(id)) {
                found = true;
                System.out.print("Enter New Name: ");
                String name = sc.nextLine();
                System.out.print("Enter New Salary: ");
                String salary = sc.nextLine();
                fw.write(id + "," + name + "," + salary + "\n");
            } else {
                fw.write(line + "\n");
            }
        }

        br.close();
        fw.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);

        System.out.println(found ? "Employee updated ✅" : "Employee not found ❌");
    }

    // DELETE
    static void deleteEmployee() throws IOException {
        System.out.print("Enter Employee ID to delete: ");
        String id = sc.nextLine();

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        FileWriter fw = new FileWriter(tempFile);

        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {
            String[] emp = line.split(",");
            if (!emp[0].equals(id)) {
                fw.write(line + "\n");
            } else {
                found = true;
            }
        }

        br.close();
        fw.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);

        System.out.println(found ? "Employee deleted 🗑️" : "Employee not found ❌");
    }
}

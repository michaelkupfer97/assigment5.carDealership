package carDealership;
//Assignment: 5
//Author: Michael Kupfer , ID: 209493246

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simple car dealership program that manages employees and cars.
 */
public class carDealership {
    /**
     * Sorts an ArrayList of comparable objects in ascending order.
     * @param array the ArrayList to be sorted
     * @param <T>   the type of objects in the ArrayList (must implement Comparable)
     */
    public static <T extends Comparable<T>> void sort(ArrayList<T> array) {
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.size() - 1; j++) {
                if (array.get(j).compareTo(array.get(j + 1)) == -1) {
                    T tmp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, tmp);
                }
            }
        }
    }

    /**
     * The main method of the car dealership program.
     * @throws IOException if an error occurs while reading files
     */
    public static void main(String[] args) throws  IOException {
        ArrayList<Car> carArray = readCarFromFile();
        ArrayList<Employee> empArray = readEmployeeFromFile();

        Scanner scan = new Scanner(System.in);
        menu choice = null;
        while (choice != menu.END_PROGRAM) {
            printMenu();
            int userInput = scan.nextInt();
            switch (userInput) {
                case 1:
                    choice = menu.DISPLAY_EMPLOYEES;
                    sort(empArray);
                    for (Object emp : empArray) {
                        System.out.println(emp);
                    }
                    break;

                case 2:
                    choice = menu.DISPLAY_AVAILABLE_CARS;
                    File file = new File("src/carDealership/CarDealership.txt");
                    Scanner inputAvlCars= new Scanner(file);
                    while (inputAvlCars.hasNext()) {
                        String str = inputAvlCars.nextLine();
                        System.out.println(str);
                    }
                    break;

                case 3:
                    choice = menu.SELL_CAR;
                    //print the employees
                    for (int i=0;i<empArray.size();i++){
                        System.out.println("first name: "+empArray.get(i).getFirstname()+"id: "+(empArray.get(i)).getId());
                    }
                    //get the employee
                    Employee emp=pickEmployee(scan,empArray);
                    //print the cars
                    File file1 = new File("src/carDealership/CarDealership.txt");
                    Scanner inputAvlCars1= new Scanner(file1);
                    while (inputAvlCars1.hasNext()) {
                        String str = inputAvlCars1.nextLine();
                        System.out.println(str);
                    }
                    //get the car
                    Car car=pickCar(scan, carArray);
                    //selling the car with chosen employee
                    emp.carSell(car);
                    break;

                case 4:
                    choice = menu.ADD_CAR;
                    ArrayList<Object> carDetails = getCarDetails(scan);
                    try {
                        Car newCar = new Car((String) carDetails.get(0), (int) carDetails.get(1), (double) carDetails.get(2), (int) carDetails.get(3), (String) carDetails.get(4));
                        carArray.add(newCar);
                    } catch (Exception e) {
                        System.out.println("Warning Invalid data, failed to add car to the dealership");
                    }
                    break;

                case 5:
                    choice = menu.END_PROGRAM;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    /**
     * Prints the menu options to the console.
     */
    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Display Employees");
        System.out.println("2. Display Available Cars");
        System.out.println("3. Sell Car");
        System.out.println("4. Add Car");
        System.out.println("5. End Program");
        System.out.print("Enter your choice: ");
    }
    /**
     * Allows the user to select an employee from the provided ArrayList of employees.
     * @param scan      the Scanner object to read user input
     * @param employees the ArrayList of employees
     * @return the selected Employee object
     */
    private static Employee pickEmployee(Scanner scan, ArrayList<Employee> employees) {
        while (true) {
            System.out.println("Pick employee ID: ");
            int ID = scan.nextInt();
            System.out.println(ID);
            for (Employee emp : employees) {
                if (emp.getId()==ID) {
                    return emp;
                }
            }
            System.out.println("Employee ID wasn't found.\nPlease enter an employee ID: ");
        }
    }
    /**
     * Allows the user to select a car from the provided ArrayList of cars.
     * @param scan the Scanner object to read user input
     * @param cars the ArrayList of cars
     * @return the selected Car object
     */
    private static Car pickCar(Scanner scan, ArrayList<Car> cars) {
        while (true) {
            System.out.println("Pick car number: ");
            String carNum = scan.next();
            for (Car car : cars) {
                if (car.getLicensePlate().equals(carNum)) {
                    return car;
                }
            }
            System.out.println("Car number wasn't found.\nPlease enter a car number: ");
        }
    }
    /**
     * Prompts the user to enter car details and returns them as an ArrayList.
     *
     * @param scan the Scanner object to read user input
     * @return an ArrayList containing the car details entered by the user
     */
    private static ArrayList<Object> getCarDetails(Scanner scan) {
        ArrayList<Object> carDetails = new ArrayList<>();

        System.out.println("Enter car maker: ");
        String carMaker = scan.next();

        System.out.println("Enter km: ");
        int km = scan.nextInt();

        System.out.println("Enter price: ");
        double price = scan.nextDouble();

        System.out.println("Enter car year made: ");
        int yearMade = scan.nextInt();

        System.out.println("Enter car licensePlate: ");
        String licensePlate = scan.next();

        carDetails.add(carMaker);
        carDetails.add(km);
        carDetails.add(price);
        carDetails.add(yearMade);
        carDetails.add(licensePlate);

        return carDetails;
    }
    /**
     * Retrieves the path to the file containing available car information.
     * @return the Path object representing the file path
     */
    private static Path getAvailableCarsPath() {
        return Paths.get("src/carDealership/CarDealership.txt");
        }
    /**
     * Reads car information from a file and returns an ArrayList of Car objects.
     * @return an ArrayList of Car objects read from the file
     * @throws IOException if an error occurs while reading the file
     */
    private static ArrayList<Car> readCarFromFile() throws IOException {
        List<String> lines = Files.readAllLines(getAvailableCarsPath());
        ArrayList<Car> availableCars = new ArrayList<>();
        for (String line : lines) {
            String[] word = line.split("\\s+");
            try {
                Car car = new Car( word[0], Integer.parseInt(word[1]), Double.parseDouble(word[2]), Integer.parseInt(word[3]), (word[4]));
                availableCars.add(car);
            } catch (Exception e) {
                System.out.println("Warning Invalid data, the car hasn't been added to the car dealership");
            }
        }
        return availableCars;
    }
    /**
     * Retrieves the path to the file containing employee information.
     * @return the Path object representing the file path
     */
    private static Path getEmployeesPath() {
        return Paths.get("src/carDealership/Employee.txt");
    }
    /**
     * Reads employee information from a file and returns an ArrayList of Employee objects.
     * @return an ArrayList of Employee objects read from the file
     * @throws IOException if an error occurs while reading the file
     */
    private static ArrayList<Employee> readEmployeeFromFile() throws IOException {
        List<String> lines = Files.readAllLines(getEmployeesPath());
        ArrayList<Employee> employees = new ArrayList<>();
        for (String line : lines) {
            String[] word = line.split("\\s+");
            try {
                Employee employee = new Employee((String) word[0], Integer.parseInt(word[1]), Integer.parseInt(word[2]));
                employees.add(employee);
            } catch (Exception e) {
                System.out.println("Warning Invalid data, the employee hasn't been added to the car dealership");
            }
        }
        return employees;
    }

}

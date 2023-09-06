package carDealership;
//Assignment: 5
//Author: Michael Kupfer , ID: 209493246
import java.io.IOException;
/**
 * Represents an Employee in a car dealership.
 * Implements the Comparable interface to enable sorting based on the number of car sells.
 */
public class Employee implements Comparable{

    private String firstname;
    private int id;
    private int numOfSells;
    /**
     * Constructs an Employee object with the specified details.
     * @param firstname   the first name of the employee
     * @param id          the ID of the employee
     * @param numOfSells  the number of car sells by the employee
     * @throws InvalidValueException if the provided values are invalid
     */
    public Employee(String firstname, int id, int numOfSells) throws InvalidValueException {
        //check if first name made only from letters.
        if (firstname.matches("[a-zA-Z]+"))
            this.firstname = firstname;
        else
            throw new InvalidValueException("Invalid value provided(need to be only characters): " + firstname);
        //converting id to string and check its length.
       String numId=Integer.toString(id);
        if (numId.length()==9)
            this.id = id;
        else
            throw new InvalidValueException("Invalid value provided(need to be 9 numbers): " + id);
        //check if num of sells is positive.
        if (numOfSells < 0)
            throw new InvalidValueException("Invalid value provided(cant be negative): " + numOfSells);
        else
            this.numOfSells = numOfSells;
    }
    /**
     * Sells a car and increments the number of car sells by the employee.
     * @param car the car to be sold
     * @throws IOException if an error occurs while selling the car
     */
    public void carSell(Car car)throws IOException {
        car.sellCar();
        numOfSells++;
    }
    /**
     * Calculates the salary of the employee based on the number of car sells.
     * @return the salary of the employee
     */
    public double salaryCalc(){
        return 6000+100*numOfSells;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + firstname + '\'' +
                ", id=" + id +
                ", numOfSells=" + numOfSells +
                ", salary=" + salaryCalc() +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Employee other = (Employee) o;
        if (numOfSells == other.numOfSells) return 0;
        if (numOfSells < other.numOfSells) return -1;
        return 1;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfSells() {
        return numOfSells;
    }

    public void setNumOfSells(int numOfSells) {
        this.numOfSells = numOfSells;
    }
}

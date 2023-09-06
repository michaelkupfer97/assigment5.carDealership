package carDealership;
//Assignment: 5
//Author: Michael Kupfer , ID: 209493246
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

/**
 * Represents a car in a car dealership.
 */
public class Car {
    private String licensePlate;
    private int yearMade;
    private String maker;
    private int km;
    private double price;

    /**
     * Constructs a new Car object.
     * @param licensePlate The license plate of the car.
     * @param yearMade     The year the car was made.
     * @param maker        The car maker.
     * @param km           The kilometers driven by the car.
     * @param price        The price of the car.
     * @throws InvalidValueException If any of the provided values are invalid.
     */
    public Car(String maker,  int km , double price, int yearMade, String licensePlate) throws InvalidValueException {
        if (licensePlate.length() != 6)
            throw new InvalidValueException("Invalid value provided(need to be 6 characters): " + licensePlate);
        else
            this.licensePlate = licensePlate;
        if (yearMade <= 2016)
            throw new InvalidValueException("Invalid value provided(need to be 2017+): " + yearMade);
        else
            this.yearMade = yearMade;
        if (km < 0)
            throw new InvalidValueException("Invalid value provided(cant be negative): " + km);
        else
            this.km = km;
        if (price <= 0)
            throw new InvalidValueException("Invalid value provided(cant be negative): " + price);
        else
            this.price = price;
        this.maker = maker;
    }
    /**
     * Returns a string representation of the car.
     * @return The car's details as a string.
     */
    @Override
    public String toString() {
        return  licensePlate + ' ' + yearMade + ' ' + maker + ' ' + km + ' ' + price ;
    }
    /**
     * Applies a discount to the car's price.
     * @param percentage The discount percentage.
     * @throws InvalidValueException If the provided discount percentage is invalid or too high, above 5k discount.
     */
    public void carDiscount(int percentage) throws InvalidValueException {
        if (percentage < 0)
            throw new InvalidValueException("Invalid value provided(cant be negative): " + percentage);
        if (((price / 100) * percentage) > 5000)
            throw new InvalidValueException("Invalid value provided(cant be over 5000): " + percentage);
        price = (price / 100) * (100 - percentage);
    }
    /**
     * Writes the car's details to the soldCars.txt file in the carDealership package.
     * @throws IOException If an I/O error occurs while writing the file.
     */
    public void sellCar() throws IOException {
        Path carDealershipPath = Path.of("carDealership/carDealership.txt");
        Path soldCarsPath = Path.of("carDealership/Sold.txt");

        List<String> carDealershipLines = Files.readAllLines(carDealershipPath);
        String soldCarDetails = this.toString();

        carDealershipLines.removeIf(line -> line.equals(soldCarDetails));

        Files.write(carDealershipPath, carDealershipLines);

        Files.writeString(soldCarsPath, soldCarDetails + "\n", StandardOpenOption.APPEND);
    }
    /**
     * Checks if the car is equal to another object.
     * @param o The object to compare with.
     * @return true if the car is equal to the other object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return yearMade == car.yearMade && km == car.km && Double.compare(car.price, price) == 0 && Objects.equals(licensePlate, car.licensePlate) && Objects.equals(maker, car.maker);
    }

    /**
     * Getters and Setters of the class
     */

    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public int getYearMade() {
        return yearMade;
    }
    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }
    public String getMaker() {
        return maker;
    }
    public void setMaker(String maker) {
        this.maker = maker;
    }
    public int getKm() {
        return km;
    }
    public void setKm(int km) {
        this.km = km;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}

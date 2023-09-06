package carDealership;
/**
 * Custom exception class for representing invalid values in the car dealership system.
 */
//Assignment: 5
//Author: Michael Kupfer , ID: 209493246
public class InvalidValueException extends Exception{
    /**
     * Constructs an InvalidValueException with the specified error message.
     * @param message the error message
     */
    public InvalidValueException(String message) {
        super(message);
    }
}

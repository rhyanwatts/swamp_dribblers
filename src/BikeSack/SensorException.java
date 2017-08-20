package BikeSack;

/**
 * Exception that is thrown when the Sensor is set to an invalid value
 * 
 * @author DevTeam1
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SensorException extends Exception {

    /**
     * Create a new <code>SensorException</code> with the defined message
     * 
     * @param message
     *            The error message to contain in the exception
     * @see Exception
     */
    public SensorException(String message) {
        super(message);
    }
}

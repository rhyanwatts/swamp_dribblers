package BikeSack;

/**
 * Exception that is thrown when the Sensor is set to an invalid value
 * @author SwampDribblers
 */
@SuppressWarnings("serial")
public class SensorException extends Exception {

	/**
	 * Create a new <code>SensorException</code> with the defined message
	 * @param message The message to contain in the Exception
	 * @see Exception
	 */
	public SensorException(String message) {
		super(message);
	}
}

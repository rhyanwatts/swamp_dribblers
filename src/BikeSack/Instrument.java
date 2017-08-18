package BikeSack;

/**
 * Provides the user-facing representation of the status of a Sensor
 * @see Sensor
 * @author SwampDribblers
 */
public class Instrument {

	/**
	 * Holds the current value of the <code>Instrument</code>
	 */
	private int current;

	/**
	 * Creates an Instrument with a <code>current</code> value of 0
	 * @see #current
	 */
	public Instrument() {
		current = 0;
	}

	/**
	 * Creates an Instrument with the specified <code>current</code> value
	 * @param current The value to set the Sensor to
	 * @see #current
	 */
	public void setCurrent(int current) {
		this.current = current;
	}

	/**
	 * @return The current value of the Instrument
	 * @see #current
	 */
	public int getCurrent() {
		return current;
	}

    /**
     * Determines whether the Instrument is considered to be in an "on" state
     * @return true if current {@literal >} 0, otherwise false
     * @see #current
     */
    public boolean isOn() {
        if(current > 0) {
            return true;
        }
        return false;
    }
}

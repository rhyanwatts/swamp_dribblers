package BikeSack;

/**
 * The Sensors provide data to the BikeSack system about the current physical
 * state of the motorbike. These are currently manipulated via keyboard input,
 * but could also be connected to real sensors.
 * 
 * @author DevTeam1
 * @version 1.0
 */
public class Sensor {
    /**
     * Controls the amount that the value of the sensor will change by when using
     * the increase() or decrease() methods
     */
    public static final int INCREMENT = 5;

    private int min;
    private int max;
    private int current = min;
    private int warning = max;
    private int defaultValue = warning;

    /**
     * @param min
     *            The minimum value the Sensor can read
     * @param max
     *            The maximum value the Sensor can read
     */
    public Sensor(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * @param min
     *            The minimum value the Sensor can read
     * @param max
     *            The maximum value the Sensor can read
     * @param warning
     *            The value where a Sensor is above recommended operation
     */
    public Sensor(int min, int max, int warning) {
        this(min, max);
        this.warning = warning;
    }

    /**
     * @param min
     *            The minimum value the Sensor can read
     * @param max
     *            The maximum value the Sensor can read
     * @param warning
     *            The value where a Sensor is above recommended operation
     * @param defaultValue
     *            The value a Sensor should return when the current value is not
     *            available (real world sensor failure)
     */
    public Sensor(int min, int max, int warning, int defaultValue) {
        this(min, max, warning);
        this.defaultValue = defaultValue;
    }

    /**
     * @return The minimum value the Sensor can read
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min
     *            The minimum value the Sensor can read
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return The maximum value the Sensor can read
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max
     *            The maximum value the Sensor can read
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return The current value of the Sensor
     */
    public int getCurrent() {
        return current;
    }

    /**
     * @param current
     *            The current value of the Sensor
     * @throws SensorException
     *             If new current value is outside Sensor min/max range
     */
    public void setCurrent(int current) throws SensorException {
        if (current >= getMin() && current <= getMax()) {
            this.current = current;
        } else {
            throw new SensorException("Sensor set outside range (min:" + min + ", max:" + max + ", set:" + current
                    + "). Sensor value stayed at " + getCurrent());
        }
    }

    /**
     * @return The value where a Sensor is above recommended operation
     */
    public int getWarning() {
        return warning;
    }

    /**
     * @param warning
     *            The value where a Sensor is above recommended operation
     */
    public void setWarning(int warning) {
        this.warning = warning;
    }

    /**
     * @return The value a Sensor should return when the current value is not
     *         available (real world sensor failure)
     */
    public int getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue
     *            The value a Sensor should return when the current value is not
     *            available (real world sensor failure)
     */
    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Toggles a Sensor between minimum (off) and maximum (on) values. If a Sensor
     * is anywhere above the minimum value, it will set it to minimum. Otherwise the
     * Sensor will be set to maximum.
     * 
     * @throws SensorException
     *             If value is attempted to be set outside min/max range
     */
    public void toggle() throws SensorException {
        if (getCurrent() > getMin()) {
            setCurrent(getMin());
        } else {
            setCurrent(getMax());
        }
    }

    /**
     * Toggles a Sensor between minimum (off) and specified (on) value. If a Sensor
     * is anywhere above the minimum value, it will set it to minimum. Otherwise the
     * Sensor will be set to the specified value.
     * 
     * @param onValue
     *            The value that will be considered as "on" when toggling
     * @throws SensorException
     *             If value is attempted to be set outside min/max range
     */
    public void toggle(int onValue) throws SensorException {
        if (getCurrent() > getMin()) {
            setCurrent(getMin());
        } else {
            setCurrent(onValue);
        }
    }

    /**
     * Increase the current Sensor value by INCREMEMENT amount.
     * 
     * @throws SensorException
     *             If the value of the sensor is increased past the Sensor's maximum
     *             value
     */
    public void increase() throws SensorException {
        setCurrent(getCurrent() + INCREMENT);
    }

    /**
     * Decrease the current Sensor value by INCREMEMENT amount.
     * 
     * @throws SensorException
     *             If the value of the sensor is decreased past the Sensor's minimum
     *             value
     */
    public void decrease() throws SensorException {
        setCurrent(getCurrent() - INCREMENT);
    }

}
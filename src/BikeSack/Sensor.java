package BikeSack;

public class Sensor {
	public static final int INCREMENT = 5;

	private int min;
	private int max;
	private int current = min;
	private int warning = max;
	private int defaultValue = warning;

	public Sensor(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public Sensor(int min, int max, int warning) {
		this.min = min;
		this.max = max;
		this.warning = warning;
	}

	public Sensor(int min, int max, int warning, int defaultValue) {
		this.min = min;
		this.max = max;
		this.warning = warning;
		this.defaultValue = defaultValue;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) throws SensorException {
		if (current >= min && current <= max) {
			this.current = current;
		} else {
			throw new SensorException("Sensor set outside range (min:" + min + ", max:" + max + ", set:" + current
					+ "). Sensor value stayed at " + this.current);
		}
	}

	public int getWarning() {
		return warning;
	}

	public void setWarning(int warning) {
		this.warning = warning;
	}

	public int getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(int defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void toggle() throws SensorException {
		if (getCurrent() > min) {
			setCurrent(min);
		} else {
			setCurrent(max);
		}
	}

	// Toggle with a custom on value
	public void toggle(int onValue) throws SensorException {
        if (getCurrent() > min) {
            setCurrent(min);
        } else {
            setCurrent(onValue);
        }
    }
	
	public void increase() throws SensorException {
		setCurrent(getCurrent() + INCREMENT);
	}

	public void decrease() throws SensorException {
		setCurrent(getCurrent() - INCREMENT);
	}

}
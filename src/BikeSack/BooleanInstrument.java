package BikeSack;
/**
 * The BooleanInstrument Class extends BikeSack's abstract {@link Instrument}
 * 
 * This class allows for an {@link Instrument} with a Boolean value. 
 * 
 * <h2>Useful Methods include:</h2>
 * {@code setCurrent()} Sets the current value to a value of 0 or 1 to represent a boolean value or throw 
 * exception if not trying to set value that does not represent boolean values
 *  
 * {@code toString()} Returns a "On" for value of 1 or "Off" for value of 0
 * 
 * @author DevTeam
 * @since 9/8/2017
 * @version 0.1
 */
public class BooleanInstrument extends Instrument {

	private int min;
	private int max;

	public BooleanInstrument() {
		min = 0;
		max = 1;
	}

	public void setCurrent(int current) {
		if (current == min) {
			super.setCurrent(current);
		} else if (current == max) {
			super.setCurrent(current);
		} else
			try {
				throw new SensorException("Not Boolean Value");
			} catch (SensorException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public String toString() {
		int current = super.getCurrent();
		if (current == min) {
			return "Off";
		} else if (current == max) {
			return "On";
		}
		return "Error";
	}
}

package BikeSack;
/**
 * <h1>Bike Sack HeadlightInstrument Class</h1> The Headlight Instrument Class extends
 * BikeSack's 'Instrument'
 * 
 * This class allows for an instrument with a Boolean value. 
 * 
 * <h2>Useful Methods include:
 * <h2>setCurrent() Sets the current value to a value of 0 or 1 to represent a boolean value or throw 
 * exception if not trying to set value that does not represent boolean values
 *  
 * toString() Returns a "High" for value of 1 or "Low" for value of 0
 * 
 * @author 
 * @since 9/8/2017
 * @version 0.1
 */
public class HeadlightInstrument extends BooleanInstrument {

	private int min;
	private int max;

	public HeadlightInstrument() {
		min = 0;
		max = 1;
	}
	//Override toString for headlight status
	@Override
	public String toString() {
		int current = super.getCurrent();
		if (current == min) {
			return "Low";
		} else if (current == max) {
			return "High";
		}
		return "Error";
	}
}

package BikeSack;
/**
 * <h1>Bike Sack HeadlightInstrument Class</h1> The Headlight Instrument Class extends
 * BikeSack's 'Instrument'
 * 
 * This class allows for an instrument with a Boolean value with status of low and high. 
 * 
 * <h2>Useful Methods include:</h2>
 *  
 * {@code toString()} Returns a "High" for value of 1 or "Low" for value of 0
 * 
 * @author DevTeam2
 * @since 9/8/2017
 * @version 0.1
 */
public class HeadlightInstrument extends BooleanInstrument {

	private int min;
	private int max;
	
	/**
	 * Construct a new headlightInstrument object with Min = 0 and Max = 1
	 */
	public HeadlightInstrument() {
		min = 0;
		max = 1;
	}
	/**
	 * Return a string to represent the current value of the HeadlightInstrument
	 * Low when current = 0
	 * High when current = 1
	 */
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

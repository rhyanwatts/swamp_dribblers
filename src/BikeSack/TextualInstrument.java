package BikeSack;

/**
 * Provides an <code>Instrument</code> which as oriented around displaying text.
 * @see Instrument
 * @see TextualInstrumnent
 */
public class TextualInstrument extends Instrument {

	
	/**
	 * The string format to return when the <code>current</code> value of the <code>TextualInstrument</code> is an integer (that is; <code>multiplier</code> is 1)
	 * @see Instrument#current
	 * @see #multiplier
	 */
	public static final String FORMAT_INTEGER = "%.0f %s";
	/**
	 * The string format to return when the <code>current</code> value of the TextualInstrument is not an integer (that is; <code>multiplier</code> is not 1)
	 * @see Instrument#current
	 * @see #multiplier
	 */
	public static final String FORMAT_DOUBLE = "%.1f %s";

	/**
	 * The unit to be displayed after the <code>current</code> TextualInstrument value
	 * @see Instrument#current
	 */
	private String unit = "";
	/**
	 * The difference between the <code>current</code> value and the <code>unit</code> value.
	 * For example if <code>current</code> is stored in millimeters and the <code>unit</code> is kilometers, the multiplier would be 1000000
	 * @see Instrument#current
	 * @see #unit
	 */
	private int multiplier;

	/**
	 * Create a <code>TextualInstrument</code> with no <code>unit</code> and a <code>multiplier</code> of 1
	 * @param current The initial value of the instrument
	 * @see #unit
	 * @see #multiplier
	 */
	public TextualInstrument(int current) {
		super.setCurrent(current);
		multiplier = 1;
	}

	/**
	 * Create a <code>TextualInstrument</code> with a <code>unit</code> and a <code>multiplier</code> of 1
	 * @param current The initial value of the instrument
	 * @param unit The unit to be displayed after the <code>current</code> TextualInstrument value
	 * @see #unit
	 * @see #multiplier
	 */
	public TextualInstrument(int current, String unit) {
		this(current);
		this.unit = unit;
	}

	/**
	 * Create a <code>TextualInstrument</code> with a <code>unit</code> and a custom <code>multiplier</code>
	 * @param current The initial value of the instrument
	 * @param multiplier The difference between the <code>current</code> value and the <code>unit</code> value.
	 * @param unit The unit to be displayed after the <code>current</code> TextualInstrument value
	 * @see #unit
	 * @see #multiplier
	 */
	public TextualInstrument(int current, int multiplier, String unit) {
		this(current, unit);
		this.multiplier = multiplier;
	}

	/**
	 * Convert the <code>TextualInstrument</code> to a <code>String</code> with the <code>current</code> value, divided by the <code>multiplier</code>, followed by the <code>unit</code>.
	 * Example output if <code>current</code>=1200000, <code>multiplier</code>=1000000, and <code>unit</code>="Km": "1.2Km"
	 * @return A string of format FORMAT_INTEGER or FORMAT_DOUBLE
	 * @see #FORMAT_INTEGER
	 * @see #FORMAT_DOUBLE
	 * @see Instrument#current
	 * @see #multiplier
	 * @see #unit
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String format;
		if (multiplier == 1) {
			format = FORMAT_INTEGER;
		} else {
			format = FORMAT_DOUBLE;
		}
		return String.format(format, (double) super.getCurrent() / multiplier, unit);
	}

	/**
	 * Return a <code>String</code> representation of this <code>TextualInstrument</code> in the format supplied.
	 * Arguments passed to the <code>String.format</code> are (<code>current</code> / <code>multiplier</code>) and <code>unit</code>
	 * @param format The <code>String.format</code> format string
	 * @return A formatted string of format <code>format</code>
	 * @see Instrument#current
	 * @see #multiplier
	 * @see #unit

	 */
	public String toFormattedString(String format) {
		return String.format(format, (double) super.getCurrent() / multiplier, unit);
	}
}

package BikeSack;

public class TextualInstrument extends Instrument {

	public static final String FORMAT_INTEGER = "%.0f %s";
	public static final String FORMAT_DOUBLE = "%.1f %s";

	private String unit;
	private int multiplier;

	public TextualInstrument(int current) {
		super.setCurrent(current);
		multiplier = 1;
	}

	public TextualInstrument(int current, String unit) {
		this(current);
		this.unit = unit;
	}

	public TextualInstrument(int current, int multiplier, String unit) {
		this(current, unit);
		this.multiplier = multiplier;
	}

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

	public String toFormattedString(String format) {
		return String.format(format, (double) super.getCurrent() / multiplier, unit);
	}
}

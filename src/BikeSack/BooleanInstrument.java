package BikeSack;

public class BooleanInstrument extends Instrument {

	private int min;
	private int max;

	public BooleanInstrument() {
		min = 0;
		max = 1;
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

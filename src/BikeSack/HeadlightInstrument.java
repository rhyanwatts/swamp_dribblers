package BikeSack;

public class HeadlightInstrument extends BooleanInstrument {

	private int min;
	private int max;

	public HeadlightInstrument() {
		min = 0;
		max = 1;
	}

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

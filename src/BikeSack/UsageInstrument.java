package BikeSack;

public class UsageInstrument extends TextualInstrument {
	
	private int startNumerator = 0;
	private int startDenominator = 0;
	private int multiplier = 0;
	private int current = 0;
	
	public UsageInstrument(int current, String unit, int numerator, int denominator, int multiplier) {
		super(current,unit);
		this.startNumerator = numerator;
		this.startDenominator = denominator;
		this.multiplier = multiplier;
		
	}
	
	public void setCurrent(int numerator, int denominator) {
		current = (numerator - startNumerator) / ((denominator - startDenominator) * multiplier);
		super.setCurrent(current);
	}
	
	public void resetUsage(int numerator, int denominator){
		this.startNumerator = numerator;
		this.startDenominator = denominator;
	}

}

package BikeSack;

public class UsageInstrument extends TextualInstrument {
	
	private double startNumerator = 0;
	private double startDenominator = 0;
	private int nMultiplier = 1;
	private int dMultiplier = 1;
	private double current = 0;
	private int factor = 1;
	
	public UsageInstrument(int current, String unit, double numerator, double denominator) {
		super(current,unit);
		this.startNumerator = numerator;
		this.startDenominator = denominator;
	}
	
	public UsageInstrument(int current, String unit, double numerator, double denominator, int numeratorMultiplier, 
			int denominatorMultiplier) {
		super(current,unit);
		this.startNumerator = numerator;
		this.startDenominator = denominator;
		this.nMultiplier = numeratorMultiplier;
		this.dMultiplier = denominatorMultiplier;
	}
	
	public UsageInstrument(int current, String unit, double numerator, double denominator, int numeratorMultiplier, 
			int denominatorMultiplier, int factor) {
		super(current,unit);
		this.startNumerator = numerator;
		this.startDenominator = denominator;
		this.nMultiplier = numeratorMultiplier;
		this.dMultiplier = denominatorMultiplier;
		this.factor = factor;
	}
	@Override
	public void setUsageCurrent(double numerator, double denominator) {
		try {
			double n = ((startNumerator - numerator) / nMultiplier );
			double d = ((denominator - startDenominator) / dMultiplier);
			current = (n/d)*factor;
		} catch (Exception se) {
			System.out.println(se);
		}
		super.setCurrent((int)current);
	}
	
	public void resetUsage(int current, double numerator, double denominator){
		this.startNumerator = numerator;
		this.startDenominator = denominator;
		super.setCurrent(current);
	}

}

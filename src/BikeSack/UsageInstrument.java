package BikeSack;

public class UsageInstrument extends TextualInstrument {
	
	private double startNumerator = 0;
	private double startDenominator = 0;
	private int dMultiplier = 1;
	private double totalNumerator = 0;
	private double totalDenominator = 0;
	private double lastNumerator = 0;
	private double lastDenominator = 0;
	private double current = 0;
	private int factor = 1;
	
	public UsageInstrument(int current, String unit, double numerator, double denominator) {
		super(current,unit);
		this.startNumerator = numerator;
		this.lastNumerator = numerator;
		this.startDenominator = denominator;
		this.lastDenominator = denominator;
	}
	
	public UsageInstrument(int current, String unit, double numerator, double denominator, int denominatorMultiplier) {
		super(current,unit);
		this.startNumerator = numerator;
		this.lastNumerator = numerator;
		this.startDenominator = denominator;
		this.lastDenominator = denominator;
		this.dMultiplier = denominatorMultiplier;
	}
	
	public UsageInstrument(int current, String unit, double numerator, double denominator, int denominatorMultiplier, 
			int factor) {
		super(current,unit);
		this.startNumerator = numerator;
		this.lastNumerator = numerator;
		this.startDenominator = denominator;
		this.lastDenominator = denominator;
		this.dMultiplier = denominatorMultiplier;
		this.factor = factor;
	}
	
	public void setUsageCurrent(double numerator, double denominator) {
		try {
			double n = (lastNumerator - numerator);
			this.totalNumerator += n;
			double d = ((denominator - lastDenominator) / dMultiplier);
			this.totalDenominator += d;
			this.current = (totalNumerator/totalDenominator)*factor;
			this.lastNumerator = numerator;
			this.lastDenominator = denominator;
		} catch (Exception se) {
			System.out.println(se);
		}
		super.setCurrent((int)current);
	}

	public double getLastNumerator() {
		return lastNumerator;
	}

	public void setStartNumerator(int numerator) {
		this.startNumerator = numerator;
		this.lastNumerator = numerator;
	}
}

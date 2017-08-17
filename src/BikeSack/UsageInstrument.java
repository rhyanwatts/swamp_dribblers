package BikeSack;

/**
 * UsageInstrument builds on the TextualInstrument within the
 * {@link BikeSack} motorcycle system. Primarily intended for Fuel Usage
 * 
 * <h2>
 * 
 * <h2>Use as a Usage Instrument</h2>
 * <p>
 * - {@code UsageInstrument.setUsageCurrent(Numerator, Denominator)} to calculate the current difference between the last values and current vales.
 * </p>
 * 
 * <p>
 * - {@code Output.getLastNumerator();} to return last numerator value
 * </p>
 * 
 * <p>
 * -if {@code Output.setStartNumerator();} to set the starting value for the numerator
 * </p>
 * 
 * @author DevTeam2
 * @since 11/7/2017
 * @version 1.0
 */

public class UsageInstrument extends TextualInstrument {
	
	/**
	 * The start numerator on which the numerator difference is calculated
	 */
	private double startNumerator = 0;
	
	/**
	 * The start Denominator on which the denominator difference is calculated
	 */
	private double startDenominator = 0;
	
	/**
	 * The multiplier for the Denominator
	 */
	private int dMultiplier = 1;
	
	/**
	 * Current total of the numerator for usage calculation
	 */
	private double totalNumerator = 0;
	
	/**
	 * Current total of the denominator for usage calculation
	 */
	private double totalDenominator = 0;
	
	/**
	 * The last value that the numerator difference was calculated on
	 */
	private double lastNumerator = 0;
	
	/**
	 * The last value that the denominator difference was calculated on
	 */
	private double lastDenominator = 0;
	
	/**
	 * The current usage calculation
	 */
	private double current = 0;
	
	/**
	 * The multiplication factor for the final value
	 */
	private int factor = 1;
	
	/**
	 * This constructs a UsageInstrument object with no multipliers
	 * @param current - starting value for the calculation
	 * @param unit - unit measurement string i.e. L/Km
	 * @param numerator - Starting value for the numerator
	 * @param denominator - Starting value for the denominator
	 */
	public UsageInstrument(int current, String unit, double numerator, double denominator) {
		super(current,unit);
		this.startNumerator = numerator;
		this.lastNumerator = numerator;
		this.startDenominator = denominator;
		this.lastDenominator = denominator;
	}
	
	/**
	 * This constructs a UsageInstrunemt object with a multiplier
	 * @param current - starting value for the calculation
	 * @param unit - unit measurement string i.e. L/Km
	 * @param numerator - Starting value for the numerator
	 * @param denominator - Starting value for the denominator
	 * @param denominatorMultiplier - Denominator multiplier
	 */
	public UsageInstrument(int current, String unit, double numerator, double denominator, int denominatorMultiplier) {
		super(current,unit);
		this.startNumerator = numerator;
		this.lastNumerator = numerator;
		this.startDenominator = denominator;
		this.lastDenominator = denominator;
		this.dMultiplier = denominatorMultiplier;
	}
	
	/**
	 * This constructs a UsageInstrunemt object with a multiplier
	 * @param current - starting value for the calculation
	 * @param unit - unit measurement string i.e. L/Km
	 * @param numerator - Starting value for the numerator
	 * @param denominator - Starting value for the denominator
	 * @param denominatorMultiplier - Denominator multiplier
	 * @param factor - Correction factor for the calculated value
	 */
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
	
	/*
	 * Calculate the difference between the numerator and denominator and the startNumerator and start Denominator
	 * and update the current usage value
	 */
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
	/**
	 * Return the last numerator that was used for the calculation 
	 * @return lastNumerator
	 */
	public double getLastNumerator() {
		return lastNumerator;
	}
	
	/**
	 * Set the start numerator value to compare and calculate future calculations on
	 * @param numerator
	 */
	public void setStartNumerator(int numerator) {
		this.startNumerator = numerator;
		this.lastNumerator = numerator;
	}
}

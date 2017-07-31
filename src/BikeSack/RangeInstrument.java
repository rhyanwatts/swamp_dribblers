package BikeSack;

/**
 * <h1>Bike Sack RangeInstrument Class</h1> The Range Instrument Class extends
 * BikeSack's abstract 'Instrument'
 * 
 * This class allows for an instrument with a proportional value. It can also
 * display a selective range to only show relevant information.
 * 
 * <h2>Example usage:
 * <h2>Temperature Instrument with minimum value 0, maximum 125, current value
 * 22 and units are Celsius: Instrument tempgauge = new
 * RangeInstrument(0,125,22,"Celsius","C");
 * 
 * Volt Meter: Instrument voltmeter = new RangeInstrument(10,14,12,"Volts",
 * "V");
 * 
 * <h2>Useful Methods include:
 * <h2>getPercentage() Returns the current value - as a percentage of the range
 * 
 * getPercentageString() Returns the current value - as a percentage of the
 * range, with a % symbol and in string format
 * 
 * toString() Returns a 10char sting filled proportinally with the percentage
 * state of the instrument. e.g. 75% of range is returned as "*******---"
 * 
 * @author Matthew Flack s3493444
 * @author Aidan Holmes s3355003
 * @since 30/7/2017
 * @version 0.2
 */

public class RangeInstrument extends Instrument {

   private int min;
   private int max;
   private String unit, unitSymbol;

   public RangeInstrument() {
      // Default amounts
      min = 0;
      max = 50;
   }

   public RangeInstrument(int max) {
      min = 0;
      this.max = max;
   }

   // Chained constructor with setting for minimum
   public RangeInstrument(int minimum, int maximum) {
      this(maximum);
      this.min = minimum;
      this.setCurrent(min); // set current to bottom of range
   }

   // Chained constructor with setting for initial value
   public RangeInstrument(int minimum, int maximum, int initial) {
      this(minimum, maximum);
      this.setCurrent(initial);
   }

   // Chained constructor with setting for Units
   public RangeInstrument(int minimum, int maximum, int initial, String unit, String unitSymbol) {
      this(minimum, maximum, initial);
      this.setUnit(unit);
      this.setUnitSymbol(unitSymbol);
   }

   // Basic Getters and Setters
   public int getMin() {
      return min;
   }

   public int getMax() {
      return max;
   }

   public String getUnit() {
      return unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getUnitSymbol() {
      return unitSymbol;
   }

   public void setUnitSymbol(String unitSymbol) {
      this.unitSymbol = unitSymbol;
   }

   // Rounds down
   public int getPercentage() {
      // adjusted to calculate current - as a percentage of the min-max range
      return (int) (((double) super.getCurrent() - min) / (double) (max - min) * 100.0);
   }

   // return current value as a percentage, as a String
   public String getPercentageString() {
      return (getPercentage() + "%");
   }

   @Override
   public String toString() {
      char fullChar = '*';
      char emptyChar = '-';

      // Number of characters in the instrument
      int totalLevelChars = 10;

      // Calculate percentage
      // double gaugeLevel = ( (double)super.getCurrent() / (double)max ) * 100.0;
      int gaugeLevel = this.getPercentage();

      int numFullChars = gaugeLevel / totalLevelChars;

      StringBuilder gaugeBuilder = new StringBuilder();

      // Add the full/empty chars to the string
      for (int i = 0; i < totalLevelChars; ++i) {
         if (i < numFullChars) {
            gaugeBuilder.append(fullChar);
         } else {
            gaugeBuilder.append(emptyChar);
         }
      }
      return gaugeBuilder.toString();
   }

}

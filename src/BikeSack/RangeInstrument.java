package BikeSack;

/**
 * RangeInstrument extends BikeSack's abstract {@link Instrument} and allows for
 * an instrument with a proportional value. It can also show a selective
 * range to better display a relevant information.
 * 
 * <h2>Usage Example:</h2> Temperature Instrument with minimum value 0, maximum
 * 125, current value 22 and units are Celsius:
 * <p>
 * {@code Instrument tempgauge = new
 * RangeInstrument(0,125,22,"Celsius","C");}
 * </p>
 * 
 * <p>
 * Volt Meter Example:
 * </p>
 * {@code Instrument voltmeter = new RangeInstrument(10,14,12,"Volts",
 * "V");}
 * 
 * <h2>Useful Methods include:</h2>
 * <p>
 * {@code getPercentage()} Returns the current value - as a percentage of the
 * range
 * </p>
 * <p>
 * {@code getPercentageString()} Returns the current value - as a percentage of
 * the range, with a % symbol and in string format
 * </p>
 * <p>
 * {@code toString()} Returns a 10char sting filled proportionally with the
 * percentage state of the instrument. e.g. 75% of range is returned as
 * {@code *******---}
 * </p>
 * 
 * @author DevTeam1
 * @author DevTeam2
 * @since 30/7/2017
 * @version 1.0
 */

public class RangeInstrument extends Instrument {

   // declare private variables
   private int min;
   private int max;
   private int warningSetPoint;
   private boolean maxWarning, warningActive;
   private String unit, unitSymbol;

   /**
    * Constructs an instrument that displays the current value, maximum value of
    * the range is defined, minimum value of the range will be zero.
    * 
    * @param max
    *           The maximum display range of this instrument
    */
   public RangeInstrument(int max) {
      min = 0;
      this.max = max;
   }

   /**
    * Constructs an instrument that displays current value, minimum and maximum
    * value of the range are both defined.
    * 
    * @param minimum
    *           the minimum display range of this instrument
    * @param maximum
    *           the maximum display range of this instrument
    */
   public RangeInstrument(int minimum, int maximum) {
      this(maximum);
      this.min = minimum;
      this.setCurrent(min); // set current to bottom of range
   }

   /**
    * Constructs an instrument that displays current value, minimum and maximum
    * value of the range are both defined. An initial display value is defined for
    * instrument initialisation before it has received a value update.
    * 
    * @param minimum
    *           the minimum display range of this instrument
    * @param maximum
    *           the maximum display range of this instrument
    * @param initial
    *           the starting display value for this instrument
    */
   public RangeInstrument(int minimum, int maximum, int initial) {
      this(minimum, maximum);
      this.setCurrent(initial);
   }

   /**
    * Constructs an instrument that displays current value, minimum and maximum
    * value of the range are both defined. An initial display value is defined for
    * instrument initialisation before it has received a value update. Unit name
    * and symbol are defined.
    * 
    * @param minimum
    *           the minimum display range of this instrument
    * @param maximum
    *           the maximum display range of this instrument
    * @param initial
    *           the starting display value for this instrument
    * @param unit
    *           the name of the units this instrument is displaying
    * @param unitSymbol
    *           the symbol or abbreviation for the units this instrument is
    *           displaying
    */
   public RangeInstrument(int minimum, int maximum, int initial, String unit, String unitSymbol) {
      this(minimum, maximum, initial);
      this.setUnit(unit);
      this.setUnitSymbol(unitSymbol);
   }

   /**
    * Constructs an instrument that displays current value, minimum and maximum
    * value of the range are both defined. An initial display value is defined for
    * instrument initialisation before it has received a value update. Unit name
    * and symbol are defined. A warning set point and behaviour (minimum or maximum
    * warning) is also defined.
    * 
    * @param minimum
    *           the minimum display range of this instrument
    * @param maximum
    *           the maximum display range of this instrument
    * @param initial
    *           the starting display value for this instrument
    * @param unit
    *           the name of the units this instrument is displaying
    * @param unitSymbol
    *           the symbol or abbreviation for the units this instrument is
    *           displaying
    * @param warning
    *           the value at which the instrument will display a warning
    * @param maxWarning
    *           True if warning is maximum, False to use warning as a minimum
    */
   public RangeInstrument(int minimum, int maximum, int initial, String unit, String unitSymbol, int warning,
         boolean maxWarning) {
      this(minimum, maximum, initial, unit, unitSymbol);
      this.warningSetPoint = warning;
      this.maxWarning = maxWarning;
      this.warningActive = true;
   }

   /**
    * 
    * @return minimum value of display range for this instrument
    */
   public int getMin() {
      return min;
   }

   /**
    * 
    * @return maximum value of display range for this instrument
    */
   public int getMax() {
      return max;
   }

   /**
    * 
    * @return name of unit being displayed by this instrument
    */
   public String getUnit() {
      return unit;
   }

   /**
    * 
    * @param unit
    *           name of unit being displayed by this instrument
    */
   public void setUnit(String unit) {
      this.unit = unit;
   }

   /**
    * 
    * @return Symbol the symbol or abbreviation for the units this instrument is
    *         displaying
    */
   public String getUnitSymbol() {
      return unitSymbol;
   }

   /**
    * 
    * @param unitSymbol
    *           the symbol or abbreviation for the units this instrument is
    *           displaying
    */
   public void setUnitSymbol(String unitSymbol) {
      this.unitSymbol = unitSymbol;
   }

   /**
    * 
    * @return value of current warning trigger value or set point
    */
   public int getWarning() {
      return warningSetPoint;
   }

   /**
    * 
    * @param maxWarning
    *           True if the warning is a maximum value. False if the warning is a
    *           minimum value.
    */
   public void setMaxWarning(boolean maxWarning) {
      this.maxWarning = maxWarning;
   }

   /**
    * 
    * @return True if warning is currently triggered. False if not in warning
    *         state.
    */
   public boolean getWarningStatus() {
      if (!warningActive) {
         // no warning setpoint. Warning cannot have been triggered
         return false;
      }
      if (super.getCurrent() >= warningSetPoint && maxWarning) {
         return true; // above maximum warning setpoint
      } else if (super.getCurrent() <= warningSetPoint && !maxWarning) {
         return true; // below minimum warning setpoint
      } else {
         return false;
      }
   }

   // TODO comment out overridden method (below) after console output validation
   // complete
   @Override
   public void setCurrent(int current) {
      super.setCurrent(current);
      if (getWarningStatus()) {
         System.out.println("Instrument [WARNING " + super.getCurrent() + " breaches setpoint " + getWarning() + "]");
      }
   }

   /**
    * 
    * @return Current value, as a percentage of the instrument's display range.
    */
   public int getPercentage() {
      // adjusted to calculate current - as a percentage of the min-max range. Rounds
      // down.
      return (int) (((double) super.getCurrent() - min) / (double) (max - min) * 100.0);
   }

   /**
    * 
    * @return Current value as a percentage of the instrument's display range.
    *         String format with % symbol.
    */
   public String getPercentageString() {
      return (getPercentage() + "%");
   }

   /**
    * @return Instrument value as a proportion of the instrument's range, in basic
    *         bar graph format.
    *         <p>
    *         e.g. Range is 100 - 200 and value is 150
    *         </p>
    *         {@code RangeInstrument.toString()} returns: {@code *****-----}
    * 
    */
   @Override
   public String toString() {
      char fullChar = '*';
      char emptyChar = '-';

      // Number of characters in the instrument
      int totalLevelChars = 10;

      // Calculate percentage
      int gaugeLevel = this.getPercentage();

      int numFullChars = gaugeLevel / totalLevelChars;

      StringBuilder gaugeBuilder = new StringBuilder();

      if (getWarningStatus()) {
         // Return Warning Indication with Value
         gaugeBuilder.append("WARN!-");
         gaugeBuilder.append(super.getCurrent());
         for (int i = gaugeBuilder.length(); i < totalLevelChars; i++) {
            gaugeBuilder.append(emptyChar);
         }
      } else {
         // Return 'Graphical' Indication
         // Add the full/empty chars to the string
         for (int i = 0; i < totalLevelChars; ++i) {
            if (i < numFullChars) {
               gaugeBuilder.append(fullChar);
            } else {
               gaugeBuilder.append(emptyChar);
            }
         }

      }

      return gaugeBuilder.toString();
   }

}

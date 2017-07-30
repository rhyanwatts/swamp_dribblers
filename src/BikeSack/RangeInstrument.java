package BikeSack;

public class RangeInstrument extends Instrument{
   
   private int min, max;
   private String unit, unitSymbol;
   
   //Constructors
   // No default constructor provided. Min & Max necessary for
   // displaying a proportional range.
   
   public RangeInstrument(int minimum, int maximum) {
      min = minimum;
      max = maximum;
      this.setCurrent(min);
   }
   
   //Chained constructor with setting for initial value
   public RangeInstrument (int minimum, int maximum, int initial){
      this(minimum, maximum);
      this.setCurrent(initial);
   }
   
   //Chained constructor with setting for Units
   public RangeInstrument (int minimum, int maximum, int initial, String unit, String unitSymbol) {
      this(minimum, maximum, initial);
      this.setUnit(unit);
      this.setUnitSymbol(unitSymbol);
   }
   
   //Get & Set private variables.
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
   
   //return current int value as a percentage of the range
   public int getPercent() {
      //work with doubles to get accurate division
      double range = max - min;
      double adjustedValue = (super.getCurrent() - min);
      double percentage = ((adjustedValue / range) * 100);
      return (int)percentage;
   }
   
   //return current value as a percentage, as a String
   public String getPercentString() {
      return (getPercent() + "%");
   }

   @Override
   public String toString() {
      if(this.getUnitSymbol() != null) {
      return (super.getCurrent() + " " + this.getUnitSymbol()).toString();
      }else {
         return Integer.toString(super.getCurrent());
      }
   }

}

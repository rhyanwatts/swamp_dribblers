package BikeSack.TestingClasses;

import BikeSack.RangeInstrument;

public class RangeInstrumentTest {

   public static void main(String[] args) {
      RangeInstrument testInstrument = new RangeInstrument(50);

      testInstrument.setCurrent(10);

      System.out.println("Fuel Level: " + testInstrument);
      System.out.println();
      

      // Test with Unit Values - Example is Fuel
      RangeInstrument instrument1 = new RangeInstrument(0, 20, 13, "Litres", "L");
      System.out.println("Instrument1");
      System.out.println("Current = " + instrument1.getCurrent());
      System.out.println("Max = " + instrument1.getMax());
      System.out.println("Min = " + instrument1.getMin());
      System.out.println("Percent = " + instrument1.getPercentage());
      System.out.println("PercentString = " + instrument1.getPercentageString());
      System.out.println("Unit = " + instrument1.getUnit());
      System.out.println("UnitSymbol = " + instrument1.getUnitSymbol());
      System.out.println("ToString: " + instrument1.toString());

      // Test with Unit Values - Example is Temperature
      RangeInstrument instrument2 = new RangeInstrument(0, 135, 20, "Celcius", "C");
      System.out.println();
      System.out.println("Instrument2");
      System.out.println("Current = " + instrument2.getCurrent());
      System.out.println("Max = " + instrument2.getMax());
      System.out.println("Min = " + instrument2.getMin());
      System.out.println("Percent = " + instrument2.getPercentage());
      System.out.println("PercentString = " + instrument2.getPercentageString());
      System.out.println("Unit = " + instrument2.getUnit());
      System.out.println("UnitSymbol = " + instrument2.getUnitSymbol());
      System.out.println("ToString: " + instrument2.toString());

      // Test with Unit Values - Example is Volt meter
      RangeInstrument instrument3 = new RangeInstrument(10, 14, 12, "Volts", "V");
      System.out.println();
      System.out.println("instrument3");
      System.out.println("Current = " + instrument3.getCurrent());
      System.out.println("Max = " + instrument3.getMax());
      System.out.println("Min = " + instrument3.getMin());
      System.out.println("Percent = " + instrument3.getPercentage());
      System.out.println("PercentString = " + instrument3.getPercentageString());
      System.out.println("Unit = " + instrument3.getUnit());
      System.out.println("UnitSymbol = " + instrument3.getUnitSymbol());
      System.out.println("ToString: " + instrument3.toString());

   }

}

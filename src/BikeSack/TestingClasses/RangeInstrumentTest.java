package BikeSack.TestingClasses;

import BikeSack.RangeInstrument;

public class RangeInstrumentTest {

    public static void main(String[] args) {
        RangeInstrument testInstrument = new RangeInstrument(50); 
        
        testInstrument.setCurrent(10);
        
        System.out.println("Fuel Level: " + testInstrument);
    }

}

package BikeSack;

import java.util.Map;

import BikeSack.BikeSack.INSTRUMENTS;

public class ConsoleDisplay extends Display {

	// Print the console GUI
	public void show(Map<INSTRUMENTS, Instrument> instruments) {

	    RangeInstrument fuelLevel = (RangeInstrument)instruments.get(INSTRUMENTS.FUEL);
	    
		// Menu Options output
		System.out.println("The Bike Sack");
		System.out.println();
		System.out.println("B= Brake Lights [" + instruments.get(INSTRUMENTS.BRAKE).toString() + "]");
		System.out.println("L= Left Indicator [" + instruments.get(INSTRUMENTS.LEFT_INDICATOR).toString() + "]");
		System.out.println("R= Right Indicator [" + instruments.get(INSTRUMENTS.RIGHT_INDICATOR).toString() + "]");
		System.out.println("H= Highbeam Toggle [" + instruments.get(INSTRUMENTS.HIGH_BEAM).toString() + "]");
		System.out.println("{= Fuel Level UP");
		System.out.println("}= Fuel Level DOWN");
		System.out.println("+= Engine Temp UP");
		System.out.println("-= Engine Temp DOWN");
		System.out.println("T= Trip Reset");
		System.out.println("O= Simulate Odemeter Increment");
		System.out.println("W= Simulate Odemeter Increment Warp Speed (100 Wheel Rotations)");
		System.out.println("X= Exit");
		System.out.println();

		// Instrument Outputs
		// Each Row is 60 Chars wide, Column 20 Chars Wide, 10 Chars space between
		// columns
		System.out.println("Fuel=" + fuelLevel.getPercentage() + "% " + String.format("%12s", "") + "Engine Temp=0c ");
		System.out.println("0[" + fuelLevel + "]100" + String.format("%4s", "") + "min[----------]max");
		System.out.println();
		System.out.println("Odometer=0000000" + String.format("%4s", "") + "Trip Meter=0");
		System.out.println();
		System.out.println("Fuel Usage= 0.0Km/100L");
		System.out.println();
		System.out.println("Please enter a selection:");

	}
}

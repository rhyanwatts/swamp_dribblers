package BikeSack;

import java.util.Map;

import BikeSack.BikeSack.INSTRUMENTS;

public class ConsoleDisplay extends Display {
    
    // 
    private final static int NUM_INDICATOR_LOOPS = 29;
    private final static int INDICATOR_LOOP_DELAY = 90;
    private int indicatorLoopCounter = 0;

	// Print the console GUI
	public void show(Map<INSTRUMENTS, Instrument> instruments) {

	   //cast RangeInstruments once here to prevent code bloat later
	    RangeInstrument fuelLevel = (RangeInstrument)instruments.get(INSTRUMENTS.FUEL);
	    RangeInstrument temperature = (RangeInstrument)instruments.get(INSTRUMENTS.TEMPERATURE);
	    Instrument leftIndicator = instruments.get(INSTRUMENTS.LEFT_INDICATOR);
	    Instrument rightIndicator = instruments.get(INSTRUMENTS.RIGHT_INDICATOR);
	    
	    // If either indicator is on cycle the menu to demonstrate the fading.
	    if(leftIndicator.isOn() || rightIndicator.isOn()) {
	        if(indicatorLoopCounter != NUM_INDICATOR_LOOPS) {
	           ++indicatorLoopCounter;
	           
	           show(instruments); 
	           
	           // Delay the loop to slow the fade effect.
	           try {
	                Thread.sleep(INDICATOR_LOOP_DELAY);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        } else {
	            indicatorLoopCounter = 0;
	        }
	    }
	    
	    
		// Instrument and Sensor Output
	    	System.out.println("+" + String.format("%68s", "").replace(' ', '-') + "+");
		System.out.println("|LEFT INDICATOR |" + pad("THE BIKE SACK", 24) + pad("|RIGHT INDICATOR|", 29) );
		System.out.println("|" + buildIndicator(leftIndicator) + "|" + pad("+---------------+", 26) + pad("|" + buildIndicator(rightIndicator) + "|", 27) );
		System.out.println("|---------------+" + pad("+---------------|", 53));
		System.out.println("|" + pad("BRAKE LIGHTS", 39) + pad("|", 30));
		System.out.println("|" + pad("+---------------+", 42) + pad("|", 27));
		System.out.println("|" + pad("[" + instruments.get(INSTRUMENTS.BRAKE).toString().toUpperCase() + "]",36) + pad("|", 33));
		System.out.println("|" + pad("|", 69));	
		System.out.println("|" + pad("TEMP", 9) + pad("HIGH BEAM", 29) + pad("FUEL",22) + pad("|",9));
		System.out.println("|" + pad("+----+", 10) + pad("+-----------+",30) + pad("+----+",21) + pad("|", 8));
		System.out.println("|" + pad("[" + instruments.get(INSTRUMENTS.HIGH_BEAM).toString().toUpperCase() + "]",36) + pad("|", 33));
		System.out.println("|" + pad("[" + temperature + "]",13) + pad("[" + fuelLevel + "]", 52) + pad("|", 4));
		System.out.println("|" + pad("min",2) + pad("max", 11) + pad("0%", 39) + pad("50%",8) + pad("100%|",8));
		System.out.println("|" + pad("|", 69));	
		System.out.println("|" + pad("ODOMETER:      ",16) + pad("TRIP METER:      ", 25) + pad("FUEL USAGE:XXL/XXXKM ", 27) + pad("|",1));
		
		//Control Sections
		System.out.println("+" + String.format("%68s", "").replace(' ', '-') + "+");
		System.out.println("|" + pad("CONTROLS", 37) + pad("|", 32));
		System.out.println("|  L= Left Indicator     H= High Beam LO/HI       R= RightIndicator  |");
		System.out.println("|" + pad("B= Brakes ON/OFF", 41) + pad("|", 28));	
		System.out.println("|" + pad("T= Trip Reset", 40) + pad("|", 29));
		System.out.println("|" + pad("+= Engine Temp UP", 19) + pad("-= Engine Temp DOWN",47) + pad("|", 3));
		System.out.println("|" + pad("{= Fuel UP", 12) + pad("}= Fuel DOWN",54) + pad("|", 3));
		System.out.println("|" + pad("O= Simulate Odemeter", 44) + pad("|", 25));
		System.out.println("|    W= Simulate Odometer Increment Warp Speed (100 Wheel Rotations) |");
		System.out.println("+" + String.format("%68s", "").replace(' ', '-') + "+");
		System.out.println("Please enter a selection:");		
		}

	
	private String pad(String text, int chars) {
	    return String.format("%1$" + chars + "s", text);  
	}

	
	private String buildIndicator(Instrument indicator) {
        
        //int indicatorLen = 15;
        char onChar = '*';
        
        // Check if indicator is off
        if(indicator.getCurrent() == 0) {
            return "               ";
        }
        
        // Reset if at the max fade length
        if(indicator.getCurrent() >= BikeSack.MAX_FADE_CURRENT) {
            indicator.setCurrent(0);
        }
        
        // Get the fade level from the current
        int fadeLevel = indicator.getCurrent();
        
        // Build the indicator string
        StringBuilder indicatorBuilder = new StringBuilder();
        for(int i = 0; i < BikeSack.MAX_FADE_CURRENT; ++i )
        {
            if(i < fadeLevel + 1) {
                indicatorBuilder.append(onChar);
            }
            else {
                indicatorBuilder.append(' ');
            }
        }
        indicator.setCurrent(++fadeLevel);    
        return indicatorBuilder.toString();
    }
	
}

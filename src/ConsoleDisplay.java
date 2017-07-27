import java.util.HashMap;
import java.util.Map;

public class ConsoleDisplay extends Display {
    
    private static final int INDICATOR_LEN = 10;
    private static final char LEFT_INDICATOR_CHAR = '<';
    private static final char RIGHT_INDICATOR_CHAR = '>';
    
    // Print the console GUI
    public void updateConsole
        (Map<Instrument.InstrumentType, Instrument> instrumentPanel) {
        drawIndicators(instrumentPanel.get(Instrument.InstrumentType.LEFT_INDICATOR),
            instrumentPanel.get(Instrument.InstrumentType.RIGHT_INDICATOR));   
    	drawHeadLights(instrumentPanel.get(Instrument.InstrumentType.HIGH_BEAM), 
    			instrumentPanel.get(Instrument.InstrumentType.LOW_BEAM));
    }
     
    // Prints the indicators
    private void drawIndicators(Instrument left, Instrument right) {
        if(left.getCurrent() > 0) {
            // Need to minus one to start from the beginning 
            // (can't set to 0 as then it will be off)
            drawOnIndicator(LEFT_INDICATOR_CHAR, left.getCurrent() - 1);
        } else {
            drawOffIndicator();
        }
            
        if(right.getCurrent() > 0) {
            // Need to minus one to start from the beginning 
            // (can't set to 0 as then it will be off)
            drawOnIndicator(RIGHT_INDICATOR_CHAR, right.getCurrent() - 1);
        } else {
            drawOffIndicator();
        }
        System.out.println();
    }
    
    // Prints an on indicator with the fade level input.
    private void drawOnIndicator(char indicatorChar, int fadeLevel) {
        System.out.print(" [");
        for(int i = 0; i < INDICATOR_LEN; ++i)
        {
            if(i <= fadeLevel) {
                System.out.print(indicatorChar);
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("] ");
    }
    
    // Prints an off (empty) indicator.
    private void drawOffIndicator() {
        System.out.print(" [");
        for(int i = 0; i < INDICATOR_LEN; ++i)
        {
            System.out.print(" ");
        }
        System.out.print("] ");
    }
    
    //Prints the head lights status
    /*
     * CHANGE: Removed Reference to drawHeadLightOn/Off
     * CHANGE: fixed up logic
     */
    private String drawHeadLights(Instrument high, Instrument low) {
        String state = "LOW BEAM";
        
    	if (high.getCurrent() == Output.ON && low.getCurrent() == Output.OFF) {
            state = "HIGH BEAM";
        } else if (high.getCurrent() == Output.OFF && low.getCurrent() == Output.ON){
        	state = "LOW BEAM";
        } 
        return state;
    }
    
    //Return OFF/ON state for Indicator Instruments
    private String getIndicatorState(Instrument indicator){
    	String state;
    	if(indicator.getCurrent() >0){
    		state = "ON";
    	}
    	else
    		state = "OFF";
    	
    	return state;
    }
    
    //Return Off/On state for BrakeLight Instrument
    private String getBrakeState(Instrument brake) {
       String state;
       state = (brake.getCurrent() > 0) ? "ON": "OFF";
       return state;
    }
   	
	//Outputs Basic GUI to console
	public void basicGui(Map<Instrument.InstrumentType, Instrument> instrumentPanel)
    {	
		String leftIndicator = getIndicatorState(instrumentPanel.get(Instrument.InstrumentType.LEFT_INDICATOR));
		String rightIndicator = getIndicatorState(instrumentPanel.get(Instrument.InstrumentType.RIGHT_INDICATOR));
		String highBeam = drawHeadLights(instrumentPanel.get(Instrument.InstrumentType.HIGH_BEAM),
											instrumentPanel.get(Instrument.InstrumentType.LOW_BEAM));
		String brakeLight = getBrakeState(instrumentPanel.get(Instrument.InstrumentType.BRAKE_LIGHT));
		
		//Menu Options output
		System.out.println("The Bike Sack");
		System.out.println();
		System.out.println("B= Brake Lights [" + brakeLight + "]");
		System.out.println("L= Left Indicator [" + leftIndicator + "]");
		System.out.println("R= Right Indicator [" + rightIndicator + "]");
		System.out.println("H= Highbeam Toggle [" + highBeam + "]");
		System.out.println("{= Fuel Level UP");
		System.out.println("}= Fuel Level DOWN");
		System.out.println("+= Engine Temp UP");
		System.out.println("-= Engine Temp DOWN");
		System.out.println("T= Trip Reset");
		System.out.println("O= Simulate Odemeter Increment");
		System.out.println("W= Simulate Odemeter Increment Warp Speed (100 Wheel Rotations)");
		System.out.println("X= Exit");
		System.out.println();
		
		//Instrument Outputs
		//Each Row is 60 Chars wide, Column 20 Chars Wide, 10 Chars space between columns 
		System.out.println("Fuel=0% " + String.format("%12s", "") + "Engine Temp=0c ");
		System.out.println("0[----------]100" + String.format("%4s", "") + "min[----------]max");
		System.out.println();
		System.out.println("Odometer=0000000" + String.format("%4s", "") + "Trip Meter=0");
		System.out.println();
		System.out.println("Fuel Usage= 0.0Km/100L");
		System.out.println();
		System.out.println("Please enter a selection:");			

	}
}

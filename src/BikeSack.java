import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


// TODO: Move this...Not sure which class to put this in? Maybe output?
enum IndicatorDirection {
    LEFT, RIGHT, NONE
}

enum HeadLightLevel {
	HIGH, LOW
}

public class BikeSack {
    
    // Should this go in output class?
    public static final int MAX_FADE_CURRENT = 10;
    
    private ConsoleDisplay consoleDisplay;
    private Map<Instrument.InstrumentType, Instrument> instrumentPanel;
    
    private Output leftIndicator;
    private Output rightIndicator;
    private Output headLightsHigh; 
    private Output headLightsLow;
    private static Output brakeLight;
   
    public BikeSack() {
        consoleDisplay = new ConsoleDisplay();
        leftIndicator = new Output("Left Indicator", Output.OFF, 1);
        rightIndicator = new Output("Right Indicator", Output.OFF, 1);
        headLightsHigh = new Output("Head Lights High", Output.OFF);
        headLightsLow = new Output("Head Lights Low", Output.ON);
        brakeLight = new Output("Brake Light",Output.OFF,255);
        instrumentPanel = new HashMap<Instrument.InstrumentType, Instrument>();
        instrumentPanel.put(Instrument.InstrumentType.LEFT_INDICATOR , new BooleanInstrument());
        instrumentPanel.put(Instrument.InstrumentType.RIGHT_INDICATOR , new BooleanInstrument());
        instrumentPanel.put(Instrument.InstrumentType.HIGH_BEAM , new BooleanInstrument());
        instrumentPanel.put(Instrument.InstrumentType.LOW_BEAM , new BooleanInstrument());
        instrumentPanel.put(Instrument.InstrumentType.BRAKE_LIGHT, new BooleanInstrument());
    }
    
    
    // Update the displays with the data from the instrument panel
    public void updateDisplay() {    
        consoleDisplay.updateConsole(instrumentPanel);
    }
    
    
    // Turns the indicators on/off
    public void toggleIndicator(IndicatorDirection dir) {
        // Turn both off
        if(dir == IndicatorDirection.NONE) {
            leftIndicator.setoutputLevel(Output.OFF);
            rightIndicator.setoutputLevel(Output.OFF);
        }
        
        // Indicate left
        else if(dir == IndicatorDirection.LEFT) { 
            // Turn off if its on
            if(leftIndicator.isOn()) {
                leftIndicator.setoutputLevel(Output.OFF);
            } else {
                // turn on if its off
                rightIndicator.setoutputLevel(Output.OFF);
                leftIndicator.setoutputLevel(leftIndicator.getIncrementStep());
            }
        }
        
        // Indicate right
        else if(dir == IndicatorDirection.RIGHT) {
         // Turn off if its on
            if(rightIndicator.isOn()) {
                rightIndicator.setoutputLevel(Output.OFF);
            } else {
                // turn on if its off
                leftIndicator.setoutputLevel(Output.OFF);
                rightIndicator.setoutputLevel(rightIndicator.getIncrementStep());
            }
        }
        updateIndicatorInstruments();
        updateDisplay();
    }

    // Updates the indicator instrument from the indicator outputs and increments the 
    // fade level.
    public void updateIndicatorInstruments() {
        // Left indicator
        if(leftIndicator.isOn())
        {
            // Set the current to the output level of the indicator output
            instrumentPanel.get(Instrument.InstrumentType.LEFT_INDICATOR).setCurrent
                (leftIndicator.getOutputLevel());
            
            // Increase the current to increment the fade status, reset if at max
            if(leftIndicator.getOutputLevel() == MAX_FADE_CURRENT) {
                leftIndicator.setoutputLevel(leftIndicator.getIncrementStep());
            } else {
                leftIndicator.inc();
            }
            
        } else {
            // Turn off
            instrumentPanel.get(Instrument.InstrumentType.LEFT_INDICATOR).setCurrent(Output.OFF);
        }
        
        // Right indicator
        if(rightIndicator.isOn())
        {
            // Set the current to the output level of the indicator output
            instrumentPanel.get(Instrument.InstrumentType.RIGHT_INDICATOR).setCurrent
                (rightIndicator.getOutputLevel());
            
            // Increase the current to increment the fade status, reset if at max
            if(rightIndicator.getOutputLevel() == MAX_FADE_CURRENT) {
                rightIndicator.setoutputLevel(rightIndicator.getIncrementStep());
            } else {
                rightIndicator.inc();
            }
            
        } else {
            // Turn off
            instrumentPanel.get(Instrument.InstrumentType.RIGHT_INDICATOR).setCurrent(Output.OFF);
        }
    }
    
    //Set head light to high or low
    public void updateHeadLight () {
    	
    	//Set head light to low
    	if (headLightsHigh.getOutputLevel() == Output.ON && headLightsLow.getOutputLevel() == Output.OFF) {
    		//Check if high beam is on, if it is set high beam to off and low beam to on
    		headLightsHigh.setoutputLevel(Output.OFF);
    		headLightsLow.setoutputLevel(Output.ON);
    	} 
    	else if (headLightsHigh.getOutputLevel() == Output.OFF && headLightsLow.getOutputLevel() == Output.ON)
    	{
    		headLightsHigh.setoutputLevel(Output.ON);
    		headLightsLow.setoutputLevel(Output.OFF);
    	} else {
    		headLightsLow.setoutputLevel(Output.ON);
    	}
    	
    	updateHeadLightInstruments();
        updateDisplay();
    }
    public void updateHeadLightInstruments() {
        // Left indicator
        if (headLightsHigh.isOn())
        {
            // Set the current to the output level of the indicator output
            instrumentPanel.get(Instrument.InstrumentType.HIGH_BEAM).setCurrent
                (Output.ON);
            instrumentPanel.get(Instrument.InstrumentType.LOW_BEAM).setCurrent
            	(Output.OFF);
        } 
        else
        {
            // Set the current to the output level of the indicator output
        	instrumentPanel.get(Instrument.InstrumentType.HIGH_BEAM).setCurrent
            	(Output.OFF);
        	instrumentPanel.get(Instrument.InstrumentType.LOW_BEAM).setCurrent
                (Output.ON);
        }
    }
    
    public void brake() {
       brakeLight.toggle();
       instrumentPanel.get(Instrument.InstrumentType.BRAKE_LIGHT).setCurrent(brakeLight.getOutputLevel());
       updateDisplay();
    }
	
	public static void main(String[] args) {
    	
    	BikeSack bikeSack = new BikeSack();
    	Scanner input = new Scanner(System.in);
    	String selection;
    	ConsoleDisplay consoleDisplay = new ConsoleDisplay();
    	
    	do{   	
	    	consoleDisplay.basicGui(bikeSack.instrumentPanel);
	    	selection = input.nextLine();		

			switch (selection.toUpperCase()) {
				case "B":
					//Brake Light
				   bikeSack.brake();
					break;
				case "L":
					//Left Indicator
					bikeSack.toggleIndicator(IndicatorDirection.LEFT);
					break;
				case "R":
					//Right Indicator
					bikeSack.toggleIndicator(IndicatorDirection.RIGHT);
					break;
				case "H":
					//Head Light Toggle
					bikeSack.updateHeadLight();
					break;
				case "{":
					//Fuel Level UP
					break;
				case "}":
					//Fuel Level DOWN
					break;
				case "+":
					//Engine Temp UP
					break;
				case "-":
					//Engine Temp DOWN
					break;
				case "X":
					//Exit application
					break;
				default:
					System.out.println("Invalid selection, please try again.");
					break;
			}
		}while(!selection.equals("X"));
    	input.close();
    }
}


import java.io.Console;
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
    
    // Set up constants for the keyboard inputs
    public static final String LEFT_INDICATOR_KEY = "L";
    public static final String RIGHT_INDICATOR_KEY = "R";
    public static final String HIGH_BEAM_KEY = "H";
    public static final String BRAKE_KEY = "B";
    public static final String TEMPERATURE_INCREASE_KEY = "+";
    public static final String TEMPERATURE_DECREASE_KEY = "-";
    public static final String FUEL_INCREASE_KEY = "}";
    public static final String FUEL_DECREASE_KEY = "{";
    public static final String TRIP_RESET_KEY = "T";
    public static final String ODOMETER_INCREASE_KEY = "O";
    public static final String ODOMETER_WARP_KEY = "W";
    public static final String EXIT_KEY = "X";
    
    // Define the sensor types, will be used in a map to store the sensors
    public static enum CONNECTED_SENSORS {
    	LEFT_INDICATOR, RIGHT_INDICATOR, HIGH_BEAM, BRAKE, TEMPERATURE, FUEL, TRIP, ODOMETER
    }
    
    private ConsoleDisplay consoleDisplay;
    private Map<Instrument.InstrumentType, Instrument> instrumentPanel;
    
    private Output leftIndicator;
    private Output rightIndicator;
    private Output headLightsHigh; 
    private Output headLightsLow;
   
    public BikeSack() {
        consoleDisplay = new ConsoleDisplay();
        leftIndicator = new Output("Left Indicator", Output.OFF, 1);
        rightIndicator = new Output("Right Indicator", Output.OFF, 1);
        headLightsHigh = new Output("Head Lights High", Output.OFF);
        headLightsLow = new Output("Head Lights Low", Output.ON);
        instrumentPanel = new HashMap<Instrument.InstrumentType, Instrument>();
        instrumentPanel.put(Instrument.InstrumentType.LEFT_INDICATOR , new BooleanInstrument());
        instrumentPanel.put(Instrument.InstrumentType.RIGHT_INDICATOR , new BooleanInstrument());
        instrumentPanel.put(Instrument.InstrumentType.HIGH_BEAM , new BooleanInstrument());
        instrumentPanel.put(Instrument.InstrumentType.LOW_BEAM , new BooleanInstrument());
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
    public void updateHeadLight (HeadLightLevel head) {
    	
    	//Set head light to low
    	if (head == HeadLightLevel.LOW) {
    		//Check if high beam is on, if it is set high beam to off and low beam to on
    		if (headLightsHigh.getOutputLevel() == Output.ON) 
    		{
    			headLightsHigh.setoutputLevel(Output.OFF);
    			headLightsLow.setoutputLevel(Output.ON);
    		} 
    		else 
    		{
    			headLightsLow.setoutputLevel(Output.ON);
    		}
    	}
    	
    	//Set head light to High
    	if (head == HeadLightLevel.HIGH) {
    		//Set head light to high
    		headLightsLow.setoutputLevel(Output.OFF);
    		headLightsHigh.setoutputLevel(Output.ON);
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

	public static String getUserInput(Scanner input) {
		String selection = input.nextLine();
		if (selection.length() < 1) {
			selection = " ";
		}
		selection = selection.substring(0, 1);
		return selection.toUpperCase();
	}

	public static void setSensors(String selection, Map<CONNECTED_SENSORS, Sensor> sensors) throws SensorException {
		switch (selection) {
		case BRAKE_KEY:
			// Brake Light
			sensors.get(CONNECTED_SENSORS.BRAKE).toggle();
			break;
		case LEFT_INDICATOR_KEY:
			// Left Indicator
			sensors.get(CONNECTED_SENSORS.LEFT_INDICATOR).toggle();
			break;
		case RIGHT_INDICATOR_KEY:
			// Right Indicator
			sensors.get(CONNECTED_SENSORS.RIGHT_INDICATOR).toggle();
			break;
		case HIGH_BEAM_KEY:
			// High Beam Toggle
			sensors.get(CONNECTED_SENSORS.HIGH_BEAM).toggle();
			break;
		case FUEL_INCREASE_KEY:
			// Fuel Level UP
			sensors.get(CONNECTED_SENSORS.FUEL).increase();
			break;
		case FUEL_DECREASE_KEY:
			// Fuel Level DOWN
			sensors.get(CONNECTED_SENSORS.FUEL).decrease();
			break;
		case TEMPERATURE_INCREASE_KEY:
			// Engine Temp UP
			sensors.get(CONNECTED_SENSORS.TEMPERATURE).increase();
			break;
		case TEMPERATURE_DECREASE_KEY:
			// Engine Temp DOWN
			sensors.get(CONNECTED_SENSORS.TEMPERATURE).decrease();
			break;
		case EXIT_KEY:
			// Exit application
			break;
		default:
			System.out.println("Invalid selection, please try again.");
			break;
		}
	}
	
	private static void initialiseSensors(Map<CONNECTED_SENSORS, Sensor> sensors) {
		sensors.put(CONNECTED_SENSORS.BRAKE, new Sensor(0, 1));
		sensors.put(CONNECTED_SENSORS.FUEL, new Sensor(0, 255, 25, 0));
		sensors.put(CONNECTED_SENSORS.HIGH_BEAM, new Sensor(0, 1));
		sensors.put(CONNECTED_SENSORS.LEFT_INDICATOR, new Sensor(0, 1));
		sensors.put(CONNECTED_SENSORS.ODOMETER, new Sensor(0, 1));
		sensors.put(CONNECTED_SENSORS.RIGHT_INDICATOR, new Sensor(0, 1));
		sensors.put(CONNECTED_SENSORS.TEMPERATURE, new Sensor(0, 125, 80));
		sensors.put(CONNECTED_SENSORS.TRIP, new Sensor(0, 1));
	}

	private static void setDummySensorValues(Map<CONNECTED_SENSORS, Sensor> sensors) throws SensorException {
		sensors.get(CONNECTED_SENSORS.BRAKE).setCurrent(0);
		sensors.get(CONNECTED_SENSORS.FUEL).setCurrent(125);
		sensors.get(CONNECTED_SENSORS.HIGH_BEAM).setCurrent(0);
		sensors.get(CONNECTED_SENSORS.LEFT_INDICATOR).setCurrent(0);
		sensors.get(CONNECTED_SENSORS.ODOMETER).setCurrent(0);
		sensors.get(CONNECTED_SENSORS.RIGHT_INDICATOR).setCurrent(0);
		sensors.get(CONNECTED_SENSORS.TEMPERATURE).setCurrent(60);
		sensors.get(CONNECTED_SENSORS.TRIP).setCurrent(0);
	}

	public static void main(String[] args) {
		BikeSack bikeSack = new BikeSack();
		Map<CONNECTED_SENSORS, Sensor> sensors = new HashMap<>();
		Scanner input = new Scanner(System.in);
		String selection;
		ConsoleDisplay consoleDisplay = new ConsoleDisplay();

		// Set up sensors
		initialiseSensors(sensors);

		// Set some reasonable values for testing
		try {
			setDummySensorValues(sensors);
		} catch (SensorException exception) {
			System.out.println("Error setting dummy sensor values");
			System.out.println(exception.getMessage());
		}

		do {
			consoleDisplay.basicGui(bikeSack.instrumentPanel);

			selection = getUserInput(input);

			try {
				setSensors(selection, sensors);
			} catch (SensorException exception) {
				System.out.println("Error setting sensor value");
				System.out.println(exception.getMessage());
			}

		} while (!selection.equals(EXIT_KEY));

		input.close();
	}
}


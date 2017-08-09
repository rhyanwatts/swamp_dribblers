package BikeSack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BikeSack {

	public static final int MAX_FADE_CURRENT = 15;
	public static final int START_FADE_CURRENT = 1;

	// Constant for the wheel diameter in mm. Used for odometer calculations
	public static final int WHEEL_CIRCUMFERENCE = 1950;
	// Multiplier constant to convert wheel circumference unit to instrument unit (mm to Km)
	public static final int WHEEL_MULTIPLIER = 1000000;
	// The number of wheel rotations simulated by a warp
	public static final int WARP_ROTATIONS = 100;

	// Set up constants for the keyboard inputs
	public static final String LEFT_INDICATOR_KEY = "L";
	public static final String RIGHT_INDICATOR_KEY = "R";
	public static final String HIGH_BEAM_KEY = "H";
	public static final String BRAKE_KEY = "B";
	public static final String TEMPERATURE_INCREASE_KEY = "+";
	public static final String TEMPERATURE_DECREASE_KEY = "-";
	public static final String FUEL_INCREASE_KEY = "{";
	public static final String FUEL_DECREASE_KEY = "}";
	public static final String TRIP_RESET_KEY = "T";
	public static final String ODOMETER_INCREASE_KEY = "O";
	public static final String ODOMETER_WARP_KEY = "W";
	public static final String EXIT_KEY = "X";

	// Define the connected sensors, will be used in a map to store the sensors
	public static enum CONNECTED_SENSORS {
		LEFT_INDICATOR, RIGHT_INDICATOR, HIGH_BEAM, BRAKE, TEMPERATURE, FUEL, TRIP, ODOMETER
	}

	// Define the connected outputs, will be used in a map to store the outputs
	public static enum CONNECTED_OUTPUTS {
		LEFT_INDICATOR, RIGHT_INDICATOR, HIGH_BEAM, BRAKE
	}

	// Define the instruments, will be used in a map to store the instruments
	public static enum INSTRUMENTS {
		LEFT_INDICATOR, RIGHT_INDICATOR, HIGH_BEAM, BRAKE, FUEL, TEMPERATURE, TRIP, ODOMETER
	}

	// Private member variables
	private Map<CONNECTED_SENSORS, Sensor> sensors = new HashMap<>();
	private Map<CONNECTED_OUTPUTS, Output> outputs = new HashMap<>();
	private Map<INSTRUMENTS, Instrument> instruments = new HashMap<>();
	private Display display = new ConsoleDisplay();
	private int odometer = 0;
	private int tripMeter = 0;
  
   // Private variables specific to this motorcycle. Avoids MagicNumbers.
   // TODO write these to file and load on startup if dev time remaining

   // Sensor Minimum Values
   private int brakeSenseMin = 0, fuelSenseMin = 0, highBeamSenseMin = 0;
   private int indicatorSenseMin = 0, odometerSenseMin = 0, tempSenseMin = 0;
   private int tripSenseMin = 0;

   // Sensor Maximum Values
   private int brakeSenseMax = 1, highBeamSenseMax = 1, odometerSenseMax = 100, tripSenseMax = 1;
   private int fuelSenseMax = 25;
   private int tempSenseMax = 135;

   // Sensor Warning Values
   private int tempSenseWarn = 120;

   // Sensor Initial Values - If not reusing existing min or max values
   private int tempSenseInit = 25; //ambient air temp. Cold start.

   // Instrument Values
   private String fuelInstUnit = "Liters";
   private String fuelInstUnitSymbol = "L";
   private int fuelInstWarn = 5;
   private boolean fuelInstWarnMax = false;
   private int tempInstMin = 60;
   private String tempInstUnit = "Celsius";
   private String tempInstUnitSmybol = ((char)176 + "C"); //ASCII Deg. Symbol
   private boolean tempInstWarnMax = true;
	
  // Constructor
	public BikeSack() {
		// Set up sensors
		initialiseSensors();

		// Set up the outputs
		initialiseOutputs();

		// Set up the instruments
		initialiseInstruments();
	}

	// Adjust the sensors base on the selection
	public void setSensors(String selection) throws SensorException {
		switch (selection) {
		case BRAKE_KEY:
			// Brake Light
			sensors.get(CONNECTED_SENSORS.BRAKE).toggle();
			System.out
					.println("Sensor [Type= BRAKE, State= " + sensors.get(CONNECTED_SENSORS.BRAKE).getCurrent() + "]");
			break;
		case LEFT_INDICATOR_KEY:
			// Left Indicator
			sensors.get(CONNECTED_SENSORS.LEFT_INDICATOR).toggle(START_FADE_CURRENT);
			System.out.println("Sensor [Type= LEFT_INDICATOR, State= "
					+ sensors.get(CONNECTED_SENSORS.LEFT_INDICATOR).getCurrent() + "]");
			if (sensors.get(CONNECTED_SENSORS.RIGHT_INDICATOR).getCurrent() != sensors
					.get(CONNECTED_SENSORS.RIGHT_INDICATOR).getMin()) {
				sensors.get(CONNECTED_SENSORS.RIGHT_INDICATOR).toggle(START_FADE_CURRENT);
				System.out.println("Sensor [Type= RIGHT_INDICATOR, State= "
						+ sensors.get(CONNECTED_SENSORS.RIGHT_INDICATOR).getCurrent() + "]");
			}
			break;
		case RIGHT_INDICATOR_KEY:
			// Right Indicator
			sensors.get(CONNECTED_SENSORS.RIGHT_INDICATOR).toggle(START_FADE_CURRENT);
			System.out.println("Sensor [Type= RIGHT_INDICATOR, State= "
					+ sensors.get(CONNECTED_SENSORS.RIGHT_INDICATOR).getCurrent() + "]");
			if (sensors.get(CONNECTED_SENSORS.LEFT_INDICATOR).getCurrent() != sensors
					.get(CONNECTED_SENSORS.LEFT_INDICATOR).getMin()) {
				sensors.get(CONNECTED_SENSORS.LEFT_INDICATOR).toggle(START_FADE_CURRENT);
				System.out.println("Sensor [Type= LEFT_INDICATOR, State= "
						+ sensors.get(CONNECTED_SENSORS.LEFT_INDICATOR).getCurrent() + "]");
			}
			break;
		case HIGH_BEAM_KEY:
			// High Beam Toggle
			sensors.get(CONNECTED_SENSORS.HIGH_BEAM).toggle();
			System.out.println(
					"Sensor [Type= HIGH_BEAM, State= " + sensors.get(CONNECTED_SENSORS.HIGH_BEAM).getCurrent() + "]");
			break;
		case FUEL_INCREASE_KEY:
			// Fuel Level UP
			sensors.get(CONNECTED_SENSORS.FUEL).increase();
			System.out.println("Sensor [Type= FUEL, State= " + sensors.get(CONNECTED_SENSORS.FUEL).getCurrent() + "]");
			break;
		case FUEL_DECREASE_KEY:
			// Fuel Level DOWN
			sensors.get(CONNECTED_SENSORS.FUEL).decrease();
			System.out.println("Sensor [Type= FUEL, State= " + sensors.get(CONNECTED_SENSORS.FUEL).getCurrent() + "]");
			break;
		case TEMPERATURE_INCREASE_KEY:
			// Engine Temp UP
			sensors.get(CONNECTED_SENSORS.TEMPERATURE).increase();
			System.out.println("Sensor [Type= TEMPERATURE, State= "
					+ sensors.get(CONNECTED_SENSORS.TEMPERATURE).getCurrent() + "]");
			break;
		case TEMPERATURE_DECREASE_KEY:
			// Engine Temp DOWN
			sensors.get(CONNECTED_SENSORS.TEMPERATURE).decrease();
			System.out.println("Sensor [Type= TEMPERATURE, State= "
					+ sensors.get(CONNECTED_SENSORS.TEMPERATURE).getCurrent() + "]");
			break;
		case ODOMETER_INCREASE_KEY:
			sensors.get(CONNECTED_SENSORS.ODOMETER).setCurrent(1);
			;
			System.out.println(
					"Sensor [Type= ODOMETER, State= " + sensors.get(CONNECTED_SENSORS.ODOMETER).getCurrent() + "]");
			break;
		case ODOMETER_WARP_KEY:
			sensors.get(CONNECTED_SENSORS.ODOMETER).setCurrent(WARP_ROTATIONS);
			System.out.println(
					"Sensor [Type= ODOMETER, State= " + sensors.get(CONNECTED_SENSORS.ODOMETER).getCurrent() + "]");
			break;
		case TRIP_RESET_KEY:
			sensors.get(CONNECTED_SENSORS.TRIP).toggle();
			System.out.println("Sensor [Type= TRIP, State= " + sensors.get(CONNECTED_SENSORS.TRIP).getCurrent() + "]");
			break;
		case EXIT_KEY:
			// Exit application
			break;
		default:
			System.out.println("Invalid selection, please try again.");
			break;
		}
	}

   // Set up the sensors
   private void initialiseSensors() {
      sensors.put(CONNECTED_SENSORS.BRAKE, new Sensor(brakeSenseMin, brakeSenseMax));
      sensors.put(CONNECTED_SENSORS.FUEL, new Sensor(fuelSenseMin, fuelSenseMax));
      sensors.put(CONNECTED_SENSORS.HIGH_BEAM, new Sensor(highBeamSenseMin, highBeamSenseMax));
      sensors.put(CONNECTED_SENSORS.LEFT_INDICATOR, new Sensor(indicatorSenseMin, MAX_FADE_CURRENT));
      sensors.put(CONNECTED_SENSORS.ODOMETER, new Sensor(odometerSenseMin, odometerSenseMax));
      sensors.put(CONNECTED_SENSORS.RIGHT_INDICATOR, new Sensor(indicatorSenseMin, MAX_FADE_CURRENT));
      sensors.put(CONNECTED_SENSORS.TEMPERATURE, new Sensor(tempSenseMin, tempSenseMax, tempSenseWarn));
      sensors.put(CONNECTED_SENSORS.TRIP, new Sensor(tripSenseMin, tripSenseMax));
   }

   // Set up the outputs
   private void initialiseOutputs() {
      outputs.put(CONNECTED_OUTPUTS.LEFT_INDICATOR, new Output("Left Indicator", Output.OFF));
      outputs.put(CONNECTED_OUTPUTS.RIGHT_INDICATOR, new Output("Right Indicator", Output.OFF));
      outputs.put(CONNECTED_OUTPUTS.HIGH_BEAM, new Output("Head Lights High", Output.OFF));
      outputs.put(CONNECTED_OUTPUTS.BRAKE, new Output("Brake Light", Output.OFF));
   }

   // Set up the instruments
   private void initialiseInstruments() {
      instruments.put(INSTRUMENTS.LEFT_INDICATOR, new BooleanInstrument());
      instruments.put(INSTRUMENTS.RIGHT_INDICATOR, new BooleanInstrument());
      instruments.put(INSTRUMENTS.HIGH_BEAM, new HeadlightInstrument());
      instruments.put(INSTRUMENTS.BRAKE, new BooleanInstrument());
      instruments.put(INSTRUMENTS.FUEL, new RangeInstrument(fuelSenseMin, fuelSenseMax, fuelSenseMax, fuelInstUnit,
            fuelInstUnitSymbol, fuelInstWarn, fuelInstWarnMax));
      instruments.put(INSTRUMENTS.TEMPERATURE, new RangeInstrument(tempInstMin, tempSenseMax, tempSenseInit,
            tempInstUnit, tempInstUnitSmybol, tempSenseWarn, tempInstWarnMax));
		instruments.put(INSTRUMENTS.ODOMETER, new TextualInstrument(odometer, WHEEL_MULTIPLIER, "Km"));
		instruments.put(INSTRUMENTS.TRIP, new TextualInstrument(tripMeter, WHEEL_MULTIPLIER, "Km"));
	}

// Set the sensors to have plausable defaults since we don't have real sensors
   private void setDummySensorValues() throws SensorException {
      sensors.get(CONNECTED_SENSORS.BRAKE).setCurrent(brakeSenseMin); //off
      sensors.get(CONNECTED_SENSORS.FUEL).setCurrent(fuelSenseMax); //full tank
      sensors.get(CONNECTED_SENSORS.HIGH_BEAM).setCurrent(highBeamSenseMin); //off
      sensors.get(CONNECTED_SENSORS.LEFT_INDICATOR).setCurrent(indicatorSenseMin); //off
      sensors.get(CONNECTED_SENSORS.ODOMETER).setCurrent(odometerSenseMin); // Zero
      sensors.get(CONNECTED_SENSORS.RIGHT_INDICATOR).setCurrent(indicatorSenseMin); //off
      sensors.get(CONNECTED_SENSORS.TEMPERATURE).setCurrent(tempSenseInit); //Ambient Temp
      sensors.get(CONNECTED_SENSORS.TRIP).setCurrent(tripSenseMin); // Zero
   }

	// Set the outputs based on the inputs
	private void updateOutputs() {
		for (CONNECTED_SENSORS sensorName : sensors.keySet()) {
			for (CONNECTED_OUTPUTS outputName : outputs.keySet()) {
				if (sensorName.name().equals(outputName.name())) {
					Sensor sensor = sensors.get(sensorName);
					int sensorValue = sensor.getCurrent();

					Output output = outputs.get(outputName);
					int outputValue = output.getOutputLevel();

					// Convert the Sensor range to the Output range
					int sensorToOutput = (sensorValue / sensor.getMax()) * Output.ON;

					if (sensorToOutput != outputValue) {
						outputs.get(outputName).setoutputLevel(sensorToOutput);
					}
				}
			}
		}
	}

	// Set the instruments based on the inputs
	private void updateInstruments() throws SensorException {
		for (CONNECTED_SENSORS sensorName : sensors.keySet()) {
			for (INSTRUMENTS instrumentName : instruments.keySet()) {

				// Separate logic to update stored odometer variable and decrease sensor as we are only simulating it 
				if (sensorName.name().equals(CONNECTED_SENSORS.ODOMETER.name())
						&& sensorName.name().equals(instrumentName.name())) {
					Sensor sensor = sensors.get(sensorName);
					int sensorValue = sensor.getCurrent();

					while (sensorValue != sensor.getMin()) {
						odometer += WHEEL_CIRCUMFERENCE;

						Instrument instrument = instruments.get(instrumentName);
						instrument.setCurrent(odometer);

						instruments.get(INSTRUMENTS.TRIP).setCurrent(odometer - tripMeter);

						sensor.setCurrent(--sensorValue);
						System.out.println("Sensor [Type= ODOMETER, State= " + sensor.getCurrent() + "]");
					}
					
				// Trip meter also needs different logic as sensor does not translate directly to instrument
				} else if (sensorName.name().equals(CONNECTED_SENSORS.TRIP.name())
						&& sensorName.name().equals(instrumentName.name())) {
					Sensor sensor = sensors.get(sensorName);
					int sensorValue = sensor.getCurrent();

					if (sensorValue != sensor.getMin()) {
						tripMeter = odometer;

						Instrument instrument = instruments.get(instrumentName);
						instrument.setCurrent(odometer - tripMeter);
					}
					
				// Everything else, set the instrument value to the sensor value
				} else if (sensorName.name().equals(instrumentName.name())) {
					Sensor sensor = sensors.get(sensorName);
					int sensorValue = sensor.getCurrent();

					Instrument instrument = instruments.get(instrumentName);
					int instrumentValue = instrument.getCurrent();

					if (instrumentValue != sensorValue) {
						instruments.get(instrumentName).setCurrent(sensorValue);
					}
				}
			}
		}
	}

	private void showDisplay() {
		display.show(instruments);
	}

	// Get the user's menu selection
	public static String getUserInput(Scanner input) {
		String selection = input.nextLine();
		if (selection.length() < 1) {
			selection = " ";
		}
		selection = selection.substring(0, 1);
		return selection.toUpperCase();
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		String selection;

		BikeSack bikeSack = new BikeSack();

		// Set some reasonable values for testing
		try {
			bikeSack.setDummySensorValues();
		} catch (SensorException exception) {
			System.out.println("Error setting dummy sensor values");
			System.out.println(exception.getMessage());
		}

		do {
			// Set the outputs based on the sensor values
			bikeSack.updateOutputs();

			// Set the instruments based on the sensor values
			try {
				bikeSack.updateInstruments();
			} catch (SensorException exception) {
				System.out.println("Error setting sensor value");
				System.out.println(exception.getMessage());
			}

			// Show the interface
			bikeSack.showDisplay();

			// Get user input
			selection = getUserInput(input);

			// Set the sensors based on user input. Would not be necessary once using real
			// sensors
			try {
				bikeSack.setSensors(selection);
			} catch (SensorException exception) {
				System.out.println("Error setting sensor value");
				System.out.println(exception.getMessage());
			}

		} while (!selection.equals(EXIT_KEY));

		// Close the scanner
		input.close();
	}

}

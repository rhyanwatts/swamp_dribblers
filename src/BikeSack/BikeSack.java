package BikeSack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Bike Sack is a complete motorcycle computer instrument and light control
 * solution. Using a Raspberry PI hardware the Java based solution manages and
 * monitors all aspects of a motorcycle's electrical system. Information
 * collected about the current state of the bike is output to screen to keep the
 * rider well informed.
 * <p>
 * System produced as a group project for RMIT CPT111 Building IT Systems [BITS]
 * SP2 2017.
 * </p>
 * <h3>Course Instructor:</h3> Byron Fisher.
 * <h3>Team Mentor:</h3> Edward Watkins.
 * <h3>Team Leader</h3> Ben Denford
 * <h3>Dev Team 1</h3> Steven Grueber, Aidan Holmes
 * <h3>Dev Team 2</h3> Matthew Flack, Rhyan Watts
 * 
 * 
 * @author Aidan Holmes | S3355003
 * @author Matthew Flack | S3493444
 * @author Steven Grueber | S3502715
 * @author Ben Denford | S3573670
 * @author Rhyan Watts | S3590266
 * @version 1.0
 */
public class BikeSack {

    public static final int MAX_FADE_CURRENT = 15;
    public static final int START_FADE_CURRENT = 1;

    /**
     * Constant for the wheel diameter in mm. Used for odometer calculations
     */
    public static final int WHEEL_CIRCUMFERENCE = 1950;
    /**
     * Multiplier constant to convert wheel circumference unit to instrument unit
     * (mm to Km)
     */
    public static final int WHEEL_MULTIPLIER = 1000000;
    /**
     * The number of wheel rotations simulated by a warp
     */
    public static final int WARP_ROTATIONS = 100;
    /**
     * Constant multiplier for fuel usage
     */
    public static final int USAGE_MULTIPLIER = 100;

    /**
     * The menu option which selects left indicator
     */
    public static final String LEFT_INDICATOR_KEY = "L";
    /**
     * The menu option which selects right indicator
     */
    public static final String RIGHT_INDICATOR_KEY = "R";
    /**
     * The menu option which selects high beam
     */
    public static final String HIGH_BEAM_KEY = "H";
    /**
     * The menu option which selects braking
     */
    public static final String BRAKE_KEY = "B";
    /**
     * The menu option which selects to increase the engine temperature
     */
    public static final String TEMPERATURE_INCREASE_KEY = "+";
    /**
     * The menu option which selects to decrease the engine temperature
     */
    public static final String TEMPERATURE_DECREASE_KEY = "-";
    /**
     * The menu option which selects to increase the amount of fuel remaining
     */
    public static final String FUEL_INCREASE_KEY = "{";
    /**
     * The menu option which selects to decrease the amount of fuel remaining
     */
    public static final String FUEL_DECREASE_KEY = "}";
    /**
     * The menu option which selects to toggle the trip meter button being pressed
     */
    public static final String TRIP_RESET_KEY = "T";
    /**
     * The menu option which selects to trigger the odometer wheel sensor
     */
    public static final String ODOMETER_INCREASE_KEY = "O";
    /**
     * The menu option which selects to trigger the odometer wheel sensor multiple
     * times
     */
    public static final String ODOMETER_WARP_KEY = "W";
    /**
     * The menu option which displays the Full graphical GUI
     */
    public static final String SHOW_FULL_GUI = "G";
    /**
     * The menu option which selects to exit the application
     */
    public static final String EXIT_KEY = "X";

    /**
     * The connected sensors
     */
    public static enum CONNECTED_SENSORS {
        LEFT_INDICATOR, RIGHT_INDICATOR, HIGH_BEAM, BRAKE, TEMPERATURE, FUEL, TRIP, ODOMETER
    }

    /**
     * The connected outputs
     */
    public static enum CONNECTED_OUTPUTS {
        LEFT_INDICATOR, RIGHT_INDICATOR, HIGH_BEAM, BRAKE
    }

    /**
     * The connected instruments
     */
    public static enum INSTRUMENTS {
        LEFT_INDICATOR, RIGHT_INDICATOR, HIGH_BEAM, BRAKE, FUEL, TEMPERATURE, TRIP, ODOMETER, FUEL_USAGE
    }

    /**
     * The Sensors that are connected to the system
     * 
     * @see CONNECTED_SENSORS
     * @see Sensor
     */
    private Map<CONNECTED_SENSORS, Sensor> sensors = new HashMap<>();
    /**
     * The Outputs that are connected to the system
     * 
     * @see CONNECTED_OUTPUTS
     * @see Output
     */
    private Map<CONNECTED_OUTPUTS, Output> outputs = new HashMap<>();
    /**
     * The Instruments that are connected to the system
     * 
     * @see INSTRUMENTS
     * @see Instrument
     */
    private Map<INSTRUMENTS, Instrument> instruments = new HashMap<>();
    /**
     * The <code>Display</code> for the system. This will be what displays the
     * <code>Instruments</code> to the user
     * 
     * @see Display
     * @see Instrument
     */
    private Display display = new ConsoleDisplay();
    /**
     * How far the vehicle the BikeSack is attached to has traveled since the system
     * was started
     */
    private int odometer = 0;
    /**
     * How far the vehicle has traveled since the trip meter was last reset
     */
    private int tripMeter = 0;

    /**
     * The minimum value for the <code>Sensor</code>
     * 
     * @see Sensor
     */
    private int brakeSenseMin = 0, fuelSenseMin = 0, highBeamSenseMin = 0, indicatorSenseMin = 0, odometerSenseMin = 0,
            tempSenseMin = 0, fuelUsageMin = 0, tripSenseMin = 0;
    /**
     * The maximum value for the <code>Sensor</code>
     * 
     * @see Sensor
     */
    private int brakeSenseMax = 1, highBeamSenseMax = 1, odometerSenseMax = 100, tripSenseMax = 1, fuelSenseMax = 25,
            tempSenseMax = 135;
    /**
     * The warning value for the <code>Sensor</code>
     * 
     * @see Sensor
     */
    private int tempSenseWarn = 120;
    /**
     * The initial dummy value for the <code>Sensor</code>. Set to reasonable
     * ambient air temperature
     * 
     * @see Sensor
     */
    private int tempSenseInit = 25;

    /**
     * Sets the unit for the fuel <code>Instrument</code>
     * 
     * @see Instrument
     */
    private String fuelInstUnit = "Liters";
    /**
     * Sets the unit symbol for the fuel <code>Instrument</code>
     * 
     * @see Instrument
     */
    private String fuelInstUnitSymbol = "L";
    /**
     * Sets the warning value for the fuel <code>Instrument</code>
     * 
     * @see Instrument
     */
    private int fuelInstWarn = 5;
    /**
     * Sets the warning value as a minimum safe value for the fuel
     * <code>Instrument</code>
     * 
     * @see Instrument
     * @see RangeInstrument
     */
    private boolean fuelInstWarnMax = false;

    /**
     * Sets the minimum value for the temperature <code>Instrument</code>
     * 
     * @see Instrument
     */
    private int tempInstMin = 60;
    /**
     * Sets the unit for the temperature <code>Instrument</code>
     * 
     * @see Instrument
     */
    private String tempInstUnit = "Celsius";
    /**
     * Sets the unit symbol for the temperature <code>Instrument</code>
     * 
     * @see Instrument
     */
    private String tempInstUnitSmybol = ((char) 176 + "C"); // ASCII Deg. Symbol
    /**
     * Sets the warning value as a maximum safe value for the temperature
     * <code>Instrument</code>
     * 
     * @see Instrument
     * @see RangeInstrument
     */
    private boolean tempInstWarnMax = true;

    /**
     * Creates a new BikeSack
     */
    public BikeSack() {
        // Set up sensors
        initialiseSensors();

        // Set up the outputs
        initialiseOutputs();

        // Set up the instruments
        initialiseInstruments();
    }

    /**
     * Triggers the <code>Sensor</code> associated with the menu option selected.
     * 
     * @param selection
     *            The input from the user
     * @see Sensor
     * @see #getUserInput(Scanner)
     * @throws SensorException
     *             The Sensor was not able to be modified in the requested way
     */
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
            ((UsageInstrument) instruments.get(INSTRUMENTS.FUEL_USAGE))
                    .setStartNumerator(instruments.get(INSTRUMENTS.FUEL).getCurrent());
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
        case SHOW_FULL_GUI:
        	GuiDisplay display = new GuiDisplay();
        	display.show(instruments);
        	break;
        case EXIT_KEY:
            // Exit application
            break;
        default:
            System.out.println("Invalid selection, please try again.");
            break;
        }
    }

    /**
     * Creates all the <code>Sensors</code> and stores them
     * 
     * @see Sensor
     */
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

    /**
     * Creates all the <code>Outputs</code> and stores them
     * 
     * @see Output
     */
    private void initialiseOutputs() {
        outputs.put(CONNECTED_OUTPUTS.LEFT_INDICATOR, new Output("Left Indicator", Output.OFF));
        outputs.put(CONNECTED_OUTPUTS.RIGHT_INDICATOR, new Output("Right Indicator", Output.OFF));
        outputs.put(CONNECTED_OUTPUTS.HIGH_BEAM, new Output("Head Lights High", Output.OFF));
        outputs.put(CONNECTED_OUTPUTS.BRAKE, new Output("Brake Light", Output.OFF));
    }

    /**
     * Creates all the <code>Instruments</code> and stores them
     * 
     * @see Instrument
     * @see HeadlightInstrument
     * @see BooleanInstrument
     * @see RangeInstrument
     * @see TextualInstrument
     * @see UsageInstrument
     */
    private void initialiseInstruments() {
        instruments.put(INSTRUMENTS.LEFT_INDICATOR, new Instrument());
        instruments.put(INSTRUMENTS.RIGHT_INDICATOR, new Instrument());
        instruments.put(INSTRUMENTS.HIGH_BEAM, new HeadlightInstrument());
        instruments.put(INSTRUMENTS.BRAKE, new BooleanInstrument());
        instruments.put(INSTRUMENTS.FUEL, new RangeInstrument(fuelSenseMin, fuelSenseMax, fuelSenseMax, fuelInstUnit,
                fuelInstUnitSymbol, fuelInstWarn, fuelInstWarnMax));
        instruments.put(INSTRUMENTS.TEMPERATURE, new RangeInstrument(tempInstMin, tempSenseMax, tempSenseInit,
                tempInstUnit, tempInstUnitSmybol, tempSenseWarn, tempInstWarnMax));
        instruments.put(INSTRUMENTS.ODOMETER, new TextualInstrument(odometer, WHEEL_MULTIPLIER, "Km"));
        instruments.put(INSTRUMENTS.TRIP, new TextualInstrument(tripMeter, WHEEL_MULTIPLIER, "Km"));
        instruments.put(INSTRUMENTS.FUEL_USAGE, new UsageInstrument(fuelUsageMin, "L/100Km", fuelSenseMax,
                odometerSenseMin, WHEEL_MULTIPLIER, USAGE_MULTIPLIER));
    }

    /**
     * Sets the <code>Sensor</code> to have plausible defaults since we don't have
     * real sensors
     * 
     * @see Sensor
     * @throws SensorException
     *             When the Sensor can't be set to the requested value
     */
    private void setDummySensorValues() throws SensorException {
        sensors.get(CONNECTED_SENSORS.BRAKE).setCurrent(brakeSenseMin); // off
        sensors.get(CONNECTED_SENSORS.FUEL).setCurrent(fuelSenseMax); // full tank
        sensors.get(CONNECTED_SENSORS.HIGH_BEAM).setCurrent(highBeamSenseMin); // off
        sensors.get(CONNECTED_SENSORS.LEFT_INDICATOR).setCurrent(indicatorSenseMin); // off
        sensors.get(CONNECTED_SENSORS.ODOMETER).setCurrent(odometerSenseMin); // Zero
        sensors.get(CONNECTED_SENSORS.RIGHT_INDICATOR).setCurrent(indicatorSenseMin); // off
        sensors.get(CONNECTED_SENSORS.TEMPERATURE).setCurrent(tempSenseInit); // Ambient Temp
        sensors.get(CONNECTED_SENSORS.TRIP).setCurrent(tripSenseMin); // Zero
    }

    /**
     * Sets the <code>Outputs</code> based on the <code>Sensors</code>
     * 
     * @see Sensor
     * @see Output
     */
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

    /**
     * Sets the <code>Instruments</code> based on the <code>Sensors</code>. Contains
     * different logic for the odometer and trip meter
     * 
     * @see Sensor
     * @see Instrument
     * @throws SensorException
     *             WHen the Sensor is set outside its valid range
     */
    private void updateInstruments() throws SensorException {
        for (CONNECTED_SENSORS sensorName : sensors.keySet()) {
            for (INSTRUMENTS instrumentName : instruments.keySet()) {

                // Separate logic to update stored odometer variable and decrease sensor as we
                // are only simulating it
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

                    // Trip meter also needs different logic as sensor does not translate directly
                    // to instrument
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
        if (((UsageInstrument) instruments.get(INSTRUMENTS.FUEL_USAGE)).getLastNumerator() >= instruments
                .get(INSTRUMENTS.FUEL).getCurrent()) {
            ((UsageInstrument) instruments.get(INSTRUMENTS.FUEL_USAGE)).setUsageCurrent(
                    (double) instruments.get(INSTRUMENTS.FUEL).getCurrent(),
                    (double) instruments.get(INSTRUMENTS.ODOMETER).getCurrent());
        } else {
            ((UsageInstrument) instruments.get(INSTRUMENTS.FUEL_USAGE))
                    .setStartNumerator(instruments.get(INSTRUMENTS.FUEL).getCurrent());
        }
    }

    /**
     * Triggers the <code>Display</code> to show itself
     * 
     * @see #display
     * @see Display
     */
    private void showDisplay() {
        display.show(instruments);
    }

    /**
     * Gets the user's menu selection
     * 
     * @param input
     *            A Scanner attached to the input source
     * @return A String containing a single character
     */
    public static String getUserInput(Scanner input) {
        String selection = input.nextLine();
        if (selection.length() < 1) {
            selection = " ";
        }
        selection = selection.substring(0, 1);
        return selection.toUpperCase();
    }

    /**
     * The main method of the application. Creates a new <code>BikeSack</code>
     * 
     * @see #BikeSack()
     * @param args
     *            The command line arguments
     */
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

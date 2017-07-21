import java.util.HashMap;
import java.util.Map;


// TODO: Move this...Not sure which class to put this in? Maybe output?
enum IndicatorDirection {
    LEFT, RIGHT, NONE
}

public class BikeSack {
    
    // Should this go in output class?
    public static final int MAX_FADE_CURRENT = 10;
    
    private ConsoleDisplay consoleDisplay;
    private Map<Instrument.InstrumentType, Instrument> instrumentPanel;
    
    private Output leftIndicator;
    private Output rightIndicator;
    
    
    public BikeSack() {
        consoleDisplay = new ConsoleDisplay();
        leftIndicator = new Output("Left Indicator", Output.OFF, 1);
        rightIndicator = new Output("Left Indicator", Output.OFF, 1);
        instrumentPanel = new HashMap<Instrument.InstrumentType, Instrument>();
        instrumentPanel.put(Instrument.InstrumentType.LEFT_INDICATOR , new BooleanInstrument());
        instrumentPanel.put(Instrument.InstrumentType.RIGHT_INDICATOR , new BooleanInstrument());
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
    
}

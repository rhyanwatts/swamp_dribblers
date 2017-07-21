import java.util.HashMap;
import java.util.Map;


// TODO: Move this...Not sure which class to put this in?
enum IndicatorDirection {
    LEFT, RIGHT, NONE
}

public class BikeSack {
    
    private ConsoleDisplay consoleDisplay;
    private Map<Instrument.InstrumentType, Instrument> instrumentPanel;
    
    private Output leftIndicator;
    private Output rightIndicator;
    
    
    public BikeSack() {
        consoleDisplay = new ConsoleDisplay();
        leftIndicator = new Output();
        rightIndicator = new Output();
        instrumentPanel = new HashMap<Instrument.InstrumentType, Instrument>();
        instrumentPanel.put(Instrument.InstrumentType.LEFT_INDICATOR , new BooleanInstrument());
        instrumentPanel.put(Instrument.InstrumentType.RIGHT_INDICATOR , new BooleanInstrument());
    }
    
    public void updateDisplay() {
        
        consoleDisplay.updateConsole(instrumentPanel);
    }
    
    
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
                leftIndicator.setoutputLevel(Output.ON);
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
                rightIndicator.setoutputLevel(Output.ON);
            }
        }
        updateIndicatorInstruments();
        updateDisplay();
    }

    private void updateIndicatorInstruments() {
        if(leftIndicator.isOn())
        {
            instrumentPanel.get(Instrument.InstrumentType.LEFT_INDICATOR).setCurrent(Output.ON);
        } else {
            instrumentPanel.get(Instrument.InstrumentType.LEFT_INDICATOR).setCurrent(Output.OFF);
        }
        
        if(rightIndicator.isOn())
        {
            instrumentPanel.get(Instrument.InstrumentType.RIGHT_INDICATOR).setCurrent(Output.ON);
        } else {
            instrumentPanel.get(Instrument.InstrumentType.RIGHT_INDICATOR).setCurrent(Output.OFF);
        }
    }
    
}

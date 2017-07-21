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
}
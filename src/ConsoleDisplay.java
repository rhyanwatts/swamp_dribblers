import java.util.Map;

public class ConsoleDisplay extends Display {
    
    private static final int INDICATOR_LEN = 10;
    private static final char LEFT_INDICATOR_CHAR = '<';
    private static final char RIGHT_INDICATOR_CHAR = '>';
    
    public void updateConsole
        (Map<Instrument.InstrumentType, Instrument> instrumentPanel) {
        drawIndicators(instrumentPanel.get(Instrument.InstrumentType.LEFT_INDICATOR),
            instrumentPanel.get(Instrument.InstrumentType.RIGHT_INDICATOR));   
    }

    private void drawIndicators(Instrument left, Instrument right) {
        if(left.getCurrent() > 0) {
            drawOnIndicator(LEFT_INDICATOR_CHAR, 10);
        } else {
            drawOffIndicator();
        }
            
        if(right.getCurrent() > 0) {
            drawOnIndicator(RIGHT_INDICATOR_CHAR, 10);
        } else {
            drawOffIndicator();
        }
    }
    
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
    
    private void drawOffIndicator() {
        System.out.print(" [");
        for(int i = 0; i < INDICATOR_LEN; ++i)
        {
            System.out.print(" ");
        }
        System.out.print("] ");
    }
}

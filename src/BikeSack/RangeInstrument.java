package BikeSack;

public class RangeInstrument extends Instrument {

    private int min;
    private int max;
    
    public RangeInstrument() {
        // Default amounts
        min = 0;
        max = 50;
    }
    
    public RangeInstrument(int max) {
        min = 0;
        this.max = max;
    }
    
    // Rounds down
    public int getPercentage() {
        return (int) ( (double)super.getCurrent() / (double)max * 100.0);
    }
    
    @Override
    public String toString() {
        char fullChar = '*';
        char emptyChar = '-';
        
        // Number of characters in the instrument 
        int totalLevelChars = 10;
        
        // Calculate percentage
        double gaugeLevel = ( (double)super.getCurrent() / (double)max ) * 100.0;
        
        int numFullChars = (int)gaugeLevel / totalLevelChars;
        
        StringBuilder gaugeBuilder = new StringBuilder();
        
        // Add the full/empty chars to the string
        for(int i = 0; i < totalLevelChars; ++i)
        {
            if (i < numFullChars) {
                gaugeBuilder.append(fullChar);
            } else {
                gaugeBuilder.append(emptyChar);
            }
        }
        return gaugeBuilder.toString();
    }

}

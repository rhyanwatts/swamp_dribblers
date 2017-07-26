
public abstract class Instrument {
    
    public enum InstrumentType {
        LEFT_INDICATOR, RIGHT_INDICATOR, HIGH_BEAM, LOW_BEAM, BRAKE_LIGHT
    }
    
    private int current;
    
    public Instrument() {
        current = 0;
    }
    
    public void setCurrent(int current) {
        this.current = current;
    }
    
    public int getCurrent() {
        return current;
    }
}

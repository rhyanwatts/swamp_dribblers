package BikeSack;

public class Instrument {

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

    public boolean isOn() {
        if(current > 0) {
            return true;
        }
        return false;
    }

	public void setUsageCurrent(double numerator, double denominator) {
		
	}

	public void resetUsage(int current, double numerator, double denominator) {
		
	}
}

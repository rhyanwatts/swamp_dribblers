import java.util.Scanner;

public class SACK_headLights {
	boolean headlight = false;
	
	public void setHeadLight() {
		boolean x = false;
		String selection;
		Scanner keyboard = new Scanner(System.in);
		
		while (x == false) {
			
			System.out.println("Current Headlight Status - " + getHeadLight());
			System.out.println("Please Enter High or Low");
			selection = keyboard.next();
			
			switch (selection.toLowerCase()) {
				case "high":
					headlight = true;
					x = true;
					break;
				case "low":
					headlight = false;
					x = true;
					break;
				default:
					System.out.println("Pleaase enter a valid selection");
					break;
			}
		}
		keyboard.close();
	}
	
	public String getHeadLight() {
		if (headlight == true) {
			return "High";
		} else {
			return "Low";
		}
	}
	
}

import java.util.Scanner;

public class SACK_Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Variables
		boolean x = false;
		int selection;
		Scanner keyboard = new Scanner(System.in);
		SACK_headLights headlight = new SACK_headLights();
		
		
		while (x == false) {
			try {
				System.out.println("The Bike SACK");
				System.out.println("1. Brake Lights" + " getBrakeLights()");
				System.out.println("2. Left Indicator" + " getLeftIndicator()");
				System.out.println("3. Right Indicator" + " getRightIndicator()");
				System.out.println("4. Headlights " + headlight.getHeadLight());
				System.out.println("5. Fuel Level");
				System.out.println("6. Engine Temp");
				System.out.println("7. Odometer");
				System.out.println("8. Trip Meter");
				System.out.println("9. Fuel Usage");
				System.out.println("0 - Quit");
				System.out.println("Please Enter a Selection(number)");
				selection = keyboard.nextInt();
				switch (selection) {
					case 1:
						//Feature 1
						break;
					case 2:
						//Feature 2
						break;
					case 3:
						//Feature 3
						break;
					case 4:
						//Feature 4
						headlight.setHeadLight();
						break;
					case 5:
						//Feature 5
						break;
					case 6:
						//Feature 6
						break;
					case 7:
						//Feature 7
						break;
					case 8:
						//Feature 8
						break;
					case 9:
						//Feature 9
						break;
					case 0:
						System.exit(1);
						break;
					default:
						System.out.println("Please Enter a Valid Selection");
						break;
				}
			} catch (Exception ex) {
				System.out.println("Please Enter a Valid Selection");
				keyboard.nextLine();
			}
		}
		keyboard.close();
	}
}

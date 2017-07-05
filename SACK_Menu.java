import java.util.Scanner;

public class SACK_Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Variables
		boolean x = false;
		int selection;
		Scanner keyboard = new Scanner(System.in);
		
		
		while (x == false) {
			System.out.println("Please Enter a Selection(number)");
			System.out.println("1 - Feature 1");
			System.out.println("2 - Feature 2");
			System.out.println("3 - Feature 3");
			System.out.println("4 - Feature 4");
			System.out.println("5 - Feature 5");
			System.out.println("0 - Quit");
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
				//Feature 5
				break;
			case 5:
				//Feature 5
				break;
			case 0:
				System.exit(1);
				break;
			default:
				System.out.println("Please Enter a Valid Selection");
				break;
			}
		}
	}

}

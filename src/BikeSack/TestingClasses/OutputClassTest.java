package BikeSack.TestingClasses;

import BikeSack.Output;

public class OutputClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Output brake = new Output("brake light");

		System.out.println(brake.getOutputLevel());
		System.out.println(brake.getOutputType());
		brake.inc();
		System.out.println(brake.getOutputLevel());
		brake.setIncrementStep(50);
		brake.inc();
		System.out.println(brake.getOutputLevel());
		brake.inc();
		brake.inc();
		brake.inc();
		System.out.println(brake.getOutputLevel());
		if (brake.inc()) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		System.out.println(brake.getOutputLevel());
		if (brake.inc()) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		System.out.println(brake.getOutputLevel());

		System.out.println(brake.getIncrementStep());
		System.out.println(brake.getOutputLevel());
		System.out.println(brake.getOutputType());

		if (brake.dec()) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		System.out.println(brake.getOutputLevel());
		if (brake.dec()) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		System.out.println(brake.getOutputLevel());
		if (brake.dec()) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		System.out.println(brake.getOutputLevel());
		if (brake.dec()) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		System.out.println(brake.getOutputLevel());
		if (brake.dec()) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		System.out.println(brake.getOutputLevel());
		if (brake.dec()) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		System.out.println(brake.getOutputLevel());

		brake.setIncrementStep(255);
		if (brake.dec()) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		System.out.println(brake.getOutputLevel());
		if (brake.inc()) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		System.out.println(brake.getOutputLevel());
		brake.toggle();
		brake.toggle();

	}

}

package BikeSack.TestingClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import BikeSack.ConsoleDisplay;
import BikeSack.Display;
import BikeSack.GuiDisplay;
import BikeSack.Instrument;
import BikeSack.BikeSack.INSTRUMENTS;

public class GuiDisplayTest {

	public static void main(String[] args) {
		
		Map<INSTRUMENTS, Instrument> instruments = new HashMap<>();
		Display display = new GuiDisplay();
		display.show(instruments);
		
		//Turn on Low Beam

		for(ActionListener a: ((GuiDisplay)display).btnLowBeam.getActionListeners())
		{
			a.actionPerformed(null);
		}
		for(ActionListener a: ((GuiDisplay)display).btnLowBeam.getActionListeners())
		{
			a.actionPerformed(null);
		}
		
		//Loop Indicator
		for(int i = 0; i < 5; i++)
		{
			((GuiDisplay)display).indicatorFadeLeft(((GuiDisplay)display).indicatorLeft);
			((GuiDisplay)display).indicatorFadeRight(((GuiDisplay)display).indicatorLeft);
		}

			
	}					

}

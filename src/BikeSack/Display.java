package BikeSack;

import java.util.Map;

import BikeSack.BikeSack.INSTRUMENTS;

/**
 * Abstract class for a BikeSack Display objects. Ensures that all
 * implementations of display can be updated by calling
 * {@code show(instruments)} and passing a HashMap of {@link Instrument} objects.
 * 
 * @see Instrument
 * @see ConsoleDisplay
 * @see GuiDisplay
 */
public abstract class Display {

   public abstract void show(Map<INSTRUMENTS, Instrument> instruments);

}

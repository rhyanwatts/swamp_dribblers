package BikeSack;

/**
 * <h1>Bike Sack Output defines an object that will control any of the
 * outputs within the BikeSack motorcycle system.</h1>
 * 
 * <h2>Use as a dimmable (PWM) type output</h2> Value for this object can range
 * between 0 - 255 for use with PWM output for dimming. The output can use
 * Output.setLevel(int level) to set a custom level. Output.inc() and
 * Output.dec() will increment and decrement the output level by a default value
 * of 25, however a custom increment/decrement step can be also be defined by
 * using Output.setIncrementStep(int increment);
 * 
 * <h2>Use as a binary on/off type output</h2>
 * <p>
 * - {@code Output.toggle()} to simply switch on or off.
 * </p>
 * 
 * <p>
 * - {@code Output.setLevel(Output.on);} or {@code Output.setLevel(Output.off)}
 * </p>
 * 
 * <p>
 * -if {@code Output.setIncrementStep(255);} is used, then {@code Output.inc();} and
 * {@code Output.dec();} will also turn the output on/off.
 * </p>
 * 
 * @author Aidan Holmes s3355003
 * @since 11/7/2017
 * @version 1.0
 */
public class Output {

   /**
    * Provide common language field to define output value. Allows for more
    * readable code when using this class.
    * 
    * OFF = 0 (0% output value)
    */
   public static final int OFF = 0;

   /**
    * Provide common language field to define output value. Allows for more
    * readable code when using this class.
    * 
    * MED = 128 (50% output value)
    */
   public static final int MED = 128;

   /**
    * Provide common language field to define output value. Allows for more
    * readable code when using this class.
    * 
    * ON = 255 (100% output value)
    */
   public static final int ON = 255;

   // declare private variables
   private String outputType;
   private int outputLevel;
   private int incrementStep;

   /**
    * This constructs an Output object with the following default values: Output
    * level = Off, Increment size = 25
    */
   public Output() {
      outputType = null;
      outputLevel = OFF;
      incrementStep = 25; // Default = approx 10 steps for full range
   }

   /**
    * This constructs an Output object with a name and the following default
    * values: OutputLevel = Off Increment size = 25
    * 
    * @param type
    *           Name of output type e.g. "Brake Light"
    */
   public Output(String type) {
      this(); // Call default constructor
      outputType = type;
   }

   /**
    * This constructs an Output with a name, initial output level and the following
    * default values: Increment size = 25
    * 
    * @param type
    *           Name of output type e.g. "Brake Light"
    * @param level
    *           Initial Output level (0 - 255)
    */
   public Output(String type, int level) {
      this(); // Call default constructor
      outputType = type;
      outputLevel = level;
   }

   /**
    * This constructs an Output object with a name, initial output level and
    * increment size.
    * 
    * @param type
    *           Name of output type e.g. "Brake Light"
    * @param level
    *           Initial Output level (0 - 255)
    * @param increment
    *           Increment step size (default = 25)
    */
   public Output(String type, int level, int increment) {
      this(); // Call default constructor
      outputType = type;
      outputLevel = level;
      incrementStep = increment;
   }

   /**
    * @return The current Output level of this object.
    */
   public int getOutputLevel() {
      return outputLevel;
   }

   /**
    * @return The name of this Output
    */
   public String getOutputType() {
      return outputType;
   }

   /**
    * @return The increment step size for this Output
    */
   public int getIncrementStep() {
      return incrementStep;
   }

   /**
    * @param level
    *           The new output level for this Output
    * @return True if setting output value was successful. Otherwise False.
    */
   public boolean setoutputLevel(int level) {
      // validate input in valid range
      if (level >= OFF && level <= ON) {
         outputLevel = level;
         writeState();
      }
      boolean result = (outputLevel == level) ? true : false;
      return result;
   }

   /**
    * @param increment
    *           The new increment step size for this Output
    * @return True if setting increment value was successful. Otherwise False.
    */
   public boolean setIncrementStep(int increment) {
      if (increment >= OFF && increment <= ON) {
         incrementStep = increment;
         writeState();
      }
      boolean result = (incrementStep == increment) ? true : false;
      return result;
   }

   /**
    * Increment Output value by current increment step size (default = 25)
    * 
    * @return True if incrementing output value was successful. Otherwise False.
    */
   public boolean inc() {
      int temp = outputLevel;
      // if closer to ON than the increment, just turn ON
      if (outputLevel + incrementStep <= ON) {
         outputLevel += incrementStep;
      } else {
         outputLevel = ON;
      }
      if (outputLevel > temp) {
         writeState();
         return true; // if value was increased
      } else {
         return false; // if value was not increased
      }
   }

   /**
    * Decrement Output value by current increment step size (default = 25)
    * 
    * @return True if decrementing output value was successful. Otherwise False.
    */
   public boolean dec() {
      int temp = outputLevel;
      // if closer to OFF than the increment, just turn OFF
      if (outputLevel - incrementStep >= OFF) {
         outputLevel -= incrementStep;
      } else {
         outputLevel = OFF;
      }
      if (outputLevel < temp) {
         writeState();
         return true; // if value was decreased
      } else {
         return false; // if value was not decreased
      }
   }

   /**
    * 
    * @return True if the Output level is higher than OFF (0)
    */
   public boolean isOn() {
      if (outputLevel > 0) {
         return true;
      }
      return false;
   }

   /**
    * Toggles or flip / flops the Output value between OFF and ON.
    * <p>
    * If current Output higher than 0, first action is to turn OFF.
    * </p>
    * <p>
    * Easy implementation of binaly ON/OFF behaviour.
    * </p>
    */
   public void toggle() {
      if (outputLevel > OFF) {
         outputLevel = OFF;
         writeState();
      } else if (outputLevel == OFF) {
         outputLevel = ON;
         writeState();
      }
   }

   /**
    * Writes current state of object to Console for A3 validation testing.
    */
   private void writeState() {
      /*
       * TODO - comment out all calls to this method once validation has been
       * completed and the Text GUI has been written
       */
      System.out.println(this.toString());
   }

   /**
    * @return Output name and current output value in human readable String.
    *         <p>
    *         e.g. "Type=Brake Light, State= ON"
    *         </p>
    */
   @Override
   public String toString() {
      // prepare state in a human readable form
      String state;
      if (outputLevel == OFF) {
         state = "OFF";
      } else if (outputLevel == ON) {
         state = "ON";
      } else {
         state = "Dimmed to " + Integer.toString(outputLevel) + "/255";
      }
      // Modify eclipses auto-generated toString() return statement for our purpose
      return "Output [" + (outputType != null ? "Type= " + outputType + ", " : "") + "State= " + state + "]";
   }
}

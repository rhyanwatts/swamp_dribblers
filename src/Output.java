/**
 * <h1> Bike Sack Output Class </h1>
 * The Output class defines an object that can behave as any of the
 * outputs within the BikeSack motorcycle system.
 * 
 * <h2>Use as a dimmable (PWM) type output</h2>
 * Outputs can have a value of 0 - 255 set for use with PWM output for 
 * dimming. The output can use Output.setLevel(int level) to set
 * a custom level. Output.inc() and Output.dec() will increment and
 * decrement the output level by a default value of 25, however a 
 * custom increment/decrement step can be defined by using
 * Output.setIncrementStep(int increment);
 * 
 * <h2>Use as a binary on/off type output </h2>
 * By using Output.setLevel(Output.on); or
 * Output.setLevel(Output.off); the output can behave in a binary
 * on/off fashion.
 * 
 * Alternatively, if Output.setIncrementStep(255); is used, then
 * Output.inc(); and Output.dec(); will also turn the output on/off.
 * 
 * @author Aidan Holmes s3355003
 * @since 11/7/2017
 * @version 0.1
 */


public class Output {

   //set publicly accessible constants for simple output levels
   public static final int off = 0;
   public static final int med = 128;
   public static final int on = 255;

   // declare private variables
   private String outputType;
   private int outputLevel;
   private int incrementStep;

   // Default Constructor
   public Output() {
      outputType = null;
      outputLevel = off;
      incrementStep = 25; //Default = approx 10 steps for full range
   }

   // Overloaded Constructor including type string
   public Output(String type) {
      this(); // Call default constructor
      outputType = type;
   }

   // Overloaded Constructor including type and level
   public Output(String type, int level) {
      this(); // Call default constructor
      outputType = type;
      outputLevel = level;
   }

   // Overloaded Constructor including type, level and Increment Size
   public Output(String type, int level, int increment) {
      this(); // Call default constructor
      outputType = type;
      outputLevel = level;
      incrementStep = increment;
   }

   // Getter - returns output level
   public int getOutputLevel() {
      return outputLevel;
   }

   // Getter - returns output type
   public String getOutputType() {
      return outputType;
   }

   // Getter - returns increment size
   public int getIncrementStep() {
      return incrementStep;
   }

   // Setter - set output level, returns true if successful
   public boolean setoutputLevel(int level) {
      if (level >= off && level <= on) {
         outputLevel = level;
      }
      boolean result = (outputLevel == level) ? true : false;
      return result;
   }
   
   // Setter - sets increment step
   public boolean setIncrementStep(int increment) {
      if (increment >= off && increment <= on) {
         incrementStep = increment;
      }
      boolean result = (incrementStep == increment) ? true : false;
      return result;
   }

   // Increments output Level. Returns True if successful.
   public boolean inc() {
      int temp = outputLevel;
      if (outputLevel + incrementStep <= on) {
         outputLevel += incrementStep;
      } else {
         outputLevel = on;
      }
      if (outputLevel > temp) {
         return true;
      } else {
         return false;
      }
   }

   // Decrements output Level. Returns True if successful.
   public boolean dec() {
      int temp = outputLevel;
      if (outputLevel - incrementStep >= off) {
         outputLevel -= incrementStep;
      } else {
         outputLevel = off;
      }
      if (outputLevel < temp) {
         return true;
      } else {
         return false;
      }
   }
}

/**
 * <h1> Bike Sack Light Class </h1>
 * The Light class defines an object that can behave as any of the
 * lights within the BikeSack motorcycle lighting system.
 * 
 * <h2>Use as a dimmable (PWM) type light</h2>
 * Lights can have a value of 0 - 255 set for use with PWM output for 
 * dimming. The light can use Light.setLightLevel(int level) to set
 * a custom level. Light.int() and Light.dec() will increment and
 * decrement the light level by a default value of 25, however a 
 * custom increment/decrement step can be defined by using
 * Light.setIncrementStep(int increment);
 * 
 * <h2>Use as a binary on/off type light </h2>
 * By using Light.setLightLevel(Light.on); or
 * Light.setLightLevel(Light.off); the light can behave in a binary
 * on/off fashion.
 * 
 * Alternatively, if Light.setIncrementStep(255); is used, then
 * Light.inc(); and Light.dec(); will also turn the light on/off.
 * 
 * @author Aidan Holmes s3355003
 * @since 11/7/2017
 * @version 0.1
 */


public class Light {

   //set publicly accessible constants for simple light levels
   public static final int off = 0;
   public static final int med = 128;
   public static final int on = 255;

   // declare private variables
   private String lightType;
   private int lightLevel;
   private int incrementStep;

   // Default Constructor
   public Light() {
      lightType = null;
      lightLevel = off;
      incrementStep = 25; //Default = approx 10 steps for full range
   }

   // Overloaded Constructor including type string
   public Light(String type) {
      this(); // Call default constructor
      lightType = type;
   }

   // Overloaded Constructor including type and level
   public Light(String type, int level) {
      this(); // Call default constructor
      lightType = type;
      lightLevel = level;
   }

   // Overloaded Constructor including type, level and Increment Size
   public Light(String type, int level, int increment) {
      this(); // Call default constructor
      lightType = type;
      lightLevel = level;
      incrementStep = increment;
   }

   // Getter - returns light level
   public int getLightLevel() {
      return lightLevel;
   }

   // Getter - returns light type
   public String getLightType() {
      return lightType;
   }

   // Getter - returns increment size
   public int getIncrementStep() {
      return incrementStep;
   }

   // Setter - set light level, returns true if successful
   public boolean setLightLevel(int level) {
      if (level >= off && level <= on) {
         lightLevel = level;
      }
      boolean result = (lightLevel == level) ? true : false;
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

   // Increments Light Level. Returns True if successful.
   public boolean inc() {
      int temp = lightLevel;
      if (lightLevel + incrementStep <= on) {
         lightLevel += incrementStep;
      } else {
         lightLevel = on;
      }
      if (lightLevel > temp) {
         return true;
      } else {
         return false;
      }
   }

   // Decrements Light Level. Returns True if successful.
   public boolean dec() {
      int temp = lightLevel;
      if (lightLevel - incrementStep >= off) {
         lightLevel -= incrementStep;
      } else {
         lightLevel = off;
      }
      if (lightLevel < temp) {
         return true;
      } else {
         return false;
      }
   }
}

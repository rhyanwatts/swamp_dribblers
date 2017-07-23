
public class HeadLightTest {
	public static void main(String[] args) {
        BikeSack bikeSack = new BikeSack();
        
        // Test Left
        System.out.println("Indicating Left");
        bikeSack.toggleIndicator(IndicatorDirection.LEFT);
        
        // Test Right
        System.out.println("\nIndicating Right");
        bikeSack.toggleIndicator(IndicatorDirection.RIGHT);

        // Test toggle right off
        System.out.println("\nIndicating Right (toggle off)");
        bikeSack.toggleIndicator(IndicatorDirection.RIGHT);
        
        // Left on to test toggle off
        System.out.println("\nIndicating Left");
        bikeSack.toggleIndicator(IndicatorDirection.LEFT);
        
        // Test toggle left off
        System.out.println("\nIndicating Left (toggle off)");
        bikeSack.toggleIndicator(IndicatorDirection.LEFT);
        
        // Right on to test turning off
        System.out.println("\nIndicating Right");
        bikeSack.toggleIndicator(IndicatorDirection.RIGHT);
        
        // Test turning off
        System.out.println("\nTurning Off");
        bikeSack.toggleIndicator(IndicatorDirection.NONE);

        // Left on to test turning off
        System.out.println("\nIndicating Left");
        bikeSack.toggleIndicator(IndicatorDirection.LEFT);

        // Test turning off
        System.out.println("\nTurning Off");
        bikeSack.toggleIndicator(IndicatorDirection.NONE);

        //Head Light Low
        System.out.println("\nLow Beam on");;
        bikeSack.updateHeadLight(HeadLightLevel.LOW);
 
        
        //Head Light High
        System.out.println("\nHigh Beam on");;
        bikeSack.updateHeadLight(HeadLightLevel.HIGH);

        
      //Head Light Low
        System.out.println("\nLow Beam on");;
        bikeSack.updateHeadLight(HeadLightLevel.LOW);


    
	}
	
	
	
}


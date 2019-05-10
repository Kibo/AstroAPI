package swisseph;

import org.testng.annotations.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import swisseph.SweConst;

public class TestMultithread {

	final double LONGITUDE = 16.0542676;
	final double LATITUDE = 48.8559107;
	final double GEOALT = 286;
	final String PATH_TO_EPHEMERIS = "/data/ephemeris";
	
  @Test(threadPoolSize = 10, invocationCount = 1000, timeOut = 1000)
  public void testMultiProcessing() {
	  
	  Long id = Thread.currentThread().getId();
      System.out.println("Test method executing on thread with id: " + id);
	  
	  
	  LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 16, 20);
		
		List<Integer> planets = new ArrayList<Integer>();		
		planets.add( SweConst.SE_SUN );
		planets.add( SweConst.SE_JUPITER );
		
		int iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_SPEED;
				
		SwissEph sw = new SwissEph( PATH_TO_EPHEMERIS );
						
		SweDate sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0 + event.getSecond()/360.0, SweDate.SE_GREG_CAL);
														
		Map<String, List<Double>> data = new HashMap<String, List<Double>>();
		
		for (Integer planet : planets) {
			
			double[] xp= new double[6];
			StringBuffer serr = new StringBuffer();
						
			int ret = sw.swe_calc_ut(sd.getJulDay(),
				planet,
				iflag,
				xp,
				serr);
										
			if (ret != iflag) {
				if (serr.length() > 0) {
					System.err.println("Warning: " + serr);
				} else {
					System.err.println( String.format("Warning, different flags used (0x%x)", ret));					
				}
			}
								
			// @see swisseph.SwissEph.swe_calc
			List<Double> values = new ArrayList<Double>();
			values.add(xp[0]); //longitude
			values.add(xp[3]); //speed in longitude
			
			data.put( sw.swe_get_planet_name(planet), values);		
		}
							
		assertEquals(2, data.size());
			
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
		
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(0, data.get("Sun").get(0).intValue()); //Spring is comming 		  									
  }        
}

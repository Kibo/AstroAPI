package cz.kibo.api.astrology;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import swisseph.SweConst;

public class ChartTest {
	
	private static final double DELTA = 1e-15;

	@Test
	public void planetsGeocentricTest() {
		
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 17, 00 );
		
		List<Integer> planets = new ArrayList<Integer>();
		
		planets.add( SweConst.SE_SUN );
		planets.add( SweConst.SE_JUPITER );
					
		Chart chart = new Chart(event, planets);
		Map<String, List<Double>> data = chart.getPlanets();
		
		assertEquals(2, data.size());
				
		assertTrue(data.containsKey("Sun"));
		assertTrue(data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(0, data.get("Sun").get(0).intValue()); //Spring is comming 
	}
}



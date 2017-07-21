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
				
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(0, data.get("Sun").get(0).intValue()); //Spring is comming 
	}
	
	@Test
	public void planetNameTest() {
		
		LocalDateTime event = LocalDateTime.now();
		
		List<Integer> planets = new ArrayList<Integer>();		
		planets.add( SweConst.SE_MOON );
		planets.add( SweConst.SE_SUN );
		planets.add( SweConst.SE_MERCURY );
		planets.add( SweConst.SE_VENUS );
		planets.add( SweConst.SE_MARS );
		planets.add( SweConst.SE_JUPITER );
		planets.add( SweConst.SE_SATURN );
		planets.add( SweConst.SE_URANUS );
		planets.add( SweConst.SE_NEPTUNE );
		planets.add( SweConst.SE_PLUTO );
		planets.add( SweConst.SE_CHIRON );
		planets.add( SweConst.SE_MEAN_APOG); // Lilith
		planets.add(SweConst.SE_MEAN_NODE ); // Nort Node
		
		Chart chart = new Chart(event, planets);
		Map<String, List<Double>> data = chart.getPlanets();
					
		assertTrue("Moon", data.containsKey("Moon"));
		assertTrue("Sun", data.containsKey("Sun"));
		assertTrue("Mercury", data.containsKey("Mercury"));
		assertTrue("Venus",data.containsKey("Venus"));
		assertTrue("Mars",data.containsKey("Mars"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
		assertTrue("Saturn",data.containsKey("Saturn"));
		assertTrue("Uranus",data.containsKey("Uranus"));
		assertTrue("Neptune",data.containsKey("Neptune"));
		assertTrue("Pluto",data.containsKey("Pluto"));
		assertTrue("Chiron",data.containsKey("Chiron"));
		assertTrue("Lilith",data.containsKey("Lilith"));
		assertTrue("NNode",data.containsKey("NNode"));			
	}
}



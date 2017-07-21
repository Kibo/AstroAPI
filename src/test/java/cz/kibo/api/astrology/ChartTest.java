package cz.kibo.api.astrology;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import swisseph.SweConst;

public class ChartTest {
	
	final double LONGITUDE = 16.0542676;
	final double LATITUDE = 48.8559107;
	final double GEOALT = 286;
	
	private static final double DELTA = 1e-15;

	@Test
	public void planetsGeocentricTest() {
		
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 16, 20);
		
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
		
		System.out.println("cz.kibo.api.astrologyChartTest.planetsGeocentricTest() -> Sun geocentric: " + data.get("Sun").get(0));
	}
	
	@Test
	public void planetTopocentricTest() {
		
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 16, 20);
		Coordinates coords = new Coordinates(LONGITUDE, LATITUDE, GEOALT);
		
		List<Integer> planets = new ArrayList<Integer>();		
		planets.add( SweConst.SE_SUN );
		planets.add( SweConst.SE_JUPITER );
		
		Chart chart = new Chart(event, planets, coords);
		Map<String, List<Double>> data = chart.getPlanets();
		
		assertEquals(2, data.size());
		
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(0, data.get("Sun").get(0).intValue()); //Spring is comming 	
		
		System.out.println("cz.kibo.api.astrologyChartTest.planetsTopocentricTest() -> Sun topocentric: " + data.get("Sun").get(0));
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



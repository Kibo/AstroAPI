package cz.kibo.api.astrology.domain;

import static org.junit.Assert.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import swisseph.SweConst;

public class PlanetTest {

	final double LONGITUDE = 16.0542676;
	final double LATITUDE = 48.8559107;
	final double GEOALT = 286;
	
	@Test
	public void planetsGeocentricTest() {
		
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 16, 20);
		
		List<Integer> planets = new ArrayList<Integer>();		
		planets.add( SweConst.SE_SUN );
		planets.add( SweConst.SE_JUPITER );
		
		int iflag = 0; // tropical
					
		Planet chart = new Planet(event, planets, iflag);
		Map<String, List<Double>> data = chart.getPlanets();
		
		assertEquals(2, data.size());
				
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(0, data.get("Sun").get(0).intValue()); //Spring is comming 			
	}
	
	@Test
	public void planetTopocentricTest() {
		
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 16, 20);
		Coordinates coords = new Coordinates(LONGITUDE, LATITUDE, GEOALT);
		
		List<Integer> planets = new ArrayList<Integer>();		
		planets.add( SweConst.SE_SUN );
		planets.add( SweConst.SE_JUPITER );
		
		int iflag = 0; // tropical
		
		Planet chart = new Planet(event, planets, coords, iflag);
		Map<String, List<Double>> data = chart.getPlanets();
		
		assertEquals(2, data.size());
		
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(0, data.get("Sun").get(0).intValue()); //Spring is comming 				
	}
	
	@Test
	public void planetsGeocentricSiderealTest() {
					
		LocalDateTime event = LocalDateTime.of( 2018, 4, 18, 4, 00);
					
		List<Integer> planets = new ArrayList<Integer>();		
		planets.add( SweConst.SE_SUN );
		planets.add( SweConst.SE_JUPITER );
		
		int iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_DELUCE;
		
		Planet chart = new Planet(event, planets, iflag);
		Map<String, List<Double>> data = chart.getPlanets();
		
		assertEquals(2, data.size());
		
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(0, data.get("Sun").get(0).intValue());
		assertEquals(202, data.get("Jupiter").get(0).intValue());	
		
				
		iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_FAGAN_BRADLEY;
		
		chart = new Planet(event, planets, iflag);
		data = chart.getPlanets();
		
		assertEquals(2, data.size());
		
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(3, data.get("Sun").get(0).intValue());
		assertEquals(205, data.get("Jupiter").get(0).intValue());
		
		iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_KUGLER1;
					
		chart = new Planet(event, planets, iflag);
		data = chart.getPlanets();
		
		assertEquals(2, data.size());
		
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(1, data.get("Sun").get(0).intValue());
		assertEquals(204, data.get("Jupiter").get(0).intValue());
		
		iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_SASSANIAN;
		
		chart = new Planet(event, planets, iflag);
		data = chart.getPlanets();
		
		assertEquals(2, data.size());
		
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(7, data.get("Sun").get(0).intValue());
		assertEquals(210, data.get("Jupiter").get(0).intValue());			
	}
	
	@Test
	public void planetsTopocentricSiderealTest() {
								
		LocalDateTime event = LocalDateTime.of( 2018, 4, 18, 4, 00);
		Coordinates coords = new Coordinates(LONGITUDE, LATITUDE, GEOALT);
		
		List<Integer> planets = new ArrayList<Integer>();		
		planets.add( SweConst.SE_SUN );
		planets.add( SweConst.SE_JUPITER );
		
		int iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_DELUCE;
		
		Planet chart = new Planet(event, planets, coords, iflag);
		Map<String, List<Double>> data = chart.getPlanets();
		
		assertEquals(2, data.size());
		
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(0, data.get("Sun").get(0).intValue());
		assertEquals(202, data.get("Jupiter").get(0).intValue());	
		
				
		iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_FAGAN_BRADLEY;
		
		chart = new Planet(event, planets, coords, iflag);
		data = chart.getPlanets();
		
		assertEquals(2, data.size());
		
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(3, data.get("Sun").get(0).intValue());
		assertEquals(205, data.get("Jupiter").get(0).intValue());
		
		iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_KUGLER1;
					
		chart = new Planet(event, planets, coords, iflag);
		data = chart.getPlanets();
		
		assertEquals(2, data.size());
		
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(1, data.get("Sun").get(0).intValue());
		assertEquals(204, data.get("Jupiter").get(0).intValue());
		
		iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_SASSANIAN;
		
		chart = new Planet(event, planets, coords, iflag);
		data = chart.getPlanets();
		
		assertEquals(2, data.size());
		
		assertTrue("Sun",data.containsKey("Sun"));
		assertTrue("Jupiter",data.containsKey("Jupiter"));
			
		assertTrue( data.get("Sun").get(1) > 0 );
		assertTrue( data.get("Jupiter").get(1) < 0 ); // Retrograde
		
		assertEquals(7, data.get("Sun").get(0).intValue());
		assertEquals(210, data.get("Jupiter").get(0).intValue());	
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
		
		Planet chart = new Planet(event, planets, 0);
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

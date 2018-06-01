package cz.kibo.api.astrology.builder;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import cz.kibo.api.astrology.domain.Transit;
import swisseph.SweConst;

public class TransitBuilderTest {
	
	final double LONGITUDE = 16.0542676;
	final double LATITUDE = 48.8559107;
	final double GEOALT = 286;

	@Test
	public void planetToPointGeocentricTest() {
				
		LocalDateTime event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		Transit transit = new TransitBuilder( event)
										.planet("Sun")
										.toPoint(90.0)
										.build();
		
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 6, 21, 4, 24), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));										
	}
	
	@Test
	public void planeToPointAspectTest() {
		
		LocalDateTime event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		Transit transit = new TransitBuilder( event)
										.planet("Moon")
										.toPoint(270.0)
										.aspect(180.0)
										.build();
		
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 6, 23, 22, 06), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));													
	}
	
	@Test
	public void planeToPointAspect2Test() {
		
		LocalDateTime event = LocalDateTime.of( 2018, 1, 1, 0, 0);		
		Transit transit = new TransitBuilder( event)
										.planet("Pluto")
										.toPoint(20.0)
										.aspect(-90.0)
										.build();
		
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2018, 2, 6, 17, 27), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));													
	}
	
	@Test
	public void planeToPointAspect3Test() {
		
		LocalDateTime event = LocalDateTime.of( 2017, 6, 17, 0, 0);		
		Transit transit = new TransitBuilder( event)
										.planet("Moon")
										.toPoint(90.0)
										.aspect(-90.0)
										.build();
		
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 6, 17, 17, 54), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));													
	}
	
	@Test
	public void planeToPointBackwardsTest() {
		
		LocalDateTime event = LocalDateTime.of( 2017, 6, 20, 0, 0);		
		Transit transit = new TransitBuilder( event)
										.planet("Moon")
										.toPoint(0.0)
										.backwards(true)
										.build();
		
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 6, 17, 17, 54), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));													
	}
	
	
	@Test
	public void planetToPlanetGeocentricTest() {
					
		LocalDateTime event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		Transit transit = new TransitBuilder( event)
										.planet("Moon")
										.toPlanet("Sun")
										.aspect(90.0)
										.build();		
		
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 7, 1, 00, 51), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));					
	}
}

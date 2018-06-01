package cz.kibo.api.astrology.domain;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import swisseph.SweConst;

public class TransitTest {
	
	final double LONGITUDE = 16.0542676;
	final double LATITUDE = 48.8559107;
	final double GEOALT = 286;
	
	@Test
	public void planetToPointGeocentricTest() {
		
		int iflag = 0; // tropical
		
		LocalDateTime event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		Transit transit = new Transit( event, SweConst.SE_SUN, 90.0, iflag);
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 6, 21, 4, 24), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
		
		
		event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		transit = new Transit( event, SweConst.SE_MOON, 90.0, iflag);
		date = transit.getDate();	
		
		assertEquals( LocalDateTime.of( 2017, 6, 23, 22, 06), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
	}
	
	@Test
	public void planetToPointGeocentricBackwardTest() {
		
		int iflag = 0; // tropical
		
		LocalDateTime event = LocalDateTime.of( 2017, 6, 25, 0, 0);		
		Transit transit = new Transit( event, SweConst.SE_SUN, 90.0, iflag, true);
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 6, 21, 4, 24), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
		
		
		event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		transit = new Transit( event, SweConst.SE_MOON, 90.0, iflag, true);
		date = transit.getDate();	
		
		assertEquals( LocalDateTime.of( 2017, 5, 27, 11, 24), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
	}
		
	@Test
	public void planetToPointTopocentricTest() {
		
		int iflag = 0; // tropical
		
		Coordinates coords = new Coordinates(LONGITUDE, LATITUDE, GEOALT);
		
		LocalDateTime event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		Transit transit = new Transit( event, SweConst.SE_SUN, 90.0, coords, iflag);
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 6, 21, 4, 21), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
		
		
		event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		transit = new Transit( event, SweConst.SE_MOON, 90.0, coords, iflag);
		date = transit.getDate();	
		
		assertEquals( LocalDateTime.of( 2017, 6, 23, 22, 15), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
	}
	
	@Test
	public void planetToPointGeocentricSirerealTest() {
		
		int iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_DELUCE;
					
		LocalDateTime event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		Transit transit = new Transit( event, SweConst.SE_SUN, 90.0, iflag);
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 7, 20, 14, 18), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
				
		event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		transit = new Transit( event, SweConst.SE_MOON, 90.0, iflag);
		date = transit.getDate();	
		
		assertEquals( LocalDateTime.of( 2017, 6, 25, 18, 55), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));		
	}
	
	@Test
	public void planetToPointTopocentricSirerealTest() {
		
		int iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_KUGLER1;		
		
		Coordinates coords = new Coordinates(LONGITUDE, LATITUDE, GEOALT);
		
		LocalDateTime event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		Transit transit = new Transit( event, SweConst.SE_SUN, 90.0, coords, iflag);
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 7, 18, 12, 38), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
		
		
		iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_RAMAN;
		
		event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		transit = new Transit( event, SweConst.SE_MOON, 90.0, coords, iflag);
		date = transit.getDate();	
		
		assertEquals( LocalDateTime.of( 2017, 6, 25, 9, 16), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));		
	}
	
	@Test
	public void planetToPlanetGeocentricTest() {
		
		int iflag = 0; // tropical
		
		LocalDateTime event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		Transit transit = new Transit( event, SweConst.SE_SUN, SweConst.SE_MOON, 0.0, iflag);
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 6, 24, 2, 30), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
		
		
		event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		transit = new Transit( event, SweConst.SE_SUN, SweConst.SE_MOON, 180.0, iflag);
		date = transit.getDate();	
		
		assertEquals( LocalDateTime.of( 2017, 7, 9, 4, 06), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));		
	}
	
	@Test
	public void planetToPlanetTopocentricTest() {
		
		int iflag = 0; // tropical
		
		Coordinates coords = new Coordinates(LONGITUDE, LATITUDE, GEOALT);
		
		LocalDateTime event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		Transit transit = new Transit( event, SweConst.SE_SUN, SweConst.SE_MOON, 0.0, coords, iflag);
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 6, 24, 1, 43), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
		
		
		event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		transit = new Transit( event, SweConst.SE_SUN, SweConst.SE_MOON, 180.0, coords, iflag);
		date = transit.getDate();	
		
		assertEquals( LocalDateTime.of( 2017, 7, 9, 5, 33), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));				
	}
	
	@Test
	public void planetToPlanetGeocentricSirerealTest() {
		
		int iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_DELUCE;
		
		LocalDateTime event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		Transit transit = new Transit( event, SweConst.SE_SUN, SweConst.SE_MOON, 0.0, iflag);
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 6, 24, 2, 30), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
				
		event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		transit = new Transit( event, SweConst.SE_SUN, SweConst.SE_MOON, 180.0, iflag);
		date = transit.getDate();	
		
		assertEquals( LocalDateTime.of( 2017, 7, 9, 4, 06), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));				
	}
	
	@Test
	public void planetToPlanetTopocentricSirerealTest() {
	
		int iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_SASSANIAN;
		
		Coordinates coords = new Coordinates(LONGITUDE, LATITUDE, GEOALT);
		
		LocalDateTime event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		Transit transit = new Transit( event, SweConst.SE_SUN, SweConst.SE_MOON, 0.0, coords, iflag);
		LocalDateTime date = transit.getDate();				
		
		assertEquals( LocalDateTime.of( 2017, 6, 24, 1, 43), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));
				
		event = LocalDateTime.of( 2017, 6, 18, 0, 0);		
		transit = new Transit( event, SweConst.SE_SUN, SweConst.SE_MOON, 180.0, coords, iflag);
		date = transit.getDate();	
		
		assertEquals( LocalDateTime.of( 2017, 7, 9, 5, 33), LocalDateTime.of( date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute()));						
	}
}

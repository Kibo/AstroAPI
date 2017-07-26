package cz.kibo.api.astrology.domain;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import swisseph.SweConst;

public class CuspTest {

	final double LONGITUDE = 16.0542676;
	final double LATITUDE = 48.8559107;
	final double GEOALT = 286;
	
	private static final double DELTA = 1e-15;
	
	@Test
	public void cuspsPlacidusTropicalWithIflagTest() {
		
		final int PLACIDUS_HOUSE_SYSTEM = 'P';
		
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 5, 6);		
		Coordinates coords = new Coordinates(LONGITUDE, LATITUDE, GEOALT);
									
		int iflag = 0; // tropical - default
		
		Cusp chart = new Cusp(event, coords, PLACIDUS_HOUSE_SYSTEM, iflag);
		List<Double> data = chart.getCusps();
		
		assertEquals(12, data.size());
		
		assertEquals(0, data.get(0).intValue()); 
				
		System.out.println( chart.toString() );
	}
	
	@Test
	public void cuspsPlacidusSiderealWithIflagTest() {
		
		final int PLACIDUS_HOUSE_SYSTEM = 'P';
		
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 5, 6);		
		Coordinates coords = new Coordinates(LONGITUDE, LATITUDE, GEOALT);
									
		int iflag = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_HUBER;
		
		Cusp chart = new Cusp(event, coords, PLACIDUS_HOUSE_SYSTEM, iflag);
		List<Double> data = chart.getCusps();
		
		assertEquals(12, data.size());
		
		assertEquals(335, data.get(0).intValue()); 
								
		System.out.println( chart.toString() );
	}
}

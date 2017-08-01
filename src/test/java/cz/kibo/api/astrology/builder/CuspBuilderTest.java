package cz.kibo.api.astrology.builder;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import cz.kibo.api.astrology.domain.Cusp;

public class CuspBuilderTest {
	
	final double LONGITUDE = 16.0542676;
	final double LATITUDE = 48.8559107;
	final double GEOALT = 286;

	@Test
	public void placidusTest() {		
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 5, 6);
		Cusp ephemeris = new CuspBuilder(event)
						.houses("Placidus")
						.topo(LONGITUDE, LATITUDE, GEOALT)
						.build();
		
		assertEquals(12, ephemeris.getCusps().size());		
		assertEquals(0, ephemeris.getCusps().get(0).intValue()); 		
	}
	
	@Test
	public void siderealTest() {		
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 5, 6);
		Cusp ephemeris = new CuspBuilder(event)
						.houses("Placidus")
						.topo(LONGITUDE, LATITUDE, GEOALT)
						.zodiac("Babyl Huber")
						.build();
		
		assertEquals(12, ephemeris.getCusps().size());		
		assertEquals(335, ephemeris.getCusps().get(0).intValue()); 		
	}
}

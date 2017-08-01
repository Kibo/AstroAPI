package cz.kibo.api.astrology.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cz.kibo.api.astrology.domain.Coordinates;
import cz.kibo.api.astrology.domain.Planet;
import swisseph.SweConst;

/**
 * The builder for planet positions.
 * 
 *	Default: 
 *  - geocentric coordinate system
 *  - tropical zodiac type
 * 
 * @example
 * <pre>
 *	 // Topocentric, tropical, all planets.
 *	Planet planetEphemeris = new PlanetBuilder(event)
 * 					.planets() 					
 * 					.topo(48.8555, 18.0488, 0)
 * 					.build();
 *	planetEphemeris.toJSON();
 * 
 *  // Geocentric, sidereal, Sun and Moon only.
 *	Planet planetEphemeris = new PlanetBuilder(event)
 * 					.planet('Sun, Moon')
 *  				.zodiac("Fagan Bradley")	
 *					.build();
 *	planetEphemeris.toJSON();
 * </pre>
 * 
 * @author Tomas Jurman tomasjurman@gmail.com
 */
public class PlanetBuilder extends Builder{
		
	private final LocalDateTime event;
	private final List<Integer> planetsList = new ArrayList<Integer>();
	private Coordinates coords;
	private int iflags = 0; // tropical default
		
	/**
	 * Creates Planet builder. 
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 */
	public PlanetBuilder( LocalDateTime event ) {
		this.event = event;
	}
	
	/**
	 * Sets all planets
	 * 
	 * @return
	 */
	public PlanetBuilder planets() {
		planetsList.clear();
		planetsList.add(SweConst.SE_SUN);
		planetsList.add(SweConst.SE_MOON);
		planetsList.add(SweConst.SE_MERCURY);
		planetsList.add(SweConst.SE_VENUS);
		planetsList.add(SweConst.SE_MARS);
		planetsList.add(SweConst.SE_JUPITER);
		planetsList.add(SweConst.SE_SATURN);
		planetsList.add(SweConst.SE_URANUS);
		planetsList.add(SweConst.SE_NEPTUNE);
		planetsList.add(SweConst.SE_PLUTO);
		planetsList.add(SweConst.SE_CHIRON);	
		planetsList.add(SweConst.SE_MEAN_APOG);
		planetsList.add(SweConst.SE_MEAN_NODE);				
		return this;
	}
	
	/**
	 * Sets the planets of interest
	 * 
	 * @param planets Comma separated string of planet names.
	 * @return
	 */
	public PlanetBuilder planet( String planets) {		
		planetsList.clear();
		
		String[] interest = planets.split(",");
		for(int i = 0, ln = interest.length; i < ln; i++ ) {		
			planetsList.add( super.getPlanet( interest[i] ));
		}
						
		return this;
	}
		
	/**
	 * Sets topocentric cordinate system.
	 * 
	 * @param lon The Longitude in degrees
     * @param lat The Latitude in degrees 
     * @param geoalt The height above sea level in meters
     * @return	
	 */
	public PlanetBuilder topo(double lon, double lat, double geoalt) {
		this.coords = super.getCoordinates(lon, lat, geoalt);
		return this;
	}
		
	/**
	 * Sets sidereal mode
	 * 
	 * @param siderealMode sidereal mode
	 * @return
	 */
	public PlanetBuilder zodiac(String siderealMode) {
		this.iflags = super.getSiderealFlags(siderealMode);				
		return this;
	}
			
	/**
	 * Builds query
	 * 
	 * @return
	 */
	public Planet build() {
		
		Planet ephemeris;
		
		if( this.coords == null ) {
			ephemeris = new Planet(this.event, this.planetsList, this.iflags);
		}else {
			ephemeris = new Planet(this.event, this.planetsList, this.coords, this.iflags);
		}
				
		return ephemeris;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanetBuilder other = (PlanetBuilder) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlanetBuilder [event=" + event + "]";
	}	
}

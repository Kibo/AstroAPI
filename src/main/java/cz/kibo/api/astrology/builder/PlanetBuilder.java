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
public class PlanetBuilder {
		
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
			switch (interest[i].trim()) {
				case "Sun":		this.planetsList.add(SweConst.SE_SUN);
						 		break;
				case "Moon":	this.planetsList.add(SweConst.SE_MOON);
			 					break;
				case "Mercury":	this.planetsList.add(SweConst.SE_MERCURY);
								break;	
				case "Venus":	this.planetsList.add(SweConst.SE_VENUS);
								break;	
				case "Mars":	this.planetsList.add(SweConst.SE_MARS);
								break;	
				case "Jupiter":	this.planetsList.add(SweConst.SE_JUPITER);
								break;	
				case "Saturn":	this.planetsList.add(SweConst.SE_SATURN);
								break;	
				case "Neptune":	this.planetsList.add(SweConst.SE_NEPTUNE);
								break;
				case "Uranus":	this.planetsList.add(SweConst.SE_URANUS);
								break;				
				case "Pluto":	this.planetsList.add(SweConst.SE_PLUTO);
								break;	
				case "Chiron":	this.planetsList.add(SweConst.SE_CHIRON);
								break;	
				case "Lilith":	this.planetsList.add(SweConst.SE_MEAN_APOG);
								break;	
				case "NNode":	this.planetsList.add(SweConst.SE_MEAN_NODE);
								break;								 
				default: 
					throw new IllegalArgumentException( "Unknown planet name: " + interest[i]);            	
			}			
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
		this.coords = new Coordinates(lon, lat, geoalt);
		return this;
	}
	
	/**
	 * Sets sidereal mode
	 * 
	 * @param siderealMode sidereal mode
	 * @return
	 */
	public PlanetBuilder zodiac(String siderealMode) {
		switch (siderealMode.trim()) {
		
			case "Fagan Bradley": 
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_FAGAN_BRADLEY;
				break;
								
			case "Lahiri": 
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_LAHIRI;
				break;
								
			case "Deluce":
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_DELUCE;
				break;
			
			case "Ramanb": 
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_RAMAN;
				break;
				
			case "Ushashashi": 
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_USHASHASHI;
				break;
				
			case "Krishnamurti": 
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_KRISHNAMURTI;
				break;
				
			case "Djwhal Khul": 
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_DJWHAL_KHUL;
				break;
				
			case "Yukteshwar": 
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_YUKTESHWAR;
				break;
				
			case "Jn Bhasin": 
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_JN_BHASIN;
				break;
				
			case "Babyl Kugler 1":
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_KUGLER1;
				break;
				
			case "Babyl Kugler 2":
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_KUGLER2;
				break;
				
			case "Babyl Kugler 3":
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_KUGLER3;
				break;
				
			case "Babyl Huber":
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_HUBER;
				break;
				
			case "Babyl Etpsc":
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_ETPSC;
				break;
				
			case "Aldebaran 10Tau":
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_ALDEBARAN_15TAU;
				break;
				
			case "Hipparchos":
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_HIPPARCHOS;
				break;
				
			case "Sassanian":
				this.iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_SASSANIAN;
				break;
						
			default: 
				throw new IllegalArgumentException( "Unknown sidereal mode: " + siderealMode);
		
		}
				
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
}

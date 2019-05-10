package cz.kibo.api.astrology.builder;

import cz.kibo.api.astrology.domain.Coordinates;
import swisseph.SweConst;

public abstract class Builder {
		
	/**
	 * Get coordinate.
	 * 
	 * @param lon The Longitude in degrees
     * @param lat The Latitude in degrees 
     * @param geoalt The height above sea level in meters
     * @return	
	 */
	protected Coordinates getCoordinates(double lon, double lat, double geoalt) {
		return new Coordinates(lon, lat, geoalt);		
	}
	
	/**
	 * Get planet
	 * 
	 * @param planetName
	 * @return
	 * 
	 * @see swisseph.SweConst
	 */
	protected int getPlanet( String planetName) {
		
		int planet = 0;
		
		switch (planetName.trim()) {
			case "Sun":		planet = SweConst.SE_SUN;
					 		break;
			case "Moon":	planet = SweConst.SE_MOON;
		 					break;
			case "Mercury":	planet = SweConst.SE_MERCURY;
							break;	
			case "Venus":	planet = SweConst.SE_VENUS;
							break;	
			case "Mars":	planet = SweConst.SE_MARS;
							break;	
			case "Jupiter":	planet = SweConst.SE_JUPITER;
							break;	
			case "Saturn":	planet = SweConst.SE_SATURN;
							break;	
			case "Neptune":	planet = SweConst.SE_NEPTUNE;
							break;
			case "Uranus":	planet = SweConst.SE_URANUS;
							break;				
			case "Pluto":	planet = SweConst.SE_PLUTO;
							break;	
			case "Chiron":	planet = SweConst.SE_CHIRON;
							break;	
			case "Lilith":	planet = SweConst.SE_MEAN_APOG;
							break;	
			case "NNode":	planet = SweConst.SE_MEAN_NODE;
							break;								 
			default: 
				throw new IllegalArgumentException( "Unknown planet name: " + planetName);      			
		}
		
		return planet;
	}
	
	/**
	 * Get iflags for sidereal mode
	 * 
	 * @param siderealMode sidereal mode
	 * @return
	 */
	protected int getSiderealFlags(String siderealMode) {
		
		int iflags = 0;
		
		switch (siderealMode.trim()) {
		
			case "Fagan Bradley": 
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_FAGAN_BRADLEY;
				break;
								
			case "Lahiri": 
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_LAHIRI;
				break;
								
			case "Deluce":
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_DELUCE;
				break;
			
			case "Ramanb": 
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_RAMAN;
				break;
				
			case "Ushashashi": 
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_USHASHASHI;
				break;
				
			case "Krishnamurti": 
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_KRISHNAMURTI;
				break;
				
			case "Djwhal Khul": 
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_DJWHAL_KHUL;
				break;
				
			case "Yukteshwar": 
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_YUKTESHWAR;
				break;
				
			case "Jn Bhasin": 
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_JN_BHASIN;
				break;
				
			case "Babyl Kugler 1":
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_KUGLER1;
				break;
				
			case "Babyl Kugler 2":
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_KUGLER2;
				break;
				
			case "Babyl Kugler 3":
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_KUGLER3;
				break;
				
			case "Babyl Huber":
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_HUBER;
				break;
				
			case "Babyl Etpsc":
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_BABYL_ETPSC;
				break;
				
			case "Aldebaran 10Tau":
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_ALDEBARAN_15TAU;
				break;
				
			case "Hipparchos":
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_HIPPARCHOS;
				break;
				
			case "Sassanian":
				iflags = SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_SASSANIAN;
				break;
						
			default: 
				throw new IllegalArgumentException( "Unknown sidereal mode: " + siderealMode);
		
		}
				
		return iflags;
	}
}

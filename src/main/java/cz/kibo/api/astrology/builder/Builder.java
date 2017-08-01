package cz.kibo.api.astrology.builder;

import cz.kibo.api.astrology.domain.Coordinates;
import swisseph.SweConst;

public abstract class Builder {
		
	/**
	 * Sets topocentric cordinate system.
	 * 
	 * @param lon The Longitude in degrees
     * @param lat The Latitude in degrees 
     * @param geoalt The height above sea level in meters	
	 */
	protected Coordinates setTopo(double lon, double lat, double geoalt) {
		return new Coordinates(lon, lat, geoalt);		
	}
	
	/**
	 * Sets sidereal mode
	 * 
	 * @param siderealMode sidereal mode
	 * @return
	 */
	public int setZodiac(String siderealMode) {
		
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

package cz.kibo.api.astrology.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cz.kibo.api.astrology.domain.Coordinates;
import cz.kibo.api.astrology.domain.Cusp;
import cz.kibo.api.astrology.domain.Planet;
import swisseph.SweConst;

/**
 * The builder for cusps positions.
 * 
 *	Default: 
 *  - tropical zodiac type
 * 
 * @example
 * <pre>
 *	 // Tropical, Campanus.
 *	Cusp cuspEphemeris = new CuspBuilder(event)
 * 					.houses("Campanus") 					
 * 					.topo(48.8555, 18.0488, 0)
 * 					.build();
 *	cuspEphemeris.toJSON();
 * 
 *  // Sidereal, Placidus
 *	Cusp cuspEphemeris = new CuspBuilder(event)
 * 					.houses("Palcidus")
 * 					.topo(48.8555, 18.0488, 0)
 *  				.zodiac("Fagan Bradley")	
 *					.build();
 *	cuspEphemeris.toJSON();
 * </pre>
 * 
 * @author Tomas Jurman tomasjurman@gmail.com
 */
public class CuspBuilder extends Builder{
	
	private final LocalDateTime event;	
	private Coordinates coords;
	private int houses = 'P'; // Placidus default
	private int iflags = 0; // tropical default

	/**
	 * Creates Cusp builder. 
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 */
	public CuspBuilder( LocalDateTime event ) {
		this.event = event;
	}
	
	/**
	 * Sets houses sytem
	 * 
	 * @param houses the name of the house system
	 * @return
	 */
	public CuspBuilder houses(String houses) {
		
		switch (houses.trim()) {
			case "Placidus":
				this.houses = SweConst.SE_HSYS_PLACIDUS;
				break;
			case "Koch":
				this.houses = SweConst.SE_HSYS_KOCH;
		 		break;
			case "Porphyrius":
				this.houses = SweConst.SE_HSYS_PORPHYRIUS;
				break;	
			case "Regiomontanus":
				this.houses = SweConst.SE_HSYS_REGIOMONTANUS;
				break;	
			case "Campanus":
				this.houses = SweConst.SE_HSYS_CAMPANUS;
				break;	
			case "Equal":
				this.houses = SweConst.SE_HSYS_EQUAL;
				break;	
			case "Vehlow Equal":
				this.houses = SweConst.SE_HSYS_VEHLOW;
				break;	
			case "Whole":
				this.houses = SweConst.SE_HSYS_WHOLE_SIGN;
				break;
			case "Axial Rotation":
				this.houses = SweConst.SE_HSYS_MERIDIAN;
				break;	
			case "Horizontal":
				this.houses = SweConst.SE_HSYS_HORIZONTAL;
				break;		
			case "Polich/Page":
				this.houses = SweConst.SE_HSYS_POLICH_PAGE;
				break;	
			case "Alcabitius":
				this.houses = SweConst.SE_HSYS_ALCABITIUS;
				break;
			case "Gauquelin sectors":
				this.houses = SweConst.SE_HSYS_GAUQUELIN_SECTORS;
				break;	
			case "Morinus":
				this.houses = SweConst.SE_HSYS_MORINUS;
				break;											
			default: 
				throw new IllegalArgumentException( "Unknown houses system: " + houses);            	
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
	public CuspBuilder topo(double lon, double lat, double geoalt) {
		this.coords = super.getCoordinates(lon, lat, geoalt);
		return this;
	}
		
	/**
	 * Sets sidereal mode
	 * 
	 * @param siderealMode sidereal mode
	 * @return
	 */
	public CuspBuilder zodiac(String siderealMode) {
		this.iflags = super.getSiderealFlags(siderealMode);				
		return this;
	}	
	
	/**
	 * Builds query
	 * 
	 * @return
	 */
	public Cusp build() {							
		return new Cusp(this.event, this.coords, this.houses, this.iflags);
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
		CuspBuilder other = (CuspBuilder) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CuspBuilder [event=" + event + "]";
	}	
}

package cz.kibo.api.astrology.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cz.kibo.api.astrology.domain.Coordinates;
import cz.kibo.api.astrology.domain.Planet;
import cz.kibo.api.astrology.domain.Transit;

/**
 * The builder for transit positions.
 * 
 *	Default: 
 *  - tropical zodiac type
 * 
 * @example
 * <pre>
 * // Tropical, the Moon in opposition with the Sun.
 *	Transit transit = new TransitBuilder(event)
 * 					.planet("Moon") 					
 * 					.toPlanet("Sun") 
 * 					.aspect(180.0)
 * 					.build();
 *	
 *  System.out.println( transit.getTransit() );
 * 
 *  // Sidereal, the Moon in trine with the point 36.3° in zodiac.
 *	Planet transit = new PlanetBuilder(event)
 * 					.planet('Moon')
 * 					.toPoint(36.3)
 * 					.aspect(120.0) 					
 *  				.zodiac("Fagan Bradley")
 *  				.backwards(true)	
 *					.build();
 *
 *	System.out.println( transit.getTransit() );
 * </pre>
 * 
 * @author Tomas Jurman tomasjurman@gmail.com
 */
public class TransitBuilder extends Builder{
	
	private final LocalDateTime event;	
	private Coordinates coords;
	private Integer planet;
	private Integer planet2;		
	private Double point;
	private int iflags = 0; // tropical default
	private double aspect = 0.0; // default
	private boolean backwards = false;
	
	/**
	 * Creates Transit builder. 
	 * 
	 * @param event The start date and the time in Universal Time (UT).
	 */
	public TransitBuilder( LocalDateTime event ) {
		this.event = event;
	}
	
	/**
	 * Sets the transiting planets.
	 * 
	 * @param planet The transiting planet.
	 * @return
	 */
	public TransitBuilder planet( String planetName) {
		this.planet = super.getPlanet(planetName);
		return this;
	}
	
	/**
	 * Sets the transiting planets.
	 * 
	 * Note: It is possible set transiting planet or point. Not both together.
	 * 
	 * @param planet The second planet that will be transited by the first planet.
	 * @return
	 */
	public TransitBuilder toPlanet( String planetName) {
		this.point = null; // planet2 or point
		this.planet2 = super.getPlanet(planetName);
		return this;
	}
	
	/**
	 * Sets the transiting point.
	 * 
	 * Note: It is possible set point or transiting planet. Not both together.
	 * 
	 * @param point The desired transit degree.
	 * @return
	 */
	public TransitBuilder toPoint( Double point) {
		this.planet2 = null; // point or planet2
		this.point = point;
		return this;
	}
	
	/**
	 * Sets the aspect to point or planet.
	 * 
	 * @param aspect the aspect in degree. For example: 0, 60, 90, 120, 180,...
	 * @return
	 */
	public TransitBuilder aspect( Double aspect ) {
		this.aspect = aspect;
		return this;
	}
	
	/**
	 * Sets the direction of counting
	 * 
	 * @param backwards 
	 * @return
	 */
	public TransitBuilder backwards( boolean backwards ) {
		this.backwards = backwards;
		return this;
	}
	
	/**
	 * Sets sidereal mode
	 * 
	 * @param siderealMode sidereal mode
	 * @return
	 */
	public TransitBuilder zodiac(String siderealMode) {
		this.iflags = super.getSiderealFlags(siderealMode);				
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
	public TransitBuilder topo(double lon, double lat, double geoalt) {
		this.coords = super.getCoordinates(lon, lat, geoalt);
		return this;
	}
	
	/**
	 * Builds query
	 * 
	 * @return
	 */
	public Transit build() {
		
		Transit trasit;
				
		if(this.point == null) { // to planet
			
			if(this.coords == null) { // geocentric
				trasit = new Transit(this.event, this.planet, this.planet2, this.aspect, this.iflags, this.backwards);
				
			}else { // topocentric
				trasit = new Transit(this.event, this.planet, this.planet2, this.aspect, this.coords, this.iflags, this.backwards);
			}
						
		}else { // to point
			
			if(this.coords == null) { // geocentric
				trasit = new Transit(this.event, this.planet, (this.point + this.aspect), this.iflags, this.backwards);
				
			}else { // topocentric
				trasit = new Transit(this.event, this.planet, (this.point + this.aspect), this.coords, this.iflags, this.backwards);
			}			
		}
					
		return trasit;
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
		TransitBuilder other = (TransitBuilder) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransitBuilder [event=" + event + "]";
	}	
}

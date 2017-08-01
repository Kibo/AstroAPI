package cz.kibo.api.astrology.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cz.kibo.api.astrology.domain.Coordinates;

/**
 * The builder for transit positions.
 * 
 *	Default: 
 *  - tropical zodiac type
 * 
 * @example
 * <pre>
 * // Tropical, the Moon in conjunction with the Sun.
 *	Transit time = new TransitBuilder(event)
 * 					.planet("Moon") 					
 * 					.toPlanet("Sun") 
 * 					.build();
 *	
 *  System.out.println(time);
 * 
 *  // Sidereal, the Moon in conjunction with the point 36.3° in zodiac.
 *	Planet time = new PlanetBuilder(event)
 * 					.planet('Moon')
 * 					.toPoint(36.3)
 *  				.zodiac("Fagan Bradley")	
 *					.build();
 *
 *	System.out.println(time);
 * </pre>
 * 
 * @author Tomas Jurman tomasjurman@gmail.com
 */
public class TransitBuilder {
	
	private final LocalDateTime event;	
	private Coordinates coords;
	private Integer planet;
	private Integer planet2;		
	private Double point;
	private int iflags = 0; // tropical default
	
	/**
	 * Creates Transit builder. 
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 */
	public TransitBuilder( LocalDateTime event ) {
		this.event = event;
	}
}

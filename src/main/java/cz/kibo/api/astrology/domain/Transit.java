package cz.kibo.api.astrology.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;
import swisseph.TCPlanet;
import swisseph.TCPlanetPlanet;
import swisseph.TransitCalculator;

/**
 * Representation of transit positions at a certain date and time.
 * 
 * All time events - input and output - are in Universal Time (UT).
 * This class should not be used alone. Use {@link cz.kibo.api.astrology.builder.TransitBuilder} to create the correct instance of this class.
 * 
 * @author Tomas Jurman tomasjurman@gmail.com
 *
 */
public class Transit extends Ephemeris{
	
	private final LocalDateTime event;	
	private Integer planet;
	private Integer planet2;		
	private Double point;
	private Double offset;	
	private Coordinates coords;
	private int iflag;
	private Double transit;
	
	private SwissEph sw;
	private SweDate sd;
	
	/**
	 * Calculates planets transit to point in zodiac. Planets in geocentric cordinate system.
	 * 
	 * @param event Start date in Universal Time (UT).
	 * @param planet The transiting planet. Constants of planets are in {@link swisseph.SweConst}.
	 * @param point The desired transit degree.
	 * @param iflag Options for sidereal or tropical calculation. 0 - tropical, SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_* - for sidereal .Dont use other flags!.
	 * @param backwards
	 * 
	 * @see swisseph.SweConst
	 */	
	public Transit( LocalDateTime event, Integer planet, Double point, int iflag, boolean backwards) {
		super();
		this.event = event;
		this.planet = planet;
		this.point = point;
		this.iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
		
		sw = new SwissEph( super.getPathToEphemeris() );
		sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0 + event.getSecond()/360.0, SweDate.SE_GREG_CAL);
										
		if( (iflag & 0xF0000) ==  SweConst.SEFLG_SIDEREAL ) {
			sw.swe_set_sid_mode( iflag & 0x00FF );
			this.iflag |= SweConst.SEFLG_SIDEREAL;
		}
		
		TransitCalculator tc = new TCPlanet(
                sw,
                this.planet,
                this.iflag,
                this.point);
		 
		this.transit = sw.getTransitUT(tc, sd.getJulDay(), backwards);
	}
	
	/**
	 * Calculates planets transit to point in zodiac. Planets in geocentric cordinate system.
	 * 
	 * @param event Start date in Universal Time (UT).
	 * @param planet The transiting planet. Constants of planets are in {@link swisseph.SweConst}.
	 * @param point The desired transit degree.
	 * @param iflag Options for sidereal or tropical calculation. 0 - tropical, SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_* - for sidereal .Dont use other flags!.
	 * 
	 * @see swisseph.SweConst
	 */	
	public Transit( LocalDateTime event, Integer planet, Double point, int iflag) {
		this( event, planet, point, iflag, false);	 				 
	}
		
	/**
	 * Calculates planets transit to point in zodiac. Planets in topocentric cordinate system.
	 * 
	 * @param event Start date in Universal Time (UT).
	 * @param planet The transiting planet. Constants of planets are in {@link swisseph.SweConst}.
	 * @param point The desired transit degree.
	 * @param iflag Options for sidereal or tropical calculation. 0 - tropical, SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_* - for sidereal .Dont use other flags!.
	 * @param backwards
	 * 
	 * @see swisseph.SweConst
	 */	
	public Transit( LocalDateTime event, Integer planet, Double point, Coordinates coords, int iflag, boolean backwards) {
		super();
		this.event = event;
		this.planet = planet;
		this.point = point;
		this.coords = coords;
		this.iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE | SweConst.SEFLG_TOPOCTR;
			
		sw = new SwissEph( super.getPathToEphemeris() );
		sw.swe_set_topo(this.coords.getLongitude(), this.coords.getLatitude(), this.coords.getGeoalt());
		sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0 + event.getSecond()/360.0, SweDate.SE_GREG_CAL);
				
		if( (iflag & 0xF0000) ==  SweConst.SEFLG_SIDEREAL ) {
			sw.swe_set_sid_mode( iflag & 0x00FF );	
			this.iflag |= SweConst.SEFLG_SIDEREAL;
		}
		
		TransitCalculator tc = new TCPlanet(
                sw,
                this.planet,
                this.iflag,
                this.point);
		 
		this.transit = sw.getTransitUT(tc, sd.getJulDay(), backwards);		
	}
	
	/**
	 * Calculates planets transit to point in zodiac. Planets in topocentric cordinate system.
	 * 
	 * @param event Start date in Universal Time (UT).
	 * @param planet The transiting planet. Constants of planets are in {@link swisseph.SweConst}.
	 * @param point The desired transit degree.
	 * @param iflag Options for sidereal or tropical calculation. 0 - tropical, SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_* - for sidereal .Dont use other flags!.
	 * 
	 * @see swisseph.SweConst
	 */	
	public Transit( LocalDateTime event, Integer planet, Double point, Coordinates coords, int iflag) {
		this( event, planet, point, coords, iflag, false);	
	}	
	
	/**
	 * Calculates transit of two different planets to each other. Planets in geocentric cordinate system.
	 * 
	 * @param event Start date in Universal Time (UT).
	 * @param planet The transiting planet. Constants of planets are in {@link swisseph.SweConst}.
	 * @param planet2 The second planet that will be transited by the first planet.
	 * @param offset The desired transit degree.
	 * @param iflag Options for sidereal or tropical calculation. 0 - tropical, SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_* - for sidereal .Dont use other flags!.
	 * @param backwards
	 * 
	 * @see swisseph.SweConst
	 */
	public Transit( LocalDateTime event, Integer planet, Integer planet2, double offset, int iflag, boolean backwards) {
		super();
		this.event = event;
		this.planet = planet;
		this.planet2 = planet2;
		this.offset = offset;
		this.iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
		
		sw = new SwissEph( super.getPathToEphemeris() );
		sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0 + event.getSecond()/360.0, SweDate.SE_GREG_CAL);
										
		if( (iflag & 0xF0000) ==  SweConst.SEFLG_SIDEREAL ) {
			sw.swe_set_sid_mode( iflag & 0x00FF );				
			this.iflag |= SweConst.SEFLG_SIDEREAL;
		}
		
		TransitCalculator tc = new TCPlanetPlanet(
                sw,
                this.planet,
                this.planet2,
                this.iflag,
                this.offset);
		 
		this.transit = sw.getTransitUT(tc, sd.getJulDay(), backwards);		
	}
	
	/**
	 * Calculates transit of two different planets to each other. Planets in geocentric cordinate system.
	 * 
	 * @param event Start date in Universal Time (UT).
	 * @param planet The transiting planet. Constants of planets are in {@link swisseph.SweConst}.
	 * @param planet2 The second planet that will be transited by the first planet.
	 * @param offset The desired transit degree.
	 * @param iflag Options for sidereal or tropical calculation. 0 - tropical, SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_* - for sidereal .Dont use other flags!.
	 * 
	 * @see swisseph.SweConst
	 */
	public Transit( LocalDateTime event, Integer planet, Integer planet2, double offset, int iflag) {
		this( event, planet, planet2, offset, iflag, false );
	}
	
	
	/**
	 * Calculates transit of two different planets to each other. Planets in topocentric cordinate system.
	 * 
	 * @param event Start date in Universal Time (UT).
	 * @param planet The transiting planet. Constants of planets are in {@link swisseph.SweConst}.
	 * @param planet2 The second planet that will be transited by the first planet.
	 * @param offset The desired transit degree.
	 * @param coords longitude, latitude, geoalt for topocentric.
	 * @param iflag Options for sidereal or tropical calculation. 0 - tropical, SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_* - for sidereal .Dont use other flags!.
	 * @param backwards
	 * 
	 * @see swisseph.SweConst
	 */
	public Transit( LocalDateTime event, Integer planet, Integer planet2, double offset, Coordinates coords, int iflag, boolean backwards) {
		super();
		this.event = event;
		this.planet = planet;
		this.planet2 = planet2;
		this.coords = coords;
		this.offset = offset;
		this.iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE | SweConst.SEFLG_TOPOCTR;
		
		sw = new SwissEph( super.getPathToEphemeris());
		sw.swe_set_topo(this.coords.getLongitude(), this.coords.getLatitude(), this.coords.getGeoalt());
		sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0 + event.getSecond()/360.0, SweDate.SE_GREG_CAL);
										
		if( (iflag & 0xF0000) ==  SweConst.SEFLG_SIDEREAL ) {
			sw.swe_set_sid_mode( iflag & 0x00FF );	
			this.iflag |= SweConst.SEFLG_SIDEREAL;
		}
		
		TransitCalculator tc = new TCPlanetPlanet(
                sw,
                this.planet,
                this.planet2,
                this.iflag,
                this.offset);
		 
		this.transit = sw.getTransitUT(tc, sd.getJulDay(), backwards);		
	}
	
	/**
	 * Calculates transit of two different planets to each other. Planets in topocentric cordinate system.
	 * 
	 * @param event Start date in Universal Time (UT).
	 * @param planet The transiting planet. Constants of planets are in {@link swisseph.SweConst}.
	 * @param planet2 The second planet that will be transited by the first planet.
	 * @param offset The desired transit degree.
	 * @param coords longitude, latitude, geoalt for topocentric.
	 * @param iflag Options for sidereal or tropical calculation. 0 - tropical, SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_* - for sidereal .Dont use other flags!.
	 * 
	 * @see swisseph.SweConst
	 */
	public Transit( LocalDateTime event, Integer planet, Integer planet2, double offset, Coordinates coords, int iflag) {
		this( event, planet, planet2, offset, coords, iflag, false);
	}
	
	
	public LocalDateTime getDate() {
		SweDate sweDate = new SweDate(this.transit, SweDate.SE_GREG_CAL);		 		 		
		return LocalDateTime.ofInstant(sweDate.getDate(0).toInstant(), ZoneOffset.UTC);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + iflag;
		result = prime * result + ((planet == null) ? 0 : planet.hashCode());
		result = prime * result + ((transit == null) ? 0 : transit.hashCode());
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
		Transit other = (Transit) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (iflag != other.iflag)
			return false;
		if (planet == null) {
			if (other.planet != null)
				return false;
		} else if (!planet.equals(other.planet))
			return false;
		if (transit == null) {
			if (other.transit != null)
				return false;
		} else if (!transit.equals(other.transit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UTC: " + getDate();
	}
}

package cz.kibo.api.astrology.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;
import swisseph.TCPlanet;
import swisseph.TransitCalculator;

/**
 * Representation of transit positions at a certain date and time.
 * 
 * All time events - input and output - are in Universal Time (UT).
 * This class should not be used alone. Use {@linkcz.kibo.api.astrology.builder.TransitBuilder} to create the correct instance of this class.
 * 
 * @author Tomas Jurman tomasjurman@gmail.com
 *
 */
public class Transit extends Ephemeris{
	
	private final LocalDateTime event;	
	private Integer planet;	
	private Double point;
	private Coordinates coords;
	private int iflag;
	private Double transit;
	
	private SwissEph sw;
	private SweDate sd;
	
	/**
	 * Calculates planets transit to point. Planets in geocentric cordinate system.
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 * @param planet The transiting planet. Constants of planets are in {@link swisseph.SweConst}.
	 * @param point  The desired transit degree.
	 * @param iflag The calculation type flags.  Dont use: SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE. {@link http://th-mack.de/download/swisseph-doc/index.html}
	 * 
	 * @see swisseph.SweConst
	 */
	public Transit( LocalDateTime event, Integer planet, Double point, int iflag) {
		super();
		this.event = event;
		this.planet = planet;
		this.point = point;
		this.iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE;
		
		sw = new SwissEph( super.getPathToEphemeris() );
		sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0 + event.getSecond()/360.0, SweDate.SE_GREG_CAL);
										
		if( (iflag & 0xF0000) ==  SweConst.SEFLG_SIDEREAL ) {
			sw.swe_set_sid_mode( iflag & 0x00FF );			
		}
				
		this.iflag |= iflag;
		
		boolean backwards = false;
		
		TransitCalculator tc = new TCPlanet(
                sw,
                this.planet,
                this.iflag,
                this.point);
		 
		this.transit = sw.getTransitUT(tc, sd.getJulDay(), backwards);		 				 
	}
	
	/**
	 * Calculates planets transit to point. Suitable for topocentric cordinate system.
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 * @param planet The transiting planet. Constants of planets are in {@link swisseph.SweConst}.
	 * @param point  The desired transit degree.
	 * @param iflag The calculation type flags.  Dont use: SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE. {@link http://th-mack.de/download/swisseph-doc/index.html}
	 * 
	 * @see swisseph.SweConst
	 */
	public Transit( LocalDateTime event, Integer planet, Double point, Coordinates coords, int iflag) {
		super();
		this.event = event;
		this.planet = planet;
		this.point = point;
		this.coords = coords;
		this.iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_TRANSIT_LONGITUDE ;
			
		sw = new SwissEph( super.getPathToEphemeris() );
		sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0 + event.getSecond()/360.0, SweDate.SE_GREG_CAL);
				
		if( (iflag & 0xF0000) ==  SweConst.SEFLG_SIDEREAL ) {
			sw.swe_set_sid_mode( iflag & 0x00FF );			
		}
		
		if( (iflag & 0xF000) ==  SweConst.SEFLG_TOPOCTR ) {
			sw.swe_set_topo(this.coords.getLongitude(), this.coords.getLatitude(), this.coords.getGeoalt());			
		}
		
		this.iflag |= iflag;
		
		boolean backwards = false;
			
		TransitCalculator tc = new TCPlanet(
                sw,
                this.planet,
                this.iflag,
                this.point);
		 
		this.transit = sw.getTransitUT(tc, sd.getJulDay(), backwards);		
	}
	
	public LocalDateTime getTransit() {
		SweDate sweDate = new SweDate(this.transit, SweDate.SE_GREG_CAL);		 		 		
		return LocalDateTime.ofInstant(sweDate.getDate(0).toInstant(), ZoneOffset.UTC);
	}	
}

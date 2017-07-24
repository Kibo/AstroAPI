package cz.kibo.api.astrology.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;

/**
 * Representation of planets ephemeris at a certain date and time.
 * 
 * All time events - input and output - are in Universal Time (UT).
 * This class should not be used alone. Use {@linkcz.kibo.api.astrology.builder.PlanetBuilder} to create the correct instance of this class.
 * 
 * @author Tomas Jurman tomasjurman@gmail.com
 *
 */
public class Planet extends Ephemeris{
		
	private Map<String, List<Double>> planetsPositions;
	
	private final LocalDateTime event;	
	private List<Integer> planets;	
	private Coordinates coords;
	private int iflag;
		
	private SwissEph sw;
	private SweDate sd;
	
	
	/**
	 * Calculates planets positions. Planets in geocentric cordinate system.
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 * @param planets List of planets for position calculation. Constants of planets are in {@link swisseph.SweConst}.
	 * @see swisseph.SweConst
	 */
	public Planet( LocalDateTime event, List<Integer> planets) {
		super();
		this.event = event;
		this.planets = planets;
		
		sw = new SwissEph( super.getPathToEphemeris() );
		sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0, SweDate.SE_GREG_CAL);
		
		int iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_SPEED;
								
		this.planetsPositions = calculatePlanets( planets, sw, sd, iflag);	
		
	}
	
	/**
	 * Calculates planets positions. Planets in topocentric cordinate system.
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 * @param planets List of planets for position calculation. Constants of planets are in {@link swisseph.SweConst}.	
	 * @param coords longitude, latitude, geoalt for topocentric. Calculations relative to the observer on some place on the earth rather than relative to the center of the earth.
	 * @see swisseph.SweConst
	 */
	public Planet( LocalDateTime event, List<Integer> planets, Coordinates coords ) {
		super();
		this.event = event;
		this.planets = planets;
		this.coords = coords;
		
		sw = new SwissEph( getPathToEphemeris() );
		sw.swe_set_topo(this.coords.getLongitude(), this.coords.getLatitude(), this.coords.getGeoalt());
		sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0, SweDate.SE_GREG_CAL);
		
		int iflag = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_SPEED | SweConst.SEFLG_TOPOCTR;
								
		this.planetsPositions = calculatePlanets( planets, sw, sd, iflag);			
	}
	
	/**
	 * Calculates planets and cusps positions with specific options.
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 * @param planets List of planets for position calculation. Constants of planets are in {@link swisseph.SweConst}. 
	 * @param coords longitude, latitude, geoalt. 
	 * @param iflag Options for calculation. {@link http://www.astro.com/swisseph/swephprg.htm#_Toc471829060} 
	 * @see swisseph.SweConst
	 * @see iflag http://www.astro.com/swisseph/swephprg.htm#_Toc471829060
	 */
	public Planet( LocalDateTime event, List<Integer> planets, Coordinates coords, int iflag) {
		super();
		this.event = event;
		this.planets = planets;
		this.coords = coords;
		this.iflag = iflag;
		
		sw = new SwissEph( getPathToEphemeris() );		
		sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0, SweDate.SE_GREG_CAL);				
		
		if( (this.iflag & 0xF000) ==  SweConst.SEFLG_TOPOCTR ) {
			sw.swe_set_topo(this.coords.getLongitude(), this.coords.getLatitude(), this.coords.getGeoalt());
		}
				
		this.planetsPositions = calculatePlanets( this.planets, this.sw, this.sd, this.iflag);				
	}
	
	public Map<String, List<Double>> getPlanets() {
		return this.planetsPositions;
	}	
	
	public Coordinates getCoordinates() {		
		return new Coordinates(this.coords.getLatitude(), this.coords.getLongitude(), this.coords.getGeoalt());
	}
	
	public LocalDateTime getEvent() {		
		return this.event;
	}	

	private Map<String, List<Double>> calculatePlanets( List<Integer> planets, SwissEph calculator, SweDate date, int flags ) {
		Map<String, List<Double>> data = new HashMap<String, List<Double>>();
						
		for (Integer planet : planets) {
			
			double[] xp= new double[6];
			StringBuffer serr = new StringBuffer();
			int ret = sw.swe_calc_ut(date.getJulDay(),
					planet,
					flags,
					xp,
					serr);
			
			if (ret != flags) {
				if (serr.length() > 0) {
					System.err.println("Warning: " + serr);
				} else {
					System.err.println( String.format("Warning, different flags used (0x%x)", ret));
				}
			}
								
			// @see swisseph.SwissEph.swe_calc
			List<Double> values = new ArrayList<Double>();
			values.add(xp[0]); //longitude
			values.add(xp[3]); //speed in longitude
			
			data.put( getPlanetName(planet), values);			
		}
								
		return data;
	}
	
	/*
	 * @param planet - int from swisseph.SweConst
	 * 
	 * @see swisseph.SwissEph.swe_get_planet_name(int ipl)	
	 */
	private String getPlanetName(int planet) {	
				
		String name = sw.swe_get_planet_name(planet);
		
		if(planet == SweConst.SE_MEAN_APOG){
			name = "Lilith";
		}
		
		if(planet == SweConst.SE_MEAN_NODE){
			name = "NNode";
		}
		 				
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((planetsPositions == null) ? 0 : planetsPositions.hashCode());
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
		Planet other = (Planet) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (planetsPositions == null) {
			if (other.planetsPositions != null)
				return false;
		} else if (!planetsPositions.equals(other.planetsPositions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ UTC: " + this.event);
		
		if(this.coords != null) {
			sb.append(", " + this.coords + " ]\n");
		}else {
			sb.append(" ]\n");
		}
					
		for (Map.Entry<String, List<Double>> planet : this.planetsPositions.entrySet()){
			sb.append(planet.getKey() + ": " + planet.getValue() +"\n");
		}					
		
		return sb.toString();
	}	
}
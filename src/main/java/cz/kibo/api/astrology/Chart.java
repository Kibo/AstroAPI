package cz.kibo.api.astrology;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;


/**
 * Represents an ephemeris data at a certain date and time. All time events - input and output - are in Universal Time (UT).
 * 
 * This class should not be used alone. Use {@linkcz.kibo.api.astrology.ChartBuilder} to create the correct instance of this class.
 * @author Tomas Jurman tomasjurman@gmail.com
 */
public class Chart {
	
	private final Properties settings = new Properties();
	
	private List<Double> cuspsPositions;
	private Map<String, List<Double>> planetsPositions;
		
	private final LocalDateTime event;	
	private List<Integer> planets;	
	private Coordinates coords;
	private Integer houseSystem;
	
	private SwissEph sw;
	private SweDate sd;
	
	/**
	 * Calculates positions of planets in geocentric coordinate.
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 * @param planets List of planets for position calculation. Constants of planets are in {@link swisseph.SweConst}.
	 * @see swisseph.SweConst
	 */
	public Chart( LocalDateTime event, List<Integer> planets) {
		super();
		this.event = event;
		this.planets = planets;
						
		sw = new SwissEph( getPathToEphemeris() );
		sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0, SweDate.SE_GREG_CAL);
		
		int flags = SweConst.SEFLG_SWIEPH | SweConst.SEFLG_SPEED;
								
		this.planetsPositions = calculatePlanets( planets, sw, sd, flags);										
	}
	
	/**
	 * Calculates positions of planets and cusps in geocentric coordinate.
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 * @param planets List of planets for position calculation. Constants of planets are in {@link swisseph.SweConst}.
	 * @param houseSystem The house system as a character given as an integer. Constant from {@link swisseph.SweConst} 
	 * @see swisseph.SweConst
	 */
	public Chart( LocalDateTime event, List<Integer> planets, Integer houseSystem ) {
		super();
		this.event = event;
		this.planets = planets;		
		this.houseSystem = houseSystem;								
	}
	
	/**
	 * Calculates positions of planets in topocentric coordinate.
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 * @param planets List of planets for position calculation. Constants of planets are in {@link swisseph.SweConst}.	
	 * @param coords longitude, latitude, geoalt for topocentric. Calculations relative to the observer on some place on the earth rather than relative to the center of the earth.
	 * @see swisseph.SweConst
	 */
	public Chart( LocalDateTime event, List<Integer> planets, Coordinates coords ) {
		super();
		this.event = event;
		this.planets = planets;										
		this.coords = coords;
	}
					
	/**
	 * Calculates positions of planets and cusps in topocentric coordinate.
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).
	 * @param planets List of planets for position calculation. Constants of planets are in {@link swisseph.SweConst}.
	 * @param houseSystem The house system as a character given as an integer. Constant from {@link swisseph.SweConst}. 
	 * @param coords longitude, latitude, geoalt for topocentric. Calculations relative to the observer on some place on the earth rather than relative to the center of the earth.
	 * @see swisseph.SweConst
	 */
	public Chart( LocalDateTime event, List<Integer> planets, Integer houseSystem, Coordinates coords) {
		super();
		this.event = event;
		this.planets = planets;				
		this.houseSystem = houseSystem;								
		this.coords = coords;		
	}
		
	public List<Double> getHouses() {
		// Could by null. If only planets calculations are invoke.
		return cuspsPositions != null ? cuspsPositions : new ArrayList<Double>();
	}
	
	public Map<String, List<Double>> getPlanets() {
		return planetsPositions;
	}	
	
	public Coordinates getCoordinates() {		
		return new Coordinates(this.coords.getLatitude(), this.coords.getLongitude(), this.coords.getGeoalt());
	}
	
	public LocalDateTime getEvent() {		
		return this.event;
	}	
			
	public String toJSON() {
		return "TODO";
	}
		
	private String getPathToEphemeris() {
		
		try {									
			settings.load( this.getClass().getResourceAsStream("/settings.properties") );			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return settings.getProperty("ephemeris.path");		
	}
	
	private Map<String, List<Double>> calculatePlanets( List<Integer> planets, SwissEph calculator, SweDate date, int flags) {
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
	 * @see seasnam.txt You can get a list of names from http://cfa-www.harvard.edu/iau/lists/MPNames.html, which you would like to rename to seasnam.txt and move to your ephemeris directory.
	 */
	private String getPlanetName(int planet) {	
		
		// TODO
		String name = sw.swe_get_planet_name(planet);
		
		if(planet == SweConst.SE_MEAN_APOG){
			name = "Lilith";
		}
		
		if(planet == SweConst.SE_MEAN_NODE){
			name = "NNode";
		}
		 				
		return name;
	}
}
package cz.kibo.api.astrology.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.kibo.api.astrology.json.Convertor;
import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;

/**
 * Representation of cusps positions at a certain date and time.
 * 
 * All time events - input and output - are in Universal Time (UT).
 * This class should not be used alone. Use {@link cz.kibo.api.astrology.builder.PlanetBuilder} to create the correct instance of this class.
 * 
 * @author Tomas Jurman tomasjurman@gmail.com
 *
 */
public class Cusp extends Ephemeris{
	
	private List<Double> cuspsPositions;
			
	private final LocalDateTime event;		
	private Coordinates coords;
	private Integer houseSystem;
	private int iflag;
	
	private SwissEph sw;
	private SweDate sd;
	
	/**
	 * Calculates cusps positions with specific options.
	 * 
	 * @param event The date and the time of the event in Universal Time (UT).	
	 * @param coords longitude, latitude, geoalt. 
	 * @param houseSystem The house system as a character given as an integer.
	 * @param iflag Options for sidereal or tropical calculation. 0 - tropical, SweConst.SEFLG_SIDEREAL | SweConst.SE_SIDM_* - for sidereal. Dont use other flags!.
	 * 
	 * @see iflag @http://th-mack.de/download/swisseph-doc/swisseph/SwissEph.html#swe_set_sid_mode(int)
	 */
	public Cusp( LocalDateTime event, Coordinates coords, Integer houseSystem, int iflag) {
		super();
		this.event = event;		
		this.coords = coords;
		this.houseSystem = houseSystem;
		this.iflag = iflag;	
		
		sw = new SwissEph( super.getPathToEphemeris() );		
		sd = new SweDate(event.getYear(), event.getMonthValue(), event.getDayOfMonth(), event.getHour() + event.getMinute()/60.0 + event.getSecond()/3600.0, SweDate.SE_GREG_CAL);
		
		if( (this.iflag & 0xF0000) ==  SweConst.SEFLG_SIDEREAL ) {
			sw.swe_set_sid_mode( this.iflag & 0x00FF );
		}
					
		this.cuspsPositions = calculateCusps(this.sw, this.sd, this.houseSystem, this.coords, this.iflag);
	}
	
	public List<Double> getCusps() {		
		return this.cuspsPositions;
	}
		
	public Coordinates getCoordinates() {		
		return new Coordinates(this.coords.getLatitude(), this.coords.getLongitude(), this.coords.getGeoalt());
	}
	
	public LocalDateTime getEvent() {		
		return this.event;
	}
	
	/**
	 * Converts cusps positions to JSON string
	 * @return
	 */
	public String toJSON() {
		Convertor convertor = new Convertor( getCusps() );
		return convertor.getJSON().toString();
	}
		
	private List<Double> calculateCusps( SwissEph calculator, SweDate date, Integer hSystem, Coordinates coordinates, int flags ){
		
		List<Double> cPositions = new ArrayList<Double>();
		
		double[] cusps = new double[13];
		double[] acsc = new double[10];
		int result = sw.swe_houses(sd.getJulDay(),
				flags,
				coordinates.getLatitude(),
				coordinates.getLongitude(),
				hSystem,
				cusps,
				acsc);
		
		if(result == SweConst.ERR) {
			System.err.println("Error! Cusps calculation was not possible.");
			//TODO Exception
		}
		
		for(int i = 1; i <= 12; i++){
			cPositions.add(cusps[i]);					
		}
		
		return cPositions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuspsPositions == null) ? 0 : cuspsPositions.hashCode());
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
		Cusp other = (Cusp) obj;
		if (cuspsPositions == null) {
			if (other.cuspsPositions != null)
				return false;
		} else if (!cuspsPositions.equals(other.cuspsPositions))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( (this.iflag & 0xF0000) ==  SweConst.SEFLG_SIDEREAL ? "Sidereal - " : "Tropical \n");		
		if( (this.iflag & 0xF0000) ==  SweConst.SEFLG_SIDEREAL ) {
			sb.append( sw.swe_get_ayanamsa_name(this.iflag & 0x00FF) + "\n");
		}			
		sb.append("[ UTC: " + this.event + ", ");			
		sb.append(", " + this.coords + ", ");
		sb.append(this.sw.swe_house_name( (char)(this.houseSystem.intValue())) + " ]\n");	
		sb.append(this.cuspsPositions + " ]\n");		
		return sb.toString();
	}	
}

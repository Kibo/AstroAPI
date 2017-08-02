package cz.kibo.api.astrology.json;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Helper class for data conversion to JSON.
 * 
 * @author Tomas Jurman tomasjurman@gmail.com
 *
 */
public class Convertor {
	
	private final String PLANETS_KEY_NAME = "planets";
	private final String CUSPS_KEY_NAME = "cusps";
	
	
	private JSONObject dataset = new JSONObject();
	
	/**
	 * Creates Planetary Convertor
	 *  
	 * @param planets The output from class cz.kibo.api.astrology.domain.Planet
	 */
	public Convertor( Map<String, List<Double>> planetsPositions ) {
		
		JSONObject planets = new JSONObject();
		
		for (Map.Entry<String, List<Double>> entry : planetsPositions.entrySet()){
			String planet = entry.getKey();
			List<Double> xp = entry.getValue();
						
			JSONArray data = new JSONArray();
			for(Double value : xp) {
				data.put(value);
			}
										
			planets.put(planet, data);
		}
		
		dataset.put(PLANETS_KEY_NAME, planets);
	}
	
	/**
	 * Creates Cusps Convertor
	 *  
	 * @param planets The output from class cz.kibo.api.astrology.domain.Cusp
	 */
	public Convertor( List<Double> cuspsPositions ) {
		JSONArray cusps = new JSONArray();	
		
		for(Double cup : cuspsPositions) {
			cusps.put(cup);
		}
				
		dataset.put(CUSPS_KEY_NAME, cusps);
	}
			
	/**
	 * @return Returns the converted data	
	 */
	public JSONObject getJSON() {
		return dataset;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataset == null) ? 0 : dataset.hashCode());
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
		Convertor other = (Convertor) obj;
		if (dataset == null) {
			if (other.dataset != null)
				return false;
		} else if (!dataset.equals(other.dataset))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return dataset.toString();
	}		
}

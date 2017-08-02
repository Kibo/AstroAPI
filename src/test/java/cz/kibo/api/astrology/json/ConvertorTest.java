package cz.kibo.api.astrology.json;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ConvertorTest {

	@Test
	public void planetsTest() {
		
		String result = "{\"planets\":{\"Moon\":[4,5,6],\"Sun\":[1,2,3]}}";
		
		Map<String, List<Double>> planetsPositions = new HashMap<String, List<Double>>();
		List<Double> val1 = new ArrayList<Double>(){
			{
				add(1.0);
				add(2.0);
				add(3.0);
			}
		};
		
		List<Double> val2 = new ArrayList<Double>(){
			{
				add(4.0);
				add(5.0);
				add(6.0);
			}
		};
				
		planetsPositions.put("Sun", val1);
		planetsPositions.put("Moon", val2);
				
		Convertor convertor = new Convertor( planetsPositions );
		
		assertEquals(result, convertor.getJSON().toString());		
	}
	
	@Test
	public void cuspsTest() {
		String result = "{\"cusps\":[1,2,3]}";
		List<Double> cuspsPosition = new ArrayList<Double>() {
			{
				add(1.0);
				add(2.0);
				add(3.0);				
			}
		};
		
		Convertor convertor = new Convertor( cuspsPosition );
		
		assertEquals(result, convertor.getJSON().toString());			
	}
}

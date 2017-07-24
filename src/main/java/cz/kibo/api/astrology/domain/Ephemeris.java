package cz.kibo.api.astrology.domain;

import java.io.IOException;
import java.util.Properties;

public abstract class Ephemeris {
	
	private final Properties settings = new Properties();
	
	protected String getPathToEphemeris(){			
		try {									
			settings.load( this.getClass().getResourceAsStream("/settings.properties") );			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return settings.getProperty("ephemeris.path");		
	}
}

# AstroAPI
The AstroAPI is a simple API allowing consumers to get planets and cusps positions. The AstroAPI uses Swiss Ephemeris library port to Java by [Thomas Mack](http://th-mack.de/). AstroAPI is clear and tested API over this library.

**Planets**:
Sun, Moon, Mercury, Venus, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto, Chiron, Lilith, NNode.

**House systems**:
Campanus, Koch, Morinus, Porphyry, Placidus, Equal. TODO

**Coordinate systems**
Geocentric, topocentric.

### Version: 0.0.1-SNAPSHOT


## Documentation
- [High precision ephemeris developed by Astrodienst](http://www.astro.com/swisseph/swephinfo_e.htm)
- [Thomas Mack - a port of the SwissEphemeris package to Java](http://th-mack.de/international/download/)


### Install
- [set path](https://github.com/Kibo/AstroAPI/blob/master/src/main/resources/settings.properties) to ephemeris
- mvn build
- mvn package
- mvn javadoc:javadoc


### Example
``` 
//All planets, houses, topocentric
Chart chart = new ChartBuilder( LocalDateTime event )
			.planets()
			.houses("Placidus")
			.topo(48.8555, 18.0488)
			.buildChart();
			
chart.toJSON()	;	

```


```
//Position of Sun, geocentric			
Chart chart = new ChartBuilder( LocalDateTime event )
			.planet("Sun")
			.buildChart();
			
chart.toJSON()	;			
```	

```
//Houses, topocentric			
Chart chart = new ChartBuilder( LocalDateTime event )
			.houses("Campanus")
			.topo(48.8555, 18.0488)
			.buildChart();
			
chart.toJSON()	;
```		
```
//Next transit Sun to point 36° in zodiac, geocentric			
Transit transit = new TransitBuilder( LocalDateTime event )
			.planet("Sun")
			.toPoint(36)
			.aspects("0,60,90,120,180")			
			.buildTransit();
			
transit.toJSON();			
```	

```
//Next transit Sun to Mars, count 4, geocentric			
Transit transit = new TransitBuilder( LocalDateTime event )
			.planet("Sun")
			.toPlanet("Mars")
			.aspects("90")		
			.count(4)
			.buildTransit();	
			
transit.toJSON();
	
```					

## License
GNU public version 3
	
# AstroAPI
The AstroAPI is a simple API allowing consumers to get planets and cusps positions. The AstroAPI uses Swiss Ephemeris library port to Java by [Thomas Mack](http://th-mack.de/). AstroAPI is clear and tested API over this library.

**Planets**:
Sun, Moon, Mercury, Venus, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto, Chiron, Lilith, NNode.

**House systems**:

Placidus, Koch, Porphyrius, Regiomontanus, Campanus, Equal, Vehlow Equal, Whole, Axial Rotation, Horizontal, Polich/Page, Alcabitius, Gauquelin sectors, Morinus.

**Coordinate systems**

Geocentric, Topocentric.

**Zodiac type**

Tropical, Sidereal

### Version: 0.8

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
// Topocentric, tropical, all planets.
Planet planetEphemeris = new PlanetBuilder(event)
  					.planets() 					
  					.topo(48.8555, 18.0488, 0)
  					.build();
planetEphemeris.toJSON();

```

```
// Geocentric, sidereal, Sun and Moon only.
Planet planetEphemeris = new PlanetBuilder(event)
 					.planet('Sun, Moon')
					.zodiac("Fagan Bradley")	
					.build();
planetEphemeris.toJSON();				
```	

```
 // Tropical, Campanus.
 Cusp cuspEphemeris = new CuspBuilder(event)
  					.houses("Campanus") 					
  					.topo(48.8555, 18.0488, 0)
  					.build();
 cuspEphemeris.toJSON();
```	

```
// Sidereal, Placidus
Cusp cuspEphemeris = new CuspBuilder(event)
  					.houses("Palcidus")
  					.topo(48.8555, 18.0488, 0)
    					.zodiac("Fagan Bradley")	
 					.build();
cuspEphemeris.toJSON();
```
	
```
// Tropical, the Moon in conjunction with the Sun.
 Transit time = new TransitBuilder(event)
  					.planet("Moon") 					
  					.toPlanet("Sun") 
  					.build();
 	
 System.out.println(time);	
```	

```
// Sidereal, the Moon in conjunction with the point 36.3° in zodiac.
 Planet time = new PlanetBuilder(event)
  					.planet('Moon')
  					.toPoint(36.3)
   				.zodiac("Fagan Bradley")	
 					.build();
 
System.out.println(time);	
```					

## License
GNU public version 3
	
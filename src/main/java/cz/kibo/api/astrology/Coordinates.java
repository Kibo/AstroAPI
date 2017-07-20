package cz.kibo.api.astrology;

public class Coordinates {
	
	private final double latitude;
	private final double longitude;
    private final double geoalt;
    
    /**
     * 
     * @param lat The Latitude in degrees 
     * @param lon The Longitude in degrees
     * @param geoalt The height above sea level in meters
     */
    public Coordinates(double lat, double lon, double geoalt) {
    	super();        
    	this.latitude = lat;
    	this.longitude = lon;        
        this.geoalt = geoalt;
    }

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getGeoalt() {
		return geoalt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(geoalt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Coordinates other = (Coordinates) obj;
		if (Double.doubleToLongBits(geoalt) != Double.doubleToLongBits(other.geoalt))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + ", geoaltitude=" + geoalt + "]";
	}     
}

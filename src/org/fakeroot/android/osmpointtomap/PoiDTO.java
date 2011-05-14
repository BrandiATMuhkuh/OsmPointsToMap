/*
 *  This file is part of OsmPointsToMap.
 *
 *  OsmPointsToMap is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OsmPointsToMap is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OsmPointsToMap.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.fakeroot.android.osmpointtomap;

import java.io.Serializable;

public class PoiDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private double lat;
	private double lng;
	private String title;
	private String amenity;
	private String keyname;
	private String keyvalue;
	
	public PoiDTO() {
		// TODO Auto-generated constructor stub
	}

	public PoiDTO(double lat, double lng, String title, String keyname, String keyvalue, String amentiy) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.title = title;
		this.amenity = amentiy;
		this.keyname = keyname;
		this.keyvalue = keyvalue;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * @return the name
	 */
	public String getTitle() {
		return title;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoiDTO [lat=" + lat + ", lng=" + lng + ", title=" + title
				+ ", amenity=" + amenity + ", keyname=" + keyname
				+ ", keyvalue=" + keyvalue + "]";
	}

	/**
	 * @return the amenity
	 */
	public String getAmenity() {
		return amenity;
	}

	/**
	 * @return the tname
	 */
	public String getKeyName() {
		return keyname;
	}
	
	
	/**
	 * @return the tname
	 */
	public String getKeyVame() {
		return keyvalue;
	}
	
	
}

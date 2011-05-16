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
package org.fakeroot.android.osmpointtomap.pojos;

import android.graphics.drawable.Drawable;

public class KeyAmenityStyle {

	private String _amentiy;
	private String _key; 
	private Drawable _markerPic;
	
	/**
	 * amentiy or key must not be null
	 * @param amentiy
	 * @param key
	 * @param markerPic must not be null
	 */
	public KeyAmenityStyle(String amentiy, String key, Drawable markerPic) {
		_amentiy=amentiy;
		_key=key;
		_markerPic=markerPic;
	}

	/**
	 * @return the amentiy
	 */
	public String getAmentiy() {
		return _amentiy;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return _key;
	}

	/**
	 * @return the markerPic
	 */
	public Drawable getMarkerPic() {
		return _markerPic;
	}
	
	
}

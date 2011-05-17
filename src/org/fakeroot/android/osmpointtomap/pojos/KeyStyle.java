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

public class KeyStyle {

	private String _key; 
	private KeyStyle[] _value;
	private Drawable _markerPic;
	
	/**
	 * key must not be null
	 * @param key
	 * @param markerPic must not be null
	 */
	public KeyStyle(String key, Drawable markerPic) {
		_key=key;
		_markerPic=markerPic;
	}
	
	/**
	 * key must not be null
	 * @param key 
	 * @param value
	 * @param markerPic
	 */
	public KeyStyle(String key, KeyStyle[] value, Drawable markerPic) {
		this(key, markerPic);
		_value=value;
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

	/**
	 * @return the value
	 */
	public KeyStyle[] getValue() {
		return _value;
	}
	
	
}

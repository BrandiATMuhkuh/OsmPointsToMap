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
package org.fakeroot.android.osmpointtomap.marker;

import org.fakeroot.android.osmpointtomap.PoiDTO;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class OverlayMarker extends OverlayItem {

	private PoiDTO _poidto;
	
	public OverlayMarker(GeoPoint point, PoiDTO poidto) {
		super(point, poidto.getTitle(), poidto.getTitle());
		_poidto=poidto;
	}

	/**
	 * @return the poidto
	 */
	public PoiDTO getPoidto() {
		return _poidto;
	}
	
	

}

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

public class PoiContainer {
	PoiDTO[] poi;
	
	public PoiContainer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the poi
	 */
	public PoiDTO[] getPoi() {
		return poi;
	}

	/**
	 * @param poi the poi to set
	 */
	public void setPoi(PoiDTO[] poi) {
		this.poi = poi;
	}
	
	
}

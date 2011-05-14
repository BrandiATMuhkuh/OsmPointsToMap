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

import java.util.ArrayList;

import org.fakeroot.android.osmpointtomap.InfoPop;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.google.android.maps.ItemizedOverlay;

public class HelloItemizedOverlay extends ItemizedOverlay<OverlayMarker> {

	private ArrayList<OverlayMarker> mOverlays = new ArrayList<OverlayMarker>();
	private Context _context;

	public HelloItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		_context = context;
	}

	public void clear() {
		mOverlays.clear();
		populate();
	}

	public void population() {
		populate();
	}

	public void addOverlay(OverlayMarker overlay) {
		mOverlays.add(overlay);
	}

	@Override
	protected OverlayMarker createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onTap(int index) {
		Log.d("osm", "tap: "+index);
		OverlayMarker item = mOverlays.get(index);
		new InfoPop(_context, item.getPoidto());
		
		return false;
	}
}

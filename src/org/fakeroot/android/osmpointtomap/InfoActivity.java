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

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends Activity {
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	TextView textview = new TextView(this);
        textview.setText("This is the Info tab");
        setContentView(textview);
    }
}

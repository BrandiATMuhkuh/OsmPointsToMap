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

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.android.maps.MapView;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class InitA extends TabActivity {

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.init);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, MapA.class);

		if(Worker.getInstance().getAdMobId()!=null){
			LinearLayout layout = ((LinearLayout) findViewById(R.id.initlayout));
			Worker.getInstance().setAdMob(new AdView(this, AdSize.BANNER, Worker.getInstance().getAdMobId()));
			
			AdView adView = Worker.getInstance().getAdMob();
			AdRequest request = new AdRequest();
			adView.loadAd(request);
			layout.addView(adView,0);
		
		}
		
		
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("Map")
				.setIndicator("Map", res.getDrawable(R.drawable.ic_map_marker))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, ListA.class);
		spec = tabHost
				.newTabSpec("List")
				.setIndicator("List", res.getDrawable(R.drawable.ic_notepad))
				.setContent(intent);
		tabHost.addTab(spec);
		
		

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu, menu);
		return true;
	}


	
}
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

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InfoA extends ListActivity {
	
	private Worker _worker = Worker.getInstance();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);


	  ListView lv = getListView();
	  lv.setTextFilterEnabled(true);

	  lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	    	
	    	
	    	if(_worker.getPoiDTOSave()[position]!=null){
	    		new InfoPop(getMyContext(), _worker.getPoiDTOSave()[position]);
	    	}

	    }

	  });
	}
	
	public Context getMyContext(){
		return this;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		//start to get data
		if(_worker.getPoiDTOSave()!=null){
			String[] pois = new String[_worker.getPoiDTOSave().length];
			
			for(int i=0;i<_worker.getPoiDTOSave().length;i++){
				pois[i]=_worker.getPoiDTOSave()[i].getTitle();
			}
			
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, pois));
		}
		
		
		
	}
}

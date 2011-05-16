package org.fakeroot.android.osmpointtomap;

import java.util.List;

import org.fakeroot.android.osmpointtomap.pojos.PoiDTO;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListViewAdapter extends BaseAdapter{

	 private Context context;
	 private List<PoiDTO> poiList;
	 
	public ListViewAdapter(Context context, List<PoiDTO> poiList ) { 
       this.context = context;
       this.poiList = poiList;
   }
	
	@Override
	public int getCount() {
		return poiList.size();
	}

	@Override
	public Object getItem(int position) {
		return poiList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PoiDTO poi = poiList.get(position);
		return new ListAdapterView(this.context, poi);
	}
	
}

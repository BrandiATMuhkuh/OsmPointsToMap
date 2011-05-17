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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import org.fakeroot.android.osmpointtomap.pojos.BoundingBox;
import org.fakeroot.android.osmpointtomap.pojos.KeyAmenityStyle;
import org.fakeroot.android.osmpointtomap.pojos.PoiContainer;
import org.fakeroot.android.osmpointtomap.pojos.PoiDTO;
import com.google.gson.Gson;
import android.util.Log;


/**
 * The first time start the worker you must
 * setUrl()
 * and can set
 * setKeys()
 * setAmenities()
 * 
 *
 */
public class Worker {

	private static Worker _worker;
	private PoiDTO[] _responseSave;
	private KeyAmenityStyle[] _keys = null;
	private Gson gson = new Gson();
	private String _url;
	private String _mapApiKey;
	private String _adMobId;

	/**
	 * Set KeyAmenityStyle(null, String key, Drawable markerPic) 
	 * @param keys
	 */
	public void setKeys(KeyAmenityStyle[] keys){
		_keys=keys;
	}
	
	
	/**
	 * url should look like this http://10.10.43.166:8080/OsmPointsToMapService/OsmPointsToMapService
	 * @param url
	 */
	public void setUrl(String url){
		_url=url;
	}

	
	

	/**
	 * @return the adMobId
	 */
	public String getAdMobId() {
		return _adMobId;
	}

	/**
	 * @param adMobId the adMobId to set
	 */
	public void setAdMobId(String adMobId) {
		_adMobId = adMobId;
	}

	/**
	 * @return the keys
	 */
	public KeyAmenityStyle[] getKeys() {
		return _keys;
	}

	
	
	

	/**
	 * @return the mapApiKey
	 */
	public String getMapApiKey() {
		return _mapApiKey;
	}

	/**
	 * @param mapApiKey the mapApiKey to set
	 */
	public void setMapApiKey(String mapApiKey) {
		_mapApiKey = mapApiKey;
	}

	private Worker() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Worker getInstance(){
		if(_worker==null)_worker=new Worker();
		
		return _worker;
	}
	
	/**
	 * 
	 * @return can be null
	 */
	public PoiDTO[] getPoiDTOSave(){
		return _responseSave;
	}

	public PoiDTO[] getPoi(BoundingBox bbox){
		try {
			//http://127.0.0.1:8888/osmpointtomap/OsmPoiJson?swLat=55.3&swLng=12.4&neLat=56.2&neLng=11.2
			Log.d("latLng", ""+bbox.getLowerRightCorner().getLatitudeE6());
			Log.d("latLng", ""+bbox.getLowerRightCorner().getLongitudeE6());
			Log.d("latLng", ""+bbox.getUpperLeftCorner().getLatitudeE6());
			Log.d("latLng", ""+bbox.getUpperLeftCorner().getLongitudeE6());
		
			/*
			URL url = new URL("http://192.168.120.53:8080/osmpointstomap/osmpointtomap/OsmPoiJson?" +
					"swLat="+(bbox.getLowerRightCorner().getLatitudeE6()/1e6)+"&" +
					"swLng="+(bbox.getLowerRightCorner().getLongitudeE6()/1e6)+"&" +
					"neLat="+(bbox.getUpperLeftCorner().getLatitudeE6()/1e6)+"&" +
					"neLng="+(bbox.getUpperLeftCorner().getLongitudeE6()/1e6)+"&"+
					"keys=shop");
			*/
			
			StringBuffer urlString = new StringBuffer();
			urlString.append(_url+"?");
			urlString.append("swLat="+(bbox.getLowerRightCorner().getLatitudeE6()/1e6)+"&");
			urlString.append("swLng="+(bbox.getLowerRightCorner().getLongitudeE6()/1e6)+"&");
			urlString.append("neLat="+(bbox.getUpperLeftCorner().getLatitudeE6()/1e6)+"&");
			urlString.append("neLng="+(bbox.getUpperLeftCorner().getLongitudeE6()/1e6)+"");
			
			if(_keys!=null){
				for(KeyAmenityStyle kas: _keys){
					urlString.append("&key="+kas.getKey());
					
					//add here special request
				}
			}
			
			URL url = new URL(urlString.toString());
			Log.d("restquery", urlString.toString());
			
			//URL url = new URL("http://192.168.120.53:8080/osmpointstomap/osmpointtomap/OsmPoiJson?swLat=55.3&swLng=12.4&neLat=56.2&neLng=11.2&keys=shop");
			
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		   
			
		    StringBuffer str = new StringBuffer();
		    String strs;
		    while ((strs = in.readLine()) != null) {
		    	str.append(strs);
		    	Log.d("response", strs);
		        // str is one line of text; readLine() strips the newline character(s)
		    }
		    in.close();
		    
		    Log.d("response", str.toString());
		    
		    String dJson = URLDecoder.decode(str.toString());
			//PoiContainer points = gson.fromJson("{\"poi\":[]}", PoiContainer.class);

			PoiContainer points = gson.fromJson(dJson, PoiContainer.class);
			_responseSave=points.getPoi();

			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return _responseSave;
	}
}

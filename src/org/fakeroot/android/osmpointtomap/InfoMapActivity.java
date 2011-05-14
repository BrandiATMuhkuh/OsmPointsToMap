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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fakeroot.android.osmpointtomap.marker.HelloItemizedOverlay;
import org.fakeroot.android.osmpointtomap.marker.OverlayMarker;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class InfoMapActivity extends MapActivity {

	private MapView mapView;
	private InfoMapController controller;
	//HelloItemizedOverlay itemizedoverlay;
	private HashMap<String, HelloItemizedOverlay> overlayList = new HashMap<String, HelloItemizedOverlay>();
	private Worker worker = Worker.getInstance();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.infomapactivity);
        
        
        //Do something with your map
        //mapView = new MapView(this, "0S0PTvQ0ddcL66DeaCTV6O9zH8LqOHKI7wFwlSw");
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        
        List<Overlay> listOfOverlays = mapView.getOverlays();
        //listOfOverlays.clear();
        listOfOverlays.add(new Overlay(){
        	@Override
    	    public boolean onTouchEvent(MotionEvent event, MapView mapView) 
    	    {
        		Log.d("osm", "onTouchEvent2: "+event.getAction());

        		//---when user lifts his finger---
    	        if (event.getAction() == 1) {    
    	        	
    		    	Log.d(ACTIVITY_SERVICE, "changed");
    		    	mapChanged();
    		    	return false;
    	        }
				return false; 
        		
    	    }
        	
        	
        });   
        
        
        
        controller=new InfoMapController();
        controller.setInfoMapActivity(this);
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable  = this.getResources().getDrawable(R.drawable.m_market);
        
        if(worker.getKeys()!=null){
        	for(KeyAmenityStyle key:worker.getKeys()){
        		HelloItemizedOverlay temp = new HelloItemizedOverlay(key.getMarkerPic(),this);
        		overlayList.put(key.getKey(), temp);
        		temp.population();
                mapOverlays.add(temp);
        	}
        }
        
        if(worker.getAmenities()!=null){
        	for(KeyAmenityStyle amenties:worker.getAmenities()){
        		HelloItemizedOverlay temp = new HelloItemizedOverlay(amenties.getMarkerPic(),this);
        		overlayList.put(amenties.getAmentiy(), temp);
        		temp.population();
                mapOverlays.add(temp);
        	}
        }
        
        
           
        
        
        
        
    }
    
    
    public void drawMarker(PoiDTO[] markers){
    	
    	for(String k:overlayList.keySet()){
    		overlayList.get(k).clear();
    	}
    	
    	
    	for(PoiDTO poi:markers){
    		
    		int lat = (int)(poi.getLat() * 1e6);
    		int lng = (int)(poi.getLng() * 1e6);
    		GeoPoint point = new GeoPoint(lat,lng);
    		
    		if(worker.getKeys()!=null){
    			
            	for(KeyAmenityStyle key:worker.getKeys()){
            		if(poi.getKeyName()!=null && key.getKey().equals(poi.getKeyName())){
            			Log.d("latLng", "Lat: "+lat+" lng: "+lng+" ,w: "+poi.getKeyName());
            			overlayList.get(key.getKey()).addOverlay(new OverlayMarker(point, poi));
            		}
            	}
    		}
    		
    		if(worker.getAmenities()!=null){
            	for(KeyAmenityStyle amenity:worker.getAmenities()){
            		if(poi.getAmenity()!=null && amenity.getAmentiy().equals(poi.getAmenity())){
            			Log.d("latLng", "Lat: "+lat+" lng: "+lng+" ,a: "+poi.getAmenity());
            			overlayList.get(amenity.getAmentiy()).addOverlay(new OverlayMarker(point, poi));
            		}
            	}
    		}
    		
    		
    	}
    	
    	for(String k:overlayList.keySet()){
    		overlayList.get(k).population();
    	}
    	
    }

    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void mapChanged(){		
		controller.onMapChanged(new BoundingBox(mapView.getMapCenter(), mapView.getLatitudeSpan(), mapView.getLongitudeSpan()));
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("osm", "onTouchEvent: "+event.getAction());
		
		//---when user lifts his finger---
        if (event.getAction() == 1) {    
        	
	    	Log.d("osm", "changed");
	    	mapChanged();
	    	return true;
        }
		return false; 
	}


}

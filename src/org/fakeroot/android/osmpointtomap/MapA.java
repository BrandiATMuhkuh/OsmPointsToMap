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

import java.util.HashMap;
import java.util.List;
import org.fakeroot.android.osmpointtomap.marker.HelloItemizedOverlay;
import org.fakeroot.android.osmpointtomap.marker.OverlayMarker;
import org.fakeroot.android.osmpointtomap.pojos.BoundingBox;
import org.fakeroot.android.osmpointtomap.pojos.KeyAmenityStyle;
import org.fakeroot.android.osmpointtomap.pojos.PoiDTO;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MapA extends MapActivity {

	private AdView adView;
	private LocationManager _lm;
	private boolean userGpsRequest = true;
	private MapController _mc;
	private MapView mapView;
	private InfoMapController controller;
	// HelloItemizedOverlay itemizedoverlay;
	private HashMap<String, HelloItemizedOverlay> overlayList = new HashMap<String, HelloItemizedOverlay>();
	private Worker worker = Worker.getInstance();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infomapactivity);

		// Do something with your map
		RelativeLayout layout = ((RelativeLayout) findViewById(R.id.infomaplayout));

		// mapView = (MapView) findViewById(R.id.mapview);
		mapView = new MapView(this, worker.getMapApiKey());
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);
		{
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.FILL_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			layout.addView(mapView, lp);
		}

		// Add ads
		adView = new AdView(this, AdSize.BANNER, worker.getAdMobId());
		{
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					320,
					50);
			// Initiate a generic request to load it with an ad
			AdRequest request = new AdRequest();
			request.setTesting(true);
	        request.addTestDevice(AdRequest.TEST_EMULATOR);
			adView.loadAd(request);
			layout.addView(adView,lp);
		}

		

		List<Overlay> listOfOverlays = mapView.getOverlays();

		// listOfOverlays.clear();
		listOfOverlays.add(new Overlay() {
			@Override
			public boolean onTouchEvent(MotionEvent event, MapView mapView) {
				Log.d("osm", "onTouchEvent2: " + event.getAction());

				// ---when user lifts his finger---
				if (event.getAction() == 1) {

					Log.d(ACTIVITY_SERVICE, "changed");
					mapChanged();
					return false;
				}
				return false;

			}

		});

		controller = new InfoMapController();
		controller.setInfoMapActivity(this);

		List<Overlay> mapOverlays = mapView.getOverlays();

		if (worker.getKeys() != null) {
			for (KeyAmenityStyle key : worker.getKeys()) {
				HelloItemizedOverlay temp = new HelloItemizedOverlay(
						key.getMarkerPic(), this);
				overlayList.put(key.getKey(), temp);
				temp.population();
				mapOverlays.add(temp);
			}
		}


		// location
		_mc = mapView.getController();
		_mc.setZoom(16);

		_lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		mapChanged();

	}

	public void drawMarker(PoiDTO[] markers) {

		for (String k : overlayList.keySet()) {
			overlayList.get(k).clear();
		}

		for (PoiDTO poi : markers) {

			int lat = (int) (poi.getLat() * 1e6);
			int lng = (int) (poi.getLng() * 1e6);
			GeoPoint point = new GeoPoint(lat, lng);

			if (worker.getKeys() != null) {

				for (KeyAmenityStyle key : worker.getKeys()) {
					if (poi.getKeyName() != null
							&& key.getKey().equals(poi.getKeyName())) {
						Log.d("latLng", "Lat: " + lat + " lng: " + lng
								+ " ,w: " + poi.getKeyName());
						overlayList.get(key.getKey()).addOverlay(
								new OverlayMarker(point, poi));
					}
				}
			}

		}

		for (String k : overlayList.keySet()) {
			overlayList.get(k).population();
		}

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	private void mapChanged() {
		controller.onMapChanged(new BoundingBox(mapView.getMapCenter(), mapView
				.getLatitudeSpan(), mapView.getLongitudeSpan()));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.thx:
			startActivity(new Intent(this, ThxA.class));

			break;
		case R.id.location:
			Toast.makeText(this, "Locate me", Toast.LENGTH_LONG).show();
			userGpsRequest = true;

			LocationListener locationListener = new LocationListener() {

				@Override
				public void onStatusChanged(String provider, int status,
						Bundle extras) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onProviderEnabled(String provider) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onProviderDisabled(String provider) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onLocationChanged(Location location) {
					if (location != null && userGpsRequest) {
						Log.d("OMS",
								"Location: " + "lat: " + location.getLatitude()
										+ "lng: " + location.getLongitude());

						_mc.setCenter(new GeoPoint((int) (location
								.getLatitude() * 1e6), (int) (location
								.getLongitude() * 1e6)));
						userGpsRequest = false;
						_mc.setZoom(16);
						mapChanged();
						AdRequest request = new AdRequest();
						request.setTesting(true);
				        request.addTestDevice(AdRequest.TEST_EMULATOR);
						request.setLocation(location);
						adView.loadAd(request);
					}

				}
			};

			if (_lm.isProviderEnabled(LocationManager.GPS_PROVIDER))
				_lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
						locationListener);
			else if (_lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
				_lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
						0, locationListener);

			break;
		case R.id.search:
			Toast.makeText(this, "search", Toast.LENGTH_LONG).show();
			break;
		}
		return true;
	}

}

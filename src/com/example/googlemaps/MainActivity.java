package com.example.googlemaps;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity {

	public final LatLng LOCATION_BURNABY= new LatLng(49.27645, -122.917587);
	public final LatLng LOCATION_SURREY= new LatLng(49.187500, -122.849000);
	
	private GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		if (map != null) {
			setUpMap();
		}
	}

	private void setUpMap() {
		// Enable MyLocation layer of Google map
				map.setMyLocationEnabled(true);

				// Get Location manager object from System service LOCATION_SERVICE
				LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);

				// Create a criteria object to retrieve provider
				Criteria criteria = new Criteria();

				// Get the name of the best provider

				String provider = locManager.getBestProvider(criteria, true);

				// Get current location
				Location myLocation = locManager.getLastKnownLocation(provider);

				// Set map type
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

				// Get the latitude of the current location
				double latitude = myLocation.getLatitude();

				// Get the longitude of the current location
				double longitude = myLocation.getLongitude();

				// Create a LatLng object for the current location
				LatLng LOCATION_CURRENT = new LatLng(latitude, longitude);

				// Show the current location in Google map
				map.moveCamera(CameraUpdateFactory.newLatLng(LOCATION_CURRENT));

				// Zoom in the google map
				CameraUpdate camUpdate = CameraUpdateFactory.newLatLngZoom(
						LOCATION_CURRENT, 15);
				map.animateCamera(camUpdate);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick_Burnaby(View v){
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_BURNABY, 13);
		map.animateCamera(update);
	}
	
	public void onClick_Surrey(View v){
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_SURREY, 14);
		map.animateCamera(update);
	}
}

package com.parthdave.nearbysearch.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parthdave.nearbysearch.model.NearByApiResponse;

import java.util.List;
import java.util.Locale;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by nuhkoca on 1/16/18.
 */

public class AddressUtils {
    private static String getAddressNameString(LatLng latLng, Context context) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");

                }
                strAdd = strReturnedAddress.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strAdd;
    }

    public static void placeMarkerOnMap(LatLng latLng, String placeName, String vicinity, GoogleMap googleMap) {
        MarkerOptions markerOptions = new MarkerOptions();

        String titleStr = placeName + " : " + vicinity;  // add these two lines
        markerOptions.title(titleStr);
        markerOptions.position(latLng);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));
        googleMap.addMarker(markerOptions);
    }

    public static void placeMarkerOnMap(LatLng location, Context context, GoogleMap googleMap) {
        MarkerOptions markerOptions = new MarkerOptions();

        String titleStr = getAddressNameString(location, context);  // add these two lines
        markerOptions.title(titleStr);
        markerOptions.position(location);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));
        googleMap.addMarker(markerOptions);
    }
}

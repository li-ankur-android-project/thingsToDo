package com.yahoo.thingsToDo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thingsToDo.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapViewFragment extends Fragment {

    private OnItemSelectedListener listener;

    private GoogleMap googleMap;

    // Define the events that the fragment will use to communicate
    public interface OnItemSelectedListener {
        public void onRssItemSelected(GoogleMap googleMap);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     */
    public static MapViewFragment newInstance(String param1, String param2) {
        MapViewFragment fragment = new MapViewFragment();

        return fragment;
    }

    public MapViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map_view, container, false);
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapxyz));
        if (mapFragment != null) {
			mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    Toast.makeText(getActivity(), "Success!!", Toast.LENGTH_SHORT).show();
                    googleMap = map;
                    if (listener != null)
                        listener.onRssItemSelected(map);
                }
            });
		} else {
			Toast.makeText(getActivity(), "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
		}


        // Inflate the layout for this fragment
        return v;
    }

    // Store the listener (activity) that will have events fired once the fragment is attached
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    public void animateCamera(CameraUpdate cameraUpdate){
        googleMap.animateCamera(cameraUpdate);
    }


}

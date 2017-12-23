package com.example.tiago.myapplication.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.tiago.myapplication.R;
import com.example.tiago.myapplication.domain.Establishment;
import com.example.tiago.myapplication.utils.CustomMapView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    public static final String EXTRA_PROM = "extra_prom";
    private Establishment mProm;
    private GoogleMap mGoogleMap;
    private CustomMapView mMapView;
    private View mView;
    private TextView txtAdress;





    public static MapFragment novaInstancia(Establishment prom) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_PROM, prom);
        MapFragment fragment = new MapFragment();
        fragment.setArguments(parametros);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            if (getArguments().getSerializable(EXTRA_PROM) != null) {
                mProm = (Establishment) getArguments().getSerializable(EXTRA_PROM);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map, container, false);


        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (CustomMapView) mView.findViewById(R.id.mapView);

        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        float lat = Float.parseFloat("-25.422058");
        float log = Float.parseFloat("-49.271947");

        googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, log)).title("Here"));
        CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(lat, log)).zoom(16).bearing(0).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }


}

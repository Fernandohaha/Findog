package com.example.fernando.myapplication.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.fernando.myapplication.R;
import com.example.fernando.myapplication.adapter.DogAdapter;
import com.example.fernando.myapplication.domain.dto.DogDTO;
import com.example.fernando.myapplication.domain.dto.LocalizacaoDTO;
import com.example.fernando.myapplication.utils.utils.manager.ConnectionManager;
import com.example.fernando.myapplication.utils.utils.manager.KeyboardManager;
import com.example.fernando.myapplication.utils.utils.services.DogService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    DogDTO dog;
    LocalizacaoDTO local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapss);

        rootView = (LinearLayout) findViewById(R.id.rootView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle("Localização");
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
        }

        dog = (DogDTO) getIntent().getExtras().getSerializable("dog");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        progress = (ProgressBar) findViewById(R.id.progress);

        progress.setVisibility(View.VISIBLE);
        KeyboardManager.hideSoftKeyboard(getActivity());
        startTask("taskGetDog", taskGetDog(), R.id.progress);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if(local != null){
            LatLng suco = new LatLng(local.getLongetitude(), local.getLatitude());
            mMap.addMarker(new MarkerOptions().position(suco).title("suco's house"));
            float zoomLevel = 16.0f; //This goes up to 21
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(suco, zoomLevel));
            Circle circle = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(local.getLongetitude(), local.getLatitude()))
                    .radius(30)
                    .strokeWidth(1f)
                    .fillColor(0x550000FF));

        }
    }

    private TaskListener taskGetDog() {
        return new TaskListener<LocalizacaoDTO>() {

            @Override
            public LocalizacaoDTO execute() throws Exception {

                LocalizacaoDTO res = new LocalizacaoDTO();
                try {

                    if (ConnectionManager.isConnected(getContext())) {
                        res = DogService.GetDogLocation(dog.getId());

                    } else {
                        res = null;
                    }

                } catch (Exception e) {
                    res = null;
                }
                return res;
            }
            @Override
            public void updateView(LocalizacaoDTO res) {
                progress.setVisibility(View.GONE);

                local = res;
                if(mMap != null && local != null){
                    LatLng suco = new LatLng(local.getLongetitude(), local.getLatitude());
                    mMap.addMarker(new MarkerOptions().position(suco).title("suco's house"));
                    float zoomLevel = 16.0f; //This goes up to 21
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(suco, zoomLevel));
                    Circle circle = mMap.addCircle(new CircleOptions()
                            .center(new LatLng(local.getLongetitude(), local.getLatitude()))
                            .radius(30)
                            .strokeWidth(1f)
                            .fillColor(0x550000FF));
                }
                else{
                    snack(rootView, "Não Encontrada");
//                    finish();
                }

            }

            @Override
            public void onError(Exception exception) {

            }

            @Override
            public void onCancelled(String cod) {
            }
        };
    }

}

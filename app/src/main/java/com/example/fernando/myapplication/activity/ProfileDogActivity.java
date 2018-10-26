package com.example.fernando.myapplication.activity;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.fernando.myapplication.R;
import com.example.fernando.myapplication.adapter.DogAdapter;
import com.example.fernando.myapplication.domain.dto.DogDTO;
import com.google.android.gms.maps.MapsInitializer;

public class ProfileDogActivity extends BaseActivity {

    DogDTO dog;

    TextView tvNome;
    TextView tvId;
    TextView tvRaca;
    TextView tvDtNasc;
    TextView tvVacina;
    TextView tvLocalizarDog;
    TextView tvListVacinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_dog);

        tvNome = (TextView) findViewById(R.id.tvNome);
        tvId = (TextView) findViewById(R.id.tvId);
        tvRaca = (TextView) findViewById(R.id.tvRaca);
        tvDtNasc = (TextView) findViewById(R.id.tvDtNasc);
        tvVacina = (TextView) findViewById(R.id.tvVacina);
        tvLocalizarDog = (TextView) findViewById(R.id.tvLocalizarDog);
        tvListVacinas = (TextView) findViewById(R.id.tvListVacinas);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle("Dog Profile");
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
        }

        dog = (DogDTO) getIntent().getExtras().getSerializable("dog");

        tvNome.setText(dog.getNome());
        tvRaca.setText(dog.getRaca());
        tvDtNasc.setText(dog.getDt_nasc());
        tvId.setText(String.valueOf(dog.getId()));

        tvVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("dog",dog);

                show(getActivity(), VacinasActivity.class, bundle);
            }
        });

        tvLocalizarDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("dog",dog);

                show(getActivity(), MapsActivity.class, bundle);

            }
        });

        tvListVacinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("dog",dog);

                show(getActivity(), VacinaListActivity.class, bundle);

            }
        });


    }
}

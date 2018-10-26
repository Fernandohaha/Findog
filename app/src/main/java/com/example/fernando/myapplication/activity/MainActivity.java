package com.example.fernando.myapplication.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fernando.myapplication.R;
import com.example.fernando.myapplication.adapter.DogAdapter;
import com.example.fernando.myapplication.domain.dto.BaseDTO;
import com.example.fernando.myapplication.domain.dto.DogDTO;
import com.example.fernando.myapplication.domain.dto.LoginDTO;
import com.example.fernando.myapplication.utils.utils.manager.ConnectionManager;
import com.example.fernando.myapplication.utils.utils.manager.KeyboardManager;
import com.example.fernando.myapplication.utils.utils.services.DogService;
import com.example.fernando.myapplication.utils.utils.services.LoginService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    DogAdapter Adapter;
    ListView DogLista;
    List<DogDTO> listaDogs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = (LinearLayout) findViewById(R.id.rootView);
        DogLista  = (ListView) findViewById(R.id.lista);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle("Index");
        }
        progress = (ProgressBar) findViewById(R.id.progress);



        setupNavDrawer(this);

    }

    private TaskListener taskGetDogs() {
        return new TaskListener<List<DogDTO>>() {

            @Override
            public List<DogDTO> execute() throws Exception {

                List<DogDTO> res = new ArrayList<>();
                try {


                        if (ConnectionManager.isConnected(getContext())) {

                            prefs = getSharedPreferences(PREFS_LOGIN, 0);

                            res = DogService.GetdogbyUser(prefs.getInt("id",0));

                        } else {
                            //res.setSucesso(false);
                            //res.setMensagem("Sem conexao");
                        }

                } catch (Exception e) {
                    res = null;
                }
                return res;
            }
            @Override
            public void updateView(List<DogDTO> res) {
                progress.setVisibility(View.GONE);
                listaDogs = res;
                Adapter= new DogAdapter(res,getApplicationContext());
                DogLista.setAdapter(Adapter);
                DogLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        DogDTO dog =  listaDogs.get(position);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("dog",dog);

                        show(getActivity(), ProfileDogActivity.class,bundle);
                    }
                });



            }

            @Override
            public void onError(Exception exception) {

            }

            @Override
            public void onCancelled(String cod) {
            }
        };
    }


    @Override
    protected void onResume() {
        super.onResume();
        progress.setVisibility(View.VISIBLE);
        KeyboardManager.hideSoftKeyboard(getActivity());
        startTask("taskGetDogs", taskGetDogs(), R.id.progress);

    }



}





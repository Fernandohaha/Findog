package com.example.fernando.myapplication.activity;

        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;

        import com.example.fernando.myapplication.R;
        import com.example.fernando.myapplication.adapter.DogAdapter;
        import com.example.fernando.myapplication.adapter.VacinaAdapter;
        import com.example.fernando.myapplication.domain.dto.DogDTO;
        import com.example.fernando.myapplication.domain.dto.VacinaDTO;
        import com.example.fernando.myapplication.utils.utils.manager.ConnectionManager;
        import com.example.fernando.myapplication.utils.utils.services.DogService;
        import com.example.fernando.myapplication.utils.utils.services.VacinaService;

        import java.util.ArrayList;
        import java.util.List;

public class VacinaListActivity extends BaseActivity {

    ListView VacinaLista;
    VacinaAdapter Adapter;

    List<VacinaDTO> listavacinas = new ArrayList<>();
    DogDTO dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina_list);

        VacinaLista = (ListView) findViewById(R.id.ListVacinas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle("Index");
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
        }
        dog = (DogDTO) getIntent().getExtras().getSerializable("dog");
        startTask("taskgetVacinas", taskGetVacinas());
    }

    private TaskListener taskGetVacinas() {
        return new TaskListener<List<VacinaDTO>>() {

            @Override
            public List<VacinaDTO> execute() throws Exception {

                List<VacinaDTO> res = new ArrayList<>();
                try {


                    if (ConnectionManager.isConnected(getContext())) {

                        res = VacinaService.GetVacinaById(dog.getId());

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
            public void updateView(List<VacinaDTO> res) {
                listavacinas = res;
                Adapter = new VacinaAdapter(res, getApplicationContext());
                VacinaLista.setAdapter(Adapter);
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

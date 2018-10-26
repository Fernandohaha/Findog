package com.example.fernando.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.fernando.myapplication.R;
import com.example.fernando.myapplication.domain.dto.BaseDTO;
import com.example.fernando.myapplication.domain.dto.CadastrarDogDTO;
import com.example.fernando.myapplication.utils.utils.manager.ConnectionManager;
import com.example.fernando.myapplication.utils.utils.manager.KeyboardManager;
import com.example.fernando.myapplication.utils.utils.services.DogService;

public class CadastrodogActivity extends BaseActivity {
    EditText DogText;
    EditText RacaText;
    EditText DateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrodog);

        Button btCadastrar = (Button) findViewById(R.id.bcadastrardog);
        DogText = (EditText) findViewById(R.id.etDogname);
        RacaText = (EditText) findViewById(R.id.etRaca);
        DateText = (EditText) findViewById(R.id.etdatepicker);
        rootView = (LinearLayout) findViewById(R.id.rootView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle("Cadastro de Cachorro");
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
        }
        btCadastrar.setOnClickListener(onClickbtCadastrar());
    }
    private View.OnClickListener onClickbtCadastrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardManager.hideSoftKeyboard(getActivity());
                startTask("TaskCadastrarDog", TaskCadastrarDog());
            }
        };
    }


    private BaseActivity.TaskListener TaskCadastrarDog() {
        return new BaseActivity.TaskListener<BaseDTO>() {

            @Override
            public BaseDTO execute() throws Exception {

                BaseDTO res = new BaseDTO();
                try {
                    prefs = getSharedPreferences(PREFS_LOGIN, 0);



                    CadastrarDogDTO Cadastro = new CadastrarDogDTO();
                    String DogNome = (DogText.getText().toString());
                    String DogRaca = (RacaText.getText().toString());
                    String DogData = (DateText.getText().toString());


                    if (DogNome.equals("") || DogRaca.equals("") || DogData.equals(""))
                    {
                        res.setSucesso(false);
                        res.setMensagem("Campos Obrigatórios");
                    }
                    else if (!DogNome.equals("") || !DogRaca.equals("") || !DogData.equals("")) {
                        Cadastro.setNome(DogNome);
                        Cadastro.setRaca(DogRaca);
                        Cadastro.setDt_nasc(DogData);
                        Cadastro.setUsuarioId((prefs.getInt("id",0)));


                        if (ConnectionManager.isConnected(getContext())) {
                            res = DogService.ValidarDog(Cadastro);

                        } else {
                            res.setSucesso(false);
                            res.setMensagem("Sem conexao");
                        }
                    }
                } catch (Exception e) {
                    res.setSucesso(false);
                    res.setMensagem("Tente novamente");
                }
                return res;
            }

            @Override
            public void updateView(BaseDTO res) throws InterruptedException {

                if(res.isSucesso()) {
                    snack(rootView, res.getMensagem());


                }
                else
                    snack(rootView, res.getMensagem());

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

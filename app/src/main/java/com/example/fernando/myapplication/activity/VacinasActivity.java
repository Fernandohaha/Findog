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
import com.example.fernando.myapplication.domain.dto.VacinaDTO;
import com.example.fernando.myapplication.domain.dto.DogDTO;
import com.example.fernando.myapplication.utils.utils.manager.ConnectionManager;
import com.example.fernando.myapplication.utils.utils.manager.KeyboardManager;
import com.example.fernando.myapplication.utils.utils.services.VacinaService;

public class VacinasActivity extends BaseActivity {

    EditText VacinaNomeText;
    EditText ReforcoText;
    EditText DataText;
    DogDTO dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacinas);
        Button btCadastrar = (Button) findViewById(R.id.bCadastrarVacina);
        VacinaNomeText = (EditText) findViewById(R.id.etVacinaNome);
        ReforcoText = (EditText) findViewById(R.id.etReforco);
        DataText = (EditText) findViewById(R.id.etDataVacina);
        rootView = (LinearLayout) findViewById(R.id.rootView);
        btCadastrar.setOnClickListener(onClickbtCadastrar());

        dog = (DogDTO) getIntent().getExtras().getSerializable("dog");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle("Cadastro de Vacina");
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeButtonEnabled(true);
        }
    }

    private View.OnClickListener onClickbtCadastrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardManager.hideSoftKeyboard(getActivity());
                startTask("TaskCadastrarVacina", TaskCadastrarVacina());
            }
        };
    }


    private BaseActivity.TaskListener TaskCadastrarVacina() {
        return new BaseActivity.TaskListener<BaseDTO>() {

            @Override
            public BaseDTO execute() throws Exception {

                BaseDTO res = new BaseDTO();
                try {
                    VacinaDTO Cadastro = new VacinaDTO();
                    String VacinaNome = (VacinaNomeText.getText().toString());
                    String VacinaReforco = (ReforcoText.getText().toString());
                    String VacinaData = (DataText.getText().toString());


                    if (VacinaNome.equals("") || VacinaReforco.equals("") || VacinaData.equals("")) {
                        res.setSucesso(false);
                        res.setMensagem("Campos Obrigatórios");
                    } else if (!VacinaNome.equals("") || !VacinaReforco.equals("") || !VacinaData.equals("")) {
                        Cadastro.setNome_Vacina(VacinaNome);
                        Cadastro.setReforco(VacinaReforco);
                        Cadastro.setData(VacinaData);
                        Cadastro.setCachorroId(String.valueOf(dog.getId()));


                        if (ConnectionManager.isConnected(getContext())) {
                            res = VacinaService.ValidarVacina(Cadastro);

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

                if (res.isSucesso()) {
                    snack(rootView, res.getMensagem());


                } else
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



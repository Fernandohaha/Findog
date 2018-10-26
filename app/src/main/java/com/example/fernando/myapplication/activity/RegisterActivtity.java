package com.example.fernando.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.fernando.myapplication.R;
import com.example.fernando.myapplication.domain.dto.BaseDTO;
import com.example.fernando.myapplication.domain.dto.CadastrarUserDTO;
import com.example.fernando.myapplication.utils.utils.manager.ConnectionManager;
import com.example.fernando.myapplication.utils.utils.manager.KeyboardManager;
import com.example.fernando.myapplication.utils.utils.services.UserService;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class RegisterActivtity extends BaseActivity {

    private ProgressBar progress;
    EditText NameText;
    EditText CpfText;
    EditText EmailText;
    EditText CelularText;
    EditText EnderecoText;
    EditText SenhaText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activtity);
        final EditText campo_cpf = (EditText) findViewById(R.id.etCPF);
        campo_cpf.addTextChangedListener (Mask.insert("###.###.###-##",campo_cpf));

        final EditText campo_telefone = (EditText) findViewById(R.id.etCelular);
        campo_telefone.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone));


        Button btCadastrar = (Button) findViewById(R.id.bCadadstrar);
        NameText = (EditText) findViewById(R.id.etNome);
        CpfText = (EditText) findViewById(R.id.etCPF);
        EmailText = (EditText) findViewById(R.id.etEmail);
        CelularText = (EditText) findViewById(R.id.etCelular);
        EnderecoText = (EditText) findViewById(R.id.etEndereco);
        SenhaText = (EditText) findViewById(R.id.etSenha);
        rootView = (LinearLayout) findViewById(R.id.rootView);


        btCadastrar.setOnClickListener(onClickbtCadastrar());
    }


    private View.OnClickListener onClickbtCadastrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardManager.hideSoftKeyboard(getActivity());
                startTask("TaskValidateRegister", TaskValidateRegister());
            }
        };
    }


    private BaseActivity.TaskListener TaskValidateRegister() {
        return new BaseActivity.TaskListener<BaseDTO>() {

            @Override
            public BaseDTO execute() throws Exception {

                BaseDTO res = new BaseDTO();
                try {
                    CadastrarUserDTO Cadastro = new CadastrarUserDTO();
                    String Nome = (NameText.getText().toString());
                    String CPF = (CpfText.getText().toString());
                    String Email = (EmailText.getText().toString());
                    String Celular = (CelularText.getText().toString());
                    String Endereco = (EnderecoText.getText().toString());
                    String Senha = (SenhaText.getText().toString());

                    if (!Nome.equals("") || !Nome.equals("") || !Email.equals("") || !Celular.equals("") || !Endereco.equals("") || !Senha.equals("")) {
                        Cadastro.setNome(Nome);
                        Cadastro.setCpf(CPF);
                        Cadastro.setEmail(Email);
                        Cadastro.setCelular(Celular);
                        Cadastro.setEndereco(Endereco);
                        Cadastro.setSenha(Senha);
                        if (ConnectionManager.isConnected(getContext())) {
                            res = UserService.ValidarUser(Cadastro);

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
                    currentThread().sleep(5000);
                    show(getActivity(), LoginActivity.class);

                    //show(getActivity(),MainActivity.class);
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

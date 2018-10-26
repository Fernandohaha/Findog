package com.example.fernando.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fernando.myapplication.R;
import com.example.fernando.myapplication.domain.dto.BaseDTO;
import com.example.fernando.myapplication.domain.dto.LoginDTO;
import com.example.fernando.myapplication.utils.utils.manager.ConnectionManager;
import com.example.fernando.myapplication.utils.utils.manager.KeyboardManager;
import com.example.fernando.myapplication.utils.utils.services.LoginService;

public class LoginActivity extends BaseActivity {


    EditText LoginText;
    EditText SenhaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btSignIn = (Button) findViewById(R.id.blogin);
        LoginText = (EditText) findViewById(R.id.etLogin);
        SenhaText = (EditText) findViewById(R.id.etSenha);

        LoginText.setText("asd@asd.com");
        SenhaText.setText("asd");

        rootView = (LinearLayout) findViewById(R.id.rootView);
        progress = (ProgressBar) findViewById(R.id.progress);

        btSignIn.setOnClickListener(onClickBnSignIn());

        TextView asd1 = (TextView) findViewById(R.id.txt_signup);

        asd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivtity.class);
                LoginActivity.this.startActivity(i);
            }
        });

        TextView asd2 = (TextView) findViewById(R.id.txt_forgotpass);

        asd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, VacinasActivity.class);
                LoginActivity.this.startActivity(i);
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    private View.OnClickListener onClickBnSignIn() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                KeyboardManager.hideSoftKeyboard(getActivity());
                startTask("taskValidateLogin", taskValidateLogin(), R.id.progress);
            }
        };
    }


    private TaskListener taskValidateLogin() {
        return new TaskListener<BaseDTO>() {

            @Override
            public BaseDTO execute() throws Exception {

                BaseDTO res = new BaseDTO();
                try {
                    LoginDTO userLogin = new LoginDTO();
                    String login = (LoginText.getText().toString());
                    String senha = (SenhaText.getText().toString());
                    if (login.equals("") || senha.equals(""))
                    {
                        res.setSucesso(false);
                        res.setMensagem("Login e Senha Obrigatórios");
                    }

                    else if (!login.equals("") && !senha.equals("")) {
                        userLogin.setEmail(login);
                        userLogin.setSenha(senha);
                        if (ConnectionManager.isConnected(getContext())) {
                            res = LoginService.ValidarLogin(userLogin);

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
            public void updateView(BaseDTO res) {

                if(res.isSucesso()){

                    prefs = getSharedPreferences(PREFS_LOGIN, 0);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("id",res.getId());
                    editor.commit();

                    show(getActivity(),MainActivity.class);
                    finish();
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




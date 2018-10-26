package com.example.fernando.myapplication.utils.utils.services;

import com.example.fernando.myapplication.domain.dto.BaseDTO;
import com.example.fernando.myapplication.domain.dto.CadastrarUserDTO;
import com.example.fernando.myapplication.domain.dto.LoginDTO;
import com.example.fernando.myapplication.utils.utils.constant.Constant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fernando on 11/11/2017.
 */
public class UserService extends  Service {

    public static BaseDTO ValidarUser (CadastrarUserDTO Cadastrar)  {

        Service service = new Service();
        BaseDTO base = new BaseDTO();

        String stringJson = getGson().toJson(Cadastrar);
        try {
            String response =  service.doPost(Constant.WS_URL_ADD_USER, stringJson);
            JSONObject jsonResponse = new JSONObject(response);

            base.setSucesso(jsonResponse.getBoolean("Sucesso"));
            base.setMensagem(jsonResponse.getString("Mensagem"));

            return base;

        }catch (Exception e){

            base.setSucesso(false);
            base.setMensagem("Erro ao realizar a request");
            return base;
        }
    }
}

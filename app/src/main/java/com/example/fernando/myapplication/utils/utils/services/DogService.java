package com.example.fernando.myapplication.utils.utils.services;

import android.util.Log;

import com.example.fernando.myapplication.domain.dto.BaseDTO;
import com.example.fernando.myapplication.domain.dto.CadastrarDogDTO;
import com.example.fernando.myapplication.domain.dto.CadastrarUserDTO;
import com.example.fernando.myapplication.domain.dto.DogDTO;
import com.example.fernando.myapplication.domain.dto.LocalizacaoDTO;
import com.example.fernando.myapplication.utils.utils.constant.Constant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.fernando.myapplication.utils.utils.services.Service.doGet;
import static com.example.fernando.myapplication.utils.utils.services.Service.doGetSimple;
import static com.example.fernando.myapplication.utils.utils.services.Service.getGson;

/**
 * Created by Fernando on 11/11/2017.
 */
public class DogService {

    public static BaseDTO ValidarDog (CadastrarDogDTO Cadastrar)  {

        Service service = new Service();
        BaseDTO base = new BaseDTO();

        String stringJson = getGson().toJson(Cadastrar);
        try {
            String response =  service.doPost(Constant.WS_URL_ADD_DOG, stringJson);
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

    public static List<DogDTO>  GetdogbyUser (int id)  {

        List<DogDTO> dogList = new ArrayList<DogDTO>();

        try {
            List<String> parameters = new ArrayList<>();
            String param = getGson().toJson(id);
            parameters.add(param);

            String responseJson = doGetSimple(Constant.WS_URL_LIST_DOG, parameters);
            JSONObject object = new JSONObject(responseJson);

            JSONArray array = object.getJSONArray("Cachorros");

            for (int j = 0; j < array.length(); j++) {

                JSONObject child = array.getJSONObject(j);
                DogDTO d = new DogDTO();
                d.setNome(child.getString("Nome"));
                d.setDt_nasc(child.getString("Dt_nasc"));
                d.setRaca(child.getString("Raca"));
                d.setId(child.getInt("Id"));
                dogList.add(d);
            }

        } catch (Exception e) {
            Log.d("FODEU", e.getMessage());
            dogList.clear();
        }
        return dogList;
    }

    public static LocalizacaoDTO GetDogLocation (int id)  {

        LocalizacaoDTO localizacao = new LocalizacaoDTO();

        try {
            List<String> parameters = new ArrayList<>();
            String param = getGson().toJson(id);
            parameters.add(param);

            String responseJson = doGetSimple(Constant.WS_URL_LOCALIZAZAO_DOG, parameters);
            JSONObject object = new JSONObject(responseJson);

            localizacao.setId(object.getInt("Id"));
            localizacao.setLatitude(object.getDouble("Latitude"));
            localizacao.setLongetitude(object.getDouble("Longetitude"));

        } catch (Exception e) {
            Log.d("FODEU", e.getMessage());
            localizacao = null;
        }
        return localizacao;
    }

}

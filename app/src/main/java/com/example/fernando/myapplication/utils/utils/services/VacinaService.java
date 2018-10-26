package com.example.fernando.myapplication.utils.utils.services;

import android.util.Log;

import com.example.fernando.myapplication.domain.dto.BaseDTO;
import com.example.fernando.myapplication.domain.dto.DogDTO;
import com.example.fernando.myapplication.domain.dto.LocalizacaoDTO;
import com.example.fernando.myapplication.domain.dto.VacinaDTO;
import com.example.fernando.myapplication.utils.utils.constant.Constant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.fernando.myapplication.utils.utils.services.Service.doGetSimple;
import static com.example.fernando.myapplication.utils.utils.services.Service.getGson;

/**
 * Created by Fernando on 12/11/2017.
 */
public class VacinaService {

    public static BaseDTO ValidarVacina (VacinaDTO Cadastrar)  {

        Service service = new Service();
        BaseDTO base = new BaseDTO();

        String stringJson = getGson().toJson(Cadastrar);
        try {
            String response =  service.doPost(Constant.WS_URL_ADD_VACINA, stringJson);
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

    public static List<VacinaDTO>  GetVacinaById (int id)  {

        List<VacinaDTO> dogList = new ArrayList<VacinaDTO>();

        try {
            List<String> parameters = new ArrayList<>();
            String param = getGson().toJson(id);
            parameters.add(param);

            String responseJson = doGetSimple(Constant.WS_URL_ADD_VACINA, parameters);
            JSONObject object = new JSONObject(responseJson);

            JSONArray array = object.getJSONArray("Vacinas");

            for (int j = 0; j < array.length(); j++) {

                JSONObject child = array.getJSONObject(j);
                VacinaDTO d = new VacinaDTO();
                d.setData(child.getString("Data"));
                d.setNome_Vacina(child.getString("Nome_Vacina"));
                d.setReforco(child.getString("Reforco"));
                dogList.add(d);
            }

        } catch (Exception e) {
            Log.d("FODEU", e.getMessage());
            dogList.clear();
        }
        return dogList;
    }
}

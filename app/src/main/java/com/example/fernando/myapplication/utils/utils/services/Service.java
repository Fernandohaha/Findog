package com.example.fernando.myapplication.utils.utils.services;

/**
 * Created by Fernando on 11/11/2017.
 */


        import com.example.fernando.myapplication.utils.utils.constant.Constant;
        import com.example.fernando.myapplication.utils.utils.helper.HttpHelper;
        import com.rebtel.repackaged.com.google.gson.Gson;
        import com.rebtel.repackaged.com.google.gson.GsonBuilder;
        import com.rebtel.repackaged.com.google.gson.reflect.TypeToken;

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;


public class Service {

    private static Gson gson;

    public static String doGet(String ws, String stringJson) {
        try {
            Map<String, String> map = new Gson().fromJson(stringJson, new TypeToken<HashMap<String, String>>() {
            }.getType());

            String url = Constant.WS_URL_BASE + ws;
            HttpHelper httpHelper = getHttpHelper();
            return httpHelper.doGet(url, map, Constant.UTF8);
        } catch (Exception e) {
            System.out.print(e);
            return "Erro ao enviar para o servidor";
        }
    }

    protected static String doGetSimple(String ws, List<String> parameters) {
        try {
            String url = Constant.WS_URL_BASE + ws;
            for (String parameter : parameters) {
                url += parameter + "/";
            }
            HttpHelper httpHelper = getHttpHelper();
            return httpHelper.doGet(url, null, Constant.UTF8);
        } catch (Exception e) {
            System.out.print(e);
            return "Erro ao enviar para o servidor";
        }
    }


    protected static String doPost(String ws, String stringJson) {
        try {
            String url = Constant.WS_URL_BASE + ws;
            HttpHelper httpHelper = getHttpHelper();
            return httpHelper.doPost(url, stringJson.getBytes(), Constant.UTF8);
        } catch (Exception e) {
            System.out.print(e);
            return "Erro ao enviar para o servidor";
        }
    }

    protected static HttpHelper getHttpHelper() {
        HttpHelper httpHelper = new HttpHelper();
        httpHelper.setContentType(Constant.CONTENT_TYPE_JSON_UTF8);
        return httpHelper;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
        return gson;
    }
}



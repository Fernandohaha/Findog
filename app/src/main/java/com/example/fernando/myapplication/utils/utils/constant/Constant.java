package com.example.fernando.myapplication.utils.utils.constant;

/**
 * Created by Fernando on 08/11/2017.
 */
public class Constant {
    public static final String WS_URL_BASE = "http://findog.azurewebsites.net/";


    public static final String KEY_TRANSITION_DETAILS = "KEY_DETAILS";

    public static final String DATE_PATTER = "dd/MM/yyyy HH:mm:ss";

    //user
    public static final String WS_URL_VALIDATE_LOGIN = "api/usuario/login";
    public static final String WS_URL_VALIDATE_UPDATE = "user/update";
    public static final String WS_URL_FIND_USER = "api/usuario/";
    public static final String WS_URL_ADD_USER = "api/usuario/";


    public static final String STATUS_OK = "OK";
    public static final String STATUS_NOK = "NOK";

    //dog
    public static final String WS_URL_ADD_DOG = "api/cachorro/";
    public static final String WS_URL_LIST_DOG = "api/cachorro/";
    public static final String WS_URL_LOCALIZAZAO_DOG = "api/coordenada/";

    //vacina
    public static final String WS_URL_ADD_VACINA = "api/vacina/";

    //notification
    public static final String WS_ALL_NOTIFICATIONS = "notifications/";
    public static final String WS_DISMISS_NOTIFICATIONS = "notifications/dismiss/";
    public static final String WS_UNSEEN_NOTIFICATIONS = "notifications/unseen/";
    public static final String CONTENT_TYPE_JSON_UTF8 = "application/json";
    public static final String UTF8 = "utf-8";

}

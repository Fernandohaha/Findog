package com.example.fernando.myapplication.domain.dto;

/**
 * Created by Fernando on 11/11/2017.
 */

public class LoginDTO {

    private String Email;
    private String Senha;

    public String getEmail(){

        return Email;

    }

    public void setEmail (String email){
        Email = email;

    }

    public String getSenha(){

        return Senha;
    }

    public void setSenha (String senha){

        Senha = senha;
    }

}

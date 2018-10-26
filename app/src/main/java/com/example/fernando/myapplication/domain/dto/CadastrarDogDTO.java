package com.example.fernando.myapplication.domain.dto;

/**
 * Created by Fernando on 11/11/2017.
 */
public class CadastrarDogDTO {



    public String nome;

    public int UsuarioId;

    public String Dt_nasc;

    public String Raca;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        UsuarioId = usuarioId;
    }


    public String getDt_nasc() {
        return Dt_nasc;
    }

    public void setDt_nasc(String dt_nasc) {
        Dt_nasc = dt_nasc;
    }

    public String getRaca() {
        return Raca;
    }

    public void setRaca(String raca) {
        Raca = raca;
    }
}
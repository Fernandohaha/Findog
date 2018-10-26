package com.example.fernando.myapplication.domain.dto;

import java.io.Serializable;

/**
 * Created by Fernando on 12/11/2017.
 */
public class DogDTO implements Serializable {

    public int Id;

    public String Nome;

    public String UsuarioId;

    public String Dt_nasc;

    public String Raca;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(String usuarioId) {
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

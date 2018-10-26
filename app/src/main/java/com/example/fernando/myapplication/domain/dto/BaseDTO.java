package com.example.fernando.myapplication.domain.dto;

/**
 * Created by Fernando on 11/11/2017.
 */
public class BaseDTO {


    boolean Sucesso;
    String Mensagem;
    int Id;
    int idMovimentacao;

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public boolean isSucesso() {
        return Sucesso;
    }

    public void setSucesso(boolean sucesso) {
        Sucesso = sucesso;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String mensagem) {
        this.Mensagem = mensagem;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

package lojainstrumento.atividade_1_pw;

import java.util.Date;

public class Instrumento {
    private Integer id;
    private String nome;
    private int qtd;
    private final Date dataCadastro;


    public Instrumento(Integer id, String nome,int quantidade, Date datacadastro){
        this.id = id;
        this.nome = nome;
        this.qtd = quantidade;
        this.dataCadastro = datacadastro;
    }
    public Instrumento(){
        this.dataCadastro = new Date();
    }
    public Instrumento(Date data, Integer id){
        this.id = id;
        this.dataCadastro = data;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Date getDataCadastro(){
        return dataCadastro;
    }
}

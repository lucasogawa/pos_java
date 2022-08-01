package br.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "disciplina")
public class Disciplina {

    private int codigo;
    private String nome;

    public Disciplina() {

    }

    public Disciplina(String nome) {
        this.nome = nome;
    }

    @XmlElement(name = "nome")
    public String getNome() {
        return nome;
    }

    @XmlElement(name = "codigo")
    public int getCodigo() {
        return codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}

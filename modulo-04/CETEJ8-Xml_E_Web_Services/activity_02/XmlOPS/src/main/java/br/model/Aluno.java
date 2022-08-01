package br.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;

@XmlRootElement(name = "aluno")
public class Aluno implements Serializable {

    private int id;
    private String ra;
    private String nome;
    private ArrayList<Disciplina> ldisciplina;

    public Aluno() {
        ldisciplina = new ArrayList<>();
    }

    public Aluno(int id, String ra, String nome) {
        super();
        this.id = id;
        this.ra = ra;
        this.nome = nome;
    }

    public Aluno(int id, String ra, String nome, ArrayList<Disciplina> ldisciplina) {
        super();
        this.id = id;
        this.ra = ra;
        this.nome = nome;
        this.ldisciplina = ldisciplina;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    @XmlAttribute(required = true)
    public String getRa() {
        return ra;
    }

    @XmlAttribute
    public String getNome() {
        return nome;
    }

    @XmlElementWrapper(name = "disciplinas", namespace = "")
    @XmlElement(name = "disciplina", namespace = "")
    public ArrayList<Disciplina> getLdisciplina() {
        return ldisciplina;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLdisciplina(ArrayList<Disciplina> ldisciplina) {
        this.ldisciplina = ldisciplina;
    }
}

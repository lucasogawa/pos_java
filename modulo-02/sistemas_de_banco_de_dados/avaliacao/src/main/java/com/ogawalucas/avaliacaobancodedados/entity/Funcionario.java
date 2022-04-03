package com.ogawalucas.avaliacaobancodedados.entity;

import javax.persistence.*;

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario {

    @Id
    @GeneratedValue
    private Long codigo;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "TELEFONE")
    private String telefone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_CARGO")
    private Cargo cargo;

    public Funcionario() {

    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "[" + "Codigo: " + this.codigo + ", Nome: " + this.nome + ", Sexo: " + this.sexo +
            ", Telefone: " + this.telefone + ", Cargo: " + getCargo().toStringWithoutFuncionarios() + "]";
    }

    public String toStringWithoutCargo() {
        return "[" + "Codigo: " + this.codigo + ", Nome: " + this.nome + ", Sexo: " + this.sexo +
            ", Telefone: " + this.telefone + "]";
    }
}

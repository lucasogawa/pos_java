package com.ogawalucas.avaliacaobancodedados.entity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "CARGO")
public class Cargo {

    @Id
    @GeneratedValue
    private Long codigo;

    @Column(name = "CARGO")
    private String cargo;

    @OneToMany(mappedBy = "cargo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    public Cargo() {

    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString() {
        return "[" + "Codigo: " + this.codigo + ", Cargo: " + this.cargo + ", " +
            "Funcionários: " + toStringFuncionarios() + "]";
    }

    private String toStringFuncionarios() {
        return funcionarios == null
            ? "[]"
            : funcionarios.stream()
            .map(Funcionario::toStringWithoutCargo)
            .collect(Collectors.joining(", "));
    }

    public String toStringWithoutFuncionarios() {
        return "[" + "Codigo: " + this.codigo + ", Cargo: " + this.cargo + "]";
    }
}

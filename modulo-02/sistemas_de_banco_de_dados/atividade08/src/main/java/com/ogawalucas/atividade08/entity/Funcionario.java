package com.ogawalucas.atividade08.entity;

import javax.persistence.*;

@Entity
@Table(name = "FUNCIONARIO")
@NamedQuery(
    name = "Funcionario.byQtdDependentes",
    query = "from Funcionario f where f.qtdDependentes = ?1"
)
@NamedNativeQuery(
    name = "Funcionario.byContainsNome",
    query = "select * from Funcionario f where f.nome like % ?1 %",
    resultClass = Funcionario.class
)
public class Funcionario {

    @Id
    @GeneratedValue
    private Long codigo;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "QTD_DEPENDENTES")
    private Integer qtdDependentes;

    @Column(name = "SALARIO")
    private Float salario;

    @Column(name = "CARGO")
    private String cargo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    public Funcionario(Long codigo, String nome, Integer qtdDependentes, Float salario, String cargo, Departamento departamento) {
        this.codigo = codigo;
        this.nome = nome;
        this.qtdDependentes = qtdDependentes;
        this.salario = salario;
        this.cargo = cargo;
        this.departamento = departamento;
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

    public Integer getQtdDependentes() {
        return qtdDependentes;
    }

    public void setQtdDependentes(Integer qtdDependentes) {
        this.qtdDependentes = qtdDependentes;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}

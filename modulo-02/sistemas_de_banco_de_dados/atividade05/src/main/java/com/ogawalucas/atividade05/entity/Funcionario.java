package com.ogawalucas.atividade05.entity;

import javax.persistence.*;

@Entity
@Table(name = "FUNCIONARIO")
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
}

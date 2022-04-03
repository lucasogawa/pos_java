package com.ogawalucas.atividade06.entity;

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
}

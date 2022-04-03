package com.ogawalucas.atividade05.entity;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento {

    @Id
    @GeneratedValue
    private Long codigo;

    @Column(name = "NOME")
    private String nome;
}

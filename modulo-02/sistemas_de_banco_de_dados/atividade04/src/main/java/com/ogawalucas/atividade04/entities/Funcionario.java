package com.ogawalucas.atividade04.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario extends AbstractPersistable<Long> {

    @Column(name = "NOME")
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
}


package com.ogawalucas.atividade04.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento extends AbstractPersistable<Long> {

    @Column(name = "NOME")
    private String nome;

    @OneToMany(mappedBy = "departamento")
    private List<Funcionario> funcionarios;
}


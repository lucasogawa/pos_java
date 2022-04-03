package com.ogawalucas.atividade04.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "LIVRO")
public class Livro extends AbstractPersistable<Long> {

    @Column(name = "NOME")
    private String nome;

    @ManyToMany(mappedBy = "livros")
    private List<Autor> autores;
}



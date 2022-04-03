package com.ogawalucas.atividade04.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AUTOR")
public class Autor extends AbstractPersistable<Long> {

    @Column(name = "NOME")
    private String nome;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "autor_livro",
        joinColumns = @JoinColumn(name = "livro_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id")
    )
    private List<Livro> livros;
}



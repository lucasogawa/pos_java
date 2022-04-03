package com.ogawalucas.atividade04.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Table(name = "PEDIDO")
public class Pedido extends AbstractPersistable<Long> {

    @Column(name = "NOME")
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}


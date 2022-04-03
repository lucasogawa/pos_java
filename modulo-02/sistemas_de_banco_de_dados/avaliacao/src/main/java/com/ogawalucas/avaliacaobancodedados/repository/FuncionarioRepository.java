package com.ogawalucas.avaliacaobancodedados.repository;

import com.ogawalucas.avaliacaobancodedados.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("select f.nome from Funcionario f order by f.nome")
    List<String> listarNomesOrdenadosAsc();
}

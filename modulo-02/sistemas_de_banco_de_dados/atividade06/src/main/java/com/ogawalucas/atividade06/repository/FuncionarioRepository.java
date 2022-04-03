package com.ogawalucas.atividade06.repository;

import com.ogawalucas.atividade06.entity.Departamento;
import com.ogawalucas.atividade06.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findByNomeAndQtdDependentes(String nome, Integer qtdDependentes);

    @Query("select f from Funcionario f where f.departamento = ?1")
    List<Funcionario> findByDepartamento(Departamento departamento);

    Funcionario findFirstByOrderByCodigoAsc();

    Funcionario findFirstByOrderBySalarioDesc();

    Funcionario findFirst3ByOrderBySalarioDesc();

    @Query("select f from Funcionario f where f.qtdDependentes = 0 order by f.nome asc")
    List<Funcionario> findByQtdDependentesOrderByNomeAsc();

    @Query("select f from Funcionario f where f.salario > ?1")
    List<Funcionario> findBySalarioGreaterThanWithJpql(Float salario);

    @Query(value = "select * from funcionario f where f.salario > ?1", nativeQuery = true)
    List<Funcionario> findBySalarioGreaterThanWithNativeQuery(Float salario);

    @Query(name = "Funcionario.byQtdDependentes")
    List<Funcionario> findByQtdDependentes(Integer qtdDependentes);

    @Query(name = "Funcionario.byContainsNome")
    List<Funcionario> findByContainsNome(String nome);
}

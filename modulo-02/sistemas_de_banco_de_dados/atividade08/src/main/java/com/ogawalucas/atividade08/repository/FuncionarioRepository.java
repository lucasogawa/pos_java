package com.ogawalucas.atividade08.repository;

import com.ogawalucas.atividade08.entity.Departamento;
import com.ogawalucas.atividade08.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
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

    @Query("select f from Funcionario f where f.qtdDependentes = 0 and f.departamento = :departamento")
    List<Funcionario> findByQtdDependentesAndDepartamento(@Param("departamento") Departamento departamento);

    @Modifying
    @Query("update Funcionario f set f.departamento = :departamentoDestino where f.departamento = :departamentoOrigem")
    int updateDepartamentoByDepartamento(@Param("departamentoOrigem") Departamento departamentoOrigem, @Param("departamentoDestino") Departamento departamentoDestino);

    @Modifying
    @Query("delete from Funcionario f where f.departamento = :departamento")
    int deleteByDepartamento(@Param("departamento") Departamento departamento);

    @Procedure
    int proc_increase_salary(int increase_percent);
}

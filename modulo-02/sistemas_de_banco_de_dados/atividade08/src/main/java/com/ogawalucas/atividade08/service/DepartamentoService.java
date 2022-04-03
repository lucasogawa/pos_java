package com.ogawalucas.atividade08.service;

import com.ogawalucas.atividade08.entity.Departamento;
import com.ogawalucas.atividade08.entity.Funcionario;
import com.ogawalucas.atividade08.repository.DepartamentoRepository;
import com.ogawalucas.atividade08.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional(readOnly = false)
    public void salvar(Departamento departamento, Funcionario funcionario) {
        departamento = repository.save(departamento);
        funcionario.setDepartamento(departamento);
        funcionarioRepository.save(funcionario);
    }
}

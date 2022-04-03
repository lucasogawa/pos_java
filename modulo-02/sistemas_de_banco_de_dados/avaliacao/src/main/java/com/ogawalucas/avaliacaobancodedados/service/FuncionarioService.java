package com.ogawalucas.avaliacaobancodedados.service;

import com.ogawalucas.avaliacaobancodedados.entity.Cargo;
import com.ogawalucas.avaliacaobancodedados.entity.Funcionario;
import com.ogawalucas.avaliacaobancodedados.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public Funcionario salvar(Funcionario funcionario, Cargo cargo) {
        funcionario.setCargo(cargo);
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void deletar(Long codigo) {
        funcionarioRepository.deleteById(codigo);
    }

    @Transactional
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    @Transactional
    public List<String> listarNomesOrdenadosAsc() {
        return funcionarioRepository.listarNomesOrdenadosAsc();
    }

    @Transactional
    public long listarQuantidade() {
        return funcionarioRepository.count();
    }
}

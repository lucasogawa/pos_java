package com.ogawalucas.avaliacaobancodedados.service;

import com.ogawalucas.avaliacaobancodedados.entity.Cargo;
import com.ogawalucas.avaliacaobancodedados.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Transactional
    public Cargo salvar(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    @Transactional
    public void deletar(Long codigo) {
        cargoRepository.deleteById(codigo);
    }

    @Transactional
    public List<Cargo> listarTodos() {
        return cargoRepository.findAll();
    }
}

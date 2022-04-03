package com.ogawalucas.avaliacaobancodedados;

import com.ogawalucas.avaliacaobancodedados.entity.Cargo;
import com.ogawalucas.avaliacaobancodedados.entity.Funcionario;
import com.ogawalucas.avaliacaobancodedados.service.CargoService;
import com.ogawalucas.avaliacaobancodedados.service.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FilterOutputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AvaliacaoBancoDeDadosApplication {

	private static final Logger LOG = LoggerFactory.getLogger(AvaliacaoBancoDeDadosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoBancoDeDadosApplication.class, args);
	}

	@Bean
	public CommandLineRunner teste(CargoService cargoService,
								   FuncionarioService funcionarioService) {
		return (arg) -> {
			LOG.info("===== Inserir pelo menos 3 Cargos =====");
			var cargo1 = new Cargo();
			var cargo2 = new Cargo();
			var cargo3 = new Cargo();

			cargo1.setCargo("Cargo 1");
			cargo2.setCargo("Cargo 2");
			cargo3.setCargo("Cargo 3");

			cargo1 = cargoService.salvar(cargo1);
			cargo2 = cargoService.salvar(cargo2);
			cargo3 = cargoService.salvar(cargo3);

			LOG.info(cargo1.toString());
			LOG.info(cargo2.toString());
			LOG.info(cargo3.toString());
			LOG.info("========================================");



			LOG.info("===== Inserir pelo menos 3 Funcionários (associando aos seus respectivos cargos) =====");
			var funcionario1 = new Funcionario();
			var funcionario2 = new Funcionario();
			var funcionario3 = new Funcionario();

			funcionario1.setNome("Nome 1");
			funcionario1.setSexo("Sexo 1");
			funcionario1.setTelefone("Telefone 1");

			funcionario2.setNome("Nome 2");
			funcionario2.setSexo("Sexo 2");
			funcionario2.setTelefone("Telefone 2");

			funcionario3.setNome("Nome 3");
			funcionario3.setSexo("Sexo 3");
			funcionario3.setTelefone("Telefone 3");

			LOG.info(funcionarioService.salvar(funcionario1, cargo1).toString());
			LOG.info(funcionarioService.salvar(funcionario2, cargo2).toString());
			LOG.info(funcionarioService.salvar(funcionario3, cargo3).toString());
			LOG.info("======================================================================================");



			LOG.info("===== Excluir pelo menos 1 Cargo pelo identificador =====");
			// cargoService.deletar(1L);
			LOG.info("=========================================================");



			LOG.info("===== Excluir pelo menos 1 Funcionário pelo identificador =====");
			// funcionarioService.deletar(1L);
			LOG.info("===============================================================");



			LOG.info("===== Listar Funcionários com os seus respectivos Cargos =====");
			funcionarioService.listarTodos().stream()
				.map(Funcionario::toString)
				.forEach(LOG::info);
			LOG.info("==============================================================");



			LOG.info("===== Listar Cargos com a respectiva lista de Funcionários =====");
			cargoService.listarTodos().stream()
				.map(Cargo::toString)
				.forEach(LOG::info);
			LOG.info("================================================================");



			LOG.info("===== Listar o Nome de Funcionários em Ordem Alfabética =====");
			funcionarioService.listarNomesOrdenadosAsc()
				.forEach(LOG::info);
			LOG.info("=============================================================");



			LOG.info("===== Listar a Quantidade de Funcionários =====");
			LOG.info(String.valueOf(funcionarioService.listarQuantidade()));
			LOG.info("===============================================");


//			List<Cargo> lstPessoa = new ArrayList (Cargo);

//			List<> lstPessoa = new ArrayList<>();

//			List lstPessoa = new ArrayList<Cargo>();

			List<Cargo>IstPessoa = new ArrayList();

			List<Cargo>lstPessoa = new ArrayList<>();
		};
	}
}

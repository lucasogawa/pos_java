# 2) Crie um banco de dados no SGBD com base no DER criado.
CREATE SCHEMA atividade02;
USE atividade02;

CREATE TABLE departamento (
    cod integer NOT NULL auto_increment,
    nome character varying(50),
    CONSTRAINT departamento_pkey PRIMARY KEY (cod)
);

CREATE TABLE funcionario (
    cod integer NOT NULL auto_increment,
    nome character varying(50),
    qtd_dependentes integer,
    salario decimal(7, 2),
    cargo character varying(50),
    cod_departamento integer NOT NULL,
    CONSTRAINT funcionario_pkey PRIMARY KEY (cod),
    CONSTRAINT cod_departamento_fkey FOREIGN KEY (cod_departamento) REFERENCES departamento(cod)
);

# 3) Popule o banco de dados criado.
INSERT INTO departamento (cod, nome) 
VALUES (1, 'ADMINISTRATIVO'),
	   (2, 'COMERCIAL'),
	   (3, 'DIRETORIA'),
	   (4, 'MARKETING');

INSERT INTO funcionario (cod, nome, qtd_dependentes, salario, cargo, cod_departamento) 
VALUES (1, 'NOME 1', 0, 1000.00, 'Gerente', 1),

	   (2, 'NOME 2', 0, 2000.00, 'SUBGERENTE', 2),
	   (3, 'NOME 3', 0, 3000.00, 'Gerente', 2),
	   
	   (4, 'NOME 4', 0, 4000.00, 'SUBGERENTE', 3),
	   (5, 'NOME 5', 0, 5000.00, 'SUBGERENTE', 3),
	   (6, 'NOME 6', 0, 6000.00, 'Gerente', 3),
	   
	   (7, 'NOME 7', 0, 7000.00, 'SUBGERENTE', 4),
	   (8, 'NOME 8', 0, 8000.00, 'SUBGERENTE', 4),
	   (9, 'NOME 9', 0, 9000.00, 'SUBGERENTE', 4),
	   (10, 'NOME 10', 0, 10000.00, 'Gerente', 4);
	  
# 4) Faça uma VISÃO (view) para cada uma das seguintes consultas:
# a. Mostre o nome do departamento que tem o maior número de funcionários, mostrando também a quantidade de funcionários desse departamento.
CREATE VIEW 4A AS 
	SELECT d.nome as nome_departamento, count(f.cod) as qtd_funcionarios
		FROM departamento d
		INNER JOIN funcionario f on f.cod_departamento = d.cod
		GROUP BY d.cod
		HAVING COUNT(f.cod) = (
								SELECT COUNT(*)
									FROM departamento d
									INNER JOIN funcionario f on f.cod_departamento = d.cod
									GROUP BY d.cod
									ORDER BY COUNT(*) DESC
									LIMIT 1
							  );
	  
# b. Mostre o nome do departamento que tem a menor quantidade de funcionários sem dependentes, com a quantidade de funcionários.
CREATE VIEW 4B AS 
	SELECT d.nome as nome_departamento, COUNT(f.cod) as qtd_funcionarios
		FROM departamento d
		INNER JOIN funcionario f on f.cod_departamento = d.cod
		WHERE f.qtd_dependentes = 0
		GROUP BY d.cod
		HAVING COUNT(d.cod) = (
							  	SELECT COUNT(d2.cod) 
									FROM departamento d2 
									INNER JOIN funcionario f2 on f2.cod_departamento = d2.cod
									WHERE f2.qtd_dependentes = 0
									GROUP BY d2.cod
									ORDER BY COUNT(d2.cod)
									LIMIT 1
							  );
	  
# c. Mostre o nome do departamento com os nomes dos seus respectivos funcionários de todos os departamentos cujo nome começa com “DIR”.
CREATE VIEW 4C AS
	SELECT d.nome as nome_departamento, f.nome as nome_funcionario
		FROM departamento d
		INNER JOIN funcionario f on f.cod_departamento = d.cod
		WHERE d.nome like 'DIR%';
	  
# d. Mostre o nome do funcionário e do departamento ao qual pertence o funcionário com o maior salário.
CREATE VIEW 4D AS 
	SELECT f.nome as nome_funcionario, d.nome as nome_departamento
		FROM funcionario f
		INNER JOIN departamento d on d.cod = f.cod_departamento 
		WHERE f.salario = (
							SELECT MAX(f2.salario)
								FROM funcionario f2
						  );

# e. Mostre o nome do departamento e do funcionário de cada departamento que tem o cargo de “Gerente”.
CREATE VIEW 4E AS 
	SELECT d.nome as nome_departamento, f.nome as nome_funcionario
		FROM departamento d 
		INNER JOIN funcionario f ON f.cod_departamento = d.cod 
		WHERE f.cargo like 'Gerente';

# 5) Crie dois usuários para acesso ao banco, sendo que:
# a. O usuário “funcionario” tem acesso limitado.
CREATE USER 'funcionario'@'localhost' IDENTIFIED BY 'funcionario';
GRANT SELECT ON atividade02 . * TO 'funcionario'@'localhost';
FLUSH PRIVILEGES;

# b. O usuário “gerente” possui com acesso completo.
CREATE USER 'gerente'@'localhost' IDENTIFIED BY 'gerente';
GRANT ALL PRIVILEGES ON atividade02 . * TO 'gerente'@'localhost';
FLUSH PRIVILEGES;
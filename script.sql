-------------------------------------------
--CRIAÇÃO DA TABELA SETOR
-------------------------------------------

CREATE TABLE setor (
    id SERIAL,
    nome VARCHAR(50),
    status CHAR(1), 
    PRIMARY KEY (id));
    
    
-------------------------------------------
--CRIAÇÃO DA TABELA DISPOSITIVO
-------------------------------------------
CREATE TABLE dispositivo(
    id SERIAL,
    nomerede VARCHAR(100),
    ip VARCHAR(100),
    setor_id INT,
    status CHAR(1),
    PRIMARY KEY (id),
    FOREIGN KEY (setor_id) REFERENCES setor(id));
    
    
-------------------------------------------
--CRIAÇÃO DA TABELA USUÁRIO
-------------------------------------------
CREATE TABLE usuario (
    id SERIAL,
    login VARCHAR(100),
    senha VARCHAR(100),
    nome VARCHAR (100),
    email VARCHAR(100),
    setor_id INT,
    tipoUsuario INT,
    status CHAR(1),
    PRIMARY KEY (id),
    FOREIGN KEY (setor_id) REFERENCES setor(id));
    
    
------------------------------------------
--CRIAÇÃO DA TABELA PRODUTO
-------------------------------------------
CREATE TABLE produto (
    id SERIAL,
    descricao VARCHAR (100),
    status CHAR,
    PRIMARY KEY (id));
    
    
------------------------------------------
--CRIAÇÃO DA TABELA STATUS
-------------------------------------------
CREATE TABLE Status (
	id SERIAL,
	descricao VARCHAR(150),
	PRIMARY KEY (id));


-------------------------------------------
--CRIAÇÃO DA TABELA TIPO
-------------------------------------------
CREATE TABLE Tipo (
	id SERIAL,
	descricao VARCHAR(150),
	PRIMARY KEY (id));
    
    
-------------------------------------------
--CRIAÇÃO DA TABELA ORDEM DE SERVIÇO
-------------------------------------------
CREATE TABLE OrdemDeServico (
    id SERIAL,
    dataEmissao DATE,
    dataEntrega DATE,
    problemaRelatado VARCHAR(100),
    problemaConstatado VARCHAR(100),
    resolucao VARCHAR(100),
    solicitante INT,
    atendente INT,
    dispositivo_id INT,
    tipo_id INT,
    status_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_ordemdeservico_usuarioS FOREIGN KEY (solicitante) REFERENCES usuario(id),
    CONSTRAINT fk_ordemdeservico_usuarioA FOREIGN KEY (atendente) REFERENCES usuario(id),
    CONSTRAINT fk_ordemdeservico_dispositivo FOREIGN KEY (dispositivo_id) REFERENCES dispositivo(id),
    CONSTRAINT fk_ordemdeservico_tipo FOREIGN KEY (tipo_id) REFERENCES tipo(id),
    CONSTRAINT fk_ordemdeservico_status FOREIGN KEY (status_id) REFERENCES status(id));


-------------------------------------------
--CRIAÇÃO DA TABELA PRODUTOS
-------------------------------------------
CREATE TABLE Produtos (
	OrdemDeServico_id INT NOT NULL,
	Produto_id INT,
	quantidade INT,
	CONSTRAINT fk_ordemdeservicoId FOREIGN KEY (OrdemDeServico_id) REFERENCES OrdemDeServico(id),
	CONSTRAINT fk_produtoId FOREIGN KEY (Produto_id) REFERENCES Produto(id),
	PRIMARY KEY (OrdemDeServico_id,Produto_id));


-------------------------------------------
--INSERÇÃO DA TABELA STATUS
-------------------------------------------
INSERT INTO status VALUES 
(DEFAULT, 'ABERTA'),
(DEFAULT, 'FECHADA');


-------------------------------------------
--INSERÇÃO DA TABELA TIPO
-------------------------------------------
INSERT INTO tipo VALUES 
(DEFAULT, 'MANUTENÇÃO CORRETIVA'),
(DEFAULT, 'MANUTENÇÃO PREVENTIVA');


-------------------------------------------
--INSERÇÃO DA TABELA SETOR
-------------------------------------------
INSERT INTO setor VALUES (DEFAULT, 'Informática', 'a');


-------------------------------------------
--INSERÇÃO DA TABELA USUÁRIO
-------------------------------------------

INSERT INTO usuario VALUES
(DEFAULT, 'Administrador', '81dc9bdb52d04dc20036dbd8313ed055', 'Administrador', 'admin@admin.com.br', '1', '1', 'A' );
--LOGIN: Administrador
--SENHA: 1234


   
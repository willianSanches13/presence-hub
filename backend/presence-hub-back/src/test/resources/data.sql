CREATE TABLE aluno (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       idade INT,
                       serie VARCHAR(255),
                       instituicao_de_ensino VARCHAR(255),
                       telefone_contato VARCHAR(255),
                       cidade VARCHAR(255),
                       estado VARCHAR(255),
                       nome_responsavel VARCHAR(255),
                       telefone_responsavel VARCHAR(255),
                       matricula_projeto VARCHAR(255),
                       data_inscricao DATE,
                       observacoes TEXT
);

CREATE TABLE professor (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL,
                           telefone_contato VARCHAR(255),
                           especializacao VARCHAR(255),
                           instituicao_vinculo VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS WORKSHOP (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    data DATE,
    professor_id BIGINT,
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

CREATE TABLE IF NOT EXISTS CERTIFICADO (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           aluno_id BIGINT,
                                           workshop_id BIGINT,
                                           assinatura_digital VARCHAR(255),
    data_emissao DATE,
    FOREIGN KEY (aluno_id) REFERENCES ALUNO(id),
    FOREIGN KEY (workshop_id) REFERENCES WORKSHOP(id)
    );

CREATE TABLE IF NOT EXISTS PARTICIPACAO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    aluno_id BIGINT,
    workshop_id BIGINT,
    presente BOOLEAN,
    FOREIGN KEY (aluno_id) REFERENCES ALUNO(id),
    FOREIGN KEY (workshop_id) REFERENCES WORKSHOP(id));





INSERT INTO aluno (id, nome, email, idade, serie, instituicao_de_ensino, telefone_contato, cidade, estado, nome_responsavel, telefone_responsavel, matricula_projeto, data_inscricao, observacoes)
VALUES (999, 'John Doe', 'john.doe@example.com', 23, '5th Year', 'Example Institute', '123-456-7893', 'Example Village', 'Example State', 'David Brown', '098-765-4324', '654323', '2023-03-01', 'No observations');

INSERT INTO aluno (id, nome, email, idade, serie, instituicao_de_ensino, telefone_contato, cidade, estado, nome_responsavel, telefone_responsavel, matricula_projeto, data_inscricao, observacoes)
VALUES (998, 'Jane Doe', 'jane.doe@example.com', 23, '5th Year', 'Example Institute', '123-456-7893', 'Example Village', 'Example State', 'David Brown', '098-765-4324', '654323', '2023-03-01', 'No observations');

INSERT INTO WORKSHOP (id, titulo, descricao, data) VALUES (997, 'Workshop 1', 'Description for Workshop 1', '2023-10-01');
INSERT INTO WORKSHOP (id, titulo, descricao, data) VALUES (996, 'Workshop 2', 'Description for Workshop 2', '2023-11-01');
INSERT INTO WORKSHOP (id, titulo, descricao, data) VALUES (995, 'Workshop 3', 'Description for Workshop 3', '2023-12-01');

INSERT INTO PARTICIPACAO (id, aluno_id, workshop_id, presente) VALUES (999, 998, 997, true);
INSERT INTO PARTICIPACAO (id, aluno_id, workshop_id, presente) VALUES (998, 999, 996, false);

INSERT INTO CERTIFICADO (id, aluno_id, workshop_id, assinatura_digital, data_emissao) VALUES (991, 998, 997, 'digital-signature-1', '2023-10-01');
INSERT INTO CERTIFICADO (id, aluno_id, workshop_id, assinatura_digital, data_emissao) VALUES (992, 999, 996, 'digital-signature-2', '2023-11-01');
INSERT INTO CERTIFICADO (id, aluno_id, workshop_id, assinatura_digital, data_emissao) VALUES (993, 998, 995, 'digital-signature-3', '2023-12-01');
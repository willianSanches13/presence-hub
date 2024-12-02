CREATE TABLE aluno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS WORKSHOP (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    data DATE
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



INSERT INTO aluno (id, nome, email, senha) VALUES (998, 'John Doe', 'john.doe@example.com', 'password');
INSERT INTO aluno (id, nome, email, senha) VALUES (999, 'Jane Doe', 'jane.doe@example.com', 'password');

INSERT INTO WORKSHOP (id, titulo, descricao, data) VALUES (997, 'Workshop 1', 'Description for Workshop 1', '2023-10-01');
INSERT INTO WORKSHOP (id, titulo, descricao, data) VALUES (996, 'Workshop 2', 'Description for Workshop 2', '2023-11-01');
INSERT INTO WORKSHOP (id, titulo, descricao, data) VALUES (995, 'Workshop 3', 'Description for Workshop 3', '2023-12-01');

INSERT INTO PARTICIPACAO (id, aluno_id, workshop_id, presente) VALUES (1, 998, 997, true);
INSERT INTO PARTICIPACAO (id, aluno_id, workshop_id, presente) VALUES (2, 999, 996, false);

INSERT INTO CERTIFICADO (id, aluno_id, workshop_id, assinatura_digital, data_emissao) VALUES (991, 998, 997, 'digital-signature-1', '2023-10-01');
INSERT INTO CERTIFICADO (id, aluno_id, workshop_id, assinatura_digital, data_emissao) VALUES (992, 999, 996, 'digital-signature-2', '2023-11-01');
INSERT INTO CERTIFICADO (id, aluno_id, workshop_id, assinatura_digital, data_emissao) VALUES (993, 998, 995, 'digital-signature-3', '2023-12-01');
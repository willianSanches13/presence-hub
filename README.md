**Planejamento OFICINA 2**

**Software: Controle de Presença**

**Alunos:**  
Crislean Santos Martim  
Daniel de Abreu  
Willian Junior Sanches  

**Requisitos Funcionais**

1. **Cadastro de Alunos**: Permitir o registro dos alunos com informações básicas: nome, turma.
2. **Registro de Presença**: Funcionalidade para registrar a presença em cada aula, identificando a aula e os alunos presentes.
3. **Consulta de Frequência**: Possibilitar que alunos e administradores consultem o histórico de presença.
4.  **Relatórios de Presença**: Gerar relatórios detalhados da frequência por período, aluno, ou turma.
5. **Notificações**: Notificar alunos ou administradores sobre ausências ou alertas de frequência baixa, se aplicável.

**Telas**

1. **Tela de Login**: Para que alunos e administradores façam login.![][image1]
2. **Dashboard Principal**: Exibe um resumo das presenças, total de aulas, e estatísticas de frequência.![][image2]
3. **Tela de Cadastro:** Para que usuários professores possam cadastrar turmas, alunos e outros professores.![][image3]
4. **Tela de Registro de Presença**: Permite que o administrador registre a presença de alunos para uma aula específica.![][image4]
5. **Tela de Consulta de Frequência**: Mostra o histórico de frequência de um aluno ou de uma turma.![][image5]
   [figma](https://www.figma.com/design/j6UrFEnNSHwvFKZpTgIbRS/Projeto-oficina-2?t=qRw8caEaC815hhqp-0)

**Modelagem do banco:**

**![][image6]**

**Fluxo de implantação no GCP**

1. Equipe de Desenvolvimento:

Os desenvolvedores enviam o código para o repositório.

2. GitHub Actions:

O GitHub Actions é acionado por um push no repositório GitHub.

Constrói a imagem Docker e envia para o Google Container Registry (GCR).

3. Spinnaker:

O código enviado é processado pelo Spinnaker, uma ferramenta de entrega contínua.

O Spinnaker cria uma imagem de nuvem (Cloud Image) como um artefato de construção (Build Artifact).

Pega a imagem do GCR e gerencia a implantação.

4. Instâncias de Teste:

A imagem de nuvem é implantada em instâncias de teste no Compute Engine.

5. Instâncias de Produção com Autoscaler:

A imagem de nuvem é também implantada em instâncias de produção com autoscaler no Compute Engine.

Adicionalmente, outra implantação ocorre em instâncias de produção com autoscaler.

6. Balanceamento de Carga:

As instâncias de produção são balanceadas por carga usando o Cloud Load Balancing, que distribui o tráfego para os usuários finais.

**![][image7]**  

# Arquitetura da API de Controle de Presença

Esta API foi desenvolvida utilizando **Java 21** com **Spring Boot 3** e segue uma arquitetura de camadas para facilitar a manutenção e escalabilidade. A estrutura é composta pelos seguintes componentes principais:

## Componentes

1. **API Gateway**  
   Responsável por gerenciar e direcionar as requisições para os endpoints apropriados da API. Serve como ponto de entrada único para os clientes, garantindo segurança e controle de tráfego.

2. **Camada de Controller**  
   Contém os endpoints REST para os recursos principais da aplicação:
    - **Auth**: Gerencia autenticação e autorização dos usuários.
    - **Aula**: Gerencia as operações relacionadas às aulas.
    - **Aluno**: Gerencia as operações relacionadas aos alunos.
    - **Presença**: Gerencia o registro e controle de presenças nas aulas.

3. **Camada de Service**  
   Implementa a lógica de negócio para cada recurso da aplicação. Nesta camada, são realizados os processamentos e validações necessárias antes de persistir ou recuperar dados do repositório.

4. **Camada de Repository**  
   Contém os repositórios que interagem diretamente com o banco de dados para realizar operações de CRUD (Create, Read, Update, Delete) nos recursos **Aula**, **Aluno** e **Presença**.

5. **Camada de Model**  
   Define as entidades do sistema, mapeando os modelos **Aula**, **Aluno** e **Presença** para tabelas no banco de dados.

6. **Banco de Dados**  
   Utiliza o **PostgreSQL** como banco de dados relacional para armazenar as informações de aulas, alunos e presenças.

## Fluxo de Requisição

1. O cliente envia uma requisição para o **API Gateway**.
2. O gateway redireciona a requisição para o **Controller** apropriado com base no endpoint solicitado.
3. O **Controller** chama o **Service** correspondente, onde a lógica de negócio é executada.
4. O **Service** interage com o **Repository** para buscar ou salvar dados no banco de dados.
5. O **Repository** acessa o **Banco de Dados** e retorna os dados para o **Service**.
6. O **Service** processa os dados e os envia de volta ao **Controller**, que responde ao cliente através do **API Gateway**.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **PostgreSQL**

Esta arquitetura modular permite uma fácil expansão e manutenção da API, promovendo separação de responsabilidades entre as diferentes camadas da aplicação.

![][image8]

[image1]: artifacts/figma/login.png

[image2]: artifacts/figma/dashboard.png

[image3]: artifacts/figma/cadastro.png

[image4]: artifacts/figma/registro.png

[image5]: artifacts/figma/relatorio.png

[image6]: artifacts/der.png

[image7]: artifacts/deploy.png

[image8]: artifacts/diagrama_api.png

# CLYVO PAWS (CLYVO VET) - FIAP CHALLENGE 2026

API RESTful desenvolvida para o sistema de gerenciamento veterinario Clyvo Paws. 
Este projeto visa digitalizar e otimizar o atendimento clinico, historico medico 
e acompanhamento preventivo de pets, facilitando a comunicacao entre a clinica e 
os tutores.

--------------------------------------------------------------------------------
## 👥 INTEGRANTES DO GRUPO (Turma: 2TDSPX)
--------------------------------------------------------------------------------
* João Pedro Pereira Camilo       | RM: 562005
* Lucas Matsubara Reis            | RM: 565020
* Pamella Christiny Chaves Brito  | RM: 565206

--------------------------------------------------------------------------------
## 🚀 TECNOLOGIAS UTILIZADAS
--------------------------------------------------------------------------------
O projeto foi desenvolvido com foco em performance, escalabilidade e boas 
praticas de engenharia de software (SOLID e Clean Code):

* Java 21
* Spring Boot 3.2.6 (Web, Data JPA, Validation)
* Oracle Database (Banco de dados relacional)
* Lombok (Reducao de boilerplate)
* Springdoc OpenAPI / Swagger (Documentacao automatizada)
* Maven (Gerenciador de dependencias)

--------------------------------------------------------------------------------
## 🏗️ ARQUITETURA E PADROES IMPLEMENTADOS
--------------------------------------------------------------------------------
* Data Transfer Objects (DTOs): Separacao estrita entre a camada de persistencia 
  (Entities) e a camada de apresentacao, prevenindo exposicao acidental de dados 
  sensiveis e loops infinitos.

* Slim Payloads (Data Shaping): Uso de DTOs resumidos (ex: TutorResumoDTO, 
  PetResumoDTO) para otimizar o trafego de rede e consumo de memoria no lado 
  do cliente (Mobile/Web).

* Paginacao Universal: Implementacao de Pageable do Spring Data nas listagens 
  de colecoes pesadas (Consultas e Medicamentos) para garantir alta performance.

* Tratamento Global de Excecoes: Uso de @RestControllerAdvice para interceptar 
  erros (404 Not Found, 400 Bad Request) e padronizar o payload de resposta em 
  JSON para facilitar o consumo pelo Front-end.

--------------------------------------------------------------------------------
## ⚙️ COMO EXECUTAR O PROJETO LOCALMENTE
--------------------------------------------------------------------------------

1. Pre-requisitos
   - Java JDK 21 instalado.
   - Maven instalado.
   - Acesso ao banco de dados Oracle (via VPN FIAP ou local).
   - IDE de sua preferencia (IntelliJ IDEA, Eclipse, VS Code).

2. Configurando o Banco de Dados
   Abra o arquivo 'src/main/resources/application.properties' e insira as suas 
   credenciais de acesso ao banco de dados Oracle da FIAP:

   spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
   spring.datasource.username=
   spring.datasource.password=

3. Rodando a Aplicacao
   Pelo terminal, navegue ate a raiz do projeto e execute:
   
   > mvn clean install
   > mvn spring-boot:run
   
   (Se estiver usando o IntelliJ, basta clicar no botao de "Play" na classe 
   ClyvovetApplication).

--------------------------------------------------------------------------------
## 📚 DOCUMENTACAO DA API (SWAGGER)
--------------------------------------------------------------------------------
Com a aplicacao em execucao, acesse a documentacao interativa para visualizar 
e testar todos os endpoints:

URL Local: http://localhost:8080/swagger-ui.html

Principais Endpoints:
* POST /tutores: Cadastra um novo tutor.
* GET /pets: Lista todos os pets cadastrados (Paginado).
* GET /consultas/pet/{petId}: Historico medico de um pet especifico (Paginado).
* POST /medicamentos/doses/check: Registra a tomada de uma dose de medicamento.
* GET /planos-preventivos: Retorna diretrizes medicas baseadas na especie.
--------------------------------------------------------------------------------

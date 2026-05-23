# CLYVO PAWS (CLYVO VET) - FIAP CHALLENGE 2026

API RESTful desenvolvida para o sistema de gerenciamento veterinario Clyvo Paws. 
Este projeto visa digitalizar e otimizar o atendimento clínico, histórico médico 
e acompanhamento preventivo de pets.

--------------------------------------------------------------------------------
## 👥 INTEGRANTES DO GRUPO (Turma: 2TDSPX)
--------------------------------------------------------------------------------
* João Pedro Pereira Camilo       | RM: 562005
* Lucas Matsubara Reis            | RM: 565020
* Pamella Christiny Chaves Brito  | RM: 565206

--------------------------------------------------------------------------------
## 🚀 ATENDIMENTO AOS REQUISITOS (JAVA ADVANCED)
--------------------------------------------------------------------------------
Para facilitar a correção pelo professor, abaixo estao os pontos chave exigidos 
no escopo do Challenge:

1. Validação de campos (Bean Validation): Implementado via DTOs (@NotBlank, @Size, @Email).
2. Paginação e Ordenação: Implementado via @PageableDefault nos endpoints de Consultas, Medicamentos e Pets.
3. Busca com parâmetros: Implementado na rota GET /planos-preventivos?especie=CACHORRO.
4. Uso de Cache: Implementado com @EnableCaching e @Cacheable na Service de CatalogoPreventivo e de Consulta.
5. Tratamento de Exceções: Implementado via @RestControllerAdvice (GlobalExceptionHandler) mapeando erros 400 e 404.
6. Relacionamentos JPA e DTOs: Implementado Data Shaping (Slim Payloads) para otimizar as consultas evitando loops infinitos.

--------------------------------------------------------------------------------
## 📁 ONDE ENCONTRAR OS ARTEFATOS EXIGIDOS
--------------------------------------------------------------------------------
Todos os documentos probatorios estao na pasta raiz do projeto na pasta "/documentos":

* Cronograma de Desenvolvimento: Veja o arquivo `cronograma.pdf`.
* Diagramas (Classes, DER e Arquitetura): Veja a pasta `/documentos/diagramas`.
* Collection do Postman/Insomnia: Veja o arquivo `clyvopaws_collection.json` na raiz do projeto contendo as requisicoes exportadas.

--------------------------------------------------------------------------------
## ⚙️ COMO EXECUTAR O PROJETO LOCALMENTE
--------------------------------------------------------------------------------
1. Configure suas credenciais do Banco de Dados Oracle no arquivo 'application.properties'.
2. Execute o projeto via Maven: 'mvn spring-boot:run' ou pela sua IDE.
3. Acesse a documentação do Swagger: http://localhost:8080/swagger-ui.html

--------------------------------------------------------------------------------

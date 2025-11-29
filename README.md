## 🧠 Sistema de Gestão de Vagas de Trabalho

### 📘 Contexto
**API RESTful em Java com Spring Boot** para gerenciar **vagas de trabalho** de uma empresa.  
O sistema ajudará a encontrar e propagar informações mais facilmente sobre vagas disponíveis no mercado de trabalho.

---

## 🎯 Requisitos Técnicos

### 🧱 1. Modelagem de Domínio

#### `User`
| Campo       | Tipo        | Descrição          |
|-------------|-------------|--------------------|
| `id`        | UUID        | Identificador      |
| `name`      | String      | **Obrigatório**    |
| `email`     | String      | **Obrigatório**    |
| `password`  | String      | **Obrigatório**    |
| `companyId` | FK(Company) | Compania associada |

#### `Company`
| Campo         | Tipo   | Descrição       |
|---------------|--------|-----------------|
| `id`          | UUID   | Identificador   |
| `name`        | String | **Obrigatório** |
| `website`     | String | **Obrigatório** |
| `description` | String | Opcional        |

#### `Job`
| Campo         | Tipo        | Descrição                  |
|---------------|-------------|----------------------------|
| `id`          | UUID        | Identificador              |
| `title`       | String      | **Obrigatório**            |
| `description` | String      | Detalhes da tarefa         |
| `modality`    | ENUM        | CLT / PJ / FREELANCE       |
| `salary`      | DECIMAL     | Salário                    |
| `active`      | BOOLEAN     | Disponibilidade da vaga    |
| `location`    | String      | Localidade da vaga da vaga |
| `createdAt`   | DATE        | Data de criação da vaga    |
| `companyId`   | FK(Company) | Compania associada         |
---

### 🌐 2. Endpoints REST

#### Autenticação e Usuários
| Método | Endpoint              | Descrição                             |
|---------|-----------------------|---------------------------------------|
| **POST** | `/auth/login`         | Autenticar usuário (retorna JWT token) |
| **POST** | `/auth/register` | Registrar-se              |

#### Company
| Método     | Endpoint          | Descrição                                                 |
|------------|-------------------|-----------------------------------------------------------|
| **POST**   | `/companies`      | Criar nova compania (apenas usuário logado)               |
| **GET**    | `/companies`      | Listar todos as companias                                 |
| **GET**    | `/companies/{id}` | Listar compania por id                                    |
| **PUT**    | `/companies/{id}` | Atualizar compania (apenas usuário logado e proprietário) |

#### Jobs
| Método     | Endpoint                                            | Descrição                                             |
|------------|-----------------------------------------------------|-------------------------------------------------------|
| **POST**   | `/jobs`                                             | Criar nova vaga (apenas usuário logado)               |
| **GET**    | `/jobs?title={}&modality={}&active={}&minSalary={}` | Listar todas as vagas (filtros e paginação)           |
| **GET**    | `/jobs/{id}`                                        | Listar vaga por id                                    |
| **GET**    | `/jobs/company/{companyId}`                         | Listar todas as vagas por compania                    |
| **PUT**    | `/jobs/{id}`                                        | Atualizar vaga (apenas usuário logado e proprietário) |
| **DELETE** | `/jobs/{id}`                                        | Inativar vaga (apenas usuário logado e proprietário)  |
---

## ✅ Requisitos Obrigatórios
- 🧑‍💻 **Java 17+** e **Spring Boot 3+**
- 🧠 **Spring Data JPA**
- 🗄️ Banco Relacional (**PostgreSQL** ou **H2**)
- ✔️ **Bean Validation**
- ⚠️ Tratamento de erros com `@ControllerAdvice`
- 📦 Uso de **DTOs** (`record` ou classes simples)
- 📘 **README** explicando como rodar o projeto

---

## 🏅 Diferenciais
- 🔐 Autenticação simples com **JWT** ou Basic Auth
- 🐳 Configuração de **Docker** / **docker-compose**
- ⚡ Uso de **MapStruct** para mapeamento de DTOs
- 🔍 Testes de API com **RestAssured**

---

## 🛠️ Tags
`#Java` `#SpringBoot` `#Backend` `#DesafioTecnico`  
`#API` `#RestAPI` `#Docker` `#Kubernetes`  
`#PostgreSQL` `#Oracle` `#JPA` `#Swagger`  
`#RestAssured` `#CleanCode` `#SoftwareEngineering`

---

### 🧾 Licença
Este projeto foi desenvolvido exclusivamente para o **processo seletivo SIS Innov & Tech** e não deve ser utilizado para fins comerciais.

---
# Sistema de Gestão Acadêmica (SGA) - API REST

**Atividade Prática Supervisionada (APS) - POO Java**  
**EEEP Alfredo Nunes de Melo - Técnico em Desenvolvimento de Sistemas**

---

## 📋 Sobre o Projeto

API REST profissional para gerenciamento de **Cursos** e **Alunos** de uma escola técnica. Este é o "motor" (Backend) do Sistema de Gestão Acadêmica, desenvolvido com as mesmas tecnologias utilizadas por grandes empresas do mercado.

## 🚀 Tecnologias Utilizadas

- **Java 17+** - Linguagem de programação
- **Spring Boot 3.2.0** - Framework para desenvolvimento rápido
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco de dados em memória
- **Maven** - Gerenciador de dependências
- **Lombok** - Redução de código boilerplate
- **Bean Validation** - Validação de dados

## 📁 Estrutura do Projeto

```
src/main/java/com/sga/
├── model/              # Entidades JPA (Curso, Aluno)
├── repository/         # Interfaces de acesso ao banco
├── service/            # Lógica de negócio
├── controller/         # Endpoints REST
└── SgaApplication.java # Classe principal
```

## 🗄️ Modelo de Dados

### Entidade: Curso
- `id` (Long) - Chave primária, auto-incremento
- `nome` (String) - Obrigatório (ex: "Técnico em Informática")
- `sigla` (String) - Único, obrigatório (ex: "INF")
- `descricao` (String) - Opcional

### Entidade: Aluno
- `id` (Long) - Chave primária, auto-incremento
- `nome` (String) - Obrigatório
- `matricula` (String) - Único, obrigatório (ex: "2024INF01")
- `email` (String) - Obrigatório, formato válido
- `curso` (Curso) - Relacionamento Many-to-One

**Relacionamento**: Um Curso pode ter vários Alunos (One-to-Many)

## 🔧 Como Executar

### Pré-requisitos
- JDK 17 ou superior instalado
- Maven instalado (ou use o wrapper incluído)
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Passos para rodar

1. **Clone ou extraia o projeto**
   ```bash
   cd "Projeto final"
   ```

2. **Execute o projeto com Maven**
   ```bash
   mvn spring-boot:run
   ```

3. **Acesse a aplicação**
   - API REST: `http://localhost:8080`
   - Console H2: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:sga_db`
     - Username: `sa`
     - Password: *(deixe em branco)*

## 📡 Endpoints da API

### Gerenciamento de Cursos (`/api/cursos`)

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| POST | `/api/cursos` | Cadastrar novo curso | 201 Created |
| GET | `/api/cursos` | Listar todos os cursos | 200 OK |
| GET | `/api/cursos/{id}` | Buscar curso por ID | 200 OK / 404 Not Found |
| DELETE | `/api/cursos/{id}` | Deletar curso* | 204 No Content / 400 Bad Request |

*Só é possível deletar um curso se não houver alunos matriculados.

### Gerenciamento de Alunos (`/api/alunos`)

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| POST | `/api/alunos` | Matricular novo aluno | 201 Created |
| GET | `/api/alunos` | Listar todos os alunos | 200 OK |
| GET | `/api/alunos/{id}` | Buscar aluno por ID | 200 OK / 404 Not Found |
| GET | `/api/alunos/curso/{cursoId}` | Listar alunos de um curso | 200 OK |
| PUT | `/api/alunos/{id}` | Atualizar dados do aluno | 200 OK / 404 Not Found |
| DELETE | `/api/alunos/{id}` | Cancelar matrícula | 204 No Content / 404 Not Found |

## 📝 Exemplos de Uso

Veja o arquivo [API_EXAMPLES.md](API_EXAMPLES.md) para exemplos completos com JSON e cURL.

## ✅ Regras de Negócio Implementadas

1. **Sigla única**: Não pode haver dois cursos com a mesma sigla
2. **Matrícula única**: Não pode haver dois alunos com a mesma matrícula
3. **Email válido**: O email do aluno deve ter formato válido
4. **Curso obrigatório**: Todo aluno deve estar vinculado a um curso existente
5. **Proteção de deleção**: Não é possível deletar um curso que tenha alunos matriculados

## 🧪 Como Testar

Use **Postman** ou **Insomnia** para testar os endpoints:

1. Primeiro, cadastre um curso (POST `/api/cursos`)
2. Depois, matricule alunos nesse curso (POST `/api/alunos`)
3. Teste as outras operações (GET, PUT, DELETE)

## 📚 Conceitos Importantes para a Defesa Oral

- **@Entity**: Marca uma classe como entidade JPA (tabela no banco)
- **@RestController**: Define que a classe é um controlador REST
- **@Autowired**: Injeta dependências automaticamente (IoC)
- **@Service**: Marca a classe como camada de serviço (lógica de negócio)
- **JpaRepository**: Interface que fornece métodos CRUD prontos
- **HTTP Status Codes**: 200 OK, 201 Created, 204 No Content, 404 Not Found, 400 Bad Request

## 👨‍💻 Autor

**Estudante**: ___________________________________  
**Curso**: Técnico em Desenvolvimento de Sistemas  
**Disciplina**: Programação Orientada a Objetos  
**Professor**: Daniel Saraiva  
**Data de Entrega**: 22/12/2025

---

## 📄 Licença

Projeto acadêmico desenvolvido para fins educacionais.

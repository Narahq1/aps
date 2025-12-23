# Exemplos de Uso da API - Sistema de Gestão Acadêmica

Este documento contém exemplos práticos de como usar todos os endpoints da API.

---

## 🎯 Fluxo Recomendado de Testes

1. Cadastrar Cursos
2. Listar Cursos
3. Matricular Alunos nos Cursos
4. Listar Alunos
5. Atualizar dados de Alunos
6. Testar validações e regras de negócio

---

## 📚 CURSOS - Endpoints

### 1. Cadastrar Novo Curso (POST)

**Endpoint**: `POST http://localhost:8080/api/cursos`

**Headers**:
```
Content-Type: application/json
```

**Body (JSON)**:
```json
{
  "nome": "Técnico em Enfermagem",
  "sigla": "ENF",
  "descricao": "Curso focado em saúde e cuidados assistenciais."
}
```

**Resposta (201 Created)**:
```json
{
  "id": 1,
  "nome": "Técnico em Enfermagem",
  "sigla": "ENF",
  "descricao": "Curso focado em saúde e cuidados assistenciais.",
  "alunos": []
}
```

**cURL**:
```bash
curl -X POST http://localhost:8080/api/cursos \
  -H "Content-Type: application/json" \
  -d "{\"nome\":\"Técnico em Enfermagem\",\"sigla\":\"ENF\",\"descricao\":\"Curso focado em saúde e cuidados assistenciais.\"}"
```

---

### 2. Cadastrar Outro Curso

**Body (JSON)**:
```json
{
  "nome": "Técnico em Informática",
  "sigla": "INF",
  "descricao": "Curso de desenvolvimento de sistemas e redes."
}
```

---

### 3. Listar Todos os Cursos (GET)

**Endpoint**: `GET http://localhost:8080/api/cursos`

**Resposta (200 OK)**:
```json
[
  {
    "id": 1,
    "nome": "Técnico em Enfermagem",
    "sigla": "ENF",
    "descricao": "Curso focado em saúde e cuidados assistenciais.",
    "alunos": []
  },
  {
    "id": 2,
    "nome": "Técnico em Informática",
    "sigla": "INF",
    "descricao": "Curso de desenvolvimento de sistemas e redes.",
    "alunos": []
  }
]
```

**cURL**:
```bash
curl http://localhost:8080/api/cursos
```

---

### 4. Buscar Curso por ID (GET)

**Endpoint**: `GET http://localhost:8080/api/cursos/1`

**Resposta (200 OK)**:
```json
{
  "id": 1,
  "nome": "Técnico em Enfermagem",
  "sigla": "ENF",
  "descricao": "Curso focado em saúde e cuidados assistenciais.",
  "alunos": []
}
```

**Resposta se não encontrar (404 Not Found)**: *(corpo vazio)*

**cURL**:
```bash
curl http://localhost:8080/api/cursos/1
```

---

### 5. Deletar Curso (DELETE)

**Endpoint**: `DELETE http://localhost:8080/api/cursos/1`

**Resposta se tiver alunos (400 Bad Request)**:
```
Não é possível deletar o curso. Existem 2 aluno(s) matriculado(s).
```

**Resposta se não tiver alunos (204 No Content)**: *(corpo vazio)*

**cURL**:
```bash
curl -X DELETE http://localhost:8080/api/cursos/1
```

---

## 👨‍🎓 ALUNOS - Endpoints

### 1. Matricular Novo Aluno (POST)

**Endpoint**: `POST http://localhost:8080/api/alunos`

**Headers**:
```
Content-Type: application/json
```

**Body (JSON)**:
```json
{
  "nome": "João da Silva",
  "matricula": "2024ENF99",
  "email": "joao.silva@aluno.ce.gov.br",
  "curso": {
    "id": 1
  }
}
```

**Resposta (201 Created)**:
```json
{
  "id": 1,
  "nome": "João da Silva",
  "matricula": "2024ENF99",
  "email": "joao.silva@aluno.ce.gov.br",
  "curso": {
    "id": 1,
    "nome": "Técnico em Enfermagem",
    "sigla": "ENF",
    "descricao": "Curso focado em saúde e cuidados assistenciais."
  }
}
```

**cURL**:
```bash
curl -X POST http://localhost:8080/api/alunos \
  -H "Content-Type: application/json" \
  -d "{\"nome\":\"João da Silva\",\"matricula\":\"2024ENF99\",\"email\":\"joao.silva@aluno.ce.gov.br\",\"curso\":{\"id\":1}}"
```

---

### 2. Matricular Mais Alunos

**Aluno 2 - Curso de Enfermagem**:
```json
{
  "nome": "Maria Oliveira",
  "matricula": "2024ENF100",
  "email": "maria.oliveira@aluno.ce.gov.br",
  "curso": {
    "id": 1
  }
}
```

**Aluno 3 - Curso de Informática**:
```json
{
  "nome": "Pedro Santos",
  "matricula": "2024INF01",
  "email": "pedro.santos@aluno.ce.gov.br",
  "curso": {
    "id": 2
  }
}
```

---

### 3. Listar Todos os Alunos (GET)

**Endpoint**: `GET http://localhost:8080/api/alunos`

**Resposta (200 OK)**:
```json
[
  {
    "id": 1,
    "nome": "João da Silva",
    "matricula": "2024ENF99",
    "email": "joao.silva@aluno.ce.gov.br",
    "curso": {
      "id": 1,
      "nome": "Técnico em Enfermagem",
      "sigla": "ENF",
      "descricao": "Curso focado em saúde e cuidados assistenciais."
    }
  },
  {
    "id": 2,
    "nome": "Maria Oliveira",
    "matricula": "2024ENF100",
    "email": "maria.oliveira@aluno.ce.gov.br",
    "curso": {
      "id": 1,
      "nome": "Técnico em Enfermagem",
      "sigla": "ENF",
      "descricao": "Curso focado em saúde e cuidados assistenciais."
    }
  }
]
```

**cURL**:
```bash
curl http://localhost:8080/api/alunos
```

---

### 4. Buscar Aluno por ID (GET)

**Endpoint**: `GET http://localhost:8080/api/alunos/1`

**Resposta (200 OK)**:
```json
{
  "id": 1,
  "nome": "João da Silva",
  "matricula": "2024ENF99",
  "email": "joao.silva@aluno.ce.gov.br",
  "curso": {
    "id": 1,
    "nome": "Técnico em Enfermagem",
    "sigla": "ENF",
    "descricao": "Curso focado em saúde e cuidados assistenciais."
  }
}
```

**cURL**:
```bash
curl http://localhost:8080/api/alunos/1
```

---

### 5. Listar Alunos de um Curso (GET)

**Endpoint**: `GET http://localhost:8080/api/alunos/curso/1`

**Resposta (200 OK)**: Lista apenas alunos do curso com ID 1 (Enfermagem)

**cURL**:
```bash
curl http://localhost:8080/api/alunos/curso/1
```

---

### 6. Atualizar Dados do Aluno (PUT)

**Endpoint**: `PUT http://localhost:8080/api/alunos/1`

**Body (JSON)** - Exemplo: corrigir email:
```json
{
  "nome": "João da Silva",
  "matricula": "2024ENF99",
  "email": "joao.silva.novo@aluno.ce.gov.br",
  "curso": {
    "id": 1
  }
}
```

**Resposta (200 OK)**:
```json
{
  "id": 1,
  "nome": "João da Silva",
  "matricula": "2024ENF99",
  "email": "joao.silva.novo@aluno.ce.gov.br",
  "curso": {
    "id": 1,
    "nome": "Técnico em Enfermagem",
    "sigla": "ENF",
    "descricao": "Curso focado em saúde e cuidados assistenciais."
  }
}
```

**cURL**:
```bash
curl -X PUT http://localhost:8080/api/alunos/1 \
  -H "Content-Type: application/json" \
  -d "{\"nome\":\"João da Silva\",\"matricula\":\"2024ENF99\",\"email\":\"joao.silva.novo@aluno.ce.gov.br\",\"curso\":{\"id\":1}}"
```

---

### 7. Cancelar Matrícula (DELETE)

**Endpoint**: `DELETE http://localhost:8080/api/alunos/1`

**Resposta (204 No Content)**: *(corpo vazio)*

**Resposta se não encontrar (404 Not Found)**: *(corpo vazio)*

**cURL**:
```bash
curl -X DELETE http://localhost:8080/api/alunos/1
```

---

## ⚠️ Testando Validações

### Teste 1: Tentar cadastrar curso com sigla duplicada

**Request**:
```json
{
  "nome": "Outro Curso de Enfermagem",
  "sigla": "ENF",
  "descricao": "Teste"
}
```

**Resposta**: `400 Bad Request` (sigla já existe)

---

### Teste 2: Tentar cadastrar aluno com matrícula duplicada

**Request**:
```json
{
  "nome": "Outro Aluno",
  "matricula": "2024ENF99",
  "email": "outro@aluno.ce.gov.br",
  "curso": {
    "id": 1
  }
}
```

**Resposta**: `400 Bad Request` (matrícula já existe)

---

### Teste 3: Tentar matricular aluno em curso inexistente

**Request**:
```json
{
  "nome": "Aluno Teste",
  "matricula": "2024XXX99",
  "email": "teste@aluno.ce.gov.br",
  "curso": {
    "id": 999
  }
}
```

**Resposta**: `400 Bad Request` (curso não encontrado)

---

### Teste 4: Tentar deletar curso com alunos

**Request**: `DELETE http://localhost:8080/api/cursos/1`

**Resposta**: `400 Bad Request` com mensagem:
```
Não é possível deletar o curso. Existem X aluno(s) matriculado(s).
```

---

## 🔍 Dicas para Testes no Postman/Insomnia

1. **Crie uma Collection** chamada "SGA - API Tests"
2. **Organize por pastas**: "Cursos" e "Alunos"
3. **Salve exemplos** de requisições bem-sucedidas e com erro
4. **Use variáveis** para o base URL: `{{base_url}}/api/cursos`
5. **Teste a ordem**: Primeiro cursos, depois alunos

---

## 📊 Acessando o Console H2

1. Acesse: `http://localhost:8080/h2-console`
2. Configure:
   - **JDBC URL**: `jdbc:h2:mem:sga_db`
   - **Username**: `sa`
   - **Password**: *(vazio)*
3. Clique em "Connect"
4. Execute queries SQL:
   ```sql
   SELECT * FROM CURSOS;
   SELECT * FROM ALUNOS;
   SELECT * FROM ALUNOS WHERE CURSO_ID = 1;
   ```

---

**Boa sorte nos testes! 🚀**

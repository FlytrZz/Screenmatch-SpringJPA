# 🎬 Screenmatch

Aplicação de linha de comando em **Java + Spring Boot** que consome a API pública **OMDb** para buscar séries e seus episódios, traduz automaticamente as sinopses para português (via API **MyMemory**) e persiste tudo em um banco **PostgreSQL** com Spring Data JPA.

Projeto desenvolvido como parte dos estudos da trilha **Java Backend** na Alura.

## ✨ Funcionalidades

- Buscar uma série pelo nome na API do OMDb e salvar no banco
- Buscar e salvar todos os episódios de todas as temporadas de uma série já cadastrada
- Listar todas as séries buscadas, ordenadas por gênero
- Buscar série por título
- Buscar séries por nome de ator, filtrando por avaliação mínima
- Buscar o Top 5 séries mais bem avaliadas
- Buscar séries por categoria/gênero
- Buscar séries "custo-benefício" (poucas temporadas e boa avaliação)
- Tradução automática da sinopse (inglês → português) ao salvar uma série

## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot 3.1.1
- Spring Data JPA
- PostgreSQL
- Jackson (parsing de JSON)
- [API OMDb](https://www.omdbapi.com/) — dados de séries e episódios
- [API MyMemory](https://mymemory.translated.net/) — tradução de texto
- Maven

## 📋 Pré-requisitos

- JDK 17 instalado
- PostgreSQL instalado e rodando

## ⚙️ Configuração

As credenciais do banco de dados são lidas a partir de **variáveis de ambiente**:

| Variável | Descrição |
|---|---|
| `DB_HOST` | Host do banco (ex: `localhost`) |
| `DB_NAME` | Nome do banco de dados |
| `DB_USER` | Usuário do PostgreSQL |
| `DB_PASSWORD` | Senha do PostgreSQL |

### Exemplo (PowerShell)

```powershell
$env:DB_HOST = "localhost"
$env:DB_NAME = "screenmatch"
$env:DB_USER = "postgres"
$env:DB_PASSWORD = "sua_senha"
```

> No Eclipse, configure essas mesmas variáveis em **Run Configurations → Environment** antes de rodar a aplicação.

O Hibernate está configurado com `ddl-auto=update`, ou seja, a tabela `series` (e a tabela de episódios relacionada) é criada/atualizada automaticamente na primeira execução. O `spring.jpa.show-sql` está habilitado, então as queries geradas pelo Hibernate aparecem no console — útil para acompanhar o que está sendo executado no banco.

> ⚠️ **Nota:** a chave da API do OMDb usada na busca de séries está fixa no código-fonte (`Principal.java`). Para um projeto pessoal ou de produção, o ideal é mover essa chave também para uma variável de ambiente, do mesmo jeito que as credenciais do banco.

## ▶️ Como rodar

Clone o repositório:

```bash
git clone https://github.com/FlytrZz/Screenmatch-SpringJPA.git
cd Screenmatch-SpringJPA
```

Rode com o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Ou, no Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

## 📁 Estrutura do projeto

```
screenmatch
├── model
│   ├── Categoria.java
│   ├── DadosEpisodio.java
│   ├── DadosSerie.java
│   ├── DadosTemporada.java
│   ├── Episodio.java
│   └── Serie.java
├── principal
│   └── Principal.java
├── repository
│   └── SerieRepository.java
└── service
    ├── ConsumoApi.java
    ├── ConverteDados.java
    ├── IConverteDados.java
    └── tradução
        ├── ConsultaMyMemory.java
        ├── DadosResposta.java
        └── DadosTraducao.java
```

## 🗂️ Modelo de dados

- **Serie** → possui vários **Episodio** (`@OneToMany`, cascade `ALL`)
- O campo `gênero` é mapeado como `@Enumerated(EnumType.STRING)`, usando o enum `Categoria`, que faz a conversão entre os nomes de gênero em inglês (retornados pela OMDb) e em português
- Campos longos como `atores` e `sinopse` são mapeados com `columnDefinition = "TEXT"`, evitando limite de tamanho da coluna

## 👤 Autor

Desenvolvido por [FlytrZz](https://github.com/FlytrZz).

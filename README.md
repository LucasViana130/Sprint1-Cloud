# ☁️ PetJourney Cloud

Projeto desenvolvido para a disciplina de Cloud Computing utilizando Java Spring Boot, Docker e Microsoft Azure.

---

# 👨‍🎓 Integrantes

| Nome                        | RM       |
| --------------------------- | -------- |
| Deryk de Souza Queiroz      | RM563412 |
| Lucas Gonçalves Viana       | RM563254 |
| Vinicius Paschoeto da Silva | RM563089 |

---

# 📌 Sobre o Projeto

O **PetJourney Cloud** é uma API REST responsável pelo gerenciamento do módulo clínico do sistema PetJourney.

A aplicação foi containerizada utilizando Docker e publicada em uma máquina virtual Linux hospedada na Microsoft Azure.

---

# 👨‍💻 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring HATEOAS
- Maven
- Docker
- Docker Compose
- Microsoft Azure
- H2 Database
- Swagger OpenAPI

---

# ☁️ Infraestrutura Cloud

A aplicação foi publicada em:

| Serviço | Descrição |
|---|---|
| Cloud Provider | Microsoft Azure |
| VM | Linux AlmaLinux 10 |
| Containerização | Docker |
| Orquestração | Docker Compose |
| Banco de Dados | H2 |
| Persistência | Docker Volume |

---

# 🏗️ Arquitetura da Solução

## Fluxo da aplicação

1. Usuário acessa Swagger
2. Requisição chega na VM Azure
3. Docker executa a API Spring Boot
4. API processa os endpoints REST
5. Dados são persistidos em volume Docker
6. Banco H2 armazena os dados

---

# 📂 Estrutura do Projeto

```bash
Sprint1-Cloud
│
├── src/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── DADOS_TESTE.sql
├── arquitetura.drawio
├── arquitetura.png
├── scripts/
└── README.md
````

---

# 🐳 Dockerfile

O projeto utiliza um Dockerfile baseado em Java 17.

## Principais funções:

* Criação da imagem Docker
* Execução da API Spring Boot
* Utilização de usuário não-root
* Persistência de dados

---

# 🐳 Docker Compose

O Docker Compose realiza:

* Build automático da aplicação
* Exposição da porta 8080
* Criação do container da API
* Persistência via volume Docker

---

# 🚀 Como Executar Localmente

## 1) Clonar repositório

```bash
git clone https://github.com/LucasViana130/Sprint1-Cloud.git
```

---

## 2) Entrar na pasta

```bash
cd Sprint1-Cloud
```

---

## 3) Gerar build da aplicação

```bash
mvn clean package
```

---

## 4) Executar containers

```bash
docker compose up -d --build
```

---

## 5) Verificar containers

```bash
docker ps
```

---

# 📚 Swagger/OpenAPI

A documentação da API pode ser acessada em:

```bash
http://IP_DA_VM:8080/swagger-ui/index.html
```

Exemplo local:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# 🧪 Funcionalidades da API

## Clínica e Veterinários

* Crud completo

## Tutor

* Apenas GET de tutores

## Pet

* Apenas GET de pets

## Agendamento e Prontuário

* CRUD de compromissos e de Prontuários

---

# 📄 Exemplo de JSON

## Criar Clínica

```json
{
  "name": "Clínica Veterinária Pet Feliz",
  "cnpj": "11222333000181",
  "phone": "11987654321",
  "email": "contato@petfeliz.com.br",
  "address": "Rua das Flores, 123 - São Paulo/SP"
}
```

---

# 💾 Persistência dos Dados

A aplicação utiliza Docker Volume para persistência do banco H2.

## Volume criado

```bash
h2-data
```

---

# 🖥️ Scripts Azure CLI

O projeto contém scripts automatizados para:

* Reconhecimento de ambiente Azure
* Deploy da VM
* Configuração Docker
* Monitoramento
* Remoção da infraestrutura

---

# 📸 Evidências

O projeto possui evidências completas contendo:

* Criação da VM
* Configuração de rede
* Deploy Docker
* Containers em execução
* Swagger funcionando
* Testes da API
* Persistência do banco
* Remoção da infraestrutura

---

# 🎥 Vídeo Demonstrativo

Adicionar link do vídeo após upload no YouTube.

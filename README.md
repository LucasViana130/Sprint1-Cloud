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

# 💼 Benefícios para o Negócio

A solução PetJourney oferece diversos benefícios para ambientes corporativos e aplicações modernas em nuvem.

## ✅ Escalabilidade

A utilização da Microsoft Azure permite aumentar recursos computacionais conforme a demanda da aplicação.

---

## ✅ Portabilidade

A aplicação utiliza Docker, permitindo execução consistente em diferentes ambientes:
- desenvolvimento
- homologação
- produção

---

## ✅ Persistência de Dados

O uso de volumes Docker garante que os dados permaneçam armazenados mesmo após reinicialização dos containers.

---

## ✅ Automação

Os scripts Azure CLI automatizam:
- criação da infraestrutura
- configuração da VM
- deploy da aplicação
- monitoramento
- remoção dos recursos

Isso reduz tempo operacional e erros manuais.

---

## ✅ Facilidade de Deploy

Com Docker Compose, toda a aplicação pode ser executada utilizando poucos comandos.

---

## ✅ Monitoramento Simplificado

A Azure fornece monitoramento integrado da infraestrutura:
- uso de CPU
- memória
- rede
- status da VM

---

## ✅ Segurança

A aplicação é executada dentro do container com usuário sem privilégios administrativos:

```dockerfile
USER springuser
```

Isso reduz riscos de segurança em ambientes produtivos.

---

# 👨‍💻 Tecnologias Utilizadas

- Java 21
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

O projeto utiliza um Dockerfile baseado em Java 21.

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

# 📘 How To Run

Este projeto demonstra o deploy de uma aplicação Java Spring Boot utilizando Docker e Microsoft Azure Cloud.

A solução executa em uma Máquina Virtual Linux hospedada na Azure, utilizando Docker Compose para orquestração da aplicação.

---

# ⚙️ Instalação da Solução

## Pré-requisitos

Antes de iniciar, é necessário possuir:

- Conta Microsoft Azure
- Azure Cloud Shell habilitado
- Git instalado
- Docker e Docker Compose
- Acesso ao GitHub

---

## 1) Clonar o repositório

No Azure Cloud Shell:

```bash
git clone https://github.com/LucasViana130/Sprint1-Cloud.git
cd Sprint1-Cloud
```

---

## 2) Entrar na pasta de scripts

```bash
cd azure-cli-scripts-petjourney
```

---

## 3) Dar permissão aos scripts

```bash
chmod +x *.sh
```

---

## 4) Executar reconhecimento da infraestrutura

```bash
bash ./01-reconhecimento.sh
```

Esse script:
- lista regiões Azure
- verifica políticas
- mostra tamanhos de VM disponíveis
- valida infraestrutura disponível

---

## 5) Executar deploy da infraestrutura

```bash
bash ./02-deploy-petjourney.sh
```

Esse script cria:
- Resource Group
- Máquina Virtual Linux
- IP Público
- Security Group
- Regras de rede
- Porta 22 (SSH)
- Porta 8080 (API)

---

## 6) Conectar na VM

```bash
ssh petjourney@IP_DA_VM
```

---

## 7) Executar configuração Docker

Dentro da VM:

```bash
cd Sprint1-Cloud
bash ./azure-cli-scripts-petjourney/03-configurar-vm-docker.sh
```

Esse script:
- instala Docker
- instala Docker Compose
- instala Git
- configura serviços
- executa containers
- realiza build automático

---

## 8) Validar containers

```bash
sudo docker ps
```

---

## 9) Acessar Swagger

No navegador:

```text
http://IP_DA_VM:8080/swagger-ui/index.html
```

---

## 10) Executar monitoramento

No Azure Cloud Shell:

```bash
bash ./04-monitoramento.sh
```

---

## 11) Remover infraestrutura

```bash
bash ./05-cleanup-petjourney.sh
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

https://www.youtube.com/watch?v=rRf5zXidzAE

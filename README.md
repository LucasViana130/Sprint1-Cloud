PetJourney Cloud ☁️🐾
📌 Sobre o Projeto

O PetJourney Cloud é uma API REST desenvolvida com Java 21 + Spring Boot, criada para gerenciamento do módulo clínico do sistema PetJourney.

O projeto foi preparado para execução em ambiente cloud utilizando:

Docker
Docker Compose
Máquina Virtual Microsoft Azure
Swagger/OpenAPI
Persistência de dados
Deploy em infraestrutura Linux
👨‍💻 Tecnologias Utilizadas
Java 21
Spring Boot 3
Spring Data JPA
Spring HATEOAS
Maven
H2 Database
Docker
Docker Compose
Microsoft Azure
Swagger OpenAPI
☁️ Arquitetura da Solução
Infraestrutura

A aplicação foi publicada em uma máquina virtual Linux hospedada no Microsoft Azure.

Componentes utilizados:
Máquina Virtual Linux (Azure VM)
Docker Engine
Docker Compose
API Spring Boot
Volume Docker para persistência
Banco H2 persistente
🏗️ Diagrama da Arquitetura

O projeto contém:

arquitetura.drawio
arquitetura.png

Representando:

Usuário acessando Swagger
Azure VM
Docker
API Spring Boot
Volume persistente
Banco H2
📂 Estrutura do Projeto
Sprint1-Cloud
│
├── src/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── DADOS_TESTE.sql
├── arquitetura.drawio
├── arquitetura.png
🐳 Dockerfile

O projeto utiliza um Dockerfile baseado em:

FROM eclipse-temurin:21-jdk

Funções:

Criação da imagem da API
Execução isolada da aplicação
Configuração do usuário não-root
Execução do JAR Spring Boot
🐳 Docker Compose

O docker-compose.yml realiza:

Build automático da aplicação
Criação do container da API
Exposição da porta 8080
Criação de volume persistente
☁️ Deploy Azure
Máquina Virtual
Configuração	Valor
Cloud Provider	Microsoft Azure
Sistema Operacional	AlmaLinux 10
Região	Canada Central
VM Size	Standard_B2ats_v2
Porta liberada	8080
Acesso remoto	SSH
🔓 Configuração de Rede

Foi criada uma regra no NSG (Network Security Group) liberando:

Porta	Protocolo
8080	TCP

Permitindo acesso externo ao Swagger e à API.

🚀 Como Executar Localmente
1. Clonar repositório
git clone https://github.com/LucasViana130/Sprint1-Cloud.git
2. Entrar na pasta
cd Sprint1-Cloud
3. Executar containers
docker compose up -d --build
4. Verificar containers
docker ps
📚 Swagger/OpenAPI

A documentação da API pode ser acessada em:

http://IP_DA_VM:8080/swagger-ui/index.html

Exemplo:

http://4.206.208.16:8080/swagger-ui/index.html
📌 Funcionalidades da API
Clínica
Criar clínica
Buscar clínica por ID
Listar clínicas
Atualizar clínica
Excluir clínica
Tutor
CRUD de tutores
Pet
CRUD de pets
Agendamento
CRUD de compromissos
📄 Exemplo de JSON
Criar Clínica
{
  "name": "Clínica Veterinária Pet Feliz",
  "cnpj": "11222333000181",
  "phone": "11987654321",
  "email": "contato@petfeliz.com.br",
  "address": "Rua das Flores, 123 - São Paulo/SP"
}
🧪 Testes Realizados
Testes no Swagger

Foram realizados testes de:

POST
GET
PUT
DELETE

Todos executados diretamente pela interface Swagger publicada na Azure VM.

📸 Evidências

O projeto contém evidências completas da execução:

Criação da VM
Configuração da rede
Clone do projeto
Instalação Docker
Build da aplicação
Containers em execução
Swagger funcionando
Testes da API

Arquivo:

Evidencias_PetJourney_Cloud.docx
💾 Persistência dos Dados

A aplicação utiliza:

Docker Volume

Para persistência do banco H2.

Volume criado:

h2-data
🔐 Segurança Aplicada
Usuário não-root no container Docker
Containers isolados
Porta específica liberada
VM Linux dedicada
📦 Build da Aplicação

Gerar JAR:

mvn clean package
🖥️ Comandos Utilizados na VM
Instalação Docker
sudo dnf install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y
Inicializar Docker
sudo systemctl enable docker
sudo systemctl start docker
Clone do Projeto
git clone https://github.com/LucasViana130/Sprint1-Cloud.git
Executar aplicação
sudo docker compose up -d --build

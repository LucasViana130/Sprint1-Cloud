# Scripts Azure CLI - PetJourney Cloud

Esta pasta contém os scripts usados na entrega de Cloud Computing.

## Ordem correta de execução

### 01 - Reconhecimento

Execute no Azure Cloud Shell:

```bash
chmod +x azure-cli-scripts-petjourney/*.sh
cd azure-cli-scripts-petjourney
./01-reconhecimento.sh
```

### 02 - Deploy da infraestrutura

Execute no Azure Cloud Shell:

```bash
./02-deploy-petjourney.sh
```

Esse script cria:
- Resource Group
- VM Linux AlmaLinux
- IP público
- regra SSH 22
- regra 8080 para Swagger/API

### 03 - Configuração da VM

Após criar a VM, conecte por SSH:

```bash
ssh petjourney@IP_PUBLICO
```

Dentro da VM, clone o repositório, entre na pasta dos scripts e execute:

```bash
chmod +x azure-cli-scripts-petjourney/*.sh
./azure-cli-scripts-petjourney/03-configurar-vm-docker.sh
```

Esse script instala:
- Git
- Docker CE
- Docker Compose Plugin

E também:
- clona/atualiza o projeto
- executa `sudo docker compose up -d --build`
- mostra containers, volumes e logs

### 04 - Monitoramento

Execute no Azure Cloud Shell:

```bash
./04-monitoramento.sh
```

### 05 - Remoção dos recursos

Execute no Azure Cloud Shell, não dentro da VM:

```bash
./05-cleanup-petjourney.sh
```

Digite `EXCLUIR` quando solicitado.

## Observação sobre `sudo`

Na VM Linux os comandos Docker podem exigir `sudo`. Isso é normal, pois o usuário da VM não está necessariamente no grupo `docker`.

O requisito de "usuário sem privilégios administrativos" é atendido dentro do container pelo Dockerfile, que usa:

```dockerfile
RUN useradd -m springuser
USER springuser
```

Pode ser validado com:

```bash
sudo docker exec sprint1-cloud-api whoami
```

O resultado esperado é:

```text
springuser
```

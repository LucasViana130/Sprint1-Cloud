#!/bin/bash
set -e

echo "=============================================="
echo "03 - CONFIGURAÇÃO DA VM + DOCKER + APLICAÇÃO"
echo "=============================================="
echo ""

echo "Este script deve ser executado DENTRO da VM, após conectar via SSH."
echo "Exemplo: ssh petjourney@IP_PUBLICO"
echo ""

REPO_URL="https://github.com/LucasViana130/Sprint1-Cloud.git"
PROJECT_DIR="Sprint1-Cloud"

echo "1) Informações da VM:"
whoami
hostname
cat /etc/os-release | head -n 5
echo ""

echo "2) Atualizando pacotes..."
sudo dnf update -y

echo ""
echo "3) Instalando dependências básicas..."
sudo dnf install -y dnf-plugins-core git curl

echo ""
echo "4) Adicionando repositório oficial do Docker CE..."
sudo dnf config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

echo ""
echo "5) Instalando Docker CE, Docker CLI, Buildx e Docker Compose Plugin..."
sudo dnf install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

echo ""
echo "6) Habilitando e iniciando o Docker..."
sudo systemctl enable docker
sudo systemctl start docker

echo ""
echo "7) Verificando versões..."
sudo docker --version
sudo docker compose version

echo ""
echo "8) Clonando ou atualizando o projeto..."
if [ -d "$PROJECT_DIR" ]; then
  echo "Projeto já existe. Atualizando..."
  cd "$PROJECT_DIR"
  git pull
else
  git clone "$REPO_URL"
  cd "$PROJECT_DIR"
fi

echo ""
echo "9) Conferindo arquivos principais..."
ls -la
echo ""

echo "10) Subindo aplicação em background com Docker Compose..."
sudo docker compose up -d --build

echo ""
echo "11) Containers em execução:"
sudo docker ps

echo ""
echo "12) Volumes Docker:"
sudo docker volume ls

echo ""
echo "13) Logs da aplicação:"
sudo docker logs sprint1-cloud-api --tail 120

echo ""
echo "14) Validando usuário sem privilégio administrativo dentro do container:"
sudo docker exec sprint1-cloud-api whoami || true

echo ""
echo "Configuração concluída."
echo "Acesse no navegador:"
echo "http://IP_PUBLICO_DA_VM:8080/swagger-ui/index.html"

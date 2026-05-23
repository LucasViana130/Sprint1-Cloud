#!/bin/bash

echo "=========================================="
echo " PetJourney Cloud - Configuração da VM"
echo "=========================================="
echo ""

echo "1) Atualizando pacotes..."
sudo dnf update -y

echo ""
echo "2) Instalando dependências..."
sudo dnf install -y dnf-plugins-core git

echo ""
echo "3) Adicionando repositório oficial do Docker..."
sudo dnf config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

echo ""
echo "4) Instalando Docker e Docker Compose Plugin..."
sudo dnf install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

echo ""
echo "5) Iniciando Docker..."
sudo systemctl enable docker
sudo systemctl start docker

echo ""
echo "6) Versões instaladas..."
sudo docker --version
sudo docker compose version

echo ""
echo "7) Clonando projeto..."
if [ ! -d "Sprint1-Cloud" ]; then
  git clone https://github.com/LucasViana130/Sprint1-Cloud.git
fi

cd Sprint1-Cloud || exit 1

echo ""
echo "8) Subindo aplicação com Docker Compose..."
sudo docker compose up -d --build

echo ""
echo "9) Containers em execução..."
sudo docker ps

echo ""
echo "10) Logs da aplicação..."
sudo docker logs sprint1-cloud-api --tail 80

echo ""
echo "Configuração da VM finalizada."
echo "Acesse: http://IP_PUBLICO_DA_VM:8080/swagger-ui/index.html"

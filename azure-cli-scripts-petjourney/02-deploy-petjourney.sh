#!/bin/bash

echo "=========================================="
echo " PetJourney Cloud - Deploy Azure VM"
echo "=========================================="
echo ""

RESOURCE_GROUP="rg-petjourney"
LOCATION="canadacentral"
VM_NAME="vm-petjourney"
VM_SIZE="Standard_B2ats_v2"
IMAGE="almalinux:almalinux-x86_64:10-gen2:latest"
ADMIN_USER="petjourney"

echo "Este script cria a infraestrutura Azure para o projeto PetJourney."
echo "Resource Group: $RESOURCE_GROUP"
echo "Região: $LOCATION"
echo "VM: $VM_NAME"
echo "Tamanho: $VM_SIZE"
echo "Imagem: AlmaLinux OS 10 x64 Gen2"
echo ""

read -s -p "Digite a senha do usuário administrador da VM: " ADMIN_PASSWORD
echo ""
read -s -p "Confirme a senha: " ADMIN_PASSWORD_CONFIRM
echo ""

if [ "$ADMIN_PASSWORD" != "$ADMIN_PASSWORD_CONFIRM" ]; then
  echo "Erro: as senhas não conferem."
  exit 1
fi

echo "1) Criando Resource Group..."
az group create \
  --name "$RESOURCE_GROUP" \
  --location "$LOCATION"

echo ""
echo "2) Criando VM..."
az vm create \
  --resource-group "$RESOURCE_GROUP" \
  --name "$VM_NAME" \
  --image "$IMAGE" \
  --size "$VM_SIZE" \
  --authentication-type password \
  --admin-username "$ADMIN_USER" \
  --admin-password "$ADMIN_PASSWORD" \
  --public-ip-sku Standard \
  --tags owner=PetJourney purpose=CloudComputing

echo ""
echo "3) Abrindo porta SSH 22..."
az vm open-port \
  --resource-group "$RESOURCE_GROUP" \
  --name "$VM_NAME" \
  --port 22 \
  --priority 300

echo ""
echo "4) Abrindo porta da API 8080..."
az vm open-port \
  --resource-group "$RESOURCE_GROUP" \
  --name "$VM_NAME" \
  --port 8080 \
  --priority 310

echo ""
echo "5) Exibindo IP público da VM..."
az vm list-ip-addresses \
  --resource-group "$RESOURCE_GROUP" \
  --name "$VM_NAME" \
  -o table

echo ""
echo "Deploy da infraestrutura finalizado."
echo "Próximo passo: conectar via SSH e executar o script 03 dentro da VM."
echo "Exemplo: ssh petjourney@IP_PUBLICO"

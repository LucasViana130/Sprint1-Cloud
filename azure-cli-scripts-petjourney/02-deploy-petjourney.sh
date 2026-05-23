#!/bin/bash
set -e

echo "=============================================="
echo "02 - DEPLOY DA INFRAESTRUTURA AZURE"
echo "=============================================="
echo ""

RESOURCE_GROUP="rg-petjourney"
LOCATION="canadacentral"
VM_NAME="vm-petjourney"
VM_SIZE="Standard_B2ats_v2"
ADMIN_USER="petjourney"
IMAGE="almalinux:almalinux-x86_64:10-gen2:latest"

echo "Configuração:"
echo "Resource Group: $RESOURCE_GROUP"
echo "Região: $LOCATION"
echo "VM: $VM_NAME"
echo "Size: $VM_SIZE"
echo "Usuário: $ADMIN_USER"
echo "Imagem: AlmaLinux OS 10 x64 Gen2"
echo ""

echo "ATENÇÃO: execute este script no Azure Cloud Shell ou em um terminal com Azure CLI logado."
echo ""

read -s -p "Digite a senha do usuário da VM: " ADMIN_PASSWORD
echo ""
read -s -p "Confirme a senha: " ADMIN_PASSWORD_CONFIRM
echo ""

if [ "$ADMIN_PASSWORD" != "$ADMIN_PASSWORD_CONFIRM" ]; then
  echo "Erro: as senhas não conferem."
  exit 1
fi

echo ""
echo "1) Criando Resource Group..."
az group create \
  --name "$RESOURCE_GROUP" \
  --location "$LOCATION" \
  -o table

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
  --tags projeto=PetJourney disciplina=CloudComputing \
  -o table

echo ""
echo "3) Abrindo porta SSH 22..."
az vm open-port \
  --resource-group "$RESOURCE_GROUP" \
  --name "$VM_NAME" \
  --port 22 \
  --priority 300 \
  -o table || true

echo ""
echo "4) Abrindo porta da API 8080..."
az vm open-port \
  --resource-group "$RESOURCE_GROUP" \
  --name "$VM_NAME" \
  --port 8080 \
  --priority 310 \
  -o table

echo ""
echo "5) Dados da VM:"
az vm list -d \
  --resource-group "$RESOURCE_GROUP" \
  -o table

echo ""
echo "6) IP público:"
az vm list-ip-addresses \
  --resource-group "$RESOURCE_GROUP" \
  --name "$VM_NAME" \
  -o table

PUBLIC_IP=$(az vm list-ip-addresses --resource-group "$RESOURCE_GROUP" --name "$VM_NAME" --query "[0].virtualMachine.network.publicIpAddresses[0].ipAddress" -o tsv)

echo ""
echo "Infraestrutura criada com sucesso."
echo "Conecte na VM com:"
echo "ssh $ADMIN_USER@$PUBLIC_IP"
echo ""
echo "Depois execute dentro da VM o script:"
echo "./03-configurar-vm-docker.sh"

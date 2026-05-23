#!/bin/bash
set -e

echo "=============================================="
echo "04 - MONITORAMENTO DA INFRAESTRUTURA AZURE"
echo "=============================================="
echo ""

RESOURCE_GROUP="rg-petjourney"
VM_NAME="vm-petjourney"

echo "ATENÇÃO: execute este script no Azure Cloud Shell ou em terminal com Azure CLI logado."
echo ""

echo "1) Status da VM:"
az vm list -d \
  --resource-group "$RESOURCE_GROUP" \
  -o table

echo ""
echo "2) IP público:"
az vm list-ip-addresses \
  --resource-group "$RESOURCE_GROUP" \
  --name "$VM_NAME" \
  -o table

echo ""
echo "3) Regras de rede do NSG:"
NSG_NAME=$(az network nsg list --resource-group "$RESOURCE_GROUP" --query "[0].name" -o tsv)
echo "NSG encontrado: $NSG_NAME"
az network nsg rule list \
  --resource-group "$RESOURCE_GROUP" \
  --nsg-name "$NSG_NAME" \
  -o table

echo ""
echo "4) Métrica de CPU da VM:"
VM_ID=$(az vm show -g "$RESOURCE_GROUP" -n "$VM_NAME" --query id -o tsv)
az monitor metrics list \
  --resource "$VM_ID" \
  --metric "Percentage CPU" \
  --aggregation Average \
  --interval PT1M \
  -o table || true

echo ""
echo "Monitoramento finalizado."

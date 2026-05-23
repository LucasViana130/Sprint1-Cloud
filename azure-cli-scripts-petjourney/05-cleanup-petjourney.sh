#!/bin/bash
set -e

echo "=============================================="
echo "05 - REMOÇÃO DOS RECURSOS AZURE"
echo "=============================================="
echo ""

RESOURCE_GROUP="rg-petjourney"

echo "ATENÇÃO: execute este script no Azure Cloud Shell ou em terminal com Azure CLI logado."
echo "NÃO execute dentro da VM via SSH."
echo ""

echo "Este script remove o Resource Group e todos os recursos criados:"
echo "- VM"
echo "- Disco"
echo "- IP público"
echo "- NSG"
echo "- Rede"
echo ""

read -p "Digite EXCLUIR para confirmar a remoção: " CONFIRM

if [ "$CONFIRM" != "EXCLUIR" ]; then
  echo "Remoção cancelada."
  exit 0
fi

echo ""
echo "Solicitando remoção do Resource Group..."
az group delete \
  --name "$RESOURCE_GROUP" \
  --yes \
  --no-wait

echo ""
echo "Remoção solicitada."
echo "Verifique depois com:"
echo "az group list -o table"

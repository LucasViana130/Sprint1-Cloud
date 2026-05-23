#!/bin/bash

RESOURCE_GROUP="rg-petjourney"

echo "=========================================="
echo " PetJourney Cloud - Remoção dos Recursos"
echo "=========================================="
echo ""

echo "ATENÇÃO: este script remove o Resource Group e todos os recursos da Azure."
read -p "Digite EXCLUIR para confirmar: " CONFIRM

if [ "$CONFIRM" != "EXCLUIR" ]; then
  echo "Operação cancelada."
  exit 0
fi

az group delete \
  --name "$RESOURCE_GROUP" \
  --yes \
  --no-wait

echo "Remoção solicitada. Verifique depois com: az group list -o table"

#!/bin/bash
set -e

echo "=============================================="
echo "01 - RECONHECIMENTO DO AMBIENTE AZURE"
echo "=============================================="
echo ""

echo "1) Conta logada:"
az account show --query "{Assinatura:name, Usuario:user.name, Estado:state}" -o table
echo ""

echo "2) Resource Groups existentes:"
az group list -o table
echo ""

echo "3) Regiões disponíveis na assinatura:"
az account list-locations --query "[].[regionalDisplayName,name]" -o table
echo ""

echo "4) Regiões permitidas pela policy, quando houver policy aplicada:"
POLICIES=$(az policy assignment list --query "[].[parameters, properties.parameters][].*.value[] | []" -o tsv || true)

if [ -n "$POLICIES" ]; then
  az account list-locations --query "[].[regionalDisplayName,name]" -o tsv | grep -iwFf <(echo "$POLICIES") || true
else
  echo "Nenhuma policy de região encontrada ou sem retorno pela consulta."
fi

echo ""
echo "5) Tamanhos pequenos testados para Canada Central:"
az vm list-sizes --location canadacentral -o table | grep -E "Standard_B1ms|Standard_B1s|Standard_B2s|Standard_B2ats_v2|Standard_A1_v2|Standard_D2s_v3" || true

echo ""
echo "Reconhecimento finalizado."

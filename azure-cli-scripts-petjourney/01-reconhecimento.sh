#!/bin/bash

echo "=========================================="
echo " PetJourney Cloud - Reconhecimento Azure"
echo "=========================================="
echo ""

echo "1) Verificando versão do Azure CLI..."
az --version
echo ""

echo "2) Exibindo conta atual..."
az account show --query "{Assinatura:name, Usuario:user.name, Estado:state}" -o table
echo ""

echo "3) Listando assinaturas disponíveis..."
az account list -o table
echo ""

echo "4) Listando regiões disponíveis..."
az account list-locations --query "[].[regionalDisplayName,name]" -o table
echo ""

echo "5) Listando regiões permitidas por políticas, quando houver políticas configuradas..."
az account list-locations --query "[].[regionalDisplayName,name]" -o tsv | grep -iwFf <(az policy assignment list --query "[].[parameters, properties.parameters][].*.value[] | []" -o tsv) || true
echo ""

echo "6) Listando tamanhos pequenos de VM na região canada central..."
az vm list-sizes --location canadacentral -o table | grep -E "B1ms|B1s|B2s|B2ats_v2|A1_v2|D2s_v3" || true
echo ""

echo "Reconhecimento finalizado."

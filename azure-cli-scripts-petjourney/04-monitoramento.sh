#!/bin/bash

RESOURCE_GROUP="rg-petjourney"
VM_NAME="vm-petjourney"

echo "=========================================="
echo " PetJourney Cloud - Monitoramento Azure"
echo "=========================================="
echo ""

echo "1) Status da VM:"
az vm get-instance-view \
  --resource-group "$RESOURCE_GROUP" \
  --name "$VM_NAME" \
  --query "instanceView.statuses[].[displayStatus]" \
  -o table

echo ""
echo "2) IP público:"
az vm list-ip-addresses \
  --resource-group "$RESOURCE_GROUP" \
  --name "$VM_NAME" \
  -o table

echo ""
echo "3) Métrica de CPU:"
az monitor metrics list \
  --resource "$(az vm show -g "$RESOURCE_GROUP" -n "$VM_NAME" --query id -o tsv)" \
  --metric "Percentage CPU" \
  --aggregation Average \
  --interval PT1M \
  -o table

echo ""
echo "Monitoramento finalizado."

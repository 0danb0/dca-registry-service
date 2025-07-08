#!/bin/bash

# Endpoints per LocalStack
ENDPOINT=http://localhost:4566
REGION=us-east-1

# Nome delle risorse
DYNAMO_TABLE=registry

echo "✅ Creazione tabella DynamoDB: $DYNAMO_TABLE"

aws --endpoint-url=$ENDPOINT dynamodb create-table \
  --table-name $DYNAMO_TABLE \
  --attribute-definitions \
    AttributeName=pk,AttributeType=S \
    AttributeName=sk,AttributeType=S \
  --key-schema \
    AttributeName=pk,KeyType=HASH \
    AttributeName=sk,KeyType=RANGE \
  --billing-mode PAY_PER_REQUEST \
  --region $REGION

echo "✅ Operazione completata"

read -n 1 -s -r -p "Premi un tasto per chiudere..."
echo
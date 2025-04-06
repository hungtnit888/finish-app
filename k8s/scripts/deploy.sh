#!/bin/bash

# Check if environment is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <environment>"
    echo "Environments: dev, staging, prod"
    exit 1
fi

ENV=$1
NAMESPACE="finish-app"

# Function to check if a command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Check prerequisites
if ! command_exists kubectl; then
    echo "kubectl is not installed. Please install it first."
    exit 1
fi

if ! command_exists helm; then
    echo "helm is not installed. Please install it first."
    exit 1
fi

# Create namespaces if they don't exist
kubectl apply -f k8s/namespaces.yaml

# Deploy environment-specific configuration
echo "Deploying $ENV environment configuration..."
kubectl apply -f k8s/env/$ENV/

# Deploy databases
echo "Deploying databases..."
kubectl apply -f k8s/databases/ -n $NAMESPACE

# Wait for databases to be ready
echo "Waiting for databases to be ready..."
kubectl wait --for=condition=ready pod -l app=postgres -n $NAMESPACE --timeout=300s
kubectl wait --for=condition=ready pod -l app=mongodb -n $NAMESPACE --timeout=300s
kubectl wait --for=condition=ready pod -l app=redis -n $NAMESPACE --timeout=300s

# Deploy monitoring stack
echo "Deploying monitoring stack..."
kubectl apply -f k8s/monitoring/ -n monitoring

# Deploy application
echo "Deploying application..."
kubectl apply -f k8s/app/ -n $NAMESPACE

# Configure ingress
echo "Configuring ingress..."
kubectl apply -f k8s/ingress/ -n $NAMESPACE

echo "Deployment completed!"
echo "To check the status of your deployment, run:"
echo "kubectl get all -n $NAMESPACE"
echo "kubectl get ingress -n $NAMESPACE" 
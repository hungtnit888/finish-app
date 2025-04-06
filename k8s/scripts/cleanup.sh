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

# Delete application resources
echo "Deleting application resources..."
kubectl delete -f k8s/app/ -n $NAMESPACE

# Delete databases
echo "Deleting databases..."
kubectl delete -f k8s/databases/ -n $NAMESPACE

# Delete monitoring stack
echo "Deleting monitoring stack..."
kubectl delete -f k8s/monitoring/ -n monitoring

# Delete ingress
echo "Deleting ingress..."
kubectl delete -f k8s/ingress/ -n $NAMESPACE

# Delete environment-specific configuration
echo "Deleting $ENV environment configuration..."
kubectl delete -f k8s/env/$ENV/

# Delete namespaces
echo "Deleting namespaces..."
kubectl delete namespace $NAMESPACE monitoring

echo "Cleanup completed!" 
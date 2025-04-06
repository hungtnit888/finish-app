#!/bin/bash

# Check if environment is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <environment>"
    echo "Environments: dev, staging, prod"
    exit 1
fi

ENV=$1
NAMESPACE="finish-app"
BACKUP_DIR="./backups/$(date +%Y%m%d_%H%M%S)"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)

# Create backup directory
mkdir -p $BACKUP_DIR

# Function to check if a command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Check prerequisites
if ! command_exists kubectl; then
    echo "kubectl is not installed. Please install it first."
    exit 1
fi

# Backup PostgreSQL
echo "Backing up PostgreSQL..."
POSTGRES_POD=$(kubectl get pod -n $NAMESPACE -l app=postgres -o jsonpath="{.items[0].metadata.name}")
kubectl exec -n $NAMESPACE $POSTGRES_POD -- pg_dump -U postgres finish_app > $BACKUP_DIR/postgres_$TIMESTAMP.sql

# Backup MongoDB
echo "Backing up MongoDB..."
MONGODB_POD=$(kubectl get pod -n $NAMESPACE -l app=mongodb -o jsonpath="{.items[0].metadata.name}")
kubectl exec -n $NAMESPACE $MONGODB_POD -- mongodump --out=/tmp/mongodb_backup
kubectl cp -n $NAMESPACE $MONGODB_POD:/tmp/mongodb_backup $BACKUP_DIR/mongodb_$TIMESTAMP

# Backup Redis
echo "Backing up Redis..."
REDIS_POD=$(kubectl get pod -n $NAMESPACE -l app=redis -o jsonpath="{.items[0].metadata.name}")
kubectl exec -n $NAMESPACE $REDIS_POD -- redis-cli SAVE
kubectl cp -n $NAMESPACE $REDIS_POD:/data/dump.rdb $BACKUP_DIR/redis_$TIMESTAMP.rdb

# Create backup archive
echo "Creating backup archive..."
tar -czf $BACKUP_DIR.tar.gz $BACKUP_DIR
rm -rf $BACKUP_DIR

echo "Backup completed! Archive created at $BACKUP_DIR.tar.gz" 
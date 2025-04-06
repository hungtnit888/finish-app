#!/bin/bash

# Configuration
BACKUP_DIR="/backup"
BACKUP_FILE=$1

if [ -z "${BACKUP_FILE}" ]; then
    echo "Usage: $0 <backup_file>"
    echo "Available backups:"
    ls -1 "${BACKUP_DIR}" | grep "redis_backup_"
    exit 1
fi

if [ ! -f "${BACKUP_DIR}/${BACKUP_FILE}" ]; then
    echo "Backup file not found: ${BACKUP_DIR}/${BACKUP_FILE}"
    exit 1
fi

echo "Starting Redis restore from ${BACKUP_FILE}..."

# Stop Redis to ensure clean restore
echo "Stopping Redis..."
docker-compose stop redis

# Extract the backup
echo "Extracting backup..."
gunzip -c "${BACKUP_DIR}/${BACKUP_FILE}" > "${BACKUP_DIR}/restore.rdb"

# Copy the dump file to Redis container
echo "Copying dump file to Redis container..."
docker cp "${BACKUP_DIR}/restore.rdb" redis:/data/dump.rdb

# Start Redis
echo "Starting Redis..."
docker-compose start redis

# Cleanup
echo "Cleaning up..."
rm "${BACKUP_DIR}/restore.rdb"

echo "Redis restore completed from ${BACKUP_FILE}" 
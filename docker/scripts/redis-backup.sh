#!/bin/bash

# Configuration
BACKUP_DIR="/backup"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)
BACKUP_NAME="redis_backup_${TIMESTAMP}"

# Create backup directory if it doesn't exist
mkdir -p "${BACKUP_DIR}"

echo "Starting Redis backup..."

# Save Redis data
echo "Saving Redis data..."
docker exec redis redis-cli SAVE

# Copy the dump file
echo "Copying Redis dump file..."
docker cp redis:/data/dump.rdb "${BACKUP_DIR}/${BACKUP_NAME}.rdb"

# Compress the backup
echo "Compressing backup..."
gzip "${BACKUP_DIR}/${BACKUP_NAME}.rdb"

# Cleanup old backups (older than 7 days)
echo "Cleaning up old backups..."
find "${BACKUP_DIR}" -name "redis_backup_*.rdb.gz" -mtime +7 -delete

# Optional: Upload to remote storage (e.g., S3)
if [ -n "${AWS_ACCESS_KEY_ID}" ] && [ -n "${AWS_SECRET_ACCESS_KEY}" ] && [ -n "${AWS_BUCKET_NAME}" ]; then
    echo "Uploading backup to S3..."
    aws s3 cp "${BACKUP_DIR}/${BACKUP_NAME}.rdb.gz" "s3://${AWS_BUCKET_NAME}/redis/${BACKUP_NAME}.rdb.gz"
fi

echo "Redis backup completed: ${BACKUP_DIR}/${BACKUP_NAME}.rdb.gz" 
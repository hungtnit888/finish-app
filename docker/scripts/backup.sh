#!/bin/bash

# Configuration
BACKUP_DIR="/backup"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)
BACKUP_NAME="app_backup_${TIMESTAMP}"

# Create backup directory if not exists
mkdir -p "${BACKUP_DIR}"

# Backup Docker volumes
echo "Backing up Docker volumes..."
docker run --rm \
  -v /var/lib/docker:/var/lib/docker \
  -v "${BACKUP_DIR}:/backup" \
  alpine tar czf "/backup/${BACKUP_NAME}_volumes.tar.gz" /var/lib/docker/volumes

# Backup PostgreSQL database
echo "Backing up PostgreSQL database..."
docker exec postgres pg_dump -U postgres finish_app > "${BACKUP_DIR}/${BACKUP_NAME}_db.sql"

# Backup MongoDB
echo "Backing up MongoDB..."
docker exec mongodb mongodump --out /dump
docker cp mongodb:/dump "${BACKUP_DIR}/${BACKUP_NAME}_mongodb"

# Backup application configuration
echo "Backing up configuration files..."
tar czf "${BACKUP_DIR}/${BACKUP_NAME}_config.tar.gz" docker/config/

# Cleanup old backups (keep last 7 days)
find "${BACKUP_DIR}" -name "app_backup_*" -type f -mtime +7 -delete

echo "Backup completed: ${BACKUP_NAME}"
echo "Backup files are stored in: ${BACKUP_DIR}"

# Optional: Upload to remote storage (e.g., S3)
if [ -n "${AWS_ACCESS_KEY_ID}" ] && [ -n "${AWS_SECRET_ACCESS_KEY}" ]; then
    echo "Uploading backup to S3..."
    aws s3 cp "${BACKUP_DIR}/${BACKUP_NAME}_volumes.tar.gz" "s3://your-bucket/backups/"
    aws s3 cp "${BACKUP_DIR}/${BACKUP_NAME}_db.sql" "s3://your-bucket/backups/"
    aws s3 cp "${BACKUP_DIR}/${BACKUP_NAME}_mongodb" "s3://your-bucket/backups/" --recursive
    aws s3 cp "${BACKUP_DIR}/${BACKUP_NAME}_config.tar.gz" "s3://your-bucket/backups/"
fi 
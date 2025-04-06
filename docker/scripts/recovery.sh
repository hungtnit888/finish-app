#!/bin/bash

# Configuration
BACKUP_DIR="/backup"
BACKUP_NAME=$1

if [ -z "${BACKUP_NAME}" ]; then
    echo "Usage: $0 <backup_name>"
    echo "Available backups:"
    ls -1 "${BACKUP_DIR}" | grep "app_backup_"
    exit 1
fi

# Stop running containers
echo "Stopping running containers..."
docker-compose -f docker/docker-compose.yml down

# Restore Docker volumes
echo "Restoring Docker volumes..."
if [ -f "${BACKUP_DIR}/${BACKUP_NAME}_volumes.tar.gz" ]; then
    docker run --rm \
        -v /var/lib/docker:/var/lib/docker \
        -v "${BACKUP_DIR}:/backup" \
        alpine sh -c "cd /var/lib/docker && tar xzf /backup/${BACKUP_NAME}_volumes.tar.gz"
fi

# Start containers
echo "Starting containers..."
docker-compose -f docker/docker-compose.yml up -d postgres mongodb

# Wait for databases to be ready
echo "Waiting for databases to be ready..."
sleep 30

# Restore PostgreSQL database
echo "Restoring PostgreSQL database..."
if [ -f "${BACKUP_DIR}/${BACKUP_NAME}_db.sql" ]; then
    docker exec -i postgres psql -U postgres finish_app < "${BACKUP_DIR}/${BACKUP_NAME}_db.sql"
fi

# Restore MongoDB
echo "Restoring MongoDB..."
if [ -d "${BACKUP_DIR}/${BACKUP_NAME}_mongodb" ]; then
    docker cp "${BACKUP_DIR}/${BACKUP_NAME}_mongodb" mongodb:/dump
    docker exec mongodb mongorestore /dump
fi

# Restore configuration
echo "Restoring configuration files..."
if [ -f "${BACKUP_DIR}/${BACKUP_NAME}_config.tar.gz" ]; then
    tar xzf "${BACKUP_DIR}/${BACKUP_NAME}_config.tar.gz" -C docker/
fi

# Start application
echo "Starting application..."
docker-compose -f docker/docker-compose.yml up -d app

echo "Recovery completed from backup: ${BACKUP_NAME}"
echo "Checking container status..."
docker-compose -f docker/docker-compose.yml ps

# Verify application health
echo "Checking application health..."
sleep 30
curl -f http://localhost:8080/actuator/health || echo "Warning: Application health check failed" 
#!/bin/bash

# Configuration
REDIS_HOST="localhost"
REDIS_PORT="6379"
LOG_FILE="/logs/redis_monitor.log"

# Create logs directory if it doesn't exist
mkdir -p "$(dirname "${LOG_FILE}")"

echo "Starting Redis monitoring..."

while true; do
    TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")
    
    # Get Redis info
    INFO=$(docker exec redis redis-cli INFO)
    
    # Extract key metrics
    USED_MEMORY=$(echo "$INFO" | grep "used_memory:" | cut -d: -f2)
    CONNECTED_CLIENTS=$(echo "$INFO" | grep "connected_clients:" | cut -d: -f2)
    TOTAL_COMMANDS=$(echo "$INFO" | grep "total_commands_processed:" | cut -d: -f2)
    KEYS=$(echo "$INFO" | grep "db0:keys=" | cut -d= -f2 | cut -d, -f1)
    
    # Log metrics
    echo "[${TIMESTAMP}] Memory: ${USED_MEMORY} bytes, Clients: ${CONNECTED_CLIENTS}, Commands: ${TOTAL_COMMANDS}, Keys: ${KEYS}" >> "${LOG_FILE}"
    
    # Check for warnings
    if [ "${USED_MEMORY}" -gt 1000000000 ]; then
        echo "[${TIMESTAMP}] WARNING: High memory usage detected (${USED_MEMORY} bytes)" >> "${LOG_FILE}"
    fi
    
    if [ "${CONNECTED_CLIENTS}" -gt 100 ]; then
        echo "[${TIMESTAMP}] WARNING: High number of connected clients (${CONNECTED_CLIENTS})" >> "${LOG_FILE}"
    fi
    
    # Sleep for 1 minute
    sleep 60
done 
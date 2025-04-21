#!/bin/bash

# Start SSH server in background
/usr/sbin/sshd -D &

# Start Spring Boot application
java -jar /app/app.jar 
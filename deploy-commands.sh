#!/bin/bash

# Build the image
docker-compose build

# Start the container
docker-compose up -d

# View logs
docker-compose logs -f

# View specific service logs
docker-compose logs app

# Check container status
docker-compose ps

# Stop the container
docker-compose down

# Restart the container
docker-compose restart

# Access container shell
docker-compose exec app bash

# Check container health
docker inspect ecommerce-backend | grep -A 10 "Health"

# View resource usage
docker stats ecommerce-backend

# Remove everything (containers, networks, images)
docker-compose down --rmi all -v
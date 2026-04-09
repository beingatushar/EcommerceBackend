#!/bin/bash

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}Deploying Ecommerce Backend with Docker${NC}"
echo -e "${GREEN}========================================${NC}"

# Check if .env file exists
if [ ! -f .env ]; then
    echo -e "${RED}Error: .env file not found!${NC}"
    echo -e "${YELLOW}Please create .env file with your database credentials${NC}"
    exit 1
fi

# Load environment variables
source .env

# Check if DB_PASSWORD is set
if [ -z "$DB_PASSWORD" ]; then
    echo -e "${RED}Error: DB_PASSWORD not set in .env file${NC}"
    exit 1
fi

# Build the Docker image
echo -e "${YELLOW}Building Docker image...${NC}"
docker-compose build

# Stop existing container if running
echo -e "${YELLOW}Stopping existing container...${NC}"
docker-compose down

# Run the container
echo -e "${YELLOW}Starting container...${NC}"
docker-compose up -d

# Check if container started successfully
if [ $? -eq 0 ]; then
    echo -e "${GREEN}Container started successfully!${NC}"
    echo -e "${GREEN}App is running on: http://localhost:8080${NC}"
    echo ""
    echo -e "${YELLOW}Useful commands:${NC}"
    echo "  docker-compose logs -f    # View logs"
    echo "  docker-compose ps         # Check status"
    echo "  docker-compose down       # Stop container"
    echo "  docker-compose restart    # Restart container"
else
    echo -e "${RED}Failed to start container${NC}"
    exit 1
fi

# Show logs
echo -e "${YELLOW}Showing logs...${NC}"
docker-compose logs -f
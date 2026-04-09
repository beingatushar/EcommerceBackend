#!/bin/bash

# ==========================================
# CONFIGURATION
# ==========================================
# Update this to match your actual Spring Boot POST endpoint
API_URL="http://localhost:8080/api/v1/products"
JSON_FILE="data.json"

# Check if the JSON file exists
if [ ! -f "$JSON_FILE" ]; then
    echo "❌ Error: $JSON_FILE not found in the current directory."
    exit 1
fi

# Check if jq is installed (required for JSON parsing)
if ! command -v jq &> /dev/null; then
    echo "❌ Error: 'jq' is not installed."
    echo "Install it using: 'sudo apt install jq' (Ubuntu), 'brew install jq' (Mac), or 'choco install jq' (Windows)"
    exit 1
fi

echo "🚀 Starting data import to $API_URL..."
echo "----------------------------------------"

# Read each object inside the "data" array line-by-line as a compact JSON string
jq -c '.data[]' "$JSON_FILE" | while read -r item; do

    # Extract the name for logging purposes
    PRODUCT_NAME=$(echo "$item" | jq -r '.name')
    echo "📦 Sending: $PRODUCT_NAME"

    # Transform the JSON payload to match the ProductEntity schema
    PAYLOAD=$(echo "$item" | jq -c '{
        name: .name,
        mrp: .price,
        stock: .stock,
        isDeleted: .isDeleted,
        isFeatured: .isFeatured,
        material: .material,
        category: .category,
        tags: .tags,
        features: .features,
        price: .price,
        description: .description,
        url: .image
    }')

    # Execute the POST request using curl
    HTTP_RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X POST "$API_URL" \
         -H "Content-Type: application/json" \
         -d "$PAYLOAD")

    # Check the HTTP response status
    if [ "$HTTP_RESPONSE" -eq 200 ] || [ "$HTTP_RESPONSE" -eq 201 ]; then
        echo "✅ Success (Status: $HTTP_RESPONSE)"
    else
        echo "❌ Failed (Status: $HTTP_RESPONSE)"
        # Print the actual error from curl for debugging if it fails
        curl -s -X POST "$API_URL" -H "Content-Type: application/json" -d "$PAYLOAD"
        echo ""
    fi
    echo "----------------------------------------"

done

echo "🎉 Import script finished!"
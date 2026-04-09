clear
find . -type f -name "*.java" -exec bash -c 'mv "$0" "${0%.java}.groovy"' {} \;
# This will kill whatever is on port 8080
sudo fuser -k 8080/tcp;
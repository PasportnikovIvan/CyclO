#!/bin/bash

echo "Starting deployment of Cyclo App..."

# Step 1: Set up the database
echo "Creating PostgreSQL database and user..."
createdb cyclo_db
createuser cyclo_user -P --encrypted --no-superuser --no-createdb --no-createrole
psql -c "GRANT ALL PRIVILEGES ON DATABASE cyclo_db TO cyclo_user;"

# Step 2: Initialize the database
echo "Initializing the database with essential data..."
psql -U cyclo_user -d cyclo_db -f src/main/resources/db/init.sql

# Step 3: Build the application
echo "Building microservices..."
# shellcheck disable=SC2164
cd rental-service
mvn clean install
# shellcheck disable=SC2164
cd ../warehouse-service
mvn clean install
# shellcheck disable=SC2164
cd ../user-service
mvn clean install
# shellcheck disable=SC2164
cd ../bike-service
mvn clean install
# shellcheck disable=SC2103
cd ..

# Step 4: Run the microservices
echo "Running microservices..."
java -jar rental-service/target/rental-service-0.0.1-SNAPSHOT.jar &
java -jar warehouse-service/target/warehouse-service-0.0.1-SNAPSHOT.jar &
java -jar user-service/target/user-service-0.0.1-SNAPSHOT.jar &

echo "Deployment complete."

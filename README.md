# CyclO team

<div>
<dl>
<dt>Team:</dt>
<dd>Ivan Pasportnikov - Vedoucí Projektu</dd>
<dd>Valentyna Rozhdestvenska</dd>
<dd>Klim Shmelev</dd>
<dd>Vladimir Mashkin</dd>
</dl>
</div>

### CyclO Project README

# CyclO: Internal Information System for CyclO s.r.o.

## Introduction

CyclO is an innovative program designed to improve the health and well-being of employees by providing them with access to long-term bicycle rentals. The application aims to facilitate this by offering a comprehensive system for managing bicycle reservations, inventory, maintenance, and user accounts.

## Technologies and Architecture

### Selected Technologies
- **Backend**: Java with Spring Boot
- **Frontend**: React.js
- **Database**: PostgreSQL (Relational Database)
- **Cache**: Hazelcast
- **Messaging**: Kafka


~~ - **Security**: OAuth2 or Basic Authorization
- **Web Services**: REST
- **Deployment**: Heroku
- **Search**: Elasticsearch (optional)~~

- **Design Patterns**: Singleton, Factory, Observer, Strategy, and Decorator

### Architecture
The application is designed using the event-based architecture pattern to handle asynchronous communication between services. 

## Functional Requirements
1. **Account Registration and Management**
   - Employee registration and account creation
   - Update personal details, change password, and password recovery

2. **Reservation System**
   - Online bicycle reservation
   - Calendar view for bike availability with filters

3. **Bicycle Management**
   - Inventory management
   - Maintenance and service tracking

4. **Notification System**
   - Automatic notifications for upcoming reservations, confirmations, and reminders
   - Maintenance alerts for administrators

5. **Reporting and Analytics**
   - Usage reports and trend analysis
   - Decision-making support for fleet expansion and optimization

6. **Mobile Application**
   - Access to reservation system and account management
   - QR code scanning for quick bike rental and return

7. **Integration with External Systems**
   - Integration with company HR systems
   - Asset management system integration

## Non-Functional Requirements
1. **Security**
   - Data encryption in storage and transit
   - Strong authentication and authorization protocols
   - Regular security audits and penetration testing

2. **Reliability**
   - High system availability and minimal downtime
   - Redundant systems and backups

3. **Performance**
   - Fast system response times
   - Optimized backend for high transaction volumes
   - Scalable to accommodate growing user and data volumes

4. **Usability**
   - Intuitive and accessible user interface
   - Compatibility with various devices and browsers

5. **Maintainability**
   - Easy maintenance and updates
   - Modular design for adding new features

6. **Scalability**
   - Easily extendable system to handle increased load
   - Flexible to adapt to future service expansions

7. **Regulatory Compliance**
   - Adherence to local and international regulations (e.g., GDPR)

## Deployment Instructions

### Step 1: Clone the Repository

First, clone the Cyclo app repository to your local machine:

```bash
git clone https://gitlab.fel.cvut.cz/mashkvla/cyclo.git
```

### Step 2: Configure the Database

Ensure your PostgreSQL is running and accessible. The script will create a new database and a user, so ensure that your PostgreSQL user has the necessary permissions to create databases and roles.

### Step 3: Run the Deployment Script

Make the script executable and run it:

```bash
chmod +x deploy.sh
./deploy.sh
```

The script will perform the following actions:
1. Create and set up the PostgreSQL database and user.
2. Initialize the database with the required schema and data.
3. Build all microservices using Maven.
4. Start each microservice in the background.

## Running the Application

Once the script completes, the microservices will be running in the background on your machine:
- **Rental Service**
- **Warehouse Service**
- **User Service**
- **Bike Service**

Each service will be accessible via their respective ports, which are configured in their application properties.
## Stopping the Services

If you need to stop the services, you can find their process IDs (PIDs) and kill them:

```bash
ps aux | grep java
kill <PID>
```

Replace `<PID>` with the process ID of the service you want to stop.

## Logging and Monitoring
- **Interceptors**: Implement interceptors for logging requests and responses to aid in debugging and monitoring system usage.


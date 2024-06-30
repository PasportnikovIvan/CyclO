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
1. **Initialize the Database**
   - Set up PostgreSQL database and run initial scripts to create tables and seed data (e.g., admin account).

2. **Deploy Backend on Heroku**
   - Push the Spring Boot application to a Heroku repository.
   - Configure environment variables for database connections and API keys.

3. **Deploy Frontend**
   - Build the React application and deploy it to a hosting service (e.g., Heroku, Netlify).

4. **Set Up Kafka (optional)**
   - Configure Kafka for message brokering if used.

5. **Configure Hazelcast (optional)**
   - Set up Hazelcast for caching if implemented.

6. **Launch the Application**
   - Start the application and verify all services are running correctly.
   - Perform initial testing and debugging.

## Use Cases
### Employees (End Users)
- **Browse Available Bicycles**: View currently available bikes.
- **Reserve Bicycles**: Book a bike for a specific date and time.
- **View Rental History**: Check past rentals including dates and duration.
- **Cancel Reservations**: Cancel a booking if plans change.

### Program Administrator
- **Manage User Accounts**: Create, update, and deactivate user accounts.
- **Manage Bicycle Inventory**: Add, update, and remove bikes.
- **Track Maintenance**: Schedule and monitor bike maintenance.
- **Generate Reports**: Create reports on bike usage and system performance.

### Company Management
- **Access Reports**: View overall statistics and generated reports.
- **Program Development**: Make strategic decisions based on data.
- **Evaluate Program Impact**: Analyze the effect on productivity and employee health.

### Maintenance Personnel
- **Access Maintenance Information**: View maintenance needs for each bike.
- **Perform Maintenance**: Carry out repairs and upkeep tasks.
- **Record Maintenance Actions**: Log completed maintenance activities.

## Logging and Monitoring
- **Interceptors**: Implement interceptors for logging requests and responses to aid in debugging and monitoring system usage.




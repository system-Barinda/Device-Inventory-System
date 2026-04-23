asset-inventory-management-system/
│
├── src/main/java/
|       com/airtel/Device_inventory_system/
│   
│           ├── config/                           # Configuration classes
│           │   └── DatabaseConfig.java
│           │
│           ├── controller/          # REST Controllers (API endpoints)
│           │   ├── DeviceController.java
│           │   ├── UserController.java
│           │   ├── AssignmentController.java
│           │   └── ReportController.java
│           │
│           ├── service/             # Business logic layer
│           │   ├── DeviceService.java
│           │   ├── UserService.java
│           │   ├── AssignmentService.java
│           │   └── ReportService.java
│           │
│           ├── repository/          # Database access (JPA interfaces)
│           │   ├── DeviceRepository.java
│           │   ├── UserRepository.java
│           │   ├── AssignmentRepository.java
│           │   └── AuditRepository.java
│           │
│           ├── model/               # Entities (Database tables)
│           │   ├── Device.java
│           │   ├── User.java
│           │   ├── Assignment.java
│           │   └── AuditHistory.java
│           │
│           ├── dto/                 # Data Transfer Objects
│           │   ├── DeviceDTO.java
│           │   ├── UserDTO.java
│           │   └── AssignmentDTO.java
│           │
│           ├── exception/           # Custom exception handling
│           │   ├── ResourceNotFoundException.java
│           │   └── GlobalExceptionHandler.java
│           │
│           ├── util/                # Helper classes
│           │   └── DateUtil.java
│           │
│           └── DeviceInventoryApplication.java  # Main class
│
├── src/main/resources/
│   ├── application.properties
│   └── data.sql (optional sample data)
│
├── pom.xml
└── README.md



## RELATIONSHIP (VERY IMPORTANT)

One user → many assignments,
One device → many assignments,
Assignments connect users & devices,
Audit history logs all actions,
## SIMPLE VISUAL IDEA
User ───────┐
            ├── Assignment ─── Device
            │
            └── Audit History
            
##             WHY THIS DESIGN IS PROFESSIONAL

1 Tracks ownership
2 Tracks device status
3 Keeps history (audit trail)
4 Supports reporting
5 Scalable for real company use
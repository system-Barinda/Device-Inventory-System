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
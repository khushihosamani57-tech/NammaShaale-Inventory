````md
# Namma-Shaale Inventory

Digital Asset Auditor for Schools

## Android App Development using Generative AI

Namma-Shaale Inventory is an Android-based Digital Asset Auditor designed to help schools manage and monitor educational resources efficiently. The application enables teachers to register school assets, track their condition, report damages, and maintain accountability for government-funded resources through a simple and professional digital platform.

---

# Problem Statement

Primary and secondary schools receive resources such as:
- Sports kits
- Laboratory equipment
- Tablets
- Classroom devices

Most schools still manage these assets manually using registers or paperwork.

Common problems include:
- Broken equipment discovered late
- Missing assets not tracked properly
- Delayed repair requests
- No centralized monitoring system
- Lack of accountability

The project solves these issues using a smart Android application.

---

# Project Vision

Namma-Shaale Inventory acts as a digital inventory monitoring system for schools.

The application helps teachers:
- Register school assets
- Perform monthly health checks
- Track damaged or lost items
- Manage repair requests
- Generate summary reports

The system improves transparency, maintenance, and resource management in schools.

---

# Key Features

## Asset Register
Teachers can add:
- Asset Name
- Serial Number
- Asset ID
- Asset Photo
- Purchase Information

Example Assets:
- Microscope
- Football
- Tablet
- Projector

---

## Monthly Health Check

Teachers can update asset condition using:
- 🟢 Working
- 🟡 Needs Repair
- 🔴 Broken

The process is designed for quick auditing.

---

## Issue Log Management

Teachers can report:
- Lost equipment
- Damaged devices
- Missing assets
- Broken materials

Issue logs contain:
- Date
- Reason
- Asset Details
- Status

---

## Repair Request Module

Displays items requiring maintenance.

Features:
- Pending repair list
- Repair tracking
- Status monitoring
- SDMC support

---

## Dashboard Analytics

Professional dashboard displaying:
- Total Assets
- Working Assets
- Broken Assets
- Repair Requests
- Audit Summary

Example:
```text
Total Assets: 50
Working: 40
Needs Repair: 5
Broken: 5
```

---

## CameraX Integration

The application uses CameraX to:
- Capture asset images
- Document damages
- Maintain proof records

Useful for:
- High-value equipment
- Maintenance reports
- Verification

---

## Summary Report Generation

The application generates:
- Inventory reports
- Repair summaries
- Damage reports

Reports help school authorities monitor resources effectively.

---

# Technologies Used

| Technology | Purpose |
|------------|---------|
| Kotlin | Android Development |
| XML | UI Design |
| Room Database | Local Storage |
| SQLite | Database |
| CameraX | Photo Capture |
| RecyclerView | Data Display |
| Material Design | UI Components |
| Android Studio | Development Environment |

---

# Database Structure

## Asset Table
Stores:
- Asset ID
- Asset Name
- Serial Number
- Condition
- Photo

## Health Check Table
Stores:
- Audit Date
- Asset Status
- Condition Updates

## Issue Log Table
Stores:
- Problem Details
- Date
- Repair Status

---

# Application Workflow

1. Teacher logs into the application
2. Assets are added into the inventory
3. Monthly health checks are performed
4. Asset conditions are updated
5. Issues are reported
6. Repair requests are generated
7. Summary reports are shared

---

# Impact Goals

## Resource Optimization
Ensures proper tracking of government-funded school resources.

## Educational Quality
Helps maintain functional labs, classrooms, and sports equipment.

## Accountability
Encourages responsible asset handling.

## Transparency
Maintains accurate digital audit records.

---

# Success Criteria

- Teachers update 10 assets within 2 minutes
- Reports generate successfully
- Dashboard remains organized and professional
- Repair requests are managed efficiently

---

# Future Enhancements

- QR Code Asset Scanning
- Firebase Cloud Sync
- AI-Based Damage Detection
- Notification Alerts
- Barcode Integration
- Voice-Based Asset Entry
- Multi-School Dashboard

---

# Advantages

- Reduces paperwork
- Saves audit time
- Improves asset monitoring
- Prevents unnoticed damage
- Easy-to-use interface
- Organized digital inventory system

---

# Project Structure

```text
app/
│
├── DashboardActivity.kt
├── AssetRegisterActivity.kt
├── HealthCheckActivity.kt
├── RepairRequestActivity.kt
├── IssueLogActivity.kt
├── Database
├── CameraX Integration
└── UI Components
```

---

# Application Screenshots

## Dashboard Screen
![Dashboard](dashboard.png)

## Asset Register Screen
![Asset Register](assetregister.png)

## Health Check Screen
![Health Check](healthcheck.png)

## Repair Request Screen
![Repair Request](repairrequest.png)

---

# Developed By

Khushi Hosamani

BE – Electronics and Communication Engineering  
Kalpataru Institute of Technology, Tiptur

VTU – Visvesvaraya Technological University

---

# Conclusion

Namma-Shaale Inventory is a smart digital solution for managing school resources efficiently. The application simplifies inventory tracking, improves accountability, and supports proper maintenance of educational infrastructure. By digitizing school asset auditing, the system helps schools maintain better learning environments and ensures effective utilization of public resources.
````

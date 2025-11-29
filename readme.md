# Order-Inventory Microservices Assignment

This project contains two Spring Boot microservices — **Order Service** and **Inventory Service** — built as part of an assignment.  
Both services communicate using REST APIs and follow clean modular design with controller, service, repository layers and a Factory Design Pattern.

---

## 📌 Project Structure

```
order-inventory-microservices-assignment/
├── inventory-service/
│   ├── src/main/java/com/korber/inventory_service/
│   └── src/test/java/com/korber/inventory_service/
│
└── order-service/
    ├── src/main/java/com/korber/order_service/
    └── src/test/java/com/korber/order_service/
```

---

## 🚀 Microservices Overview

### **1️⃣ Inventory Service**
- Maintains product batches with expiry dates  
- Returns inventory sorted by expiry  
- Deducts stock batch-wise when orders are placed  
- Uses H2 in-memory DB  
- Implements **Factory Pattern** for extendable inventory logic  

#### **Endpoints**
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/inventory/{productId}` | Get batches sorted by expiry |
| POST | `/inventory/update` | Deduct inventory after an order |

---

### **2️⃣ Order Service**
- Accepts orders  
- Calls Inventory Service to update stock  
- Stores orders using H2 in-memory DB  

#### **Endpoints**
| Method | Endpoint | Description |
|--------|-----------|-------------|
| POST | `/order` | Places an order and reduces inventory |

---

## 🗄️ H2 Console URLs

| Service | URL |
|--------|------|
| Inventory Service | `http://localhost:8081/h2-console` |
| Order Service | `http://localhost:8082/h2-console` |

Default H2 Credentials:
```
username: sa
password: (blank)
```

---

## ⚙️ Running the Project

### **Start Inventory Service**
```
cd inventory-service
mvn spring-boot:run
```

### **Start Order Service**
```
cd order-service
mvn spring-boot:run
```

---

## 🧪 Testing With Postman

### **1️⃣ Update Inventory**
```
POST http://localhost:8081/inventory/update
Content-Type: application/json

{
  "productId": "P001",
  "quantity": 50
}
```

### **2️⃣ View Inventory**
```
GET http://localhost:8081/inventory/P001
```

### **3️⃣ Place Order**
```
POST http://localhost:8082/order
Content-Type: application/json

{
  "productId": "P001",
  "quantity": 10
}
```

---

## 🧪 Unit & Integration Tests

### **Inventory Service Tests**
- Service tests using Mockito  
- Controller tests using MockMvc  
- Integration tests using @SpringBootTest  

### **Order Service Tests**
- Controller test using MockMvc  
- Service logic Mockito test  
- Integration test verifying real H2 DB behavior  

All test classes are located under:

```
inventory-service/src/test/java/
order-service/src/test/java/
```

---

## 🏗️ Factory Pattern (Inventory Service)

### **InventoryFactory**
Returns appropriate handler:
```java
public InventoryHandler getHandler(String type) {
    return defaultHandler;
}
```

### **DefaultInventoryHandler**
Handles batch-wise inventory deduction.

Clean, simple, and future extensible.

---

## 🧰 Technology Stack

- Java 17 / 21  
- Spring Boot 3.x  
- Spring Web  
- Spring Data JPA  
- H2 Database  
- Mockito + JUnit 5  
- MockMvc  
- Lombok  

---

## 📄 Build & Run

To build entire project:
```
mvn clean install
```

To run tests:
```
mvn test
```



## 📬 Author
Assignment completed by **Amit Kumar Palai**  


# Jewellery Shop

A full-stack **Jewellery Management SPA** built with **Angular** (frontend) and **Spring Boot** (backend). It allows users to manage jewellery items — add, edit, delete, filter by metal type, and sort by price — with real-time price calculation.

---

## Features

- View all jewellery items in a responsive grid
- Add new jewellery items with dynamic price calculation
- Edit existing items
- Delete items
- Filter items by metal type (Gold, Silver, etc.)
- Sort items by price (Low → High / High → Low)
- Auto-calculates final price based on:
  - Metal price per gram
  - Weight (quantity in grams)
  - Making charges
  - Shipping charges (AED 50 fixed)
  - Tax percentage

---

## Tech Stack

### Backend
| Tech | Usage |
|------|-------|
| Spring Boot | REST API |
| Java | Business Logic |
| ResponseEntity | HTTP Responses |

## Setup & Installation

### Prerequisites
- Java 17+
- Maven

### Backend (Spring Boot)

```bash
# Clone the repository
git clone https://github.com/Njsomeone11/JewelleryService.git
cd JewelleryService

# Run the Spring Boot application
./mvnw spring-boot:run
```

The backend runs on **`http://localhost:8080`**

---

## API Endpoints

Base URL: `http://localhost:8080`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/jewellery/getItems` | Fetch all jewellery items, metals & taxes |
| `POST` | `/jewellery/createItem` | Create a new jewellery item |
| `PUT` | `/jewellery/updateItem/{id}` | Update an existing item |
| `DELETE` | `/jewellery/deleteItem/{id}` | Delete an item |

### Sample Response — `GET /getItems`
```json
{
  "jewelleryItems": [
    {
      "id": 1,
      "name": "Gold Ring",
      "metalType": "Gold",
      "quantity": 5,
      "makingCharges": 250.0,
      "shippingCharges": 50.0,
      "taxId": 1,
      "finalPrice": 3200.0,
      "availability": 1
    }
  ],
  "metalItems": [
    {
      "item": "Gold",
      "pricePerGram": 300.0,
      "baseMakingCharge": 50.0
    }
  ],
  "taxes": [
    {
      "id": 1,
      "taxName": "VAT",
      "percentage": 5
    }
  ]
}
```

---

## Price Calculation Formula

```
Final Price = (Metal Price/gram × Weight)
            + (Making Charge/gram × Weight)
            + Shipping Charges (AED 50)
            + Tax%
```

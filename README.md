# 🍔 FoodFlow

FoodFlow is a simple food delivery Android application built using **Java and Firebase**.

The app allows users to create an account, browse restaurants, view restaurant menus, add food items to a cart, enter delivery details, select a payment method, and place an order.

---

## ✨ Features

- 🔐 User Registration & Login
- 🍕 Browse Restaurants
- 🍔 Restaurant Categories
- 🔎 Search Restaurants
- 📋 View Restaurant Menus
- 🛒 Add Food Items to Cart
- ➕ Increase Food Quantity
- ➖ Decrease Food Quantity
- 💰 Cart Total & Delivery Charges
- 📍 Delivery Address
- 💳 Cash on Delivery / Online Payment Selection
- ✅ Place Order
- 🔥 Firebase Firestore Integration
- 👤 Firebase Authentication
- 🚀 Splash Screen

---

## 🛠️ Technologies Used

- **Java**
- **Android Studio**
- **XML**
- **Firebase Authentication**
- **Firebase Cloud Firestore**
- **RecyclerView**
- **Android SDK**
- **Git & GitHub**

---

## 🔥 Firebase Integration

FoodFlow uses Firebase for authentication and cloud database functionality.

### Firebase Authentication

Used for:

- User Registration
- User Login
- Email & Password Authentication

### Cloud Firestore

Used for storing and loading:

- Restaurant information
- Restaurant menu items
- User-related application data

Restaurant and menu data are loaded dynamically from Firestore instead of being completely hardcoded inside the application.

---

## 📱 Application Flow

```text
Splash Screen
      ↓
Login
      ↓
Register / Existing User Login
      ↓
Home
      ↓
Restaurant
      ↓
Menu
      ↓
Add Food to Cart
      ↓
Cart
      ↓
Checkout
      ↓
Delivery Address
      ↓
Payment Selection
      ↓
Place Order
```

---

## 🏪 Restaurants

The application currently includes sample restaurants such as:

- 🍕 Pizza Palace
- 🍔 Burger House
- 🥡 Dragon Wok

Restaurant and menu information is managed through Firebase Firestore.

---

## 🗂️ Project Structure

```text
FoodFlow
│
├── app
│   └── src
│       └── main
│           ├── java
│           │   └── com.example.foodflow
│           │
│           └── res
│               ├── drawable
│               ├── layout
│               └── values
│
├── gradle
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🚀 How to Run

### 1. Clone the repository

```bash
git clone https://github.com/aashishrathod17/FoodFlow.git
```

### 2. Open the project

Open the cloned project in **Android Studio**.

### 3. Sync the project

Allow Android Studio to sync the Gradle dependencies.

### 4. Configure Firebase

The Firebase configuration file is intentionally not included in the public repository.

To run Firebase features, add your Firebase configuration file:

```text
app/google-services.json
```

### 5. Run the application

Connect an Android device or start an Android Emulator and run the application from Android Studio.

---

## 🔐 Security Note

Firebase configuration files containing project-specific credentials are excluded from the public repository using `.gitignore`.

Firebase Authentication handles user passwords securely.

Passwords are not manually stored in the application's Firestore database.

---

## 🎯 Project Purpose

FoodFlow was developed as an Android development project to practice:

- Android application development
- Java programming
- XML UI design
- Firebase integration
- Firebase Authentication
- Cloud Firestore
- RecyclerView
- Multi-screen application navigation
- Cart management
- Git & GitHub

---

## 👨‍💻 Developer

**Aashish Rathod**

B.Sc. IT Student  
Noble University, Junagadh

**GitHub:**  
https://github.com/aashishrathod17

**Linkedin:**  
https://www.linkedin.com/in/aashish-rathod-5665b03a7/?isSelfProfile=true


---

## 📄 License

This project was created for educational and portfolio purposes.

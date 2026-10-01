# 🍔 FoodFlow

FoodFlow is a simple food delivery Android application built using **Java and Firebase**.

The app allows users to create an account, browse restaurants, view restaurant menus, add food items to a cart, enter delivery details, select a payment method, and place an order.

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

## 🛠️ Technologies Used

- **Java**
- **Android Studio**
- **XML**
- **Firebase Authentication**
- **Firebase Cloud Firestore**
- **RecyclerView**
- **Android SDK**
- **Git & GitHub**

## 🔥 Firebase Integration

FoodFlow uses Firebase for:

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

## 📱 Application Flow


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

🏪 Restaurants

The application currently includes sample restaurants such as:

🍕 Pizza Palace
🍔 Burger House
🥡 Dragon Wok

Restaurant and menu information is managed through Firebase Firestore.

🗂️ Project Structure
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


🚀 How to Run
Clone this repository:
git clone https://github.com/aashishrathod17/FoodFlow.git
Open the project in Android Studio.
Connect an Android device or start an Android Emulator.
Sync the Gradle project.
Run the application.

Firebase configuration is required to run the Firebase features. The Firebase configuration file is intentionally not included in the public repository.

🔐 Security Note

Firebase configuration files containing project-specific credentials are excluded from this repository using .gitignore.

Firebase Authentication handles user passwords securely. Passwords are not stored manually in the application's Firestore database.

🎯 Project Purpose

FoodFlow was developed as an Android development project to practice:

Android application development
Java programming
XML UI design
Firebase integration
Authentication
Cloud Firestore
RecyclerView
Git & GitHub
Multi-screen application navigation
👨‍💻 Developer

Aashish Rathod

B.Sc. IT Student
Noble University, Junagadh

GitHub: @aashishrathod17

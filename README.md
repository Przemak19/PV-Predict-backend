<div id="top">

<!-- HEADER STYLE: CLASSIC -->
<div align="center">
<img src="pv-predict-backend.png" width="30%" style="position: relative; top: 0; right: 0;" alt="Project Logo"/>

# PV-PREDICT-BACKEND
</div>
<br>

---

## 📄 Table of Contents

- [Overview](#-overview)
- [Getting Started](#-getting-started)
    - [Prerequisites](#-prerequisites)
    - [Installation](#-installation)
    - [Usage](#-usage)
    - [Testing](#-testing)
- [Features](#-features)

---

## ✨ Overview

PV-Predict-backend is a robust backend service that delivers 7-day energy yield forecasts for photovoltaic solar installations. Built on Spring Boot, it seamlessly integrates weather data from the Open-Meteo API to support energy management and planning.

**Why PV-Predict-backend?**

This project aims to provide reliable solar energy predictions to optimize renewable energy utilization. The core features include:

- 🧪 **Forecasting Accuracy:** Generates detailed weekly energy yield predictions based on weather and installation parameters.
- 🌦️ **Weather Data Integration:** Fetches real-time weather forecasts from the Open-Meteo API to inform energy calculations.
- 🚀 **Scalable REST API:** Offers REST endpoints for easy access to forecast and weather data for frontend or external systems.
- ⚙️ **Energy Calculation Utility:** Provides tools to estimate solar energy production from sunshine hours and installation specifics.
- 🛡️ **Robust Error Handling & CORS:** Ensures reliable operation with centralized exception management and secure cross-origin communication.

---

## 📌 Features

|      | Component       | Details                                                                                     |
| :--- | :-------------- | :------------------------------------------------------------------------------------------ |
| ⚙️  | **Architecture**  | <ul><li>Java-based backend using Maven for build management</li><li>RESTful API design</li><li>Layered architecture with controllers, services, repositories</li></ul> |
| 🔩 | **Code Quality**  | <ul><li>Uses standard Java coding conventions</li><li>Includes validation annotations (jakarta.validation-api)</li><li>Structured package organization</li></ul> |
| 📄 | **Documentation** | <ul><li>Basic README with project overview</li><li>Uses inline JavaDoc comments</li><li>Configuration files (pom.xml) documented</li></ul> |
| 🔌 | **Integrations**  | <ul><li>Integrates with Maven Central for dependencies</li><li>Uses Jakarta EE APIs for validation</li><li>Potential API endpoints for PV prediction services</li></ul> |
| 🧩 | **Modularity**    | <ul><li>Separation of concerns via controllers, services, repositories</li><li>Configurable via properties files</li></ul> |
| 🧪 | **Testing**       | <ul><li>Test classes in dedicated test directories</li><li>Validation annotations facilitate input testing</li></ul> |
| ⚡️  | **Performance**   | <ul><li>Standard Java performance practices</li></ul> |
| 🛡️ | **Security**      | <ul><li>Input validation via jakarta.validation-api</li></ul> |
| 📦 | **Dependencies**  | <ul><li>Managed via Maven (`pom.xml`)</li><li>Key dependencies include Jakarta EE APIs, license management tools</li></ul> |

---

## 🚀 Getting Started

### 📋 Prerequisites

This project requires the following dependencies:

- **Programming Language:** Java
- **Package Manager:** Maven

### ⚙️ Installation

Build PV-Predict-backend from the source and install dependencies:

1. **Clone the repository:**

    ```sh
    ❯ git clone https://github.com/Przemak19/PV-Predict-backend
    ```

2. **Navigate to the project directory:**

    ```sh
    ❯ cd PV-Predict-backend
    ```

3. **Install the dependencies:**

**Using [maven](https://maven.apache.org/):**

```sh
mvn install
```

### 💻 Usage

Run the project with:

**Using [maven](https://maven.apache.org/):**

```sh
mvn exec:java
```

### 🧪 Testing

Pv-predict-backend uses the {__test_framework__} test framework. Run the test suite with:

**Using [maven](https://maven.apache.org/):**

```sh
mvn test
```

---

<div align="left"><a href="#top">⬆ Return</a></div>

---

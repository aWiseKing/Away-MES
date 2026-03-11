<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">AWAY-MES</h1>
<h4 align="center">🏭 Intelligent Manufacturing Execution System (MES) based on Spring Boot + Vue</h4>

<p align="center">
    <img src="https://img.shields.io/badge/Spring%20Boot-2.5.14-green.svg" alt="Spring Boot">
    <img src="https://img.shields.io/badge/Vue-2.6.12-brightgreen.svg" alt="Vue">
    <img src="https://img.shields.io/badge/Element%20UI-2.15.12-blue.svg" alt="Element UI">
    <img src="https://img.shields.io/badge/License-MIT-orange.svg" alt="License">
</p>
<p align="center">
    <span>English</span> | <a href="./README.md">简体中文</a>
</p>

---

## 📖 Introduction

**AWAY-MES** is a modern, modular Manufacturing Execution System (MES). It is deeply customized and extended based on the classic **RuoYi-Vue** framework, specifically designed for small and medium-sized manufacturing enterprises.

The system aims to solve the "black box" problem in the production process, achieving transparent management from order placement to product warehousing. By integrating core functions such as **Production Planning**, **Material Management**, **Quality Control**, and **Equipment Maintenance**, AWAY-MES helps enterprises improve production efficiency, reduce manufacturing costs, and achieve digital transformation. 🚀

---

## 🛠️ Tech Stack

This project uses a separation of frontend and backend architecture with a mature and stable technology selection.

### 🔙 Backend
| Technology | Description |
| :--- | :--- |
| **Spring Boot** | Core Framework |
| **Spring Security** | Security & Auth |
| **MyBatis** | ORM Framework |
| **Redis** | Caching Middleware |
| **Druid** | Database Connection Pool |
| **Quartz** | Job Scheduling |
| **JWT** | Authentication |

### 🎨 Frontend
| Technology | Description |
| :--- | :--- |
| **Vue.js** | Core MVVM Framework |
| **Element UI** | UI Component Library |
| **Axios** | HTTP Client |
| **Vuex** | State Management |
| **ECharts** | Data Visualization |
| **Sass** | CSS Preprocessor |

---

## ✨ Features

### 🏭 Production Center
*   📅 **Plan Management**: Production scheduling and flexible adjustment of production progress.
*   📝 **Work Order Management**: Publishing, execution, pausing, completion, and cancellation of production work orders.
*   🔨 **Process Management**: Refined control of production processes and routing.

### 📦 Storage Center
*   🏭 **Warehouse Management**: Multi-warehouse and multi-location management.
*   🧱 **Material Management**: Maintenance of basic material information, specifications, and units of measurement.
*   📊 **Inventory Monitoring**: Real-time inventory queries and tracking of inbound/outbound records.
*   🔧 **Tool Management**: Full lifecycle management of tools.

### ✅ Quality Center
*   🔍 **Process Inspection**: Quality inspection and recording during the production process.
*   📈 **Quality Monitoring**: Multi-dimensional analysis and monitoring of product quality data.

### 🛒 Order Center
*   📜 **Order Lifecycle**: Full-link status tracking from order receipt to delivery.
*   📊 **Progress Visibility**: Real-time viewing of order execution status.

### 🤝 Comprehensive Management
*   🏢 **Customer/Supplier**: Basic information management for customers and suppliers.
*   📁 **File Management**: Unified storage and management of internal system files and drawings.

### ⚙️ System Base
*   👥 **User & Permission**: Management of users, roles, departments, and posts, supporting data permission isolation.
*   🛠️ **Dev Tools**: Code generator, form builder, API documentation (Swagger).
*   👀 **System Monitoring**: Online users, server monitoring, cache monitoring, connection pool monitoring.
*   📝 **Log Management**: Operation logs, login logs, scheduled task logs.

---

## 🚀 Quick Start

### Requirements
*   JDK >= 1.8
*   MySQL >= 5.7
*   Redis >= 3.0
*   Node.js >= 12

### Run Scripts
Convenient scripts are provided in the `bin/` directory:
*   `bin/run.bat`: Start Backend Service
*   `bin/play-web.ps1`: Start Frontend Service
*   `bin/clean.bat`: Clean Project

---

## 📄 License
This project is licensed under the [GPL-3.0](https://www.gnu.org/licenses/gpl-3.0.html) License.

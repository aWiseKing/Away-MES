<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">AWAY-MES</h1>
<h4 align="center">🏭 基于 Spring Boot + Vue 的智能化制造执行系统 (MES)</h4>

<p align="center">
    <img src="https://img.shields.io/badge/Spring%20Boot-2.5.14-green.svg" alt="Spring Boot">
    <img src="https://img.shields.io/badge/Vue-2.6.12-brightgreen.svg" alt="Vue">
    <img src="https://img.shields.io/badge/Element%20UI-2.15.12-blue.svg" alt="Element UI">
    <img src="https://img.shields.io/badge/License-MIT-orange.svg" alt="License">
</p>
<p align="center">
    <a href="./README_EN.md">English</a> | <span>简体中文</span>
</p>

---

## 📖 项目介绍 | Introduction

**AWAY-MES** 是一款现代化、模块化的制造执行系统（Manufacturing Execution System）。它基于经典的 **RuoYi-Vue** 框架进行深度定制与扩展，专为中小型制造企业打造。

系统旨在解决生产过程中的黑箱问题，实现从订单下达到产品入库的全流程透明化管理。通过集成**生产计划**、**物料管理**、**质量控制**、**设备维护**等核心功能，AWAY-MES 帮助企业提高生产效率，降低制造成本，实现数字化转型。 🚀

---

## 🛠️ 技术栈 | Tech Stack

本项目采用前后端分离架构，技术选型成熟稳定。

### 🔙 后端 (Backend)
| 技术 | 说明 |
| :--- | :--- |
| **Spring Boot** | 核心框架 |
| **Spring Security** | 安全认证容器 |
| **MyBatis** | ORM 框架 |
| **Redis** | 缓存中间件 |
| **Druid** | 数据库连接池 |
| **Quartz** | 任务调度 |
| **JWT** | 身份验证 |

### 🎨 前端 (Frontend)
| 技术 | 说明 |
| :--- | :--- |
| **Vue.js** | 核心 MVVM 框架 |
| **Element UI** | UI 组件库 |
| **Axios** | HTTP 客户端 |
| **Vuex** | 状态管理 |
| **ECharts** | 数据可视化 |
| **Sass** | CSS 预处理器 |

---

## ✨ 核心功能 | Features

### 🏭 生产中心 (Production Center)
*   📅 **计划管理**：生产计划排程，灵活调整生产进度。
*   📝 **工单管理**：生产工单的发布、执行、暂停、完成与取消。
*   🔨 **工序管理**：精细化管控生产工序与工艺路线。

### 📦 仓储中心 (Storage Center)
*   🏭 **库房管理**：多仓库、多库位管理。
*   🧱 **物料管理**：物料基本信息、规格型号、计量单位维护。
*   📊 **库存监控**：实时库存查询，出入库记录追踪。
*   🔧 **刀具管理**：刀具全生命周期管理。

### ✅ 质检中心 (Quality Center)
*   🔍 **过程质检**：生产过程中的质量检验与记录。
*   📈 **质量监控**：多维度产品质量数据分析与监控。

### 🛒 订单中心 (Order Center)
*   📜 **订单全流程**：从接单到交付的全链路状态跟踪。
*   📊 **进度可视**：实时查看订单执行情况。

### 🤝 综合管理 (Comprehensive)
*   🏢 **客户/供应商**：客户与供应商基础信息管理。
*   📁 **文件管理**：系统内部文件与图纸的统一存储与管理。

### ⚙️ 系统基础 (System Base)
*   👥 **用户与权限**：用户、角色、部门、岗位管理，支持数据权限隔离。
*   🛠️ **开发工具**：代码生成器、表单构建器、API 文档 (Swagger)。
*   👀 **系统监控**：在线用户、服务器监控、缓存监控、连接池监控。
*   📝 **日志管理**：操作日志、登录日志、定时任务日志。

---

## 🚀 快速开始 | Quick Start

### 环境要求
*   JDK >= 1.8
*   MySQL >= 5.7
*   Redis >= 3.0
*   Node.js >= 12

### 运行脚本
项目提供了便捷的脚本位于 `bin/` 目录下：
*   `bin/run.bat`: 启动后端服务
*   `bin/play-web.ps1`: 启动前端服务
*   `bin/clean.bat`: 清理项目

---

## 📄 开源协议 | License
本项目采用 [MIT](https://opensource.org/licenses/MIT) 协议。

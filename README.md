# 🗳️ Online Voting System

An Android-based Online Voting System that allows registered voters to securely log in, view candidates, and cast their votes digitally.

The system is developed to make the voting process easier, faster, and more organized while reducing manual voting procedures.

---

## 📱 Project Overview

The Online Voting System is an Android application connected with a PHP and MySQL backend.

Users can register and log in using their voter credentials. After completing their profile information, voters can view candidates according to their election area and cast their vote.

An administrator can manage candidates and view election results.

---

## ✨ Features

### 👤 Voter Features

- 🔐 Secure voter login
- 📝 New voter registration
- 👤 Update voter profile
- 🆔 NID number and election area management
- 🗳️ View available candidates
- ✅ Cast vote
- 🚫 Prevent multiple voting
- 📊 View voting-related information

### 👨‍💼 Admin Features

- 🔐 Admin login
- ➕ Add new candidates
- 👥 Manage candidate information
- 📊 View election results
- 🗺️ Manage candidates according to election area
- 🚪 Admin logout

---

## 🛠️ Technologies Used

### Android Application

- Java
- Android Studio
- RecyclerView
- Volley Library

### Backend

- PHP
- REST-style PHP APIs

### Database

- MySQL

### Development Environment

- Android Studio
- Laragon
- MySQL
- Git & GitHub

---

## 🏗️ System Architecture

```text
┌─────────────────────────┐
│     Android App         │
│                         │
│  Java + XML + Volley    │
└────────────┬────────────┘
             │
             │ HTTP Requests
             ▼
┌─────────────────────────┐
│      PHP Backend        │
│                         │
│ Login / Register        │
│ Profile / Candidates    │
│ Voting / Results        │
└────────────┬────────────┘
             │
             │ SQL Queries
             ▼
┌─────────────────────────┐
│       MySQL Database    │
│                         │
│ Voters                  │
│ Candidates              │
│ Votes                   │
│ Admins                  │
└─────────────────────────┘

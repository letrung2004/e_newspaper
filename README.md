# News Sharing System Integrated with Artificial Intelligence (Microservice Architecture)
The AI-powered smart notification system **summarizes** and **converts article text to speech**, while also supporting users through the RAG (Retrieval-Augmented Generation) chatbot.

##  Key Features

-  **Summarize and convert text to speech** using Gemini API.
-  **RAG Chatbot** responds based on article content using embedding & vector search techniques.
-  **Content segmentation** based on `.` to optimize embedding.
-  **User system** managed via Identity & Profile service.
-  **Smart notification** using Notification Service.
-  **Search articles** by content.
-  **Modern** user interface converted from [ThemeWagon](https://themewagon.com/) HTML template to **ReactJS**.

---

##  System architecture

<img width="1328" height="718" alt="image" src="https://github.com/user-attachments/assets/c216966c-c6a0-4234-93d5-60e41f306ce7" />

> Using microservices model, connecting via API Gateway (Spring Cloud Gateway). Data is distributed between MongoDB, Neo4J and MySQL.

---

##  Main services

| Service             | Description                          | Database   |
|---------------------|----------------------------------|------------|
| API Gateway         | Request routing              | -          |
| Identity Service    | User account management    | MySQL      |
| Profile Service     | Manage user profiles      | Neo4J      |
| Post Service        | Manage post                    | MongoDB    |
| Article Service     | Article management                | MongoDB    |
| Notification Service| Send notification to user    | MongoDB    |
| Search Service      | Search article content       | MongoDB    |
| File Service        | File and file storage             | MongoDB    |
| AI Service          |Summarization + Text to Speech + Chatbot (RAG, Gemini + Embedding) | - |

---



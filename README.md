# JAVA Email Server

This project implements a Bridge Email Server for sending emails via Gmail, Walla, and Yahoo without logging into each vendor separately.

Each employee has email accounts under different domains. The system identifies the appropriate email provider based on the sender's address and sends accordingly.

Features include:  
- Simple Email Client: To, From, Body  
- Appending email timestamp and body size to `emails-log.csv`  

Built with Spring Boot, Docker, RabbitMQ, and Kubernetes.  
Supports multithreading for high-performance message processing.  

📌 See attached design diagram for system architecture.



![diagram](https://github.com/user-attachments/assets/35845fa6-3e62-4de2-9d2b-75920ee3fdd2)

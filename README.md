JAVA_Email_Server

This project implements a Bridge Email Server for sending emails via Gmail, Walla, and Yahoo without logging into each vendor separately.

Each employee has three email accounts (one per vendor). The system routes the email to the correct SMTP server based on the sender's address.

Features include:  
- Simple Email Client: To, From, Body 
- Appending email timestamp and body size to `emails-log.csv`  

Built with Spring Boot, Docker, RabbitMQ, and Kubernetes.  
Supports multithreading for high-performance message processing.  

📌 See attached design diagram for system architecture.



![diagram](https://github.com/user-attachments/assets/35845fa6-3e62-4de2-9d2b-75920ee3fdd2)

package com.example.email.model;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

public class EmailMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Recipient (to) is required")
    private String to;

    @NotBlank(message = "Sender (from) is required") 
    private String from;

    @NotBlank(message = "Email body is required")
    private String body;

    public EmailMessage() {
    }

    public EmailMessage(String to, String from, String body) {
        this.to = to;
        this.from = from;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
                "to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailMessage that = (EmailMessage) o;
        return Objects.equals(to, that.to) &&
                Objects.equals(from, that.from) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, from, body);
    }
}

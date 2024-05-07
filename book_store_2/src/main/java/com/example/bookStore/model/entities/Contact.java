package com.example.bookStore.model.entities;


import jakarta.persistence.*;

@Entity
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String contactMessage;

    private String contactReply;

    public Contact(String contactMessage, String contactReply, User user) {
        this.contactMessage = contactMessage;
        this.contactReply = contactReply;
        this.user = user;
    }

    public Contact() {
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactMessage() {
        return contactMessage;
    }

    public void setContactMessage(String contactMessage) {
        this.contactMessage = contactMessage;
    }

    public String getContactReply() {
        return contactReply;
    }

    public void setContactReply(String contactReply) {
        this.contactReply = contactReply;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

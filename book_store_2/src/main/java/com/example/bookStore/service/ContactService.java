package com.example.bookStore.service;


import com.example.bookStore.model.entities.Contact;
import com.example.bookStore.model.entities.User;
import com.example.bookStore.model.repo.AccountRepo;
import com.example.bookStore.model.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private AccountRepo accountRepo;

    public List<Contact> getAllContacts(){
        return contactRepo.findAll();
    }

    public List<Contact> getAllContactsAfterSetEmails(){
        List<Contact> contacts= getAllContacts();
        for (int i = 0; i < contacts.size(); i++) {
            contacts.get(i).getUser().setEmail(
                  accountRepo.findEmailByAccountId(contacts.get(i).getUser().getId())
            );

        }
        return contacts;
    }

    public void updateContact(Contact contact){
        contactRepo.save(contact);
    }
    public void addContact(Contact contact,User user){
        contact.setUser(user);
        contactRepo.save(contact);
    }


    public void deleteContact(int id){
        contactRepo.deleteById(id);
    }

//    public void setReplyToMessage(String reply,){
//
//    }
}

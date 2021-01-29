package com.snebot.fbmoll.data;

import io.quarkus.mongodb.panache.MongoEntity;
import org.apache.commons.lang3.StringUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MongoEntity(database = "contact", collection = "book")
public class ContactBook {
    @BsonId
    private ObjectId id;
    private String name;
    private List<Contact> contacts = new ArrayList<>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public ContactBook() {
    }

    public ContactBook(String name, List<Contact> contacts) {
        this.name = name;
        this.contacts = contacts;
    }

    /**
     * Find a Contact by id.
     *
     * @param id Contact id.
     * @return Contact with matching id, null if no contacts were found.
     */
    public Contact findContactById(ObjectId id) {
        Contact contact = null;
        for (int i = 0; i < this.contacts.size() && contact == null; i++) {
            Contact item = this.contacts.get(i);
            if (item.getId().equals(id)) contact = item;
        }
        return contact;
    }

    /**
     * Add a new Contact to ContactBook list.
     *
     * @param contact Contact to add.
     */
    public void add(Contact contact) {
        if (contact == null) return;
        contact.setId(new ObjectId(new Date()));
        this.contacts.add(contact);
    }

    /**
     * Remove Contact from ContactBook list.
     *
     * @param contact Contact to remove.
     */
    public void remove(Contact contact) {
        if (contact == null) return;
        this.contacts.remove(contact);
    }

    /**
     * Update ContactBook with new data by id.
     *
     * @param id ContactBook id.
     * @param data New ContactBook data.
     */
    public void update(ObjectId id, Contact data) {
        if (id == null || data == null) return;
        Contact contact = findContactById(id);
        if (contact != null) contact.copy(data);
    }

    /**
     * Copy ContactBook data.
     *
     * @param data ContactBook data to copy.
     */
    public void copy(ContactBook data) {
        if (data == null) return;
        if (data.name != null) this.name = data.name;
        if (data.contacts != null) this.contacts = data.contacts;
    }

    /**
     * Create a new copy from ContactBook data.
     *
     * @param data ContactBook data to copy.
     * @return New ContactBook.
     */
    public static ContactBook newCopy(ContactBook data) {
        ContactBook book = new ContactBook();
        book.copy(data);
        return book;
    }

    /**
     * Checks if contact books have same data.
     *
     * @param book ContactBook data.
     * @return True if similar, false otherwise.
     */
    public boolean similar(ContactBook book) {
        return StringUtils.equals(book.getName(), this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ContactBook)) return false;
        ContactBook book = (ContactBook) obj;
        return (this.id == null || this.id.equals(book.id)) &&
                StringUtils.equals(this.name, book.name);
    }
}
package com.snebot.fbmoll.data;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

public class Contact {
    private ObjectId id;
    private String name;
    private String lastName;
    private String email;
    private String address;
    private String phone;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contact() {
    }

    public Contact(String name, String lastName, String email, String address, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Copy contact data.
     *
     * @param contact Contact data to copy.
     */
    public void copy(Contact contact) {
        if (contact == null) return;
        if (contact.name != null) this.name = contact.name;
        if (contact.lastName != null) this.lastName = contact.lastName;
        if (contact.email != null) this.email = contact.email;
        if (contact.address != null) this.address = contact.address;
        if (contact.phone != null) this.phone = contact.phone;
    }

    /**
     * Create a new copy from contact data.
     *
     * @param data Contact data.
     * @return New contact copy.
     */
    public static Contact newCopy(Contact data) {
        Contact contact = new Contact();
        contact.copy(data);
        return contact;
    }

    /**
     * Checks if contacts have same data.
     *
     * @param contact Contact data.
     * @return True if similar, false otherwise.
     */
    public boolean similar(Contact contact) {
        return StringUtils.equals(contact.getName(), this.name) &&
                StringUtils.equals(contact.getLastName(), this.lastName) &&
                StringUtils.equals(contact.getEmail(), this.email) &&
                StringUtils.equals(contact.getAddress(), this.address) &&
                StringUtils.equals(contact.getPhone(), this.phone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contact)) return false;
        Contact contact = (Contact) obj;
        return (this.id == null || this.id.equals(contact.id)) &&
                this.similar(contact);
    }
}
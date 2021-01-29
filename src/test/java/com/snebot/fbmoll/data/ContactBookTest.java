package com.snebot.fbmoll.data;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class ContactBookTest {
    @Test
    void testContact() {
        Contact contactData = new Contact();
        contactData.setName("name1");
        contactData.setLastName("lastName1");
        contactData.setEmail("email1@test.com");
        contactData.setAddress("address1");
        contactData.setPhone("111111111");
        Contact contact = Contact.newCopy(contactData);
        Assertions.assertEquals(contact, contactData);
    }

    @Test
    void testContactBook() {
        ContactBook book = new ContactBook();
        book.setName("Test contact book");

        Contact contact1 = new Contact();
        contact1.setName("name1");
        contact1.setLastName("lastName1");
        contact1.setEmail("email1@test.com");
        contact1.setAddress("address1");
        contact1.setPhone("111111111");
        book.add(contact1);

        Contact contact2 = new Contact();
        contact2.setName("name2");
        contact2.setLastName("lastName2");
        contact2.setEmail("email2@test.com");
        contact2.setAddress("address2");
        contact2.setPhone("222222222");
        book.add(contact2);

        ContactBook copy = ContactBook.newCopy(book);
        Assertions.assertEquals(copy, book);

        List<Contact> contactList = book.getContacts();
        Assertions.assertTrue(contactList.get(0).equals(contact1) && contactList.get(1).equals(contact2));

        book.remove(contact1);
        book.remove(contact2);
        Assertions.assertEquals(book.getContacts().size(), 0);
    }
}

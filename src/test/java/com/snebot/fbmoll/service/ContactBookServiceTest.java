package com.snebot.fbmoll.service;

import com.snebot.fbmoll.data.ContactBook;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactBookServiceTest {
    @Inject
    ContactBookService service;
    private static ContactBook savedInstance;

    @Test
    @Order(1)
    public void testAddContactBook() {
        ContactBook book = new ContactBook("TestContactBook", null);
        this.service.deleteAll();
        savedInstance = this.service.add(book);
        Assertions.assertNotNull(savedInstance);
        Assertions.assertEquals(savedInstance, book);
    }

    @Test
    @Order(2)
    public void testFindAll() {
        List<ContactBook> all = this.service.findAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(all.size(), 1);
        Assertions.assertEquals(savedInstance, all.get(0));
    }

    @Test
    @Order(3)
    public void testFindById() {
        ContactBook book = this.service.findById(savedInstance.getId());
        Assertions.assertNotNull(book);
        Assertions.assertEquals(savedInstance, book);
    }

    @Test
    @Order(4)
    public void testUpdate() {
        savedInstance.setName("UpdatedContactBook");
        this.service.update(savedInstance);
        testFindById();
    }

    @Test
    @Order(5)
    public void testDelete() {
        this.service.delete(savedInstance);
        List<ContactBook> all = this.service.findAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(all.size(), 0);
    }

    @Test
    @Order(6)
    public void testClean() {
        this.service.deleteAll();
    }
}
package com.snebot.fbmoll.service;

import com.snebot.fbmoll.data.ContactBook;
import com.snebot.fbmoll.data.ContactBookRepository;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ContactBookService {
    @Inject
    ContactBookRepository repository;

    /**
     * Find all ContactBooks in batabase.
     *
     * @return ContactBook list.
     */
    public List<ContactBook> findAll() {
        return this.repository.findAll().list();
    }

    /**
     * Find ContactBook by id.
     *
     * @param id ContactBook id.
     * @return ContactBook with matching id.
     */
    public ContactBook findById(ObjectId id) {
        return this.repository.findById(id);
    }

    /**
     * Add a new ContactBook to the database.
     *
     * @param book ContactBook to add.
     * @return Added ContactBook.
     */
    public ContactBook add(ContactBook book) {
        this.repository.persist(book);
        return book;
    }

    /**
     * Update ContactBook data to database.
     *
     * @param book Updated ContactBook.
     */
    public void update(ContactBook book) {
        this.repository.update(book);
    }

    /**
     * Delete ContactBook from database.
     *
     * @param book ContactBook to delete.
     */
    public void delete(ContactBook book) {
        this.repository.delete(book);
    }

    /**
     * Delete all ContactBooks in database.
     */
    public void deleteAll() {
        this.repository.deleteAll();
    }
}

package com.snebot.fbmoll.resource;

import com.snebot.fbmoll.data.Contact;
import com.snebot.fbmoll.data.ContactBook;
import com.snebot.fbmoll.helper.ResponseHelper;
import com.snebot.fbmoll.service.ContactBookService;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContactBookResource {
    @Inject
    ContactBookService service;

    @GET
    public Response contactBookAll() {
        return ResponseHelper.process(() -> this.service.findAll());
    }

    @POST
    public Response contactBookCreate(ContactBook contactBook) {
        return ResponseHelper.process(contactBook, book -> this.service.add(book));
    }

    @GET
    @Path("{id}")
    public Response contactBookId(@PathParam("id") ObjectId id) {
        return ResponseHelper.process(id, oid -> {
            ContactBook book = this.service.findById(oid);
            if (book == null) return Response.Status.NOT_FOUND;
            return book;
        });
    }

    @PUT
    @Path("{id}")
    public Response contactBookUpdate(@PathParam("id") ObjectId id, ContactBook data) {
        return ResponseHelper.process(id, data, (oid, contactBook) -> {
            ContactBook book = this.service.findById(oid);
            if (book == null) return Response.Status.NOT_FOUND;
            book.copy(contactBook);
            this.service.update(book);
            return book;
        });
    }

    @DELETE
    @Path("{id}")
    public Response contactBookDelete(@PathParam("id") ObjectId id) {
        return ResponseHelper.process(id, oid -> {
            ContactBook book = this.service.findById(oid);
            if (book == null) return Response.Status.NOT_FOUND;
            this.service.delete(book);
            return book;
        });
    }

    @GET
    @Path("{id}/contact")
    public Response contact(@PathParam("id") ObjectId id) {
        return ResponseHelper.process(id, oid -> {
            ContactBook book = this.service.findById(oid);
            if (book == null) return Response.Status.NOT_FOUND;
            return book.getContacts();
        });
    }

    @POST
    @Path("{id}/contact")
    public Response contactCreate(@PathParam("id") ObjectId oid, Contact data) {
        return ResponseHelper.process(oid, data, (id, contact) -> {
            ContactBook book = this.service.findById(id);
            if (book == null) return Response.Status.NOT_FOUND;
            book.add(contact);
            this.service.update(book);
            return contact;
        });
    }

    @GET
    @Path("{id}/contact/{cid}")
    public Response contactId(@PathParam("id") ObjectId oid, @PathParam("cid") ObjectId ocid) {
        return ResponseHelper.process(oid, ocid, (id, cid) -> {
            ContactBook book = this.service.findById(id);
            if (book == null) return Response.Status.NOT_FOUND;
            Contact contact = book.findContactById(cid);
            if (contact == null) return Response.Status.NOT_FOUND;
            return contact;
        });
    }

    @DELETE
    @Path("{id}/contact/{cid}")
    public Response contactDelete(@PathParam("id") ObjectId oid, @PathParam("cid") ObjectId ocid) {
        return ResponseHelper.process(oid, ocid, (id, cid) -> {
            ContactBook book = this.service.findById(id);
            if (book == null) return Response.Status.NOT_FOUND;
            Contact contact = book.findContactById(cid);
            if (contact == null) return Response.Status.NOT_FOUND;
            book.remove(contact);
            this.service.update(book);
            return contact;
        });
    }

    @PUT
    @Path("{id}/contact/{cid}")
    public Response contactUpdate(@PathParam("id") ObjectId oid, @PathParam("cid") ObjectId ocid, Contact data) {
        return ResponseHelper.process(oid, ocid, data, (id, cid, contact) -> {
            ContactBook book = this.service.findById(id);
            if (book == null) return Response.Status.NOT_FOUND;
            if (book.findContactById(cid) == null) return Response.Status.NOT_FOUND;
            book.update(cid, contact);
            this.service.update(book);
            return contact;
        });
    }
}

package com.snebot.fbmoll.resource;

import com.snebot.fbmoll.data.CSVRecord;
import com.snebot.fbmoll.helper.ResponseHelper;
import com.snebot.fbmoll.service.CSVRecordService;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/csv")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CSVResource {
    @Inject
    CSVRecordService service;

    @GET
    public Response csvAll() {
        return ResponseHelper.process(() -> this.service.findAll());
    }

    @POST
    public Response processCSV(CSVRecord request) {
        return ResponseHelper.process(request, csvRecord -> this.service.processRequest(csvRecord));
    }

    @GET
    @Path("{id}")
    public Response getCSV(@PathParam("id") ObjectId id) {
        return ResponseHelper.process(id, oid -> {
            CSVRecord record = this.service.findById(oid);
            if (record == null) return Response.Status.NOT_FOUND;
            return record;
        });
    }

    @DELETE
    @Path("{id}")
    public Response deleteCSV(@PathParam("id") ObjectId id) {
        return ResponseHelper.process(id, oid -> {
            CSVRecord record = this.service.delete(oid);
            if (record == null) return Response.Status.NOT_FOUND;
            return record;
        });
    }
}

package com.snebot.fbmoll.service;

import com.snebot.fbmoll.data.CSVObject;
import com.snebot.fbmoll.data.CSVRecord;
import com.snebot.fbmoll.data.CSVRecordRepository;
import com.snebot.fbmoll.helper.CSVParser;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CSVRecordService {
    @Inject
    CSVRecordRepository repository;

    /**
     * Find all CSVRecords.
     *
     * @return CSVRecord list.
     */
    public List<CSVRecord> findAll() {
        return this.repository.findAll().list();
    }

    /**
     * Find CSVRecord by id.
     *
     * @param id CSVRecord id.
     * @return CSVRecord with matching id.
     */
    public CSVRecord findById(ObjectId id) {
        return this.repository.findById(id);
    }

    /**
     * Process CSV request.
     *
     * @param request CSVRecord request.
     * @return Processed CSVRecord.
     */
    public CSVRecord processRequest(CSVRecord request) {
        CSVParser parser = new CSVParser(request.getProperties());
        List<CSVObject> elements = parser.parse(request.getContent());
        String xml = parser.convert(elements, request.getName());
        request.setXml(xml);
        this.repository.persist(request);
        return request;
    }

    /**
     * Delete CSVRecord by ID.
     *
     * @param id CSVRecord ID.
     * @return Deleted CSVRecord.
     */
    public CSVRecord delete(ObjectId id) {
        CSVRecord record = this.repository.findById(id);
        this.repository.deleteById(id);
        return record;
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }
}

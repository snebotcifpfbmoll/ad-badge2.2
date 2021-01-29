package com.snebot.fbmoll.data;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CSVRecordRepository implements PanacheMongoRepository<CSVRecord> {
}
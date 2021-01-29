package com.snebot.fbmoll.data;

import com.snebot.fbmoll.helper.CSVParserProperties;
import io.quarkus.mongodb.panache.MongoEntity;
import org.apache.commons.lang3.StringUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

@MongoEntity(database = "csv", collection = "csv_record")
public class CSVRecord {
    @BsonId
    private ObjectId id;
    private String name;
    private CSVParserProperties properties;
    private String content;
    @BsonIgnore
    private String xml;

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

    public CSVParserProperties getProperties() {
        return properties;
    }

    public void setProperties(CSVParserProperties properties) {
        this.properties = properties;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public CSVRecord() {
    }

    public CSVRecord(String name, CSVParserProperties properties, String content) {
        this.name = name;
        this.properties = properties;
        this.content = content;
    }

    /**
     * Determine if CSVRecords are similar.
     *
     * @param record CSVRecord data to compare.
     * @return True if similar, false otherwise.
     */
    public boolean similar(CSVRecord record) {
        return StringUtils.equals(this.name, record.name) &&
                this.properties.equals(record.properties);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof CSVRecord)) return false;
        CSVRecord record = (CSVRecord) obj;
        return this.id == null || this.id.equals(record.id);
    }
}
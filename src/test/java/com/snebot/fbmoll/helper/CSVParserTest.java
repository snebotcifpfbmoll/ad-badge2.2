package com.snebot.fbmoll.helper;

import com.snebot.fbmoll.data.CSVObject;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import java.util.List;

@QuarkusTest
public class CSVParserTest {
    @Test
    void testCSVParse() {
        String content = "col1,col2,col3,col4\ntest,\"this is, a test\",final test\n\"test, number, 2,\",ok,maybe,it works!";
        CSVParserProperties properties = new CSVParserProperties(',', '\n', '\"', true, false);
        CSVParser parser = new CSVParser(properties);
        List<CSVObject> objects = parser.parse(content);
        Document document = parser.convertToDocument(objects, "root");
        Assertions.assertNotNull(document);
        String docToXML = parser.documentToXML(document);
        Assertions.assertNotNull(docToXML);
        String result = parser.convert(objects, "root");
        Assertions.assertNotNull(result);
    }

    @Test
    void testStripCharacters() {
        CSVParser parser = new CSVParser();
        String correct = "Test-1";
        String content = "0-Te#st-/1-";
        String result = parser.stripCharacters(content);
        Assertions.assertEquals(result, correct);
    }
}

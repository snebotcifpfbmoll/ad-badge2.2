package com.snebot.fbmoll.service;

import com.snebot.fbmoll.data.CSVRecord;
import com.snebot.fbmoll.helper.CSVParserProperties;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CSVRecordServiceTest {
    @Inject
    CSVRecordService service;
    private static CSVRecord savedInstance;

    @Test
    @Order(1)
    void testProcessRequest() {
        CSVParserProperties properties = new CSVParserProperties(';', '\n', '\"', true, false);
        String content = "Identifier;Access code;Recovery code;First name;Last name;Department;Location\\n9012;12se74;rb9012;Rachel;Booker;Sales;Manchester\\n2070;04ap67;lg2070;Laura;Grey;Depot;London;Mallorca\\n4081;30no86;cj4081;Craig;Johnson;Depot;London\\n9346;14ju73;mj9346;Mary;Jenkins;Engineering;Manchester\\n5079;09ja61;js5079;Jamie;Smith;Engineering;Manchester";
        CSVRecord record = new CSVRecord("test", properties, content);
        CSVRecord correct = new CSVRecord("test", properties, content);
        correct.setXml("<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\" standalone=\\\"no\\\"?><test><element><Department>Sales</Department><Identifier>9012</Identifier><Recovery-code>rb9012</Recovery-code><Access-code>12se74</Access-code><First-name>Rachel</First-name><Last-name>Booker</Last-name><Location>Manchester</Location></element><element><Department>Depot</Department><Identifier>2070</Identifier><Recovery-code>lg2070</Recovery-code><value-7>Mallorca</value-7><Access-code>04ap67</Access-code><First-name>Laura</First-name><Last-name>Grey</Last-name><Location>London</Location></element><element><Department>Depot</Department><Identifier>4081</Identifier><Recovery-code>cj4081</Recovery-code><Access-code>30no86</Access-code><First-name>Craig</First-name><Last-name>Johnson</Last-name><Location>London</Location></element><element><Department>Engineering</Department><Identifier>9346</Identifier><Recovery-code>mj9346</Recovery-code><Access-code>14ju73</Access-code><First-name>Mary</First-name><Last-name>Jenkins</Last-name><Location>Manchester</Location></element><element><Department>Engineering</Department><Identifier>5079</Identifier><Recovery-code>js5079</Recovery-code><Access-code>09ja61</Access-code><First-name>Jamie</First-name><Last-name>Smith</Last-name><Location>Mancheste</Location></element></test>");
        this.service.deleteAll();
        savedInstance = this.service.processRequest(record);
        Assertions.assertTrue(savedInstance.similar(correct));
    }

    @Test
    @Order(2)
    void testFindAll() {
        List<CSVRecord> all = this.service.findAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(all.size(), 1);
        Assertions.assertEquals(savedInstance, all.get(0));
    }

    @Test
    @Order(3)
    void testDelete() {
        this.service.delete(savedInstance.getId());
        List<CSVRecord> all = this.service.findAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(all.size(), 0);
    }

    @Test
    @Order(4)
    void testClean() {
        this.service.deleteAll();
    }
}
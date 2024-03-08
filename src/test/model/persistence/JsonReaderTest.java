package model.persistence;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// This code is modeled after JsonSerializationDemo from the Phase 2 demo, written by Paul Carter.
// Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Tasks t = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Tasks t = reader.read();
            assertEquals(0, t.getTaskListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Tasks t = reader.read();
            List<String> thingies = t.getTaskList();
            assertEquals(2, thingies.size());
            assertEquals("journaling", thingies.get(0));
            assertEquals("crying", thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
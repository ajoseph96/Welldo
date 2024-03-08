package model.persistence;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This code is modeled after JsonSerializationDemo from the Phase 2 demo, written by Paul Carter.
// Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Tasks t = new Tasks();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Tasks t = new Tasks();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            t = reader.read();
            assertEquals(0, t.getTaskListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Tasks t = new Tasks();
            t.addTaskList("journaling");
            t.addTaskList("crying");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            t = reader.read();
            List<String> thingies = t.getTaskList();
            assertEquals(2, thingies.size());
            assertEquals("journaling", thingies.get(0));
            assertEquals("crying", thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
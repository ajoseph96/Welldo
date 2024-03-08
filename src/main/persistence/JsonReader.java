package persistence;

import model.*;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// This code is modeled after JsonSerializationDemo from the Phase 2 demo, written by Paul Carter.
// Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Tasks from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Tasks read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTasks(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Tasks from JSON object and returns it
    private Tasks parseTasks(JSONObject jsonObject) {
        Tasks t = new Tasks();
        addTasks(t, jsonObject);
        return t;
    }


    // MODIFIES: t
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addTasks(Tasks t, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("taskList");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addThingy(t, nextThingy);
        }
    }

    // MODIFIES: t
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(Tasks t, JSONObject jsonObject) {
        String s = jsonObject.getString("string");
        t.addTaskList(s);
    }


}

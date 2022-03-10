package com.github.kyleryvn.taxservice.dao;

import com.github.kyleryvn.taxservice.utility.ResourceUtility;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class StateDAO {
    private final Map<String, String> stateURLS;

    public StateDAO() {
        this.stateURLS = addStatesToMap();
    }

    public Map<String, String> getStateURLS() {
        return stateURLS;
    }

    private Map<String, String> addStatesToMap() {
        InputStream inputStream = ResourceUtility.getFileFromResourceAsStream("docs/states.json");
        Map<String, String> map = new HashMap<>();
        String key = null;
        String value = null;

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             JsonReader jsonReader = new JsonReader(bufferedReader)) {

            try (jsonReader) {
                // to accept malformed JSON
                // If you can't easily figure out what's causing extra characters at the end and eliminate them,
                // another option is to tell GSON to parse in lenient mode:
                jsonReader.setLenient(true);

                while (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                    JsonToken nextToken = jsonReader.peek();

                    if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {
                        jsonReader.beginObject();
                    } else if (JsonToken.BEGIN_ARRAY.equals(nextToken)) {
                        jsonReader.beginArray();
                    } else if (JsonToken.NAME.equals(nextToken)) {
                        key = jsonReader.nextName();
                        //System.out.println("Token KEY >>>> " + key);
                    } else if (JsonToken.STRING.equals(nextToken)) {
                        value = jsonReader.nextString();
                        //System.out.println("Token KEY >>>> " + value);
                    } else if (JsonToken.END_ARRAY.equals(nextToken)) {
                        jsonReader.endArray();
                    } else if (JsonToken.END_OBJECT.equals(nextToken)) {
                        jsonReader.endObject();
                    } else if (JsonToken.NULL.equals(nextToken)) {
                        jsonReader.nextNull();
                        //System.out.println("Token Value >>>> null");
                    }

                    if (key == null || value == null) {
                        map.remove(key, value);
                    } else {
                        map.put(key, value);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}

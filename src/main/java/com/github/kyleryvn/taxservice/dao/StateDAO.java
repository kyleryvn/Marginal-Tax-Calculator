package com.github.kyleryvn.taxservice.dao;

import com.github.kyleryvn.taxservice.utility.ResourceUtility;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *     This class reads from JSON file and parses the data.
 * </p>
 *
 * @author Kyle Schoenhardt
 * @since v1.1.0
 */
public class StateDAO {

    /**
     * <p>
     *     This method utilizes Google's {@link com.google.gson.Gson} library to read JSON file containing an array of
     *     states that do not collect income tax, and parses data into {@link String} objects.
     * </p>
     * @return A {@link Set} containing state abbreviations
     */
    public static Set<String> getStates() {
        InputStream inputStream = ResourceUtility.getFileFromResourceAsStream("docs/statesWithoutIncomeTax.json");
        Set<String> statesWithoutIncomeTax = new HashSet<>();
        String value = null;

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             JsonReader jsonReader = new JsonReader(bufferedReader)) {

            try (jsonReader) {
                // To accept malformed JSON
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
                        jsonReader.nextName();
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

                    if (value != null) {
                        statesWithoutIncomeTax.add(value);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return statesWithoutIncomeTax;
    }
}

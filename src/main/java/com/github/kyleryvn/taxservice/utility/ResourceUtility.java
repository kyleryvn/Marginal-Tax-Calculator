package com.github.kyleryvn.taxservice.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ResourceUtility {

    public static List<String> getResourceAsList(String filename) {
        return getResourceAsList(filename, 0);
    }

    public static List<String> getResourceAsList(String filename, int lineSkip) {
        return getResourceAsList(filename, lineSkip, e -> e);
    }

    /**
     * @param filename Name of file to be used
     * @param lineSkip Amount of lines to skip in file
     * @param conversion Convert file to specific object
     * @param <T> Generic type
     * @return ArrayList
     */
    public static <T> List<T> getResourceAsList(String filename, int lineSkip, Function<String, T> conversion) {
        InputStream inputStream = getFileFromResourceAsStream(filename);

        // Change predicate to filter for specific criteria
        Predicate<? super String> predicate = p -> true;

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            return bufferedReader.lines()
                    .skip(lineSkip)
                    //.filter(predicate)
                    .map(conversion)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    // Get a file from the resources folder
    // Works everywhere, IDE, unit test, and JAR file.
    public static InputStream getFileFromResourceAsStream(String filename) {
        ClassLoader classLoader = ResourceUtility.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);

        // The stream holding file content
        if (inputStream == null) {
            throw new IllegalArgumentException("ERROR: File cannot be found");
        }
        else {
            return inputStream;
        }
    }
}

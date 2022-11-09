package com.github.kyleryvn.taxservice.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class accesses files from the resources folder.
 */
public class ResourceUtility {

    public static List<String> getResourceAsList(String filename) {
        return getResourceAsList(filename, 0);
    }

    public static List<String> getResourceAsList(String filename, int lineSkip) {
        return getResourceAsList(filename, lineSkip, e -> e);
    }

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

    public static Set<String> getResourceAsSet(String filename) {
        return getResourceAsSet(filename, 0);
    }

    public static Set<String> getResourceAsSet(String filename, int lineSkip) {
        return getResourceAsSet(filename, lineSkip, e-> e);
    }

    public static <T> Set<T> getResourceAsSet(String filename, int lineSkip, Function<String, T> conversion) {
        InputStream inputStream = getFileFromResourceAsStream(filename);

        // Change predicate to filter for specific criteria
        Predicate<? super String> predicate = p -> true;

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            return bufferedReader.lines()
                    .skip(lineSkip)
                    //.filter(predicate)
                    .map(conversion)
                    .collect(Collectors.toSet());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new HashSet<>();
    }

    // Get a file from the resources folder
    // Works everywhere, IDE, unit test, and JAR file.
    public static InputStream getFileFromResourceAsStream(String filename) {
        ClassLoader classLoader = ResourceUtility.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);

        // The stream holding file content
        if (inputStream == null) {
            throw new IllegalArgumentException("ERROR: File cannot be found");
        } else {
            return inputStream;
        }
    }
}

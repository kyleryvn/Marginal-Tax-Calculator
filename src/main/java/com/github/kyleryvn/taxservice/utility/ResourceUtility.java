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
 * This class accesses files from the internal resources folder.
 */
public class ResourceUtility {

    /**
     * <p>
     *     Processes provided file into a specified object.
     * </p>
     * @param filename Name of file to be accessed
     * @param lineSkip Number of lines to skip before reading
     * @param conversion A {@link Function} to convert file data to an object
     * @return A {@link List} containing parsed file data mapped to an object. Object is specified in conversion parameter
     * @param <T> Generic type
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

    /**
     * <p>
     *     Processes provided file into a specified object.
     * </p>
     * @param filename Name of file to be accessed
     * @param lineSkip Number of lines to skip before reading
     * @param conversion A {@link Function} to convert file data to an object
     * @return A {@link Set} containing parsed file data mapped to an object. Object is specified in conversion parameter
     * @param <T> Generic type
     */
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

    /**
     * <p>
     *     This method accesses a file from the resources folder. This method is needed to allow accessibility from
     *     any environment, such as the IDE, unit tests, and JAR files.
     * </p>
     * @param filename Name of file to be accessed
     * @return {@link InputStream}
     */
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

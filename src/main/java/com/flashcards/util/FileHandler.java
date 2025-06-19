package com.flashcards.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    /** Read a List<T> from a JSON file. If file doesn't exist, return empty list. */
    public static <T> List<T> readObjects(String path, Class<T> type) throws IOException {
        Path p = Path.of(path);
        if (Files.notExists(p)) {
            return new ArrayList<>();
        }
        String json = Files.readString(p);
        CollectionType listType =
                mapper.getTypeFactory().constructCollectionType(List.class, type);
        return mapper.readValue(json, listType);
    }

    /** Write a List<T> to a JSON file, creating parent dirs if needed. */
    public static <T> void writeObjects(String path, List<T> list) throws IOException {
        String json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(list);
        Path p = Path.of(path);
        Files.createDirectories(p.getParent());
        Files.writeString(p, json);
    }
}

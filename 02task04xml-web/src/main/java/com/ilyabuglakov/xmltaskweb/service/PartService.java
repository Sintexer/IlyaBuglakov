package com.ilyabuglakov.xmltaskweb.service;

import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.stream.Collectors;

public class PartService {

    private PartService(){}

    public static String collectParts(Collection<Part> parts) throws IOException {
        StringBuilder collected = new StringBuilder();
        for (Part part : parts) {
            collected.append(toString(part));
        }
        return collected.toString();
    }

    public static String toString(Part part) throws IOException {
        InputStream fileContent = part.getInputStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fileContent))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }

}

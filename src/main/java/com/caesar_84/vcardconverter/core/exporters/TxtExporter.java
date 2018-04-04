package com.caesar_84.vcardconverter.core.exporters;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class TxtExporter implements Exporter {

    @Override
    public void export(Map<String, Set<String>> contactList, File to) {
        Path file = Paths.get(to.toURI());

        List<String> lines = contactList.entrySet().stream()
                .map(entry -> {
                    String result = entry.getKey();
                    result += ":\n";
                    result += String.join("\n", entry.getValue());
                    return result;
                })
                .collect(Collectors.toList());

        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

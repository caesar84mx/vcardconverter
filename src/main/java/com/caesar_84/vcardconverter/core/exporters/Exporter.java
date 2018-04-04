package com.caesar_84.vcardconverter.core.exporters;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface Exporter {
    void export(Map<String, Set<String>> contactList, File to) throws IOException;
}

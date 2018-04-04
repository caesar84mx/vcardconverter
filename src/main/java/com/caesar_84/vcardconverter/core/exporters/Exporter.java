package com.caesar_84.vcardconverter.core.exporters;

import java.io.File;
import java.util.Map;
import java.util.Set;

public interface Exporter {
    void export(Map<String, Set<String>> contactList, File to);
}

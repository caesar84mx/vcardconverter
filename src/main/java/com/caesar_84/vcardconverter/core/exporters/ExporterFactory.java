package com.caesar_84.vcardconverter.core.exporters;

import com.caesar_84.vcardconverter.core.aux_classes.ExportFormat;

public class ExporterFactory {
    private ExporterFactory() {}

    public static Exporter getExporter(ExportFormat format) {
        if (format == ExportFormat.DOCX) {
            return new DocxExporter();
        }

        return new TxtExporter();
    }
}

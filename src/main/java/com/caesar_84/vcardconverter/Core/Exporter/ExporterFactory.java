package com.caesar_84.vcardconverter.Core.Exporter;

import com.caesar_84.vcardconverter.Core.Aux.ExportFormat;

public class ExporterFactory {
    private ExporterFactory() {}

    public static Exporter getExporter(ExportFormat format) {
        if (format == ExportFormat.DOCX) {
            return new DocxExporter();
        }

        return new TxtExporter();
    }
}

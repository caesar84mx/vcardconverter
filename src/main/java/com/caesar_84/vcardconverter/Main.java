package com.caesar_84.vcardconverter;

import com.caesar_84.vcardconverter.core.VcardParser;
import com.caesar_84.vcardconverter.core.aux_classes.ExportFormat;
import com.caesar_84.vcardconverter.core.exporters.Exporter;
import com.caesar_84.vcardconverter.core.exporters.ExporterFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    // first parameter: full path to vcard
    // second parameter: output format (docx and txt for now)
    public static void main(String[] args) {
        if (args.length == 2) {
            //if vcard file does not exist or is not a .vcf
            if (!Files.exists(new File(args[0]).toPath())) {
                System.out.println("File: " + args[0] + " does not exist");
                return;
            }
            if (!args[0].substring(args[0].lastIndexOf(".") + 1).equalsIgnoreCase("vcf")) {
                System.out.println("File is not a vcard");
                return;
            }

            Exporter exporter = ExporterFactory.getExporter(ExportFormat.valueOf(args[1].toUpperCase()));
            VcardParser parser = new VcardParser();
            File fileToExport = getFileToExport(new File(args[0]), ExportFormat.valueOf(args[1].toUpperCase()));

            try {
                exporter.export(parser.getContacts(new File(args[0])), fileToExport);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Done");
        } else {
            WindowModeMain.runInWindow(args);
        }
    }

    public static File getFileToExport(File from, ExportFormat format) {
        String parentTo = from.getParent();
        String fileTo = from.getName().substring(0, from.getName().lastIndexOf(".")) + "." + format.toString().toLowerCase();
        return new File(parentTo + File.separator + fileTo);
    }
}

package com.caesar_84.vcardconverter;

import com.caesar_84.vcardconverter.core.Aux.ExportFormat;
import com.caesar_84.vcardconverter.core.Exporter.Exporter;
import com.caesar_84.vcardconverter.core.Exporter.ExporterFactory;
import com.caesar_84.vcardconverter.core.VcardParser;

import java.io.File;
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
            exporter.export(parser.getContacts(new File(args[0])), fileToExport);
            System.out.println("Done");
        } else {
            System.out.println("Wrong parameters number: please, specify full path to a vcard and output format (txt or docx)");
        }
    }

    private static File getFileToExport(File from, ExportFormat format) {
        String parentTo = from.getParent();
        String fileTo = from.getName().substring(0, from.getName().lastIndexOf(".")) + "." + format.toString().toLowerCase();
        System.out.println(parentTo + File.separator + fileTo);
        return new File(parentTo + File.separator + fileTo);
    }
}

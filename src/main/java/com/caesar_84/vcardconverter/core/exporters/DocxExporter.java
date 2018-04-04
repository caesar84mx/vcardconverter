package com.caesar_84.vcardconverter.core.exporters;

import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

class DocxExporter implements Exporter {
    @Override
    public void export(Map<String, Set<String>> contactList, File to) {
        try (FileOutputStream out = new FileOutputStream(to)) {
            XWPFDocument document = new XWPFDocument();

            //create paragraph
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setFontSize(14);
            run.setText("Contacts:");

            // create table
            XWPFTable table = document.createTable(1, 2);
            table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));
            table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(5000));

            //create header row
            XWPFTableRow headerRow = table.getRow(0);
            headerRow.getCell(0).setText("Name");
            headerRow.getCell(1).setText("Phone");

            //export contacts to table
            contactList.forEach((name, phones) -> {
                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(name);
                row.getCell(1).setText(String.join("\n", phones));
            });

            document.write(out);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

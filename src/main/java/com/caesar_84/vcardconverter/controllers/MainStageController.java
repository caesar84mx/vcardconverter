package com.caesar_84.vcardconverter.controllers;

import com.caesar_84.vcardconverter.WindowModeMain;
import com.caesar_84.vcardconverter.core.aux_classes.ExportFormat;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;

public class MainStageController {
    private WindowModeMain main;
    private ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    private Label fileNameLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private RadioButton docxOption;

    @FXML
    private RadioButton txtOption;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Button exportButton;

    @FXML
    private void initialize() {
        exportButton.setDisable(true);
        docxOption.setToggleGroup(toggleGroup);
        txtOption.setToggleGroup(toggleGroup);
    }

    @FXML
    private void handleChooseFile() {
        var fileChooser = new FileChooser();
        var extensionFilter = new FileChooser.ExtensionFilter(
                "Vcard files (*.vcf)", "*.vcf"
        );
        fileChooser.getExtensionFilters().add(extensionFilter);

        var vcfFile = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (vcfFile != null) {
            main.setVcardFile(vcfFile);
            fileNameLabel.setText(vcfFile.getAbsolutePath());
            exportButton.setDisable(false);
            statusLabel.setText("Ready to export");
        }
    }

    @FXML
    private void handleExport() {
        var selected = (RadioButton) toggleGroup.getSelectedToggle();
        if (main.export(ExportFormat.valueOf(selected.getText().toUpperCase()))) {
            statusLabel.setText("Done");
        } else {
            statusLabel.setText("Failed to export");
        }
    }

    public void setMain(WindowModeMain main) {
        this.main = main;
    }
}

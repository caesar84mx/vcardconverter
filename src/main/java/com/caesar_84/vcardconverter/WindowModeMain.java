package com.caesar_84.vcardconverter;

import com.caesar_84.vcardconverter.controllers.MainStageController;
import com.caesar_84.vcardconverter.core.VcardParser;
import com.caesar_84.vcardconverter.core.aux_classes.ExportFormat;
import com.caesar_84.vcardconverter.core.exporters.ExporterFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class WindowModeMain extends Application {
    private static final String APP_VERSION = "1.1";
    private static final String APP_NAME = "Vcard Converter " + APP_VERSION;

    private File vcardFile;

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(APP_NAME);
        this.primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("media/icon.png")));

        initApp();
    }

    private void initApp() throws Exception {
        var loader = new FXMLLoader();
        var mainLayout = (AnchorPane) loader.load(getClass().getClassLoader().getResourceAsStream("fxml/MainStage.fxml"));

        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.setResizable(false);

        var mainStageController = (MainStageController) loader.getController();
        mainStageController.setMain(this);

        primaryStage.show();
    }

    public boolean export(ExportFormat format) {
        var exporter = ExporterFactory.getExporter(format);
        var parser = new VcardParser();
        var exportTo = Main.getFileToExport(vcardFile, format);
        try {
            exporter.export(parser.getContacts(vcardFile), exportTo);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    static void runInWindow(String[] args) {
        launch(args);
    }

    public void setVcardFile(File vcardFile) {
        this.vcardFile = vcardFile;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}

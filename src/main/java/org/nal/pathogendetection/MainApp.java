package org.nal.pathogendetection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	private static final Logger log = LoggerFactory.getLogger(MainApp.class);

	public static void main(final String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {

		log.info("Starting Hello JavaFX and Maven demonstration application");

		final String fxmlFile = "/fxml/nal_pathogen.fxml";
		log.debug("Loading FXML for main view from: {}", fxmlFile);
		final FXMLLoader loader = new FXMLLoader();
		final Parent rootNode = (Parent) loader.load(this.getClass().getResourceAsStream(fxmlFile));

		log.debug("Showing JFX scene");
		final Scene scene = new Scene(rootNode, 600, 400);
		scene.getStylesheets().add("/styles/styles.css");

		stage.setTitle("Pathogen Detection - https://www.ladetec.iq.ufrj.br/nal/");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}

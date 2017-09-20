package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {

	/**
	 * This pane is shown on the left side of the scene, which can have different
	 * scenes switching on it
	 */
	@FXML
	private AnchorPane _mainPane;

	/**
	 * This pane is shown on the right side of the scene, which shows the statistics
	 */
	@FXML
	private AnchorPane _statisticsPane;

	/**
	 * The only statistics controller in the main scene
	 */
	private static StatisticsSceneController _statistics;

	public final static int NUMBER_OF_QUESTIONS = 10;
	public final static int NUMBER_LOWER_BOUND = 1;
	public final static int NUMBER_UPPER_BOUND_BEGINNER = 9;
	public final static int NUMBER_UPPER_BOUND_ADVANCED = 99;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		// create a database instance
		// TODO

		// load statistics scene at the statistics pane
		_statistics = (StatisticsSceneController) replacePaneContent(_statisticsPane, "StatisticsScene.fxml");
		_statistics.setMainController(this);

		goToHome();
	}

	/**
	 * Clear the data base and reload the scene
	 */
	public void goToHome() {
		// TODO
		_statistics.reset();
		showStartScene();
	}

	/**
	 * Show the start scene on the main pane
	 */
	public void showStartScene() {
		StartSceneController startSceneController = (StartSceneController) replacePaneContent(_mainPane,
				"StartScene.fxml");
		startSceneController.setMainController(this);
	}

	/**
	 * Show the choose level scene on the main pane
	 */
	public void showChooseLevelScene() {
		ChooseLevelSceneController chooseLevelSceneController = (ChooseLevelSceneController) replacePaneContent(
				_mainPane, "ChooseLevelScene.fxml");
		chooseLevelSceneController.setMainController(this);
	}

	/**
	 * Show the record scene on the main pane
	 */
	public void showRecordScene() {
		// TODO
	}

	/**
	 * Show the result scene on the main pane
	 */
	public void showResultScene() {
		// TODO
		ResultSceneController recordController;
		recordController = (ResultSceneController) replacePaneContent(_mainPane, "ResultScene.fxml");
		recordController.setMainController(this);
	}

	/**
	 * Show the summary scene on the main pane
	 */
	public void showSummaryScene() {
		// TODO
		SummarySceneController summaryController;
		summaryController = (SummarySceneController) replacePaneContent(_mainPane, "SummaryScene.fxml");
		summaryController.setMainController(this);
		_statistics.update();
	}

	/**
	 * Replaces the content in the pane with the pane defined by the FXML file, and
	 * return the controller for the FXML
	 * 
	 * @param pane
	 * @param fxml
	 * @return Controller which implements Initializable
	 */
	private Initializable replacePaneContent(Pane pane, String fxml) {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		InputStream in = Main.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(Main.class.getResource(fxml));
		try {
			Pane content = (Pane) loader.load(in);
			pane.getChildren().setAll(content);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return (Initializable) loader.getController();
	}

	/**
	 * Returns the statisticsSceneController which takes control of the statistics
	 * of the questions and score
	 * 
	 * @return StatisticsSceneController
	 */
	public StatisticsSceneController getStatistics() {
		return _statistics;
	}

	// /**
	// * Get the data base.
	// *
	// * @return database
	// */
	// public static Database getDatabase() {
	//
	// }

	/**
	 * Asks the statistics controller to set the level (Beginner/Advanced)
	 * 
	 * @param level
	 */
	public void setLevel(String level) {
		// TODO Auto-generated method stub
		_statistics.setLevel(level);
	}

	public static String getLevel() {
		return _statistics.getLevel();
	}

	public void deleteWav() {
		// TODO Auto-generated method stub
	}

	public static int getScore() {
		int score = 0;
		if (_statistics != null) {
			score = _statistics.getScore();
		}
		return score;

	}

}

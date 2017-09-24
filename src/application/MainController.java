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
	 * The only database for the whole program
	 */
	private static Database _database;

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

		// create a database instance
		_database = new Database();

		// load statistics scene at the statistics pane
		_statistics = (StatisticsSceneController) replacePaneContent(_statisticsPane, "StatisticsScene.fxml");

		goToHome();
	}

	/**
	 * Clear the data base and reload the scene
	 */
	public void goToHome() {
		_database.clearData();
		resetStatistics();
		showStartScene();
	}

	/**
	 * Reset the StatisticsSceneController
	 */
	public void resetStatistics() {
		_statistics.reset();
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
		if (!(_statistics.getNumOfResults() < NUMBER_OF_QUESTIONS)) {
			showSummaryScene();
			return;
		}
		RecordSceneController recordController = (RecordSceneController) replacePaneContent(_mainPane,
				"RecordScene.fxml");
		recordController.setMainController(this);
	}

	/**
	 * Show the result scene on the main pane
	 */
	public void showResultScene() {
		ResultSceneController recordController;
		recordController = (ResultSceneController) replacePaneContent(_mainPane, "ResultScene.fxml");
		recordController.setMainController(this);
	}

	/**
	 * Show the summary scene on the main pane
	 */
	public void showSummaryScene() {
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

	/**
	 * Get the data base.
	 *
	 * @return database
	 */
	public static Database getDatabase() {
		if (_database == null) {
			_database = new Database();
		}
		return _database;
	}

	/**
	 * Asks the statistics controller to set the level (Beginner/Advanced)
	 * 
	 * @param level
	 */
	public void setLevel(String level) {
		_statistics.setLevel(level);
	}

	/**
	 * Get the current level of the game
	 * 
	 * @return Beginner/Advanced, if not defined yet, null will be returned
	 */
	public static String getLevel() {
		return _statistics.getLevel();
	}

	/**
	 * Delete the temperate recording file
	 */
	public void deleteWav() {
		new BashProcess("./MagicStaff.sh", "deleteWav", _database.currentNumber());
	}

	/**
	 * Get the current score of the game
	 * 
	 * @return Score in an int value
	 */
	public static int getScore() {
		int score = 0;
		if (_statistics != null) {
			score = _statistics.getScore();
		}
		return score;
	}

}

package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class StatisticsSceneController implements Initializable {
	@FXML
	private Label _scoreLabel;
	@FXML
	private ListView<Label> _resultListView;
	private String _level;
	private int _score;
	private MainController _mainController;
	private Database _database;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_database = MainController.getDatabase();
		reset();
	}

	public void setMainController(MainController mainController) {
		_mainController = mainController;
	}

	/**
	 * Reset the statistics data and GUI to initial state
	 */
	public void reset() {
		_resultListView.setItems(FXCollections.observableArrayList(new Label("Practice your Maori!")));
		_scoreLabel.setText("ScorePanel");
		_level = null;
		_score = 0;
	}

	/**
	 * Update the statistics
	 */
	public void update() {
		if (_database.currentNumber() == null) {
			return;
		}

		// get test results from the database
		List<String> resultList = _database.getTestResults();
		ObservableList<Label> data = FXCollections.observableArrayList();
		_score = 0;

		if (resultList.isEmpty()) {
			data.add(new Label("There is no result to display."));
		} else {
			for (String s : resultList) {
				// every entry is a string which contains the number value and a true/false,
				// separated by a space
				String[] entry = s.split(" ");
				// if the entry's second word is true, which means the result is true, add a
				// label of the numValue and correct into the data list
				if (entry[1].equalsIgnoreCase("true") || entry[1].toLowerCase().startsWith("t")) {
					Label line = new Label(entry[0] + ": Correct");
					line.setTextFill(Color.LIGHTGREEN);
					data.add(line);
					_score++;
				} else if (entry[1].equalsIgnoreCase("false") || entry[1].toLowerCase().startsWith("f")) {
					// if it is false, display numValue and wrong in red
					Label line = new Label(entry[0] + ": Wrong");
					line.setTextFill(Color.RED);
					data.add(line);
				}
			}
		}

		// display the score
		_scoreLabel.setText("Score: " + _score);
		// load data into listView
		_resultListView.setItems(data);
	}

	/**
	 * Set the level of the practice: Beginner/Advanced
	 * 
	 * @param level
	 */
	public void setLevel(String level) {
		_level = level;
	}

	/**
	 * 
	 * @return The level of the practice, if not defined yet, null will be returned.
	 */
	public String getLevel() {
		return _level;
	}

	/**
	 * 
	 * @return The current score. The default score is 0.
	 */
	public int getScore() {
		return _score;
	}

}

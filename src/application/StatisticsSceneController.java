package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;

public class StatisticsSceneController implements Initializable {
	@FXML
	private Label _scoreLabel;
	@FXML
	private ListView<Label> _resultListView;
	private String _level;
	private int _score;
	private MainController _mainController;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
		// TODO
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
